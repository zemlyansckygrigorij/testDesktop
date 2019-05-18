package JavaFX;

import Java.objects.Claim;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import Connection.ConnectionClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class FormControl {
    private static Claim selectClaim = null;

    public static Claim getSelectClaim() {
        return selectClaim;
    }

    public static void setSelectClaim(Claim selectClaim) {
        FormControl.selectClaim = selectClaim;
    }

    public  void runConnectForm(){
        Parent rootConnect = null;
        try {
            rootConnect = FXMLLoader.load(getClass().getResource("connector/connector.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Cоединение с сервером");
            stage.setScene(new Scene(rootConnect, 550, 375));
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    if(!ConnectionClass.getConnect()) return;
                    runClaimForm();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  void runClaimForm(){
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

    public  void runListClaimsForm(){
        Parent rootList = null;
        try {
            rootList = FXMLLoader.load(getClass().getResource("listClaims/listClaims.fxml"));
            Stage listStage = new Stage();
            listStage.setTitle("Список заявок");
            listStage.setScene(new Scene(rootList, 1000, 575));
            listStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
