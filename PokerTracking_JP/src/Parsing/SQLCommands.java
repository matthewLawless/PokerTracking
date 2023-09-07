package Parsing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLCommands {

    static final String DB_URL = "jdbc:mysql://localhost:3306";
    static final String USER = "root";
    static final String PASS = "GodDid";

    public static void main(String[] args){

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
        
        Statement stmt = conn.createStatement();)
        {
            String s = "use matthewsDB";
            stmt.executeUpdate(s);
            String sql = "CREATE TABLE monkey (c1 char(5));";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully . . . . . ");
            conn.close();



        } 
        catch( SQLException e){

            e.printStackTrace();

        }

    }
    
}
