package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    private static Connection connection;
    private static String dbName;
    private static String userName;
    private static String host ;
    private static String port;
    private static String password ;
    private static boolean autoReconnect = true;
    private static boolean useSSL = false;

    private static boolean connect = false;

    public static void setSetting(String dbName, String userName, String password, String host, String port){
        ConnectionClass.dbName = dbName;
        ConnectionClass.userName = userName;
        ConnectionClass.password = password;
        ConnectionClass.host = host;
        ConnectionClass.port = port;

    }


    public static Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dbName+ "?autoReconnect="+autoReconnect+"&useSSL="+useSSL   , userName, password);
            ConnectionClass.setConnect(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static boolean getConnect(){
        return  ConnectionClass.connect;
    }
    private static void setConnect(boolean connect){
        ConnectionClass.connect = connect;
    }

}


/*
*  private static Connection connection;
    private static String dbName= "registrationсlaims";
    private static String userName = "root";
    private static String host = "localhost";
    private static String port = "3306";
    private static boolean autoReconnect = true;
    private static boolean useSSL = false;
    private static String password = "uhbujhbq";
*
*
*
*
*
* */