package Java.repositories;

import Connection.ConnectionClass;
import Java.objects.Client;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ClientRepositoryTest {
    ArrayList<Client> clientList;
    @Before
    public void setUp() throws Exception {
        ConnectionClass.setSetting("registrationсlaims", "root", "uhbujhbq", "localhost", "3306");
        ConnectionClass.getConnection();
        clientList = ClientRepository.getClientList();

    }
    @Test
    public void getClientList() {
        int answer = clientList.size();
        assertEquals(4, answer);
    }

    @Test
    public void getClientById() {
        Client answer = ClientRepository.getClientById(1);
        assertEquals("Сидоров В.В.", answer.toString());
    }
}