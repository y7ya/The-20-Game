package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import server.Handler.Client;
import server.game.Game;

public class App {
    private final static int PORT = 8585;
    private static ServerSocket serverSocket;
    private static ArrayList<Client> clients = new ArrayList<>();
    public static ArrayList<Game> games = new ArrayList<>();

    
    public static void main(String[] args) {
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
