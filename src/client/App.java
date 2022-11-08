package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.sound.midi.Receiver;

import org.json.JSONObject;

import com.mysql.cj.protocol.Message;

import client.network.Reciever;

public class App {
    public static Scanner input = new Scanner(System.in);
    public static int game_id = -1;

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Socket s = new Socket("localhost", 8585);
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        new Reciever(s, reader, writer).start();

        JSONObject login1 = new JSONObject();
        login1.put("request", "login");
        login1.put("username", "testAcc");
        login1.put("password", "555");

        writer.write(login1.toString());
        writer.newLine();
        writer.flush();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }


        JSONObject play = new JSONObject();
        play.put("request", "join");
        play.put("type", "random");

        writer.write(play.toString());
        writer.newLine();
        writer.flush();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        String line;
        JSONObject move;
        while (true) {
            line = input.nextLine();
            move = new JSONObject();
            play.put("request", "move");
            play.put("game_id", game_id);
            play.put("move", Integer.parseInt(input.nextLine()));
            writer.write(play.toString());
            writer.newLine();
            writer.flush();
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