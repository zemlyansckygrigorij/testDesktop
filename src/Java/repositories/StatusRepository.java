package Java.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Connection.ConnectionClass;
import Java.objects.Status;


public class StatusRepository {
    private static ArrayList<Status> statusList = new ArrayList<Status>();
    static{
        Connection connection = ConnectionClass.getConnection();
        try (Statement statement = connection.createStatement()) {
            String selectStatus = "select * from registrationсlaims.status LIMIT 4";
            ResultSet resultSetStatus = statement.executeQuery(selectStatus);

            while (resultSetStatus.next()) {
                //создаем обьект -статус
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

    public static Status getStatusById(int id){
        Status result = null ;
        List<Status> statusFilter = statusList.stream().filter((status) -> {
            return status.getId() ==id;
        }).collect(Collectors.toList());
        if(statusFilter.size()>0) result = statusFilter.get(0);
        return result;
    }
}
