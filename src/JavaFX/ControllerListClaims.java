package JavaFX;

import Java.Claim;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;

import Connection.ConnectionClass;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerListClaims {
    @FXML private TableView<Claim> tableView;
    Connection connection;
    @FXML TableColumn<Claim, Integer> idClaims;
    @FXML TableColumn<Claim, String> dateTime;
    @FXML TableColumn<Claim, String> implementer;
    @FXML TableColumn<Claim, String> sender;
    @FXML TableColumn<Claim, String> status;
    @FXML TableColumn<Claim, String> description;


    // инициализация приложения
    @FXML
    private void initialize() {

        String select = " select claims.id_claims, claims.date_time,personal.name AS implementer , clients.name AS sender    ,claims.description from registrationсlaims.claims inner join registrationсlaims.personal on registrationсlaims.claims.id_implementer = registrationсlaims.personal.id_personal inner join registrationсlaims.clients on registrationсlaims.claims.id_sender = registrationсlaims.clients.id_clients;";

        //получить данные из таблицы personal - сотрудники

        connection = ConnectionClass.getConnection();

        ObservableList<Claim> сlaimsModels = FXCollections.observableArrayList();
     //   сlaimsModels.add()


        idClaims.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        implementer.setCellValueFactory(new PropertyValueFactory<>("implementer"));
        sender.setCellValueFactory(new PropertyValueFactory<>("sender"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        //add your data to the table here.
        tableView.setItems(сlaimsModels);


       /* try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(select);

            while(resultSet.next()){
          System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getString(4)+" "+resultSet.getString(5));
                idClaims.setText(resultSet.getString(1));


                dateTime.setText(resultSet.getString(2));
                implementer.setText(resultSet.getString(3));
                sender.setText(resultSet.getString(4));
                description.setText(resultSet.getString(5));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/






    }

    public void sendData() {


    }


    public void getListClaims(){

    }
}
































