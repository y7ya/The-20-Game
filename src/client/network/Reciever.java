package client.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.nio.Buffer;

import org.json.JSONObject;

import client.App;

public class Reciever extends Thread {
    
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    
    public Reciever(Socket s, BufferedReader reader, BufferedWriter writer) {
        this.socket = socket;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run() {
        String line;
        JSONObject data;
        try {
            while (true) {
                line = reader.readLine();
                System.out.println(line);
                data = new JSONObject(line);
                if(data.getString("request").equals("join") && data.has("game_id")){
                    App.game_id = data.getInt("game_id");
                }
            }
        } catch (IOException e) {}
    }
}
