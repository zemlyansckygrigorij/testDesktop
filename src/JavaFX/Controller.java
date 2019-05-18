package JavaFX;

import Java.Person;
import Java.Status;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Connection.ConnectionClass;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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
        if (idTextField.getText().equals("")) {
        }


        // Получить дату время
        LocalDateTime dateTime = LocalDateTime.now();
        // String dateNow = dateTime.toLocalDate().toString();
        String timeNow = dateTime.toLocalTime().toString().substring(0, 8);

        //вставка текущей даты в поле "Дата заявки"
        dataPicker.setValue(dateTime.toLocalDate());

        //вставка текущей времени в поле "Время заявки"
        timeTextField.setText(dateTime.toLocalTime().toString().substring(0, 8));

        //получить данные из таблицы personal - сотрудники


        connection = ConnectionClass.getConnection();


        try (Statement statement = connection.createStatement()) {
            String selectPersonal = "select * from registrationсlaims.personal LIMIT 4";
            ResultSet resultSetPersonal = statement.executeQuery(selectPersonal);

            while (resultSetPersonal.next()) {
                //создаем обьект -сотрудник
                Person person = new Person(resultSetPersonal.getInt(1), resultSetPersonal.getString(2));

                //вставка обьекта сотрудник в списки  "Отправитель" и "Исполнитель"
                implementerTextField.getItems().add(person);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //получить данные из таблицы clients - клиенты
        try (Statement statement = connection.createStatement()) {
            String selectSender = "select * from registrationсlaims.clients LIMIT 4";
            ResultSet resultSetSender = statement.executeQuery(selectSender);

            while (resultSetSender.next()) {
                //создаем обьект -сотрудник
                Person person = new Person(resultSetSender.getInt(1), resultSetSender.getString(2));

                //вставка обьекта сотрудник в списки  "Отправитель" и "Исполнитель"

                senderTextField.getItems().add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //получить данные из таблицы status - статус заявки
        try (Statement statement = connection.createStatement()) {
            String selectStatus = "select * from registrationсlaims.status";
            ResultSet resultSetStatus = statement.executeQuery(selectStatus);

            while (resultSetStatus.next()) {
                Status status = new Status(resultSetStatus.getInt(1), resultSetStatus.getString(2));
                statusTextFiled.getItems().add(status);
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
        Parent rootList = null;
        try {
            rootList = FXMLLoader.load(getClass().getResource("listClaims.fxml"));
            Stage listStage = new Stage();
            listStage.setTitle("Список заявок");
            listStage.setScene(new Scene(rootList, 1000, 575));
            listStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
































