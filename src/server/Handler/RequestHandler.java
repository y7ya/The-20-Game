package server.Handler;

import java.util.StringTokenizer;

import org.json.JSONObject;

import server.App;
import server.game.PlayWithComputer;
import server.player.Player;

public class RequestHandler {

    public static String handleRequest(String line) {
        JSONObject data = new JSONObject(line);
        switch (data.getString("request").toLowerCase()) {
            case "login":
                return login(data.getString("username"), data.getString("password"));
            case "register":
                return register(data.getString("username"), data.getString("password"));
            case "game_mood":
                return game_mood(data.getString("game_mood"));
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

    private static String login(String username, String password) {
        System.out.println("hello from login");
        return "LogIn";
    }

    private static String register(String username, String password) {
        System.out.println("hello from register");
        return "Register";
    }

    private static String game_mood(String mood) {
        switch (mood) {
            case "online":
                System.out.println("online");
                return "";
            case "with_computer":
            App.newGame();
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
