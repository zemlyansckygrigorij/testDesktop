package Java.objects;

import org.junit.*;
import static org.junit.Assert.*;
public class ClaimTest {
    Claim claimTest;
    Employer employerTest;
    Client clientTest;
    Status statusTest;
    String dateTimeTest = "2019-05-20 12:01:10";
    String descriptionTest = "description";
    @Before
    public void setUp() throws Exception {
        employerTest = new Employer(1,"employer");
        clientTest = new Client(2,"client");
        statusTest = new Status(3,"status ");
        claimTest = new Claim(5, dateTimeTest, employerTest, clientTest, statusTest, descriptionTest);
    }
    @Test
    public  void getId() {
        int answer = claimTest.getId();
        assertEquals(5, answer);
    }

    @Test
    public  void setId() {
        claimTest.setId(6);
        int answer = claimTest.getId();
        assertEquals(6, answer);
    }

    @Test
    public void getDateTime() {
        String answer = claimTest.getDateTime();
        assertEquals(dateTimeTest, answer);
    }

    @Test
    public void setDateTime() {
        claimTest.setDateTime("2019-06-21 12:05:10");
        String answer = claimTest.getDateTime();
        assertEquals("2019-06-21 12:05:10", answer);
    }

    @Test
    public void getEmployer() {
        Employer answer = claimTest.getEmployer();
        assertEquals( employerTest, answer);
    }
    @Test
    public  void setEmployer() {
        Employer employerTest = new Employer(7,"employerTest");
        claimTest.setEmployer(employerTest);
        Employer answer = claimTest.getEmployer();
        assertEquals( employerTest, answer);
    }

    @Test
    public void getClient() {
        Client answer = claimTest.getClient();
        assertEquals( clientTest, answer);
    }

    @Test
    public  void setClient() {
        Client clientTest = new Client(7,"clientTest");
        claimTest.setClient(clientTest);
        Client answer = claimTest.getClient();
        assertEquals( clientTest, answer);
    }



    @Test
    public  void getStatus() {
        Status answer = claimTest.getStatus();
        assertEquals( statusTest, answer);
    }

    @Test
    public  void setStatus() {
        Status statusTest = new Status(7,"statusTest");
        claimTest.setStatus(statusTest);
        Status answer = claimTest.getStatus();
        assertEquals( statusTest, answer);
    }

    @Test
    public void getDescription() {
        String answer = claimTest.getDescription();
        assertEquals( descriptionTest, answer);
    }

    @Test
    public void setDescription() {
        claimTest.setDescription("1234");
        String answer = claimTest.getDescription();
        assertEquals( "1234", answer);
    }

    @Test
    public  void valueOf() {
        int answer = claimTest.getId();
        assertEquals(5, answer);
    }
}