package client.handler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

public class Sender {
    public static void send(BufferedWriter writer,String line){
        try {
            writer.write(line);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
        }
    }
}
