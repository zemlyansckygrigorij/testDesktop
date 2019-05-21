package Java.objects;

import org.junit.*;

import static org.junit.Assert.*;

public class EmployerTest {
    Employer employer;
    @Before
    public void setUp() throws Exception {
        employer = new Employer(19,"bbb");
    }


    @Test
    public void getId() {
        int answer = employer.getId();

        assertEquals(19, answer);
    }

    @Test
    public  void setId() {
        employer.setId(21);
        int answer = employer.getId();
        assertEquals(21, answer);
    }

    @Test
    public void getName() {
        String answer = employer.getName();
        assertEquals("bbb", answer);
    }

    @Test
    public void setName() {
        employer.setName("aaa");
        String answer = employer.getName();
        assertEquals("aaa", answer);
    }

    @Test
    public  void toString1() {
        employer.setName("fff");
        String answer = employer.toString();
        assertEquals("fff", answer);
    }

    @Test
    public  void valueOf() {
        employer.setId(24);
        int answer = employer.valueOf();
        assertEquals(24, answer);
    }

    @After
    public void setDown() throws Exception {
        employer = null;

    }
}