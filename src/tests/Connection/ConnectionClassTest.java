package Connection;


import org.junit.*;

import static org.junit.Assert.assertEquals;

public class ConnectionClassTest {

    @Before
    public void setUp() throws Exception {
        ConnectionClass.setSetting("registrationсlaims", "root", "uhbujhbq", "localhost", "3306");
        ConnectionClass.getConnection();

    }
    @Test
    public  void setSetting() {
    }

    @Test
    public  void getConnection() {
    }

    @Test
    public  void getConnect() {
        boolean answer = ConnectionClass.getConnect();
        assertEquals(true, answer);
    }
}