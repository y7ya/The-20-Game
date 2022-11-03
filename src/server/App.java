package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import server.Handler.Client;
import server.game.Game;
import server.game.PlayWithComputer;
import server.player.Player;
import org.json.*;
import server.db.Database;

public class App {
    private final static int PORT = 8585;
    private static ServerSocket serverSocket;
    private static ArrayList<Client> clients = new ArrayList<>();
    public static ArrayList<Game> games = new ArrayList<>();
    public static Database DB;
    // public static int newGame(){
    //     Player player1 = new Player(5, "Test");
    //     PlayWithComputer game =  new PlayWithComputer(player1);
    //     App.games.add(game);
    //     return game.getID();
    // }
    
    public static void main(String[] args) {
        DB = new Database();

        try {
            serverSocket = new ServerSocket(PORT);    
        } catch (IOException e) {}

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                clients.add(new Client(socket));
            } catch (IOException e) {}

            
        }
    }
}
