package client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import client.game.Game;
import client.game.PlayWithComputer;
import client.player.Computer;
import client.player.Player;

public class App {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
     
        Scanner input = new Scanner(System.in);
        Socket s = new Socket("localhost",8585);
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        String user_choice = "";
        String user_name= "";
        String user_password= "";
     //   writer.write("login, Abdullah, 123");
       // writer.newLine();
        // writer.flush();
      
       
       
        while (true){

            System.out.println("choose a number of the following: ");

            System.out.println("1. Login ");
            System.out.println("2. Register");
           
            user_choice = input.nextLine() ; 

            if (user_choice.equalsIgnoreCase("1")){
                System.out.print("please enter username:");
                user_name= input.nextLine();
                System.out.print("please enter password:");
                user_password= input.nextLine();
                writer.write("login," + user_name +","+ user_password);
                writer.newLine();
                writer.flush();
        
           break;
         }

            else if (user_choice.equalsIgnoreCase("2")){
                System.out.print("please enter username:");
                user_name= input.nextLine();
                System.out.print("please enter password:");
                user_password= input.nextLine();
                writer.write("Register," + user_name +","+ user_password);
                writer.newLine();
                writer.flush();

            } 
          
        }

        while (true) {

            System.out.println("choose of the following: ");

            System.out.println("1. Play Online ");
            System.out.println("2. Play with Computer");
            System.out.println("3. Score Table");
            user_choice = input.nextLine(); 

            if (user_choice.equalsIgnoreCase("1")){
                writer.write("game_mood," + "online");
                writer.newLine();
                writer.flush();
           break;
         }

            else if (user_choice.equalsIgnoreCase("2")){
                writer.write("game_mood," + "offline");
                writer.newLine();
                writer.flush();
           break;

            } 
            


            
        }

      //  writer.write("login");
        //writer.newLine();
       // writer.flush();
    //Interface.welcome();
      while (true) {
            String line = reader.readLine();
            System.out.println(line);
        }
        // Player me = new Player(1,"yahya");
        // PlayWithComputer game = new PlayWithComputer(me);

        // String userInput;
        // int computerInput;
        // while (!game.game_end()) {
        //     if(game.getTurn() == 1){
        //         Interface.game(game);    
        //     }else{
        //         game.computerMove();
        //         Interface.game(game);
        //     }

        // }
        // System.out.println("The Winner is " + game.get_winner().getUsername());

    }

}