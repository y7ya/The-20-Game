package client;
import java.util.Scanner;

import client.game.Game;
import client.game.PlayWithComputer;
import client.player.Computer;
import client.player.Player;

public class App {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        Player me = new Player(1,"yahya");
        PlayWithComputer game = new PlayWithComputer(me);

        String userInput;
        int computerInput;
        while (!game.game_end()) {
            if(game.getTurn() == 1){
                Interface.game(game);    
            }else{
                game.computerMove();
                Interface.game(game);
            }

        }
        System.out.println("The Winner is " + game.get_winner().getUsername());

    }

}