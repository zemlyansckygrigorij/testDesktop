package JavaFX;

import Java.objects.Claim;
import JavaFX.claim.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import Connection.ConnectionClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class FormControl {
    private static Claim selectClaim = null;
	private static LocalDate dataBegin;
	private static LocalDate dataEnd;
    private static Stage connectForm = null;
    private static Stage claimForm = null;
    private static Stage listClaimsForm = null;
    private static Controller controller = null;
    private static Stage formDirectoryChooser = null;
    private static Stage selectDateForm = null;

    public static Controller getController() {
        return controller;
    }

    public static void setController(Controller controller) {
        FormControl.controller = controller;
    }

    public static Claim getSelectClaim() {
        return selectClaim;
    }

    public static void setSelectClaim(Claim selectClaim) {
        FormControl.selectClaim = selectClaim;
    }

    public static LocalDate getDataBegin() {
        return dataBegin;
    }

    public static LocalDate getDataEnd() {
        return dataEnd;
    }

    public static void setDataBegin(LocalDate dataBegin) {
        FormControl.dataBegin = dataBegin;
    }

    public static void setDataEnd(LocalDate dataEnd) {
        FormControl.dataEnd = dataEnd;
    }

    public static Stage getSelectDateForm() {
        return selectDateForm;
    }



    public   void runSelectDateForm(){
        try {
            Parent rootSelectDate = FXMLLoader.load(getClass().getResource("selectDate/formSelectDate.fxml"));
            Stage  selectDateStage = new Stage();
            selectDateStage.setTitle("Выберите период");
            selectDateStage.setScene(new Scene(rootSelectDate, 500, 175));
            selectDateStage.show();
            selectDateForm = selectDateStage;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public  void runConnectForm(){
        Parent rootConnect = null;
        try {
            rootConnect = FXMLLoader.load(getClass().getResource("connector/connector.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Cоединение с сервером");
            stage.setScene(new Scene(rootConnect, 550, 375));
            stage.show();
            connectForm = stage;
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

            claimStage.setOnShowing(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent event) {
                    controller.update();
                }
            });

            claimStage.show();

            claimForm = claimStage;
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
            listStage.setScene(new Scene(rootList, 1500, 575));
            listStage.show();
            listClaimsForm =listStage;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static Stage getClaimForm() {
        return claimForm;
    }

    public static Stage getConnectForm() {
        return connectForm;
    }

    public static Stage getListClaimsForm() {
        return listClaimsForm;
    }
}
