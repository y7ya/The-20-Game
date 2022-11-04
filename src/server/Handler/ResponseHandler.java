package server.Handler;

import org.json.JSONObject;

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

    
}
