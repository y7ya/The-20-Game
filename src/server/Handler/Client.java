package server.Handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import server.player.Player;

public class Client {
    private Socket socket;
    private Player player;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Sender sender;


    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.sender = new Sender(socket, this.writer);
            new Receiver(this.socket, this.reader,this.sender).start();
        } catch (IOException e) {
        }
    }

    public Socket get_socket(){
        return this.socket;
    }
    
    public Sender get_sender(){
        return this.sender;
    }
    
    public Player get_player(){
        return this.player;
    }

    public void set_player(Player player){
        this.player = player;
    }

    public BufferedWriter get_writer() {
        return this.writer;
    }

    public boolean isLoggedIn(){
        return (this.player != null);
    }

    public void closeStreams(){
        try {
            socket.close();
            reader.close();
            writer.close();    
        } catch (IOException e) {}
        }
}
