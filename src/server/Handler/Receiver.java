package server.Handler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.net.Socket;

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
            App.client_by_socket(socket).closeStreams();
            App.handleExit(socket);
        }

    }
}
