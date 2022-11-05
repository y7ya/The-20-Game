package server.game;

import server.*;
import server.player.Computer;
import server.player.Player;

public class PlayWithComputer extends Game {
    private Computer computer = new Computer();

    public PlayWithComputer(Player player) {
        super(player);
        super.setPlayer2(computer);
        super.setWithComputer();
    }


    public void computerMove() {
        int steps = computer.play(get_max_steps());
        update_last_player_steps(steps);
        move(steps);
    }

}
