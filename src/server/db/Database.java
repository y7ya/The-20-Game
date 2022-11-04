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

    public Database() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:8111/20game","root","");
        } catch (SQLException e) {
            System.out.println("can't connect to database");
            System.exit(0);
        }
    }

    public String[] register(String username, String password) throws SQLException{
        PreparedStatement ps = this.conn.prepareStatement("INSERT INTO users (`username`, `password`) VALUES (?, ?);");
        ps.setString(1, username);
        ps.setString(2, password);
        int n = ps.executeUpdate();
        if(n > 0){
            String id = get_id_by_username(username); 
            String [] data = {id,username}; 
            return data;
        };
        return null;
    }
    
    public int newGame(Player player1) throws SQLException{
        String generatedColumns[] = { "id" };
        PreparedStatement ps = this.conn.prepareStatement("INSERT INTO games (`player1`) VALUES (?);",generatedColumns);
        ps.setInt(1, player1.getId());
        int n = ps.executeUpdate();
        if(n > 0){
            // if(ps.getGeneratedKeys().next()){
            //     int id = ps.getGeneratedKeys().getInt("id");  
            //     return id;
            // }
            java.sql.ResultSet generatedKeys = ps.getGeneratedKeys();
            if ( generatedKeys.next() ) {
                return generatedKeys.getInt(1);
            }
        };
        return 0;
    }

    private String get_id_by_username(String username) throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("SELECT id FROM users where username=?");
        ps.setString(1, username);
        ResultSet rs =  ps.executeQuery();
        if(rs.next()){
            return rs.getString("id");
        }
        return null;
    }

    public String[] login(String username, String password) throws SQLException{
        PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM users where username=? AND password=?");
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs =  ps.executeQuery();

        if(rs.next()){
            String id = rs.getString("id");
            String name= rs.getString("username");
            String[] data = {id,name};
            return data;
        }else{
            return null;
        }

    }

    public boolean addPlayer2(Game game,Player player2) throws SQLException{
        PreparedStatement ps = this.conn.prepareStatement("Update games SET player2=? where id=? and player2 IS NULL");
        ps.setInt(1, player2.getId());
        ps.setInt(2, game.getID());
        int n = ps.executeUpdate();
        return n > 0;
    }
}