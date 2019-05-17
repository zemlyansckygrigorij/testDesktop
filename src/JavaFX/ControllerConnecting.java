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

public class ControllerConnecting {


    @FXML
    private TextField hostTextField;
    @FXML
    private TextField portTextField;
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label labelMessage;
    @FXML
    private TextField dataBaseTextField;


    ConnectionClass connectionClass;
    Connection connection;




    public void sendData() {
        String dataBase = dataBaseTextField.getText().trim();
        String login = loginTextField.getText().trim();
        String password = passwordTextField.getText().trim();
        String host =  hostTextField.getText().trim();
        String port = portTextField.getText().trim();

        /************ Проверки *************/
        if (dataBase.equals("")) {
            labelMessage.setText("Введите имя базы данных");
            return;
        }
        if (login.equals("")) {
            labelMessage.setText("Введите логин");
            return;
        }
        if (password.equals("")) {
            labelMessage.setText("Введите пароль");
            return;
        }
        if (host.equals("")) {
            labelMessage.setText("Введите хост");
            return;
        }
        if (port.equals("")) {
            labelMessage.setText("Введите порт");
            return;
        }

        // Ввод настроек
        ConnectionClass.setSetting(dataBase, login, password, host, port);

        //соединение с сервером
        connection = ConnectionClass.getConnection();
        if(ConnectionClass.getConnect()){
            labelMessage.setText("Соединение установлено . ");
        }else{
            labelMessage.setText("Ошибка соединения . Введите параметры заново .");
        }

        // обнуление полей
        dataBaseTextField.setText("");
        loginTextField.setText("");
        passwordTextField.setText("");
        hostTextField.setText("");
        portTextField.setText("");

    }
}
































