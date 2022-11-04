package server.Handler;

import java.net.Socket;
import java.sql.SQLException;

import org.json.JSONObject;

import server.App;
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
                return game_moodHandler(data.getString("value"), socket);
            case "move":
                return moveHandler(data.getString("game_id"),data.getString("move"),socket);
            case "score_table":
                return score_table();

            default:
                System.out.println("default");
                return "Command not found";
        }
    }

    private static String registerHandler(String username, String password, Socket socket) {
        if(username == "computer") return ResponseHandler.wrongCreds(); 
        try {
            if(App.DB.username_exist(username)) return ResponseHandler.username_exist();
            String[] data = App.DB.register(username, password);
            if (data != null) {
                for (Client client : App.clients) {
                    if (client.get_socket() == socket) {
                        Player player = new Player(Integer.parseInt(data[0]), data[1]);
                        client.set_player(player);
                        client.print_data();
                        return ResponseHandler.registered_successfully(player.getId());
                    }
                }
            }
        } catch (SQLException e) {}
        return ResponseHandler.error("register", "Something went wrong");
    }

    private static String loginHandler(String username, String password, Socket socket) {
        if(username == "computer") return ResponseHandler.wrongCreds();
        try {
            String[] data = App.DB.login(username, password);
            if (data != null) {
                Client client = App.client_by_socket(socket);
                if(client != null){
                    Player player = new Player(Integer.parseInt(data[0]), data[1]);
                    client.set_player(player);
                    client.print_data();                    
                    return ResponseHandler.loggedIn_successfully(player.getId());
                }
            }
        } catch (SQLException e) {}
        return ResponseHandler.wrongCreds();
    }

    private static String game_moodHandler(String mood, Socket socket) {
        switch (mood.toLowerCase().trim()) {
            case "online":
                System.out.println("online");
                return "";
            case "with_computer":
                int game_id = App.newGame("with_computer", socket);
                return ResponseHandler.game_created(game_id);
            case "with_friend":
                return "";
            default:
                return "Default";
        }
    }

    public static String moveHandler(String game_id,String move,Socket socket){

        // return ResponseHandler.moved();
        return "";
    }

    private static String score_table() {

        return "";
    }
}
