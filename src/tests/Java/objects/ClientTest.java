package Java.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
    Client client;
    @Before
    public void setUp() throws Exception {
        client = new Client(19,"bbb");
    }

    @Test
    public  void getId() {
        int answer = client.getId();

        assertEquals(19, answer);
    }

    @Test
    public  void setId() {
        client.setId(21);
        int answer = client.getId();
        assertEquals(21, answer);
    }

    @Test
    public  void getName() {
        String answer = client.getName();
        assertEquals("bbb", answer);
    }

    @Test
    public  void setName() {
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
    public  void valueOf() {
        client.setId(24);
        int answer = client.valueOf();
        assertEquals(24, answer);
    }

    @After
     public void setDown() throws Exception {
        client = null;
    }
}