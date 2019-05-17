package JavaFX;

import Java.Person;
import Java.Status;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import Connection.ConnectionClass;

public class ControllerListClaims {
    public DatePicker dataPicker;
    public TextField timeTextField;
    public TextField idTextField;
    public ComboBox implementerTextField;
    public ComboBox senderTextField;
    public Label labelDate;
    public Label labelTime;
    public Label labelSender;
    public Label labelImplementer;
    public Label labelStatus;

    public TextArea descriptionTextArea;
    public ComboBox statusTextFiled;
    public Label labelDescription;
    public Label labelMessage;
    ConnectionClass connectionClass;
    Connection connection;


    // инициализация приложения
    @FXML
    private void initialize() {
        String select = "select id_claims,date_time,implementer,sender,status,description from registrationсlaims.personal";
        //получить данные из таблицы personal - сотрудники

        connection = ConnectionClass.getConnection();

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(select);

            while(resultSet.next()){
           System.out.println(resultSet.getString(1));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }






    }

    public void sendData() {


        String description = descriptionTextArea.getText();

        Person sender = (Person) senderTextField.getValue();
        Person implementer = (Person) implementerTextField.getValue();
        Status status = (Status)statusTextFiled.getValue();
        //текущая время дата
        LocalDateTime dateTime = LocalDateTime.now();
        String dateNow = dateTime.toLocalDate().toString();
        String timeNow = dateTime.toLocalTime().toString().substring(0, 8);

        //время-дата для вставки в MySQL
        String dateTimeClaim = dateNow+" "+timeNow;

        //соединение с сервером
        connection = ConnectionClass.getConnection();
      /*  ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();*/


/************ Проверки *************/
        //если описание заявки не введено
        if (description.equals("")) {
            labelMessage.setText("Введите текст заявки");
            return;
        }

//если исполнитель не выбран
        if (implementer == null) {
             labelMessage.setText("Выберите исполнителя");
             return;
        }


//если отправитель не выбран
        if (sender == null) {
             labelMessage.setText("Выберите отправителя");
             return;
        }

        //если статус не выбран
        if (status == null) {
             labelMessage.setText("Выберите отправителя");
             return;
        }



        String sql = "insert into claims (date_time,id_implementer,id_sender,id_status,description) values('" +  dateTimeClaim +  "','" + implementer.getId() + "','" + sender.getId() + "','" + status.getId() + "','" + description + "')";
        //  String sql = "insert into claims (date_time,implementer,sender,id_status,description) values('98-12-21 11:30:44','"+implementer+"','"+sender+"','"+status+"','" + descriptionTextArea.getText() +"')";



        System.out.println(sql);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            /*ResultSet resultSet = statement.executeQuery(select);
String select = "select * from registrationсlaims.personal";
            while(resultSet.next()){
           System.out.println(resultSet.getString(1));


            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // labelDescription.setText(descriptionTextArea.getText());


// обнуление полей
         descriptionTextArea.setText("");
         senderTextField.setValue(null);
         implementerTextField.setValue(null);
         statusTextFiled.setValue(null);

    }


    public void getListClaims(){

    }
}
































