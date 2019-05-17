package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    Connection connection;
    private String dbName= "registrationсlaims";
    private String userName = "root";
    private String host = "localhost";
    private String port = "3306";
    private boolean autoReconnect = true;
    private boolean useSSL = false;
    private String password = "uhbujhbq";

    public Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dbName+ "?autoReconnect="+autoReconnect+"&useSSL="+useSSL   , userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
