package client.game;

import client.*;
import server.player.Computer;
import server.player.Player;

public class PlayWithComputer extends Game {
    private Computer computer = new Computer();

    public PlayWithComputer(Player player) {
        super(player);
        super.setPlayer2(computer);
    }

}
