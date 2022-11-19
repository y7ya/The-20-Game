package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import org.json.JSONObject;
import client.player.Player;
import client.game.Game;
import client.handler.Reciever;

public class App {
    public static Scanner input = new Scanner(System.in);
    public static Player player;
    public static Game game;
    private final static int PORT = 8585;
    private final static String HOST = "localhost";
    public static BufferedWriter writer = null;

    public static void main(String[] args) {
        
        Socket s = null;
        try {
            s = new Socket(HOST, PORT);
        } catch (IOException e) {
            System.out.println("Can't Connect To Server");
            System.exit(1);
        }
        
        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Something went wrong with socket connection");
            System.exit(1);
        }

        new Reciever(s, reader, writer).start();
 
        Interface.welcome();
        
        int auth = Interface.auth();
        if(auth == 1){
            Interface.register();
        }else if(auth == 2){
            Interface.login();
        }
    }

}