package Java.repositories;

import Connection.ConnectionClass;
import Java.objects.Employer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class EmployerRepository {
   private static ArrayList<Employer> employerList = new ArrayList<Employer>();
   static{
       Connection connection = ConnectionClass.getConnection();
       try (Statement statement = connection.createStatement()) {
           String selectEmployer = "select * from registrationсlaims.employers LIMIT 4";
           ResultSet resultSetEmployer = statement.executeQuery(selectEmployer);

           while (resultSetEmployer.next()) {

               //создаем обьект -сотрудник
               employerList.add(new Employer(resultSetEmployer.getInt(1), resultSetEmployer.getString(2)));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }


    public static ArrayList<Employer> getEmployerList(){
       return employerList;
    }

    public static Employer getEmployerById(int id){
        Employer result = null ;
        List<Employer> employerFilter = employerList.stream().filter((employer) -> {
            return employer.getId() ==id;
        }).collect(Collectors.toList());
        if(employerFilter.size()>0) result = employerFilter.get(0);
        return result;
    }
}
