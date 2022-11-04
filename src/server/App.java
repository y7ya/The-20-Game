package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import server.Handler.Client;
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
    
    public static Client client_by_socket(Socket socket){

        for(Client client: clients){
            if(client.get_socket() == socket) return client;
        }
        return null;
    }
    
    public static int newGame(String game_mood,Socket socket){
        switch (game_mood) {
            case "with_computer":
                PlayWithComputer game =  new PlayWithComputer(client_by_socket(socket).get_player());
                App.games.add(game);
                break;
        
            default:
                break;
        }
        // return game.getID();
        return 0;
    }
    
    public static void main(String[] args) {
        DB = new Database();

        try {
            serverSocket = new ServerSocket(PORT);    
        } catch (IOException e) {}

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                clients.add(new Client(socket));
            } catch (IOException e) {}

            
        }
    }
}
