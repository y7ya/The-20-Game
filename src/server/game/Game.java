package server.game;

import server.App;
import server.Handler.ResponseHandler;
import server.player.Player;

public class Game {
    private int id;
    private Player player1;
    private Player player2 = null;
    private int turn = 1;
    private int winning_score = 20;
    private int max_steps = 2;
    private int pointer = 0;
    private boolean isWithComputer = false;

    public Game(Player player1) {
        try {
            this.id = App.DB.newGame(player1);
            this.player1 = player1;
            this.player2 = null;
        } catch (Exception e) {}
    }

    public Player[] getPlayers() {
        Player[] players = { player1, player1 };
        return players;
    }

    public boolean inGame(Player player) {
        return ((player1 != null && player1.getId() == player.getId())
                || (player2 != null && player2.getId() == player.getId()));
    }

    public int getID() {
        return this.id;
    }

    public void setWithComputer() {
        this.isWithComputer = true;
    }

    public boolean isWithComputer() {
        return this.isWithComputer;
    }

    public void move(int steps) {
        this.pointer += steps;
        nextTurn();
    }

    public int computer_move() {
        int steps = (int) ((Math.random() * (get_max_steps())) + 1);
        move(steps);
        return steps;
    }

    public boolean hasPlayer2() {
        return player2 != null;
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

    public void setPlayer2(Player player2) {
        App.DB.addPlayer2(this, player2);
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

    public boolean isEnd() {
        return (this.pointer >= winning_score);
    }

    public Player get_winner() {
        if (turn == 1) {
            return this.player1;
        } else {
            return this.player2;
        }

    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void end(Player looser) {
        if (this.player2 == null)
            return;
            this.pointer = winning_score;
        if (player1 == looser) {
            App.DB.setWinner(this.id, player2.getId());
            App.client_by_player(player2).get_sender().send(ResponseHandler.moved(this, 0));
        } else {
            App.DB.setWinner(this.id, player1.getId());
            App.client_by_player(player2).get_sender().send(ResponseHandler.moved(this, 0));
        }
    }

}
