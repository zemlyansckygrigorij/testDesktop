package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    Connection connection;
    private String dbName;
    public Connection getConnection(){
        dbName = "registrationсlaims";
        String userName = "root";
        String localHost = "localhost:3306";
        String settingConnect = "?autoReconnect=true&useSSL=false";
        String password = "uhbujhbq";
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://"+localHost+"/"+dbName+settingConnect , userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
