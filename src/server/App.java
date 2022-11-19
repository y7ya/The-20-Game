package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import server.Handler.Client;
import server.Handler.ResponseHandler;
import server.game.Game;
import server.game.PlayWithComputer;
import server.player.Player;
import org.json.*;
import server.db.Database;

public class App {
    private final static int PORT = 8585;
    private static ServerSocket serverSocket;
    public static ArrayList<Client> clients = new ArrayList<Client>();
    public static ArrayList<Game> games = new ArrayList<>();
    public static Database DB;

    public static boolean clientLoggedIn(String usernmae) {
        for (Client client : clients) {
            if (client.get_player() == null)
                continue;
            if (client.get_player().getUsername().equals(usernmae))
                return true;
        }
        return false;
    }

    public static Client client_by_socket(Socket socket) {
        for (Client client : clients) {
            if (client.get_socket() == socket)
                return client;
        }
        return null;
    }

    public static Client client_by_player(Player player) {
        for (Client client : clients) {
            if (client.get_player() == player)
                return client;
        }
        return null;
    }

    public static Game addToRandomGame(Socket socket) {
        for (Game game : games) {
            if (!game.hasPlayer2()) {
                if (game.inGame(client_by_socket(socket).get_player()))
                    continue;
                game.setPlayer2(client_by_socket(socket).get_player());
                App.client_by_player(game.getPlayer1()).get_sender().send(ResponseHandler.player_joined(game));
                return game;
            }
        }
        return newGame("", socket);
    }

    public static Game game_by_game_id(int id) {
        for (Game game : games) {
            if (game.getID() == id) {
                return game;
            }
        }
        return null;
    }

    public static Game newGame(String game_mood, Socket socket) {
        Game game = null;
        switch (game_mood) {
            case "with_computer":
                game = new PlayWithComputer(client_by_socket(socket).get_player());
                App.games.add(game);
                return game;
            default:
                game = new Game(client_by_socket(socket).get_player());
                App.games.add(game);
                return game;
        }
    }

    public static void end_game(Game game) {
        games.remove(game);
        App.DB.setWinner(game.getID(), game.getPlayerTurn().getId());
    }

    public static void handleExit(Socket socket) {
        Client cl = client_by_socket(socket);
        if (cl == null)
            return;

        // exit game if exist and set the other player as winner
        Game g;
        Iterator<Game> itr_games = games.iterator();
        while (itr_games.hasNext()) {
            g = itr_games.next();
            Player[] players = g.getPlayers();
            if (players[0] == cl.get_player() || players[1] == cl.get_player()) {
                g.end(cl.get_player());
                itr_games.remove();
            }

        }
        // remove client from clients array
        Client c;
        Iterator<Client> itr_clients = clients.iterator();
        while (itr_clients.hasNext()) {
            c = itr_clients.next();
            if (c.get_socket() == socket)
                itr_clients.remove();
        }
    }

    public static void main(String[] args) {
        DB = new Database();

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
        }

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                clients.add(new Client(socket));
            } catch (IOException e) {
            }

        }
    }
}
