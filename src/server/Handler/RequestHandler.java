package server.Handler;

import java.net.Socket;
import java.sql.SQLException;

import org.json.JSONObject;

import client.game.PlayWithComputer;
import server.App;
import server.game.Game;
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
            // case "join":
            //     return joinHandler(data.getString("type"),socket);
            case "move":
                return moveHandler(data.getInt("game_id"),data.getInt("move"),socket);
            case "computer_move":
                return computer_moveHandler(data.getInt("game_id"),socket);
            case "score_table":
                return score_table();

            default:
                System.out.println("default");
                return "Command not found";
        }
    }

    // private static int joinHandler(String type, Socket socket) {
    //     switch (type.toLowerCase().trim()) {
    //         case "random":
    //             Game game = App.addToRandomGame(socket);
    //             return ResponseHandler.joinGame(game,socket);
    //         case "wFriend":
    //             return -1;
    //         default:
    //             return -1;
    //     }
    // }

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
        Game game = null;
        switch (mood.toLowerCase().trim()) {
            case "randomly":
                game = App.newGame("randomly", socket);
                return ResponseHandler.game_created(game.getID());
            case "with_computer":
                game = App.newGame("with_computer", socket);
                return ResponseHandler.game_created(game.getID());
            case "with_friend":
                return "";
            default:
                return "Default";
        }
    }

    public static String moveHandler(int game_id,int move,Socket socket){
        Game game = App.game_by_game_id(game_id);
        if(game.getPlayerTurn() != App.client_by_socket(socket).get_player()) return ResponseHandler.error("error","Not Your Turn");
        if(game.get_max_steps() < move || move <= 0) return ResponseHandler.error("error","wrong input");
        game.move(move);
        return ResponseHandler.moved(game,move);
    }

    public static String computer_moveHandler(int game_id,Socket socket){
        Game game = App.game_by_game_id(game_id);
        if(game.getPlayerTurn().getId() != 0) return ResponseHandler.error("error","Not Computer Turn");
        int steps = game.computer_move();
        if(game.game_end()){
            App.end_game(game);
        }
        return ResponseHandler.computer_moved(game,steps);
    }

    private static String score_table() {

        return "";
    }
}
