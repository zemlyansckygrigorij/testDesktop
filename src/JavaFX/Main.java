package JavaFX;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        new FormControl().runConnectForm();// запуск формы соединения с сервером
    }


    public static void main(String[] args) {
        launch(args);
    }

}
