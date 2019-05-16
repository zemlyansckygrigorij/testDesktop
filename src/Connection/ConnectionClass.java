package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    Connection connection;
    private String dbName;
    public Connection getConnection(){
        dbName = "registrationСlaims";
        String userName = "root";
        String localHost = "localhost:3306";

        String password = "uhbujhbq";
        try {
            Class.forName("com.mysql.jdbc.Driver");
             connection = DriverManager.getConnection("jdbc:mysql://localhost/"+ dbName, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
