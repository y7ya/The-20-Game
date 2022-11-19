package client.handler;

import org.json.JSONObject;

import client.App;
import client.Interface;
import client.game.Game;
import client.player.Player;;

public class ResponseHandler {

    public static void ResponseHandler(String line) {
        JSONObject data = new JSONObject(line);
        switch (data.getString("request").toLowerCase()) {
            case "login":
                loginHandler(data);
                break;
            case "register":
                registerHandler(data);
                break;
            case "join":
                joinHandler(data);
                break;
            case "move":
                moveHandler(data);
                break;
            case "opponent_moved":
                opponent_movedHandler(data);
                break;
            case "computer_move":
                opponent_movedHandler(data);
                break;
            default:
                break;
        }
    }

    private static void moveHandler(JSONObject data) {
        if (data.getString("message").equals("moved")) {
            if (!data.getBoolean("game_isEnd")) {
                App.game.move(data.getInt("steps"));
                Interface.game(App.game);
                if (App.game.isWithComputer()) {
                    System.out.println("It is computer turn");
                    Sender.send(App.writer, RequestHandler.computer_move());
                }
            } else {
                Interface.losing();
                Interface.home();
            }
        }
    }

    private static void opponent_movedHandler(JSONObject data) {
        if (data.getString("message").equals("moved")) {
            if (!data.getBoolean("game_isEnd")) {
                App.game.move(data.getInt("opponent_steps"));
                Interface.game(App.game);
            } else {
                Interface.winning();
                Interface.home();

            }
        }
    }

    private static void joinHandler(JSONObject data) {
        if (data.getString("message").equals("joined")) {
            App.game = new Game(data.getInt("game_id"));
            if (data.getString("status").equals("with_computer")) {
                App.game.setWithComputer();
                App.game.setOpponent(new Player(data.getInt("opponent_id"), data.getString("opponent_username")));
                Interface.game(App.game);
            } else if (data.getString("status").equals("start")) {
                App.game.setOpponent(new Player(data.getInt("opponent_id"), data.getString("opponent_username")));
                App.game.nextTurn();
                Interface.game(App.game);
                return;
            } else {
                Interface.wait_player();
                return;
            }
        } else if (data.getString("message").equals("player_joined")) {
            App.game.setOpponent(new Player(data.getInt("opponent_id"), data.getString("opponent_username")));
            Interface.game(App.game);
            return;
        } else {
            Interface.message("Try again");
            Interface.home();
            return;
        }
    }

    private static void loginHandler(JSONObject data) {
        if (data.getString("message").equals("loggedin")) {
            App.player = new Player(data.getInt("id"), data.getString("username"));
            Interface.message("LoggedIn Successfully");
            Interface.home();
            return;
        } else if (data.getString("message").equals("wrong_creds")) {
            Interface.message("Wrong username or password");
        } else if (data.getString("message").equals("already_loggedin")) {
            Interface.message("You Already logged in");
        }
        Interface.login();
    }

    private static void registerHandler(JSONObject data) {
        if (data.getString("message").equals("registered")) {
            App.player = new Player(data.getInt("id"), data.getString("username"));
            Interface.message("Registered Successfully");
            Interface.home();
            return;
        } else if (data.getString("message").equals("username_exist")) {
            Interface.message("Username is already exist");
        } else if (data.getString("message").equals("length_more_than1_less_than50")) {
            Interface.message("Username length should be more than 1 and less than 50");
        } else if (data.getString("message").equals("english_numbers_only")) {
            Interface.message("Username should be english letters and numbers only");
        } else if (data.getString("message").equals("pass_too_short")) {
            Interface.message("password should be more than 6 char");
        } else if (data.getString("message").equals("pass_too_long")) {
            Interface.message("password should be less than 6 char");
        }
        Interface.register();
    }

}
