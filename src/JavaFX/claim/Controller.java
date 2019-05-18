package JavaFX.claim;

import Java.objects.Claim;
import Java.objects.Client;
import Java.objects.Employer;
import Java.objects.Status;
import Java.repositories.ClientRepository;
import Java.repositories.EmployerRepository;
import Java.repositories.StatusRepository;
import JavaFX.FormControl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Connection.ConnectionClass;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

import java.time.LocalDateTime;






public class Controller {
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
        //если id не передан то создаем заявку
        if (FormControl.getSelectClaim()!=null) {//доделать
            Claim claim = FormControl.getSelectClaim();
            idTextField.setText(Integer.toString(claim.getId()));
           // dataPicker.setValue(claim.getDateTime());
        }


        // Получить дату время
        LocalDateTime dateTime = LocalDateTime.now();
        // String dateNow = dateTime.toLocalDate().toString();
        String timeNow = dateTime.toLocalTime().toString().substring(0, 8);

        //вставка текущей даты в поле "Дата заявки"
        dataPicker.setValue(dateTime.toLocalDate());

        //вставка текущей времени в поле "Время заявки"
        timeTextField.setText(dateTime.toLocalTime().toString().substring(0, 8));


      //  вставка обьектов в списки  "Отправитель" и "Исполнитель" "Статус"
        implementerTextField.getItems().addAll(EmployerRepository.getEmployerList());
        senderTextField.getItems().addAll(ClientRepository.getClientList());
        statusTextFiled.getItems().addAll(StatusRepository.getStatusList());

    }

    public void sendData() {


        String description = descriptionTextArea.getText();

        Client sender = (Client) senderTextField.getValue();
        Employer implementer = (Employer) implementerTextField.getValue();
        Status status = (Status)statusTextFiled.getValue();
        //текущая время дата
        LocalDateTime dateTime = LocalDateTime.now();
        String dateNow = dateTime.toLocalDate().toString();
        String timeNow = dateTime.toLocalTime().toString().substring(0, 8);

        //время-дата для вставки в MySQL
        String dateTimeClaim = dateNow+" "+timeNow;

        //соединение с сервером
        connection = ConnectionClass.getConnection();

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

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }



// обнуление полей
         descriptionTextArea.setText("");
         senderTextField.setValue(null);
         implementerTextField.setValue(null);
         statusTextFiled.setValue(null);

    }


    public void getListClaims(){
        new FormControl().runListClaimsForm();
    }
}
































