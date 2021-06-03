import Controller.*;

public class main{
    // public static void createNewDatabase() {
    //     String url = "jdbc:sqlite:C:/sqlite/db/Project1.db";
    //     try (Connection conn = DriverManager.getConnection(url)) {
    //         if (conn != null) {
    //             DatabaseMetaData meta = conn.getMetaData();
    //             System.out.println("The driver name is " + meta.getDriverName());
    //             System.out.println("A new database has been created.");
    //         }

    //     } catch (SQLException e) {
    //         System.out.println("Karan...");
    //         System.out.println(e.getMessage());
    //         System.out.println("Karan2...");
    //     }
    // }
    public static void main(String[] args) {
        // createNewDatabase();
        CreateAndInitTable Table1 = new CreateAndInitTable();
        // Table1.dropAllTables();
        // Table1.createNewTable();
        Table1.insertreader();
    }
}
