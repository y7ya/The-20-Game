package server.Handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.Socket;

import server.Player;

public class Client {
    private Socket socket;
    private Player player;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Sender sender;


    public Client(Socket socket) {
        try {
            this.socket = socket;
            // Auto flush
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.sender = new Sender(socket, this.writer);
            new Receiver(this.socket, this.reader,this.sender).start();
        } catch (IOException e) {
        }
    }

}
