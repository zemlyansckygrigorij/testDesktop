package JavaFX.listClaims;

import Java.objects.Claim;
import Java.objects.DirectoryChooserDemo;
import Java.repositories.ClaimRepository;
import JavaFX.FormControl;
import JavaFX.claim.Controller;
import com.sun.prism.impl.Disposer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.io.*;

public class ControllerListClaims {
    @FXML private TableView<Claim> tableView;

    @FXML TableColumn<Claim, Integer> idClaims;
    @FXML TableColumn<Claim, String> dateTime;
    @FXML TableColumn<Claim, String> employer;
    @FXML TableColumn<Claim, String> client;
    @FXML TableColumn<Claim, String> status;
    @FXML TableColumn<Claim, String> description;
    @FXML private Button buttonSelectedRow;
    @FXML private Label labelMessage;
    // инициализация приложения
    @FXML
    private void initialize() {


        ObservableList<Claim> сlaimsModels = FXCollections.observableArrayList(ClaimRepository.getClaimList());


        idClaims.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        employer.setCellValueFactory(new PropertyValueFactory<>("employer"));
        client.setCellValueFactory(new PropertyValueFactory<>("client"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        //add your data to the table here.
        tableView.setItems(сlaimsModels);

    }

    @FXML private void getSelectedRow() {
        Claim claim = tableView.getSelectionModel().getSelectedItem();
        FormControl.setSelectClaim(claim);
        returnFormClaim();

    }


    public void getListClaims(){

    }

    @FXML private void returnFormClaim(){
        FormControl.getListClaimsForm().close();
        FormControl.getClaimForm().show();
    }
    @FXML private void createReportClaims(){
        String path = configuringDirectoryChooser();
        String filePath = path+"/Отчет_по_заявкам.txt";
        File file = new File(filePath);
        if(file.exists()){
            labelMessage.setText("файл уже существует !");
            return;
        }
        try {
            if(!file.createNewFile()){
                labelMessage.setText("Ошибка создания файла !");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(FileWriter writer = new FileWriter(filePath, false))
        {
            // запись всей строки
            String text = "Отчет по заявкам";
            writer.write(text);
            // запись по символам
            writer.append('\n');
            writer.append('E');

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }



    }
    @FXML private void createReportImplementers(){
        String path = configuringDirectoryChooser();
        String filePath  = path+"/Отчет_по_заявкам.txt";
    }

    private String configuringDirectoryChooser() {
        // Set title for DirectoryChooser
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите папку");
        Stage stage = new Stage();
        File dir = directoryChooser.showDialog(stage);

        // Set Initial Directory
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return dir.getAbsolutePath().replaceAll("\\\\/", "/");
    }
}
































