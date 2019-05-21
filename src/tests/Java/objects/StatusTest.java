package Java.objects;

import org.junit.*;

import static org.junit.Assert.*;

public class StatusTest {
    Status status;
    @Before
    public void setUp() throws Exception {
        status = new Status(19,"bbb");

    }
    @Test
    public void getId() {
        int answer = status.getId();

        assertEquals(19, answer);
    }


    @Test
    public  void setId() {
        status.setId(21);
        int answer = status.getId();
        assertEquals(21, answer);
    }

    @Test
    public void getName() {
        String answer = status.getName();
        assertEquals("bbb", answer);
    }

    @Test
    public void setName() {
        status.setName("aaa");
        String answer = status.getName();
        assertEquals("aaa", answer);
    }

    @Test
    public void toString1() {
        status.setName("fff");
        String answer = status.toString();
        assertEquals("fff", answer);
    }

    @Test
    public void valueOf() {
        status.setId(24);
        int answer = status.valueOf();
        assertEquals(24, answer);
    }

    @After
    public void setDown() throws Exception {
        status = null;

    }
}