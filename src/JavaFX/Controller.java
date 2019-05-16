package JavaFX;

import javafx.scene.control.*;
import Connection.ConnectionClass;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;




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

    public void sendData(){
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "insert into claims (date_time,implementer,sender,id_status,description) values("+"'98-12-31 11:30:45',"+"'1',"+"'1',"+"'1','" + descriptionTextArea.getText() +"')";

        String select = "select * from registrationсlaims.personal";
        System.out.println(sql);
        try (Statement statement = connection.createStatement()) {
             statement.executeUpdate(sql);
            ResultSet resultSet = statement.executeQuery(select);

            while(resultSet.next()){
           System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(1));
               // labelDescription.setText(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // labelDescription.setText(descriptionTextArea.getText());
    }
}
































