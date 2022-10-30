package server.player;

public class Computer extends Player{
    public Computer(){
        super(0, "Computer");
    }

    public int play(int max_steps){
        return  (int)((Math.random() * (max_steps))+1)  ;
    }
}
