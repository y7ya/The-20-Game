package server.db;

import java.net.ContentHandlerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public boolean register(String username, String password) throws SQLException{
        PreparedStatement ps = this.conn.prepareStatement("INSERT INTO users (`username`, `password`) VALUES (?, ?);");
        ps.setString(1, username);
        ps.setString(2, password);
        int n = ps.executeUpdate();
        if(n > 0) return true;
        return false;
    }
    
    public boolean login(String username, String password) throws SQLException{
        PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM users where username=? AND password=?");
        ps.setString(1, username);
        ps.setString(2, password);
        
        if(ps.execute()){
            System.out.println("");
            return true;
        }else{
            return false;
        }
    }

    

}