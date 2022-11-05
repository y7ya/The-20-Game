package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;

import com.mysql.cj.protocol.Message;

public class App {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        Socket s = new Socket("localhost", 8585);
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        JSONObject login1 = new JSONObject();
        login1.put("request", "login");
        login1.put("username", "testAcc");
        login1.put("password", "555");

        System.out.println(login1.toString());
        writer.write(login1.toString());
        writer.newLine();
        writer.flush();

        String line;
        while (true) {
            line = reader.readLine();
            System.out.println(line);
            break;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        JSONObject play = new JSONObject();
        play.put("request", "game_mood");
        play.put("value", "with_computer");

        System.out.println(play.toString());
        writer.write(play.toString());
        writer.newLine();
        writer.flush();

        int game_id;
        while (true) {
            line = reader.readLine();
            JSONObject data = new JSONObject(line);
            game_id = data.getInt("game_id");
            break;
        }

        System.out.println(game_id);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        while (true) {

            

            JSONObject move = new JSONObject();
            move.put("request", "move");
            move.put("game_id", game_id);
            move.put("move", Integer.parseInt(input.nextLine()));

            System.out.println(move.toString());
            writer.write(move.toString());
            writer.newLine();
            writer.flush();

            while (true) {
                line = reader.readLine();
                System.out.println(line);

                JSONObject data = new JSONObject(line);
                if(data.getString("request").equalsIgnoreCase("error")){
                    System.out.println(data.getString("message"));
                    break;
                }
                if(data.getBoolean("game_isEnd") == true){
                    System.out.println("game end");
                    break;
                }
                break;
            }
            System.out.println("data sent");


            JSONObject cmove = new JSONObject();
            cmove.put("request", "computer_move");
            cmove.put("game_id", game_id);

            System.out.println(cmove.toString());
            writer.write(cmove.toString());
            writer.newLine();
            writer.flush();


            while (true) {
                line = reader.readLine();
                System.out.println(line);
                JSONObject data = new JSONObject(line);
                if(data.getString("request").equalsIgnoreCase("error")){
                    System.out.println(data.getString("message"));
                    break;
                }
                if(data.getBoolean("game_isEnd") == true){
                    System.out.println("game end");
                    break;
                }
                break;
            }

        }

        // Interface.welcome();
        // while (true) {
        // String line = reader.readLine();
        // System.out.println(line);
        // }
        // Player me = new Player(1,"yahya");
        // PlayWithComputer game = new PlayWithComputer(me);

        // String userInput;
        // int computerInput;
        // while (!game.game_end()) {
        // if(game.getTurn() == 1){
        // Interface.game(game);
        // }else{
        // game.computerMove();
        // Interface.game(game);
        // }

        // }
        // System.out.println("The Winner is " + game.get_winner().getUsername());

    }

}