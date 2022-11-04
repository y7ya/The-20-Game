package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;

import client.game.Game;
import client.game.PlayWithComputer;
import server.player.Computer;
import server.player.Player;

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
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }


        JSONObject newGame = new JSONObject();
        newGame.put("request", "game_mood");
        newGame.put("value", "with_computer");

        System.out.println(newGame.toString());
        writer.write(newGame.toString());
        writer.newLine();
        writer.flush();
        System.out.println("data sent 0");


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