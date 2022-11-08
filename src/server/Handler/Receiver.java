package server.Handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.nio.Buffer;

import server.App;

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
                sender.send(RequestHandler.handleRequest(line,socket));
            }
        }catch(IOException | NullPointerException e){}
        finally{
            App.handleExit(socket);
            try {
                socket.close();
            } catch (IOException e) {}
        }

    }
}
