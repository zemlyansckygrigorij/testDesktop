package JavaFX;

import Java.Person;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Connection.ConnectionClass;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.time.LocalDateTime;






public class Controller {
    public DatePicker dataPicker;
    public TextField timeTextField;
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
    ConnectionClass connectionClass ;
    Connection connection ;

    @FXML
    private void initialize() {
        LocalDateTime dateTime = LocalDateTime.now();
        String dateNow = dateTime.toLocalDate().toString();
        String timeNow = dateTime.toLocalTime().toString().substring(0, 8);
        dataPicker.setValue(dateTime.toLocalDate());
        timeTextField.setText(timeNow);
        connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        String selectPersonal = "select * from registrationсlaims.personal LIMIT 4";
        String selectStatus = "select * from registrationсlaims.status";
        try (Statement statement = connection.createStatement()) {
            Statement statementStatus = connection.createStatement();
            ResultSet resultSetPersonal = statement.executeQuery(selectPersonal);

            while(resultSetPersonal.next()){
                Person person = new Person(resultSetPersonal.getInt(1),resultSetPersonal.getString(2));

                implementerTextField.getItems().add(person);
                 senderTextField.getItems().add(person);


               // implementerTextField.getItems().add(resultSetPersonal.getString(2));
               // senderTextField.getItems().add(resultSetPersonal.getString(2));

              //  implementerTextField.getItems().
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        try (Statement statement = connection.createStatement()) {

            ResultSet resultSetStatus = statement.executeQuery(selectStatus);

            while(resultSetStatus.next()){
                statusTextFiled.getItems().add(resultSetStatus.getString(2));
               // statusTextFiled.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void sendData(){
        String description =  descriptionTextArea.getText();
        String time =  timeTextField.getText();
        String data;
        String sender;
        String implementer;
        String status;
        LocalDateTime dateTime = LocalDateTime.now();
        String dateNow = dateTime.toLocalDate().toString();
        String timeNow = dateTime.toLocalTime().toString().substring(0, 8);
        System.out.println(dateNow);
        System.out.println(timeNow);


        //если описание заявки не введено
        if(description.equals("")){
            labelMessage.setText("Введите текст заявки");
            return;
        }
//  если время не введено
        if(time.equals("")){
            labelMessage.setText("Введите время заявки");
            return;
        }
// если дата не введена
        if(dataPicker.getValue()== null){
            labelMessage.setText("Введите дату заявки");
            return;
        }else{
            data=  dataPicker.getValue().toString();
        }

//если исполнитель не выбран
        if(implementerTextField.getValue()== null){
           // labelMessage.setText("Выберите исполнителя");
           // return;
            implementer="0";
        }else{
            implementer =  implementerTextField.getValue().toString();
        }


//если отправитель не выбран
        if(senderTextField.getValue()==null){
           // labelMessage.setText("Выберите отправителя");
          //  return;
            sender ="0";
        }else{
            sender =  senderTextField.getValue().toString();
        }
        Person send = (Person )senderTextField.getValue();
        System.out.println("send id - "+send.getId());
        System.out.println(senderTextField.getValue());
        //если статус не выбран
        if(statusTextFiled.getValue()==null){
            // labelMessage.setText("Выберите отправителя");
            //  return;
            status ="0";
        }else{
            status =  statusTextFiled.getValue().toString();
        }
        System.out.println(dateTime.toLocalDate());

      /*  ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();*/
        String sql = "insert into claims (date_time,implementer,sender,id_status,description) values('"+dateNow + " "+timeNow +"','"+implementer+"','"+sender+"','"+status+"','" + descriptionTextArea.getText() +"')";
      //  String sql = "insert into claims (date_time,implementer,sender,id_status,description) values('98-12-21 11:30:44','"+implementer+"','"+sender+"','"+status+"','" + descriptionTextArea.getText() +"')";


        // 98-12-21 11:30:44
        String select = "select * from registrationсlaims.personal";
        System.out.println(sql);
        try (Statement statement = connection.createStatement()) {
             statement.executeUpdate(sql);
            /*ResultSet resultSet = statement.executeQuery(select);

            while(resultSet.next()){
           System.out.println(resultSet.getString(1));


            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // labelDescription.setText(descriptionTextArea.getText());
    }
}
































