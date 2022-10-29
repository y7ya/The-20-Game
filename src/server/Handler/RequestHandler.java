package server.Handler;

import java.util.StringTokenizer;

public class RequestHandler {

    public static String handleRequest(String line) {
        StringTokenizer token = new StringTokenizer(line, ",");
        switch (token.nextToken().toLowerCase().trim()) {
            case "login":
                return login(token.nextToken().trim(), token.nextToken().trim());
            case "register":
                return register(token.nextToken().trim(), token.nextToken().trim());

            case "game_mood":
                return game_mood(token.nextToken().trim());
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
                System.out.println("with_computer");
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
