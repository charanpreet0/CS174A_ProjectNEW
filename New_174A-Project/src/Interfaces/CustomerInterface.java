package Interfaces;

public class CustomerInterface {
        public static void Deposit() {
            // Add money to markey account balance
            // implement this function in here 
            
            // public static void add_to_balance(String username, double amount){
            //     String query = "UPDATE Has_market_acc " +
            //                    "SET balance = balance + " amount + " " +
            //                    "WHERE username = \'" + username + "\';";
            //     Utility.makeStaticUpdate(query);
        
            //     int transaction_id = Customer_DB.get_next_id("DepositTransaction", "transaction_id");
        
            //     UPDATE = "INSERT INTO Deposit"
            //     //WORK ON Transaction ID STUFF
            // }
        }
    
        public static void withdraw() {
            // subtract money from the market account balance 
    
    
            // public static void subtract_from_balance(String username, double amount){
            //     String query = "UPDATE Has_market_acc " +
            //                    "SET balance = balance - " amount + " " +
            //                    "WHERE username = \'" + username + "\';";
            //     Utility.makeStaticUpdate(query);
        
            //     // WORK ON TRANSACTION ID
        
            // }
        
    
        }
    
        public static void Buy() {
            // Implement this into the thing
    
            // public static void buy_shares(String customer_username, int num_shares, String stock_symbol) {}
        }
    
        public static void Sell() {
            // 
    
            // public static void sell_shares(String customer_username, int num_shares, String stock_symbol, double original_buying_price) {}
    
        }
    
        public static void showMarketBal() {
            // get_market_acc
            // get_acc_balance
        }
        public static void showTransac() {
            // remove some of the other helpers in stock Acc and implement them in here 
        }
    
        public static void priceStock () {}
    
        public static void actorProfile () {}
    
        public static void MovieDetails () {}
    
        public static void topMovies () {}
    
        public static void displayMovieReviews () {} 
}
