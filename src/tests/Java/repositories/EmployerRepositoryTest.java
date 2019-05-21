package Java.repositories;

import Connection.ConnectionClass;
import Java.objects.Client;
import Java.objects.Employer;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EmployerRepositoryTest {
    ArrayList<Employer> employerList;
    @Before
    public void setUp() throws Exception {
        ConnectionClass.setSetting("registrationсlaims", "root", "uhbujhbq", "localhost", "3306");
        ConnectionClass.getConnection();
        employerList = EmployerRepository.getEmployerList();

    }
    @Test
    public  void getEmployerList() {
        int answer = employerList.size();
        assertEquals(4, answer);
    }

    @Test
    public   void getEmployerById() {
        Employer answer = EmployerRepository.getEmployerById(1);
        assertEquals("Иванов И.И.", answer.toString());
    }
}