package server.db;

import java.net.ContentHandlerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

import server.App;
import server.game.Game;
import server.player.Player;

public class Database {
    private Connection conn;
    private final int PORT = 8111;
    private final String HOST = "localhost";
    private final String DB_NAME = "20game";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    public Database() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME, USERNAME,
                    PASSWORD);
        } catch (SQLException e) {
            System.out.println("can't connect to database");
            System.exit(0);
        }
    }

    public boolean username_exist(String username) {
        try {

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM users where username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;

    }

    public String[] register(String username, String password) {
        try {

            PreparedStatement ps = this.conn
                    .prepareStatement("INSERT INTO users (`username`, `password`) VALUES (?, ?);");
            ps.setString(1, username);
            ps.setString(2, password);
            int n = ps.executeUpdate();
            if (n > 0) {
                String id = get_id_by_username(username);
                String[] data = { id, username };
                return data;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public int newGame(Player player1) {
        try {

            String generatedColumns[] = { "id" };
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO games (`player1`) VALUES (?);",
                    generatedColumns);
            ps.setInt(1, player1.getId());
            int n = ps.executeUpdate();
            if (n > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    private String get_id_by_username(String username) {
        try {

            PreparedStatement ps = this.conn.prepareStatement("SELECT id FROM users where username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public String[] login(String username, String password) {
        try {

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM users where username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("username");
                String[] data = { id, name };
                return data;
            } else {
                return null;
            }
        } catch (SQLException e) {
        }
        return null;

    }

    public boolean addPlayer2(Game game, Player player2) {
        try {
            PreparedStatement ps = this.conn
                    .prepareStatement("Update games SET player2=? where id=? and player2 IS NULL");
            ps.setInt(1, player2.getId());
            ps.setInt(2, game.getID());
            int n = ps.executeUpdate();
            return n > 0;
        } catch (SQLException e) {
        }
        return false;

    }

    public void setWinner(int game_id, int Winner_id) {
        try {
            PreparedStatement ps = this.conn
                    .prepareStatement("Update games SET winner=? where id=? and winner IS NULL");
            ps.setInt(1, Winner_id);
            ps.setInt(2, game_id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

}