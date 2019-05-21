package Java.repositories;

import Connection.ConnectionClass;
import Java.objects.Claim;
import Java.objects.Status;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StatusRepositoryTest {
    ArrayList<Status> statusList;
    @Before
    public void setUp() throws Exception {
        ConnectionClass.setSetting("registrationсlaims", "root", "uhbujhbq", "localhost", "3306");
        ConnectionClass.getConnection();
        statusList = StatusRepository.getStatusList();

    }
    @Test
    public void getStatusList() {
        int answer = statusList.size();
        assertEquals(2, answer);
    }

    @Test
    public void getStatusById() {
        Status answer = StatusRepository.getStatusById(1);
        assertEquals("выполнена", answer.toString());
    }
}