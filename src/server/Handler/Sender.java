package server.Handler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

public class Sender {
    private Socket socket;
    private BufferedWriter writer;

    Sender(Socket socket, BufferedWriter writer){
        this.socket = socket;
        this.writer = writer;
    }

    public void send(String line){
        try {
            writer.write(line); // String
            writer.newLine();
            writer.flush();
        } catch (IOException e) {}        
    }
}
