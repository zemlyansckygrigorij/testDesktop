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
        Parent rootConnect = FXMLLoader.load(getClass().getResource("connector.fxml"));
        primaryStage.setTitle("Cоединение с сервером");
        primaryStage.setScene(new Scene(rootConnect, 1000, 575));
        primaryStage.show();



        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if(!ConnectionClass.getConnect()) return;
                try {
                    Parent rootClaim = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    Stage  claimStage = new Stage();
                    claimStage.setTitle("Регистрация заявок");
                    claimStage.setScene(new Scene(rootClaim, 1000, 575));
                    claimStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


       /* Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Регистрация заявок");
        primaryStage.setScene(new Scene(root, 1000, 575));
        primaryStage.show();
        */
    }


    public static void main(String[] args) {
        launch(args);
    }


}
