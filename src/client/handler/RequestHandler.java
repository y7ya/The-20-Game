package client.handler;

import org.json.JSONObject;

import client.App;

public class RequestHandler {


    public static String auth(String method,String username,String password){
        JSONObject data = new JSONObject();
        data.put("request", method);
        data.put("username", username);
        data.put("password", password);
        return data.toString();
    }

    public static String playWithComputer() {
        JSONObject data = new JSONObject();
        data.put("request", "game_mood");
        data.put("value", "with_computer");
        return data.toString();
    }

    public static String joinRandomGame() {
        JSONObject data = new JSONObject();
        data.put("request", "game_mood");
        data.put("value", "randomly");
        return data.toString();
    }

    public static String move(int steps) {
        JSONObject data = new JSONObject();
        data.put("request", "move");
        data.put("game_id", App.game.getId());
        data.put("move", steps);
        return data.toString();
    }

    public static String computer_move() {
        JSONObject data = new JSONObject();
        data.put("request", "computer_move");
        data.put("game_id", App.game.getId());
        return data.toString();
    }

    
}
