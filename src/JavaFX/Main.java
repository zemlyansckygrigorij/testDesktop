package JavaFX;

import Connection.ConnectionClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        new FormControl().runConnectForm();
       /* Parent rootConnect = FXMLLoader.load(getClass().getResource("connector/connector.fxml"));
        primaryStage.setTitle("Cоединение с сервером");
        primaryStage.setScene(new Scene(rootConnect, 5000, 575));
        primaryStage.show();



        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if(!ConnectionClass.getConnect()) return;
                try {
                    Parent rootClaim = FXMLLoader.load(getClass().getResource("claim/sample.fxml"));
                    Stage  claimStage = new Stage();
                    claimStage.setTitle("Регистрация заявок");
                    claimStage.setScene(new Scene(rootClaim, 1000, 575));
                    claimStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

*/

    }


    public static void main(String[] args) {
        launch(args);
    }


}
