package Java;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Connection.ConnectionClass;


public class StatusRepository {
    private static ArrayList<Status> statusList = new ArrayList<Status>();
    static{
        Connection connection = ConnectionClass.getConnection();
        try (Statement statement = connection.createStatement()) {
            String selectStatus = "select * from registrationсlaims.status LIMIT 4";
            ResultSet resultSetStatus = statement.executeQuery(selectStatus);

            while (resultSetStatus.next()) {
                //создаем обьект -клиент
                statusList.add(new Status(resultSetStatus.getInt(1), resultSetStatus.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    StatusRepository(){}

    public static ArrayList<Status> getStatusList(){
        return statusList;
    }
}
