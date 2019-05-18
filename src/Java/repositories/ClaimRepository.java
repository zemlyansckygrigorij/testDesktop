package Java.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Connection.ConnectionClass;
import Java.objects.Claim;
import Java.objects.Client;
import Java.objects.Employer;
import Java.objects.Status;

public class ClaimRepository {
    private static ArrayList<Claim> claimList = new ArrayList<Claim>();
    static{
        Connection connection = ConnectionClass.getConnection();
        try (Statement statement = connection.createStatement()) {
            String selectClaim = "select * from registrationсlaims.claims ";
            ResultSet resultSetClaim = statement.executeQuery(selectClaim);

            while (resultSetClaim.next()) {
                //создаем обьект -заявку
                System.out.println("employer - "+ resultSetClaim.getInt(3));
                System.out.println("Client - "+ resultSetClaim.getInt(4));
                System.out.println("Status - "+ resultSetClaim.getInt(5));
                Employer employer = EmployerRepository.getEmployerById(resultSetClaim.getInt(3));
                System.out.println("employerName - "+ employer);
                Client client = ClientRepository.getClientById(resultSetClaim.getInt(4));
                System.out.println("clientName - "+ client);
                Status status = StatusRepository.getStatusById(resultSetClaim.getInt(5));
                System.out.println("descriptName - "+ resultSetClaim.getString(6));
                claimList.add(new Claim(resultSetClaim.getInt(1), resultSetClaim.getString(2).substring(0, 19), employer, client, status, resultSetClaim.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ClaimRepository(){}

    public static ArrayList<Claim> getClaimList(){
        return claimList;
    }

    public static Claim getClaimById(int id){
        Claim result = null ;
        List<Claim> claimFilter = claimList.stream().filter((claim) -> {
            return claim.getId() ==id;
        }).collect(Collectors.toList());
        if(claimFilter.size()>0){
            result = claimFilter.get(0);
        }
        return result;
    }
}
