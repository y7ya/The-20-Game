package server.Handler;

import java.net.Socket;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.util.StringTokenizer;

import org.json.JSONObject;

import server.App;
import server.game.PlayWithComputer;
import server.player.Player;

public class RequestHandler {

    public static String handleRequest(String line, Socket socket) {
        JSONObject data = new JSONObject(line);

        switch (data.getString("request").toLowerCase()) {
            case "login":
                return loginHandler(data.getString("username"), data.getString("password"), socket);
            case "register":
                return registerHandler(data.getString("username"), data.getString("password"), socket);
            case "game_mood":
                return game_mood(data.getString("value"), socket);
            case "move":
                return "";
            // return move();
            case "score_table":
                return score_table();

            default:
                System.out.println("default");
                return "Command not found";
        }
    }

    private static String registerHandler(String username, String password, Socket socket) {
        try {
            String[] data = App.DB.register(username, password);
            if (data != null) {

                for (Client client : App.clients) {
                    if (client.get_socket() == socket) {
                        Player player = new Player(Integer.parseInt(data[0]), data[1]);
                        client.set_player(player);
                        client.print_data();
                        break;
                    }
                }

                // return registerd successfully using responseHandler class
                System.out.println("registerd");
            } else {
                // return not registerd using responseHandler class
                System.out.println("not registerd");
            }
            // here: add player to socket class
        } catch (SQLException e) {
        }
        return "Register";
    }

    private static String loginHandler(String username, String password, Socket socket) {
        try {
            String[] data = App.DB.login(username, password);
            if (data != null) {
                Client client = App.client_by_socket(socket);
                if(client != null){
                    Player player = new Player(Integer.parseInt(data[0]), data[1]);
                    client.set_player(player);
                    client.print_data();
                    // return logged in successfully using responseHandler class
                    System.out.println("logged in");
                }
            } else {
                // return wrong creds using responseHandler class
                System.out.println("wrong creds");
            }
        } catch (SQLException e) {
        }
        return "LogIn";
    }

    private static String game_mood(String mood, Socket socket) {
        switch (mood.toLowerCase().trim()) {
            case "online":
                System.out.println("online");
                return "";
            case "with_computer":
                App.newGame("with_computer", socket);
                return "";
            case "with_friend":
                return "";
            default:
                return "Command not found";
        }
    }

    private static String score_table() {

        return "";
    }
}
