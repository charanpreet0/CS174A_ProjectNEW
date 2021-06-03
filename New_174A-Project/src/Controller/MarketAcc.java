package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Connect.*;
public class MarketAcc {
    // call this every time we insert a new customer 
    // when you call this you have to prep user input for the initial_deposit and date created
    // make if statement

    public void OpenMarketAcc(String username, int initial_dep,  String date_created) {
        String newId = ""; // have the new id function called here 
        String my_query1 = "INSERT INTO Market_Acc VALUES (?, ?, ?, ?)";
        if (initial_dep > 1000) {
            String initial_dep1 = Integer.toString(initial_dep);
            try {
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(my_query1);
                prepStatement.setString(1, username);
                prepStatement.setString(2, null);
                prepStatement.setString(3, initial_dep1);
                prepStatement.setString(4, initial_dep1);
                prepStatement.executeUpdate();
                System.out.println("...");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        
            String my_query = "INSERT INTO Account VALUES (?, ?, ?)";
            try {
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(my_query);
                prepStatement.setString(1, username);
                prepStatement.setString(2, null);
                prepStatement.setString(3, initial_dep1);
                prepStatement.executeUpdate();
                System.out.println("...");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        
            String my_query2 = "INSERT INTO Has_market_acc VALUES (?, ?, ?)";
            try {
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(my_query2);
                prepStatement.setString(1, username);
                prepStatement.setString(2, newId);
                prepStatement.setString(3, username);
                prepStatement.executeUpdate();

                System.out.println("The Market Account has been opened!");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }   
        } else {
            System.out.println("The Initial deposit is under 1000.");
        }
    }   
}
