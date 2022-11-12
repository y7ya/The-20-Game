package client.game;


import client.player.Player;

public class Game {

    private int id;
    private Player opponent;
    private int turn = 1;
    private int winning_score = 20;
    private int max_steps = 2;
    private int pointer = 0;
    private boolean isWithComputer = false;

    public Game(int id){
        this.id = id;
    };

    public void setOpponent(Player player){
        this.opponent = player;
    }
    
    public Player getOpponent(){
        return this.opponent;
    }

    public void move(int steps){
        this.pointer += steps;
        nextTurn();
    }

    public void nextTurn(){
        if(this.turn == 1){
            this.turn = 2;
        }else{
            this.turn = 1;
        }
    }

    public int getTurn(){
        return this.turn;
    }
    
    public int get_max_steps(){
        return this.max_steps;
    }

    public int get_pointer(){
        return this.pointer;
    }

    public int get_winning_score(){
        return this.winning_score;
    }

    public boolean game_end(){
        return (this.pointer >= winning_score);
    }

    public int getId() {
        return this.id;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public void setWithComputer(){
        this.isWithComputer = true;
    }

    public boolean isWithComputer(){
        return this.isWithComputer;
    }
}
