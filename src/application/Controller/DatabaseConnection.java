package application.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    private static Connection connection;

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            // Load the JDBC driver
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // Establish the connection
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/property",
                    "root",
                    "1234"
            );
        }
        
        if(connection != null) 
        {
            System.out.println("DB is connected");
            
        }
        else {
            System.out.println("DB is not connected");
        }
        
        
        return connection;
    }
}