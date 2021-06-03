package UseFulFuncs;
import java.sql.Connection;
import Connect.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class usefulfuncs {
    public String getnewTransID(){
        String query = "SELECT MAX(transaction_id)\n"
                     + "FROM Transactions \n";
        try {
            Connect my_conn = new Connect();
            Connection conn = my_conn.my_connection();
            PreparedStatement prepStatement = conn.prepareStatement(query);
            ResultSet resultSet = prepStatement.executeQuery(); 
            if (resultSet.next()){
                String currentID = resultSet.getString(1);
                if (currentID == null){
                    System.out.println("This is the first transaction");
                    return "1";
                }
                String result = Integer.toString(Integer.parseInt(currentID) + 1);
                return result;
            }
            else{
                System.out.println("This is the first transaction");
                    return "1";
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String getnewAccountID(){
        String query = "SELECT MAX(ID_number)\n"
                     + "FROM Account \n";
        try {
            Connect my_conn = new Connect();
            Connection conn = my_conn.my_connection();
            PreparedStatement prepStatement = conn.prepareStatement(query);
            ResultSet resultSet = prepStatement.executeQuery(); 
            if (resultSet.next()){
                String currentID = resultSet.getString(1);
                if (currentID == null){
                    System.out.println("This is the first Account");
                    return "001";
                }
                String result = Integer.toString(Integer.parseInt(currentID) + 1);
                return result;
            }
            else{
                System.out.println("This is the first Account");
                    return "001";
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
