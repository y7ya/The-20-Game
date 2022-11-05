package server.Handler;

import java.net.Socket;

import org.json.JSONObject;

import server.game.Game;

public class ResponseHandler {
    public static String loggedIn_successfully(int user_id){
        JSONObject data = new JSONObject();
        data.put("request", "login");
        data.put("message","loggedin");
        data.put("id", user_id);
        return data.toString();
    }
    
    public static String wrongCreds(){
        JSONObject data = new JSONObject();
        data.put("request", "login");
        data.put("message","wrong_creds");
        return data.toString();
    }
    
    public static String registered_successfully(int user_id){
        JSONObject data = new JSONObject();
        data.put("request", "register");
        data.put("message","registered");
        data.put("id", user_id);
        return data.toString();
    }

    public static String username_exist(){
        JSONObject data = new JSONObject();
        data.put("request", "register");
        data.put("message","username_exist");
        return data.toString();
    }
    
    public static String game_created(int game_id){
        JSONObject data = new JSONObject();
        data.put("request", "game_mood");
        data.put("message","game_created");
        data.put("game_id", game_id);
        return data.toString();
    }
    
    public static String error(String request,String msg){
        JSONObject data = new JSONObject();
        data.put("request", request);
        data.put("message",msg);
        return data.toString();
    }

    public static String moved(Game game, int move) {
        JSONObject data = new JSONObject();
        data.put("request", "move");
        data.put("game_id", game.getID());
        data.put("game_isEnd", game.game_end());
        data.put("game_pointer", game.get_pointer());
        data.put("game_winner_id", game.get_winner().getId());
        data.put("game_winner_username", game.get_winner().getUsername());
        data.put("game_turn", game.getPlayerTurn().getId());
        return data.toString();
    }
   
    public static String computer_moved(Game game, int move) {
        JSONObject data = new JSONObject();
        data.put("request", "computer_move");
        data.put("game_id", game.getID());
        data.put("game_isEnd", game.game_end());
        data.put("game_pointer", game.get_pointer());
        data.put("game_winner_id", game.get_winner().getId());
        data.put("game_winner_username", game.get_winner().getUsername());
        data.put("game_turn", game.getPlayerTurn().getId());
        data.put("computer_steps",move);
        return data.toString();
    }
   
    public static String joinGame(Game game, Socket socket) {
        JSONObject data = new JSONObject();
        data.put("request", "join");
        data.put("game_id", game.getID());
        if(game.hasPlayer2()){
            data.put("status", "wait_player2");
        }else{
            data.put("status", "start");
        }
        return data.toString();
    }

	public static String notYourTurn(int game_id) {
		return null;
	}

    
}
