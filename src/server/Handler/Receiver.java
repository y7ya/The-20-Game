package server.Handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.nio.Buffer;

public class Receiver extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private Sender sender;

    Receiver(Socket socket,BufferedReader reader, Sender sender){
        this.socket = socket;
        this.reader = reader;
        this.sender = sender;
    }

    @Override
    public void run() {
        String line;
        try{
            while ((line = reader.readLine()) != null) {
                sender.send(RequestHandler.handleRequest(line));
            }
        }catch(IOException e){}catch(NullPointerException e){}
    }
}
