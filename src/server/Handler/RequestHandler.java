package server.Handler;

import java.net.Socket;
import java.sql.SQLException;

import org.json.JSONObject;

import server.App;
import server.game.Game;
import server.player.Player;

public class RequestHandler {

    public static String handleRequest(String line, Socket socket) {
        JSONObject data = new JSONObject(line);

        // make sure that player logged in
        if (!App.client_by_socket(socket).isLoggedIn() && !(data.getString("request").toLowerCase().equals("login")
                || data.getString("request").toLowerCase().equals("register")))
            return ResponseHandler.error("error", "login first");

        switch (data.getString("request").toLowerCase()) {
            case "login":
                return loginHandler(data.getString("username"), data.getString("password"), socket);
            case "register":
                return registerHandler(data.getString("username"), data.getString("password"), socket);
            case "game_mood":
                return game_moodHandler(data.getString("value"), socket);
            case "join":
                return joinHandler(data.getString("type"), socket);
            case "move":
                return moveHandler(data.getInt("game_id"), data.getInt("move"), socket);
            case "computer_move":
                return computer_moveHandler(data.getInt("game_id"), socket);
            case "score_table":
                return score_table();

            default:
                System.out.println("default");
                return "Command not found";
        }
    }

    private static String joinHandler(String type, Socket socket) {
        switch (type.toLowerCase().trim()) {
            case "random":
                Game game = App.addToRandomGame(socket);
                return ResponseHandler.joinGame(game, socket);
            default:
                return "";
        }
    }

    private static String registerHandler(String username, String password, Socket socket) {
        if (username == "computer")
            return ResponseHandler.wrongCreds();
        if (App.DB.username_exist(username))
            return ResponseHandler.username_exist();
        String[] data = App.DB.register(username, password);
        if (data != null) {
            for (Client client : App.clients) {
                if (client.get_socket() == socket) {
                    Player player = new Player(Integer.parseInt(data[0]), data[1]);
                    client.set_player(player);
                    client.print_data();
                    return ResponseHandler.registered_successfully(player.getId(),player.getUsername());
                }
            }
        }
        return ResponseHandler.error("register", "Something went wrong");
    }

    private static String loginHandler(String username, String password, Socket socket) {
        if (username == "computer")
            return ResponseHandler.wrongCreds();

        String[] data = App.DB.login(username, password);
        if (data != null) {
            if (App.clientLoggedIn(username)) {
                return ResponseHandler.alreadyLoggedIn();
            }
            Client client = App.client_by_socket(socket);
            if (client != null) {
                Player player = new Player(Integer.parseInt(data[0]), data[1]);
                client.set_player(player);
                client.print_data();
                return ResponseHandler.loggedIn_successfully(player.getId(),player.getUsername());
            }
        }
        return ResponseHandler.wrongCreds();
    }

    private static String game_moodHandler(String mood, Socket socket) {
        Game game = null;
        switch (mood.toLowerCase().trim()) {
            case "randomly":
                game = App.addToRandomGame(socket);
                return ResponseHandler.joinGame(game, socket);
            case "with_computer":
                game = App.newGame("with_computer", socket);
                return ResponseHandler.joinGame(game,socket);
            default:
                return ResponseHandler.error("game_mood", "invalid Game type");
        }
    }

    public static String moveHandler(int game_id, int move, Socket socket) {
        Game game = App.game_by_game_id(game_id);
        if (game == null)
            return ResponseHandler.error("move", "game not found");
        if (!game.hasPlayer2())
            return ResponseHandler.error("error", "Wait player 2");
        if (game.getPlayerTurn() != App.client_by_socket(socket).get_player())
            return ResponseHandler.error("error", "Not Your Turn");
        if (game.get_max_steps() < move || move <= 0)
            return ResponseHandler.error("error", "wrong input");
        game.move(move);
        if (game.isEnd())
            App.end_game(game);
        

        if(game.isWithComputer())return ResponseHandler.moved(game, move);
        Client sender = App.client_by_socket(socket);
        if (sender == App.client_by_player(game.getPlayer1())) {
            App.client_by_player(game.getPlayer2()).get_sender().send(ResponseHandler.opponent_moved(game, move));
        } else {
            App.client_by_player(game.getPlayer1()).get_sender().send(ResponseHandler.opponent_moved(game, move));
        }
        return ResponseHandler.moved(game, move);
    }

    public static String computer_moveHandler(int game_id, Socket socket) {
        Game game = App.game_by_game_id(game_id);
        if (game == null)
            ResponseHandler.error("move", "game not found");

        if (game.getPlayerTurn().getId() != 0)
            return ResponseHandler.error("error", "Not Computer Turn");
        int steps = game.computer_move();
        if (game.isEnd()) {
            App.end_game(game);
        }
        return ResponseHandler.computer_moved(game, steps);
    }

    private static String score_table() {

        return "";
    }
}
