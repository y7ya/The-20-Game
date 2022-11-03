package server.game;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import server.player.Player;

public class Game {
   // critical section  
   private static int game_counter = 0;
    private int id;
    private Player player1;
    private Player player2;
    private int turn = 1;
    private int winning_score = 20;
    private int max_steps = 2;
    private int pointer = 0;
    protected int last_player_steps = 0;

    Game(Player player1, Player Player2) {
        Game.game_counter++;
        this.id = Game.game_counter;
        this.player1 = player1;
        this.player2 = player2;
    }

    Game(Player player1) {
        Game.game_counter++;
        this.id = Game.game_counter;
        this.player1 = player1;
        this.player2 = null;
    }

    public int getID(){
        return this.id;
    }

    public void move(int steps) {
        this.pointer += steps;
    }

    public void nextTurn() {
        if (this.turn == 1) {
            this.turn = 2;
        } else {
            this.turn = 1;
        }
    }

    public int getTurn() {
        return this.turn;
    }

    public Player getPlayerTurn() {
        if (turn == 1) {
            return player1;
        } else {
            return player2;
        }

    }

    protected void update_last_player_steps(int last_player_steps) {
        this.last_player_steps = last_player_steps;
    }

    public int get_last_player_steps() {
        return this.last_player_steps;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int get_max_steps() {
        return this.max_steps;
    }

    public int get_pointer() {
        return this.pointer;
    }

    public int get_winning_score() {
        return this.winning_score;
    }

    public boolean game_end() {
        return (this.pointer >= winning_score);
    }

    public Player get_winner() {
        if (turn == 1) {
            return this.player1;
        } else {
            return this.player2;
        }

    }

}
