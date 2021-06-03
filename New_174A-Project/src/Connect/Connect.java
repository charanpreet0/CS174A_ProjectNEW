package Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Connect {
    public Connection my_connection() {
        // SQLite connection string
        // Change ':/Users/karanveer/Desktop/School/Spring_21/CS174A/' to whatever it is for you'
        String url = "jdbc:sqlite:Database/Project1.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println("In Exception Block Error");            
            System.out.println(e.getMessage());
        }
        return conn;
    }
}