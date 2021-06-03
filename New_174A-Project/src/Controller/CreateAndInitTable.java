package Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import Connect.*;
//import jdk.internal.jshell.tool.resources.l10n;

public class CreateAndInitTable {
        public static String[] createArray() {
                String sql1 = "CREATE TABLE Customer(\n"
                        + " name STRING,\n"
                        + " state CHAR(2),\n"
                        + " email_address CHAR(30),\n"
                        + " tax_ID_num CHAR(9),\n"
                        + " password CHAR(20),\n"
                        + " username CHAR(20),\n"
                        + " address CHAR(40),\n"
                        + " phone CHAR(15),\n"
                        + " SSN CHAR(11),\n"
                        + " PRIMARY KEY (username)\n"
                        + ");";
                
                String sql2 = "CREATE TABLE Movies(\n"
                        + " movieID INTEGER,\n"
                        + " title CHAR(30),\n"
                        + " production_year CHAR(4),\n"
                        + " genre STRING,\n"
                        + " ranking INTEGER,\n"
                        + " revenue REAL,\n"
                        + " PRIMARY KEY (movieID)\n"
                        + ");";
                
                String sql3 = "CREATE TABLE Actor_Director(\n"
                        + " name STRING,\n"
                        + " three_letter_symbol CHAR(3),\n"
                        + " dob char(10),\n"
                        + " PRIMARY KEY (name)\n"
                        + ");";
                
                String sql5 = "CREATE TABLE Movie_contract(\n"
                        + " movieID INTEGER,\n"
                        + " name STRING,\n"
                        + " total_value REAL,\n"
                        + " year CHAR(4),\n"
                        + " role STRING,\n"
                        + " PRIMARY KEY (movieID,name),\n"
                        + " FOREIGN KEY (movieID) REFERENCES Movies,\n"
                        + " FOREIGN KEY (name) REFERENCES Actor_Director\n"
                        + " ON DELETE CASCADE\n"
                        + " ON UPDATE CASCADE\n"
                        + ");";
                
                String sql16 = "CREATE TABLE Stocks(\n"
                        + " daily_closing_prices REAL,\n"
                        + " current_price REAL,\n"
                        + " three_letter_symbol CHAR(3),\n"
                        + " PRIMARY KEY (three_letter_symbol)\n"
                        + ");";
                
                String sql15 = "CREATE TABLE Stock_Acc(\n"
                        + " ID_number CHAR(9) NOT NULL,\n"
                        + " list_of_transactions CHAR(1000),\n"
                        + " balance_inshares INTEGER DEFAULT 0,\n"
                        + " UNIQUE (ID_number),\n"
                        + " PRIMARY KEY(ID_number)\n"
                        + ");";
                
                        // update this when an actor buys a stock 
                String sql6 = "CREATE TABLE Has_stock(\n"
                        + " name STRING,\n"
                        + " three_letter_symbol CHAR(3),\n"
                        + " PRIMARY KEY (name,three_letter_symbol),\n"
                        + " FOREIGN KEY (name) REFERENCES Actor_Director\n"
                        + " ON DELETE CASCADE\n"
                        + " ON UPDATE CASCADE,\n"
                        + " FOREIGN KEY (three_letter_symbol) REFERENCES Stocks\n"
                        + " ON DELETE CASCADE\n"
                        + " ON UPDATE CASCADE\n"
                        + ");"; 
                
                String sql9 = "CREATE TABLE has_stock_Acc(\n"
                        + " username CHAR(30),\n"
                        + " ID_number CHAR(9),\n"
                        + " PRIMARY KEY (ID_number,username),\n"
                        + " FOREIGN KEY (ID_number) REFERENCES Stock_Acc\n"
                        + " ON DELETE CASCADE\n"
                        + " ON UPDATE CASCADE,\n"
                        + " FOREIGN KEY (username) REFERENCES Customer\n"
                        + " ON DELETE CASCADE\n"
                        + " ON UPDATE CASCADE\n"
                        + ");";
                
                String sql10 = "CREATE TABLE is_stock(\n"
                        + " three_letter_symbol CHAR(3),\n"
                        + " ID_number CHAR(9),\n"
                        + " PRIMARY KEY (ID_number,three_letter_symbol),\n"
                        + " FOREIGN KEY (ID_number) REFERENCES Stock_Acc\n"
                        + " ON DELETE CASCADE\n"
                        + " ON UPDATE CASCADE,\n"
                        + " FOREIGN KEY (three_letter_symbol) REFERENCES Stocks\n"
                        + " ON DELETE CASCADE\n"
                        + " ON UPDATE CASCADE\n"
                        + ");";
                
                String sql11 = "CREATE TABLE buying_stock(\n"
                        + " transaction_id STRING,\n"
                        + " three_letter_symbol CHAR(3),\n"
                        + " number_of_sharestobuy INTEGER,\n"
                        + " PRIMARY KEY (transaction_id, three_letter_symbol),\n"
                        + " FOREIGN KEY (transaction_id) REFERENCES transactions\n"
                        + " ON DELETE CASCADE\n"
                        + " ON UPDATE CASCADE,\n"
                        + " FOREIGN KEY (three_letter_symbol) REFERENCES Stocks\n"
                        + " ON DELETE CASCADE\n"
                        + " ON UPDATE CASCADE\n"
                        + ");";
                
                String sql12 = "CREATE TABLE selling_stock(\n"
                        + " transaction_id STRING,\n"
                        + " three_letter_symbol CHAR(3),\n"
                        + " PRIMARY KEY (transaction_id, three_letter_symbol),\n"
                        + " FOREIGN KEY (transaction_id) REFERENCES transactions\n"
                        + " ON DELETE CASCADE\n"
                        + " ON UPDATE CASCADE,\n"
                        + " FOREIGN KEY (three_letter_symbol) REFERENCES Stocks\n"
                        + " ON DELETE CASCADE\n"
                        + " ON UPDATE CASCADE\n"
                        + ");";
                
                String sql8 = "CREATE TABLE has_these_trans(\n"
                        + " transaction_id STRING,\n"
                        + " ID_number CHAR(9),\n"
                        + " PRIMARY KEY (ID_number,transaction_id),\n"
                        + " FOREIGN KEY (ID_number) REFERENCES Account\n"
                        + " ON DELETE CASCADE\n"
                        + " ON UPDATE CASCADE,\n"
                        + " FOREIGN KEY (transaction_id) REFERENCES transactions\n"
                        + " ON DELETE CASCADE\n"
                        + " ON UPDATE CASCADE\n"
                        + ");";
                
                String sql13 = "CREATE TABLE Account(\n"
                        + " ID_number CHAR(9) NOT NULL,\n"
                        + " list_of_transactions CHAR(1000),\n"
                        + " balance REAL DEFAULT 0,\n"
                        + " PRIMARY KEY (ID_number)\n"
                        + ");";
                
                String sql14 = "CREATE TABLE Market_Acc(\n"
                        + " ID_number CHAR(9) NOT NULL,\n"
                        + " list_of_transactions CHAR(1000),\n"
                        + " balance REAL NOT NULL,\n"
                        + " initial_deposit REAL,\n"
                        + " UNIQUE (ID_number),\n"
                        + " PRIMARY KEY(ID_number),\n"
                        + " CHECK (balance >= 0),\n"
                        + " CHECK (initial_deposit >= 1000)\n"
                        + ");";
                
                String sql7 = "CREATE TABLE Has_market_acc(\n"
                        + " username CHAR(20),\n"
                        + " ID_number CHAR(9),\n"
                        + " date_created CHAR(10),\n"
                        + " PRIMARY KEY (username, ID_number),\n"
                        + " FOREIGN KEY (username) REFERENCES Customer\n"
                        + " ON DELETE CASCADE\n"
                        + " ON UPDATE CASCADE,\n"
                        + " FOREIGN KEY (ID_number) REFERENCES Market_Acc\n"
                        + " ON DELETE CASCADE\n"
                        + " ON UPDATE CASCADE\n"
                        + ");";
                
                String sql17 = "CREATE TABLE transactions(\n"
                        + " transaction_id STRING,\n"
                        + " date_of_transaction CHAR(10),\n"
                        + " PRIMARY KEY (transaction_id)\n"
                        + ");";
                
                String sql18 = "CREATE TABLE Accrue_interest(\n"
                        + " transaction_id STRING,\n"
                        + " date_of_transaction CHAR(10),\n"
                        + " PRIMARY KEY (transaction_id)\n"
                        + ");";
                
                String sql19 = "CREATE TABLE withdraw(\n"
                        + " transaction_id STRING,\n"
                        + " date_of_transaction CHAR(10),\n"
                        + " withdrawal_amount REAL,\n"
                        + " PRIMARY KEY (transaction_id)\n"
                        + ");";
                
                String sql20 = "CREATE TABLE deposits(\n"
                        + " transaction_id STRING,\n"
                        + " date_of_transaction CHAR(10),\n"
                        + " money_to_deposit REAL,\n"
                        + " PRIMARY KEY (transaction_id)\n"
                        + ");";
                
                String sql21 = "CREATE TABLE sell(\n"
                        + " transaction_id STRING,\n"
                        + " date_of_transaction CHAR(10),\n"
                        + " number_shares INTEGER,\n"
                        + " buying_price REAL,\n"
                        + " PRIMARY KEY (transaction_id)\n"
                        + ");";
        // String sql1 = "CREATE TABLE CustomersAndAccounts(\n"
        //         + " name STRING,\n"
        //         + " state CHAR(2),\n"
        //         + " password CHAR(40),\n"
        //         + " address CHAR(30),\n"
        //         + " phone CHAR(15),\n"
        //         + " ssn CHAR(11),\n"
        //         + " market_account_id INTEGER NOT NULL,\n"
        //         + " stock_account_id INTEGER,\n"
        //         + " balance REAL DEFAULT 0,\n"
        //         + " email CHAR(30),\n"
        //         + " tax_id CHAR(9),\n"
        //         + " customer_username CHAR(20),\n"
        //         + " UNIQUE (market_account_id),\n"
        //         + " UNIQUE (stock_account_id),\n"
        //         + " PRIMARY KEY (customer_username),\n"
        //         + " CHECK (balance >= 0)\n"
        //         + ");";

        // String sql2 = "CREATE TABLE BalanceInStockAccount(\n"
        //         + " customer_username CHAR(30),\n"
        //         + " balance REAL NOT NULL,\n"
        //         + " stock_symbol CHAR(3),\n"
        //         + " original_buying_price REAL,\n"
        //         + " PRIMARY KEY (customer_username, stock_symbol),\n"
        //         + " FOREIGN KEY (stock_symbol) REFERENCES ActorsAndStocks\n"
        //         + " ON DELETE NO ACTION\n"
        //         + " ON UPDATE CASCADE,\n"
        //         + " FOREIGN KEY (customer_username) REFERENCES CustomersAndAccounts\n"
        //         + " ON DELETE NO ACTION\n"
        //         + " ON UPDATE CASCADE,\n"
        //         + " CHECK (balance >= 0)\n"
        //         + ");";

        // String sql3 = "CREATE TABLE Administrators(\n"
        //         + " admin_name CHAR(30),\n"
        //         + " admin_username CHAR(15),\n"
        //         + " admin_password CHAR(20),\n"
        //         + " admin_address CHAR(50),\n"
        //         + " admin_state CHAR(2),\n"
        //         + " admin_phone CHAR(15),\n"
        //         + " admin_email CHAR(40),\n"
        //         + " admin_taxid INTEGER,\n"
        //         + " admin_ssn CHAR(11),\n"
        //         + " PRIMARY KEY (admin_ssn)\n"
        //         + ");";
        // String sql4 = "CREATE TABLE Buy(\n"
        //         + " customer_username CHAR(30),\n"
        //         + " num_shares_to_buy INTEGER,\n"
        //         + " stock_symbol CHAR(3),\n"
        //         + " date_of_transaction CHAR(10),\n"
        //         + " transaction_id INTEGER,\n"
        //         + " PRIMARY KEY (transaction_id),\n"
        //         + " FOREIGN KEY (transaction_id) REFERENCES transactions\n"
        //         + " ON DELETE CASCADE\n"
        //         + " ON UPDATE CASCADE,\n"
        //         + " FOREIGN KEY (stock_symbol) REFERENCES ActorsAndStocks\n"
        //         + " ON DELETE CASCADE\n"
        //         + " ON UPDATE CASCADE\n"
        //         + ");";

        // String sql5 = "CREATE TABLE ActorsAndStocks(\n"
        //         + " stock_symbol CHAR(3) NOT NULL,\n"
        //         + " current_price REAL,\n"
        //         + " daily_closing_price REAL,\n"
        //         + " actor_name CHAR(30),\n"
        //         + " date_of_birth CHAR(10),\n"
        //         + " PRIMARY KEY (stock_symbol)\n"
        //         + ");";

        // String sql6 = "CREATE TABLE MovieContracts(\n"
        //         + " stock_symbol CHAR(3) NOT NULL,\n"
        //         + " title CHAR(30),\n"
        //         + " role STRING,\n"
        //         + " year CHAR(4),\n"
        //         + " total_value REAL,\n"
        //         + " PRIMARY KEY (stock_symbol, title),\n"
        //         + " FOREIGN KEY (stock_symbol) REFERENCES ActorsAndStocks\n"
        //         + " ON DELETE CASCADE\n"
        //         + " ON UPDATE CASCADE,\n"
        //         + " FOREIGN KEY (title) REFERENCES Movies\n"
        //         + " ON DELETE CASCADE\n"
        //         + " ON UPDATE CASCADE\n"
        //         + ");";

        // String sql7 = "CREATE TABLE Movies(\n"
        //         + " movie_id INTEGER,\n"
        //         + " title CHAR(30),\n"
        //         + " year CHAR(4),\n"
        //         + " genre STRING,\n"
        //         + " rating DOUBLE(2,1),\n"
        //         + " revenue REAL,\n"
        //         + " PRIMARY KEY (movie_id)\n"
        //         + ");";
        

        // String sql8 = "CREATE TABLE SellTransaction(\n"
        //         + " customer_username CHAR(30),\n"
        //         + " num_shares_to_sell INTEGER,\n"
        //         + " stock_symbol CHAR(3),\n"
        //         + " date_of_transaction CHAR(10),\n"
        //         + " transaction_id INTEGER,\n"
        //         + " PRIMARY KEY (transaction_id),\n"
        //         + " FOREIGN KEY (transaction_id) REFERENCES transactions\n"
        //         + " ON DELETE CASCADE\n"
        //         + " ON UPDATE CASCADE,\n"
        //         + " FOREIGN KEY (stock_symbol) REFERENCES ActorsAndStocks\n"
        //         + " ON DELETE CASCADE\n"
        //         + " ON UPDATE CASCADE\n"
        //         + ");";

        // String sql9 = "CREATE TABLE BalanceMarketAccount(\n"
        //         + " customer_username CHAR(30),\n"
        //         + " balance REAL NOT NULL,\n"
        //         + " PRIMARY KEY (customer_username),\n"
        //         + " FOREIGN KEY (customer_username) REFERENCES CustomersAndAccounts"
        //         + " ON DELETE CASCADE"
        //         + " ON UPDATE CASCADE"
        //         + ");";

        // String sql10 = "CREATE TABLE Accrue_interest(\n"
        //         + " transaction_id INTEGER,\n"
        //         + " date_of_transaction CHAR(10),\n"
        //         + " customer_username CHAR(30),\n"
        //         + " PRIMARY KEY (transaction_id),\n"
        //         + " FOREIGN KEY (customer_username) REFERENCES CustomersAndAccounts\n"
        //         + " ON DELETE CASCADE\n"
        //         + " ON UPDATE CASCADE\n"
        //         + ");";

        // String sql11 = "CREATE TABLE withdraw(\n"
        //         + " transaction_id INTEGER,\n"
        //         + " date_of_transaction CHAR(10),\n"
        //         + " withdrawal_amount REAL,\n"
        //         + " customer_username CHAR(30),\n"
        //         + " PRIMARY KEY (transaction_id),\n"
        //         + " FOREIGN KEY (customer_username) REFERENCES CustomersAndAccounts\n"
        //         + " ON DELETE CASCADE\n"
        //         + " ON UPDATE CASCADE\n"
        //         + ");";

        // String sql12 = "CREATE TABLE deposits(\n"
        //         + " transaction_id INTEGER,\n"
        //         + " date_of_transaction CHAR(10),\n"
        //         + " money_to_deposit REAL,\n"
        //         + " customer_username CHAR(30),\n"
        //         + " PRIMARY KEY (transaction_id),\n"
        //         + " FOREIGN KEY (customer_username) REFERENCES CustomersAndAccounts\n"
        //         + " ON DELETE CASCADE\n"
        //         + " ON UPDATE CASCADE\n"
        //         + ");";
        
        
        return new String[] {sql1, sql2, sql3, sql5, sql6, sql7, sql8, sql9, sql10, sql11, sql12, sql13, sql14, sql15, sql16, sql17 ,sql18, sql19, sql20, sql21};
    }
    public static String[] createDropArray() {
        String Dsql1 = "DROP TABLE IF EXISTS Customer";
        
        String Dsql2 = "DROP TABLE IF EXISTS Movies";

        String Dsql3 = "DROP TABLE IF EXISTS Actor_Director";

        String Dsql4 = "DROP TABLE IF EXISTS Movie_contract";
        
        String Dsql5 = "DROP TABLE IF EXISTS Has_stock"; 

        String Dsql6 = "DROP TABLE IF EXISTS Has_market_acc";

        String Dsql7 = "DROP TABLE IF EXISTS has_these_trans";

        String Dsql8 = "DROP TABLE IF EXISTS has_stock_Acc";

        String Dsql9 = "DROP TABLE IF EXISTS is_stock";

        String Dsql10 = "DROP TABLE IF EXISTS buying_stock";

        String Dsql11 = "DROP TABLE IF EXISTS selling_stock";
        
        String Dsql12 = "DROP TABLE IF EXISTS Account";

        String Dsql13 = "DROP TABLE IF EXISTS Market_Acc";

        String Dsql14 = "DROP TABLE IF EXISTS Stock_Acc";

        String Dsql15 = "DROP TABLE IF EXISTS Stocks";

        String Dsql16 = "DROP TABLE IF EXISTS transactions";

        String Dsql17 = "DROP TABLE IF EXISTS Accrue_Interest";

        String Dsql18 = "DROP TABLE IF EXISTS withdraw";

        String Dsql19 = "DROP TABLE IF EXISTS deposits";

        String Dsql20 = "DROP TABLE IF EXISTS sell";

        
        return new String[] {Dsql1, Dsql2, Dsql3, Dsql4, Dsql5, Dsql6, Dsql7, Dsql8, Dsql9, Dsql10, Dsql11, Dsql12, Dsql13, Dsql14, Dsql15, Dsql16, Dsql17, Dsql18, Dsql19, Dsql20};
    }
    public void createNewTable() {
        // SQLite connection string
        // String url = "jdbc:sqlite:/Users/karanveer/Desktop/School/Spring_21/CS174A/174A-Project/sqlite/db";
        // Change ':/Users/karanveer/Desktop/School/Spring_21/CS174A/' to whatever it is for you'
        // To find it go to the db and then pwd
        String[] arr = createArray(); // Make an Array of Strings with all of the create Table commands 
//        Connect my_conn = new Connect();
//        my_conn.my_connection();
        
        try {
            Connect my_conn = new Connect();
            Connection conn = my_conn.my_connection();
            for(int i = 0; i < arr.length; i++) {
                Statement stmt = conn.createStatement();
                // create a new table
                System.out.println(i);
                stmt.executeUpdate(arr[i]);
            }
            System.out.println("All Tables have been Created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void dropAllTables() {
        // Open a connection
        String[] arr = createDropArray();
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();		      
                
                for(int i = 0; i < arr.length; i++) {
                        Statement stmt = conn.createStatement();
                        stmt.executeUpdate(arr[i]);
                        System.out.println("Table deleted in given database...");
                } 
	  
        } catch (SQLException e) {
                e.printStackTrace();
        } 
    }

    public void insertToCustomer(String [] arr){
        String insertQuery = "INSERT INTO Customer(name,state,email_address,tax_ID_num,password, username, address, phone, SSN)\n"
                + " VALUES (?,?,?,?,?,?,?,?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery);
                for (int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1, arr[i]);
                }        
                // prepStatement.setString(1,name);
                // prepStatement.setString(2,state);
                // prepStatement.setString(3,email_address);
                // prepStatement.setString(4,tax_ID_num);
                // prepStatement.setString(5,password);
                // prepStatement.setString(6,username);
                // prepStatement.setString(7,address); 
                // prepStatement.setString(8,phone);
                // prepStatement.setString(9,SSN);
                prepStatement.executeUpdate();
                System.out.println("The Customer data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }

    public void insertToMovies(String [] arr){
        String insertQuery = "INSERT INTO Movies(movieID,title,production_year, genre, ranking, revenue)\n"
                + " VALUES (?,?,?,?,?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery);  
                for (int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1, arr[i]);
                }      
                // prepStatement.setString(1,title);
                // prepStatement.setString(2,production_year);
                // prepStatement.setString(3,reviews);
                // prepStatement.setString(4,ranking);
                // prepStatement.setString(5,revenue);
                prepStatement.executeUpdate();
                System.out.println("The Movie data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }

    public void insertToActor_Director(String [] arr){
        String insertQuery = "INSERT INTO Actor_Director(name,three_letter_symbol, dob)\n"
                + " VALUES (?,?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery);     
                for (int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1, arr[i]);
                }   
                // prepStatement.setString(1,name);
                // prepStatement.setString(2,three_letter_symbol);
                // prepStatement.setString(3,dob);
                prepStatement.executeUpdate();
                System.out.println("The Actor_Director data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }

    public void insertToMovie_contract(String [] arr){
        String insertQuery = "INSERT INTO Movie_contract(movieID, name, total_value, year, role)\n"
                + " VALUES (?,?,?,?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery);        
                // prepStatement.setString(1,title);
                // prepStatement.setString(2,name);
                // prepStatement.setString(3,total_value);
                // prepStatement.setString(4,year);
                // prepStatement.setString(5,role);

                for (int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1, arr[i]);
                }
                prepStatement.executeUpdate();
                System.out.println("The Movie_contract data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }

    public void insertToHas_Stock(String [] arr){
        String insertQuery = "INSERT INTO Has_stock(name, three_letter_symbol)\n"
                + " VALUES (?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery);        
                // prepStatement.setString(1,name);
                // prepStatement.setString(2,three_letter_symbol);

                for (int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1, arr[i]);
                }
                prepStatement.executeUpdate();
                System.out.println("The Has_stock data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }

    public void insertToHas_market_acc(String [] arr){
        String insertQuery = "INSERT INTO Has_market_acc(username, ID_number, date_created, initial_deposit)\n"
                + " VALUES (?,?,?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery); 
                for(int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1,arr[i]);                
                }       
                prepStatement.executeUpdate();
                System.out.println("The Has_Stock data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }

    public void insertToHas_these_trans(String [] arr){
        String insertQuery = "INSERT INTO has_these_trans(transaction_id , ID_number)\n"
                + " VALUES (?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery); 
                for(int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1,arr[i]);                
                }       
                prepStatement.executeUpdate();
                System.out.println("The Has_these_trans data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }

    public void insertTohas_stock_Acc(String [] arr){
        String insertQuery = "INSERT INTO has_stock_Acc(username , ID_number)\n"
                + " VALUES (?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery); 
                for(int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1 ,arr[i]);                
                }       
                prepStatement.executeUpdate();
                System.out.println("The Has_stock_Acc data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }
    public void insertTois_Stock(String [] arr){
        String insertQuery = "INSERT INTO is_stock(three_letter_symbol , ID_number)\n"
                + " VALUES (?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery); 
                for(int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1,arr[i]);                
                }       
                prepStatement.executeUpdate();
                System.out.println("The is_stock data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }

    public void insertTobuying_stock(String [] arr){
        String insertQuery = "INSERT INTO buying_stock(transaction_id , three_letter_symbol, number_of_sharestobuy)\n"
                + " VALUES (?,?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery); 
                for(int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1,arr[i]);                
                }       
                prepStatement.executeUpdate();
                System.out.println("The buying_stock data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }

    public void insertToselling_stock(String [] arr){
        String insertQuery = "INSERT INTO selling_stock(transaction_id , three_letter_symbol)\n"
                + " VALUES (?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery); 
                for(int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1,arr[i]);                
                }       
                prepStatement.executeUpdate();
                System.out.println("The selling_stock data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }


    public void insertToAccount(String [] arr){
        String insertQuery = "INSERT INTO Account(ID_number , list_of_transactions, balance)\n"
                + " VALUES (?,?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery); 
                for(int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1,arr[i]);                
                }       
                prepStatement.executeUpdate();
                System.out.println("The Account data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }

    public void insertToMarket_Acc(String [] arr){
        String insertQuery = "INSERT INTO Market_Acc(ID_number , list_of_transactions, balance)\n"
                + " VALUES (?,?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery); 
                for(int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1,arr[i]);                
                }       
                prepStatement.executeUpdate();
                System.out.println("The Market_Acc data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }

    public void insertToStock_Acc(String [] arr){
        String insertQuery = "INSERT INTO Stock_Acc(ID_number , list_of_transactions, balance_inshares)\n"
                + " VALUES (?,?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery); 
                for(int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1,arr[i]);                
                }       
                prepStatement.executeUpdate();
                System.out.println("The Stock_Acc data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }

    public void insertToStocks(String [] arr){
        String insertQuery = "INSERT INTO Stocks(daily_closing_prices , current_price, three_letter_symbol)\n"
                + " VALUES (?,?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery); 
                for(int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1,arr[i]);                
                }       
                prepStatement.executeUpdate();
                System.out.println("The Stocks data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }

    public void insertTotransactions(String [] arr){
        String insertQuery = "INSERT INTO transactions(transaction_id , date_of_transaction)\n"
                + " VALUES (?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery); 
                for(int i = 0; i < arr.length; i++){
                        
                        prepStatement.setString(i+1,arr[i]);

                }   
                    
                prepStatement.executeUpdate();
                System.out.println("here");
                System.out.println("The transactions data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }


    public void insertToAccrue_interest(String [] arr){
        String insertQuery = "INSERT INTO Accrue_interest(transaction_id , date_of_transaction)\n"
                + " VALUES (?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery); 
                for(int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1,arr[i]);                
                }       
                prepStatement.executeUpdate();
                System.out.println("The Accrue_interest data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }

    public void insertTowithdraw(String [] arr){
        String insertQuery = "INSERT INTO withdraw(transaction_id , date_of_transaction, withdrawal_amount)\n"
                + " VALUES (?,?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery); 
                for(int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1,arr[i]);                
                }       
                prepStatement.executeUpdate();
                System.out.println("The withdraw data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }

    public void insertTodeposits(String [] arr){
        String insertQuery = "INSERT INTO deposits(transaction_id , date_of_transaction, money_to_deposit)\n"
                + " VALUES (?,?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery); 
                for(int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1,arr[i]);                
                }       
                prepStatement.executeUpdate();
                System.out.println("The deposits data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }


    public void insertTosell(String [] arr){
        String insertQuery = "INSERT INTO sell(transaction_id , date_of_transaction, number_shares, buying_price)\n"
                + " VALUES (?,?,?,?)";
        try{
                Connect my_conn = new Connect();
                Connection conn = my_conn.my_connection();
                PreparedStatement prepStatement = conn.prepareStatement(insertQuery); 
                for(int i = 0; i < arr.length; i++){
                        prepStatement.setString(i+1,arr[i]);                
                }       
                prepStatement.executeUpdate();
                System.out.println("The sell data has been inserted!");
        }
        catch (SQLException e){
                System.out.println(e.getMessage());

        }
    }


    public void insertreader() {
        // pass the path to the file as a parameter
        try {
                // File file = new File("C:/New_174A-Project1/Data.txt"); // charans 
                File file = new File("Given_Data.txt");
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                        String Vals = "";
                        String column_names = "";
                        String our_line = sc.nextLine();
                        System.out.println(our_line);

                        if (our_line.equals("CUSTOMER")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        insertToCustomer(insert_these);
                                        MarketAcc my_markacc = new MarketAcc();
                                        my_markacc.OpenMarketAcc(insert_these[0], 1001, "03/16/13");
                                     } 
                                }
                        } else if (our_line.equals("MOVIES")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertToMovies(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("ACTOR_DIRECTOR")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertToActor_Director(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("MOVIE_CONTRACT")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertToMovie_contract(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("HAS_STOCK")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertToHas_Stock(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("HAS_MARKET_ACC")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertToHas_market_acc(insert_these);
                                     } 
                                }
                        }
                        
                        else if (our_line.equals("HAS_THESE_TRANS")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertToHas_these_trans(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("HAS_STOCK_ACC")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertTohas_stock_Acc(insert_these);
                                        
                                     } 
                                }
                        }
                        else if (our_line.equals("IS_STOCK")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertTois_Stock(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("BUYING_STOCK")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertTobuying_stock(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("SELLING_STOCK")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertToselling_stock(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("ACCOUNT")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertToAccount(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("MARKET_ACC")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertToMarket_Acc(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("STOCK_ACC")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertToStock_Acc(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("STOCKS")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertToStocks(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("TRANSACTIONS")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertTotransactions(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("ACCRUE_INTEREST")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertToAccrue_interest(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("WITHDRAW")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertTowithdraw(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("DEPOSITS")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertTodeposits(insert_these);
                                     } 
                                }
                        }
                        else if (our_line.equals("SELL")) {
                                column_names = sc.nextLine();
                                while(sc.hasNextLine()) {
                                     Vals = sc.nextLine();
                                     if(Vals.equals("-----")) {
                                        break;
                                     } else {
                                        String [] insert_these = Vals.split(",");
                                        // Call functions
                                        insertTosell(insert_these);
                                     } 
                                }
                        }
                }        
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }   
    }



}
