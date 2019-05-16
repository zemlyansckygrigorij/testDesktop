package JavaFX;

import javafx.scene.control.*;
import Connection.ConnectionClass;

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
        labelDescription.setText(descriptionTextArea.getText());
    }
}
































