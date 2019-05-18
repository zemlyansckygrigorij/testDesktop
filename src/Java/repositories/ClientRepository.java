package Java.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Connection.ConnectionClass;
import Java.objects.Client;

import java.util.List;
import java.util.stream.Collectors;

public class ClientRepository {
    private static ArrayList<Client> clientList = new ArrayList<Client>();
    static{
        Connection connection = ConnectionClass.getConnection();
        try (Statement statement = connection.createStatement()) {
            String selectClient = "select * from registrationсlaims.clients LIMIT 4";
            ResultSet resultSetClient = statement.executeQuery(selectClient);

            while (resultSetClient.next()) {
                //создаем обьект -клиент
                clientList.add(new Client(resultSetClient.getInt(1), resultSetClient.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ClientRepository(){}

    public static ArrayList<Client> getClientList(){
        return clientList;
    }

    public static Client getClientById(int id){
        Client result = null ;

        List<Client> clientFilter = clientList.stream().filter((client) -> {
            return client.getId() ==id;
        }).collect(Collectors.toList());

        if(clientFilter.size()>0) result = clientFilter.get(0);
        return result;
    }
}
