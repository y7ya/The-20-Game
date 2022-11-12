package server.Handler;

import java.net.Socket;

import org.json.JSONObject;

import server.game.Game;

public class ResponseHandler {
    public static String loggedIn_successfully(int user_id, String username) {
        JSONObject data = new JSONObject();
        data.put("request", "login");
        data.put("message", "loggedin");
        data.put("username", username);
        data.put("id", user_id);
        return data.toString();
    }

    public static String alreadyLoggedIn() {
        JSONObject data = new JSONObject();
        data.put("request", "login");
        data.put("message", "already_loggedin");
        return data.toString();
    }

    public static String wrongCreds() {
        JSONObject data = new JSONObject();
        data.put("request", "login");
        data.put("message", "wrong_creds");
        return data.toString();
    }

    public static String registered_successfully(int user_id, String username) {
        JSONObject data = new JSONObject();
        data.put("request", "register");
        data.put("message", "registered");
        data.put("username", username);
        data.put("id", user_id);
        return data.toString();
    }

    public static String username_exist() {
        JSONObject data = new JSONObject();
        data.put("request", "register");
        data.put("message", "username_exist");
        return data.toString();
    }

    public static String error(String request, String msg) {
        JSONObject data = new JSONObject();
        data.put("request", request);
        data.put("message", msg);
        return data.toString();
    }

    public static String moved(Game game, int move) {
        JSONObject data = new JSONObject();
        data.put("request", "move");
        data.put("message", "moved");
        data.put("game_id", game.getID());
        data.put("game_isEnd", game.isEnd());
        data.put("game_pointer", game.get_pointer());
        data.put("game_winner_id", game.get_winner().getId());
        data.put("game_winner_username", game.get_winner().getUsername());
        data.put("game_turn", game.getPlayerTurn().getId());
        data.put("steps", move);
        return data.toString();
    }

    public static String computer_moved(Game game, int move) {
        JSONObject data = new JSONObject();
        data.put("request", "computer_move");
        data.put("message", "moved");
        data.put("game_id", game.getID());
        data.put("game_isEnd", game.isEnd());
        data.put("game_pointer", game.get_pointer());
        data.put("game_winner_id", game.get_winner().getId());
        data.put("game_winner_username", game.get_winner().getUsername());
        data.put("game_turn", game.getPlayerTurn().getId());
        data.put("opponent_steps", move);
        return data.toString();
    }

    public static String player_joined(Game game) {
        JSONObject data = new JSONObject();
        data.put("request", "join");
        data.put("message", "player_joined");
        data.put("game_id", game.getID());
        data.put("status", "start");
        data.put("opponent_id", game.getPlayer2().getId());
        data.put("opponent_username", game.getPlayer2().getUsername());
        return data.toString();
    }

    public static String joinGame(Game game, Socket socket) {
        JSONObject data = new JSONObject();
        data.put("request", "join");
        data.put("message", "joined");
        data.put("game_id", game.getID());
        if (game.isWithComputer()) {
            data.put("status", "with_computer");
            data.put("opponent_id", 0);
            data.put("opponent_username", "Computer");

        } else if (game.hasPlayer2()) {
            data.put("status", "start");
            data.put("opponent_id", game.getPlayer1().getId());
            data.put("opponent_username", game.getPlayer1().getUsername());
        } else {
            data.put("status", "wait_player2");
        }
        return data.toString();
    }

    public static String opponent_moved(Game game, int move) {
        JSONObject data = new JSONObject();
        data.put("request", "opponent_moved");
        data.put("message", "moved");
        data.put("game_id", game.getID());
        data.put("game_isEnd", game.isEnd());
        data.put("game_pointer", game.get_pointer());
        data.put("game_winner_id", game.get_winner().getId());
        data.put("game_winner_username", game.get_winner().getUsername());
        data.put("game_turn", game.getPlayerTurn().getId());
        data.put("opponent_steps", move);
        return data.toString();
    }

}
