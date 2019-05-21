package JavaFX;

import Connection.ConnectionClass;
import Java.objects.*;
import Java.repositories.ClaimRepository;
import Java.repositories.ClientRepository;
import Java.repositories.EmployerRepository;
import Java.repositories.StatusRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MainTest {
    ArrayList<Status> statusList;
    ArrayList<Client> clientList;
    ArrayList<Employer> employerList;
    ArrayList<Claim> claimList;
    Client client;
    Employer employer;
    Status status;

    Claim claimTest;
    Employer employerTest;
    Client clientTest;
    Status statusTest;
    String dateTimeTest = "2019-05-20 12:01:10";
    String descriptionTest = "description";
    @Before
    public void setUp() throws Exception {
        ConnectionClass.setSetting("registrationсlaims", "root", "uhbujhbq", "localhost", "3306");
        ConnectionClass.getConnection();
        statusList = StatusRepository.getStatusList();
        clientList = ClientRepository.getClientList();
        employerList = EmployerRepository.getEmployerList();
        claimList = ClaimRepository.getClaimList();

        client = new Client(19,"bbb");
        employer = new Employer(19,"bbb");
        status = new Status(19,"bbb");

        employerTest = new Employer(1,"employer");
        clientTest = new Client(2,"client");
        statusTest = new Status(3,"status ");
        claimTest = new Claim(5, dateTimeTest, employerTest, clientTest, statusTest, descriptionTest);


    }


    @Test
    public void main() {


    }
    @Test
    public  void getConnect() {
        boolean answer = ConnectionClass.getConnect();
        assertEquals(true, answer);
    }
    @Test
    public void getStatusListSize() {
        int answer = statusList.size();
        assertEquals(2, answer);
    }
    @Test
    public  void getClientListSize() {
        int answer = clientList.size();
        assertEquals(4, answer);
    }
    @Test
    public void getEmployerListSize() {
        int answer = employerList.size();
        assertEquals(4, answer);
    }
    @Test
    public void getClaimListSize() {
        int answer = claimList.size();
        assertEquals(16, answer);
    }

    @Test
    public   void getEmployerById() {
        Employer answer = EmployerRepository.getEmployerById(1);
        assertEquals("Иванов И.И.", answer.toString());
    }
    @Test
    public void getClaimById() {
        Claim answer = ClaimRepository.getClaimById(19);
        assertEquals("Заявка № 31", answer.getDescription());
    }


    @Test
    public void getStatusById() {
        Status answer = StatusRepository.getStatusById(1);
        assertEquals("выполнена", answer.toString());
    }
    @Test
    public void getClientById() {
        Client answer = ClientRepository.getClientById(1);
        assertEquals("Сидоров В.В.", answer.toString());
    }

   /**************************************************************************************************/

    @Test
    public void getClientId() {
        int answer = client.getId();

        assertEquals(19, answer);
    }

    @Test
    public void setClientId() {
        client.setId(21);
        int answer = client.getId();
        assertEquals(21, answer);
    }

    @Test
    public void getClientName() {
        String answer = client.getName();
        assertEquals("bbb", answer);
    }

    @Test
    public void setClientName() {
        client.setName("aaa");
        String answer = client.getName();
        assertEquals("aaa", answer);
    }

    @Test
    public void toString1() {
        client.setName("fff");
        String answer = client.toString();
        assertEquals("fff", answer);
    }

    @Test
    public void valueClientOf() {
        client.setId(24);
        int answer = client.valueOf();
        assertEquals(24, answer);
    }

    /****************************************************/
    @Test
    public void getEmployerId() {
        int answer = employer.getId();

        assertEquals(19, answer);
    }

    @Test
    public void setEmployerId() {
        employer.setId(21);
        int answer = employer.getId();
        assertEquals(21, answer);
    }

    @Test
    public  void getEmployerName() {
        String answer = employer.getName();
        assertEquals("bbb", answer);
    }

    @Test
    public void setEmployerName() {
        employer.setName("aaa");
        String answer = employer.getName();
        assertEquals("aaa", answer);
    }

    @Test
    public void toEmployerString2() {
        employer.setName("fff");
        String answer = employer.toString();
        assertEquals("fff", answer);
    }

    @Test
    public void valueOfEmployer() {
        employer.setId(24);
        int answer = employer.valueOf();
        assertEquals(24, answer);
    }

    /***********************************************************************/
    @Test
    public void getIdStatus() {
        int answer = status.getId();

        assertEquals(19, answer);
    }


    @Test
    public void setIdStatus() {
        status.setId(21);
        int answer = status.getId();
        assertEquals(21, answer);
    }

    @Test
    public void getNameStatus() {
        String answer = status.getName();
        assertEquals("bbb", answer);
    }

    @Test
    public void setNameStatus() {
        status.setName("aaa");
        String answer = status.getName();
        assertEquals("aaa", answer);
    }

    @Test
    public void toStringStatus() {
        status.setName("fff");
        String answer = status.toString();
        assertEquals("fff", answer);
    }

    @Test
    public void valueOfStatus() {
        status.setId(24);
        int answer = status.valueOf();
        assertEquals(24, answer);

    }

/**************************************************************************************/
    @Test
    public void getIdClaim() {
    int answer = claimTest.getId();
    assertEquals(5, answer);
    }

    @Test
    public void setIdClaim() {
        claimTest.setId(6);
        int answer = claimTest.getId();
        assertEquals(6, answer);
    }

    @Test
    public void getDateTimeClaim() {
        String answer = claimTest.getDateTime();
        assertEquals(dateTimeTest, answer);
    }

    @Test
    public void setDateTimeClaim() {
        claimTest.setDateTime("2019-06-21 12:05:10");
        String answer = claimTest.getDateTime();
        assertEquals("2019-06-21 12:05:10", answer);
    }

    @Test
    public void getEmployerClaim() {
        Employer answer = claimTest.getEmployer();
        assertEquals( employerTest, answer);
    }
    @Test
    public void setEmployerClaim() {
        Employer employerTest1 = new Employer(7,"employerTest");
        claimTest.setEmployer(employerTest1);
        Employer answer = claimTest.getEmployer();
        assertEquals( employerTest1, answer);
    }

    @Test
    public void getClientClaim() {
        Client answer = claimTest.getClient();
        assertEquals( clientTest, answer);
    }

    @Test
    public void setClientClaim() {
        Client clientTest1 = new Client(7,"clientTest");
        claimTest.setClient(clientTest1);
        Client answer = claimTest.getClient();
        assertEquals( clientTest1, answer);
    }



    @Test
    public void getStatusClaim() {
        Status answer = claimTest.getStatus();
        assertEquals( statusTest, answer);
    }

    @Test
    public void setStatusClaim() {
        Status statusTest1 = new Status(7,"statusTest");
        claimTest.setStatus(statusTest1);
        Status answer = claimTest.getStatus();
        assertEquals( statusTest1, answer);
    }

    @Test
    public  void getDescriptionClaim() {
        String answer = claimTest.getDescription();
        assertEquals( descriptionTest, answer);
    }

    @Test
    public void setDescriptionClaim() {
        claimTest.setDescription("1234");
        String answer = claimTest.getDescription();
        assertEquals( "1234", answer);
    }

    @Test
    public  void valueOfClaim() {
        int answer = claimTest.getId();
        assertEquals(5, answer);
    }











    @After
    public void setDown() throws Exception {
        client = null;
        employer = null;
        status = null;
        claimTest = null;
        employerTest = null;
        clientTest = null;
        statusTest = null;
        dateTimeTest  = null;
        descriptionTest = null;
    }
}