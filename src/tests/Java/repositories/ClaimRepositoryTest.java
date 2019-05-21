package Java.repositories;
import Connection.ConnectionClass;
import Java.objects.Claim;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ClaimRepositoryTest {
    ArrayList<Claim> claimList;
    @Before
    public void setUp() throws Exception {
        ConnectionClass.setSetting("registrationсlaims", "root", "uhbujhbq", "localhost", "3306");
        ConnectionClass.getConnection();
        claimList = ClaimRepository.getClaimList();

    }
    @Test
    public void getClaimList() {
        int answer = claimList.size();
        assertEquals(16, answer);
    }

    @Test
    public void getClaimById() {
        Claim answer = ClaimRepository.getClaimById(19);
        assertEquals("Заявка № 31", answer.getDescription());
    }
}