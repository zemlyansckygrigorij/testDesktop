package JavaFX.listClaims;

import Java.objects.Claim;
import Java.repositories.ClaimRepository;
import JavaFX.FormControl;
import com.sun.prism.impl.Disposer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerListClaims {
    @FXML private TableView<Claim> tableView;

    @FXML TableColumn<Claim, Integer> idClaims;
    @FXML TableColumn<Claim, String> dateTime;
    @FXML TableColumn<Claim, String> employer;
    @FXML TableColumn<Claim, String> client;
    @FXML TableColumn<Claim, String> status;
    @FXML TableColumn<Claim, String> description;


    // инициализация приложения
    @FXML
    private void initialize() {

        ClaimRepository.getClaimList().stream().forEach(claim ->System.out.println(claim.getId()+" - "+claim.getDateTime()+" - "+claim.getEmployer()+" - "+claim.getClient()+" - "+claim.getStatus()+" - "+claim.getDescription()));

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

    public void getSelectedRow() {
        Claim claim = tableView.getSelectionModel().getSelectedItem();
        System.out.println(claim.getId()+" zz- "+claim.getDateTime()+" - "+claim.getEmployer()+" - "+claim.getClient()+" - "+claim.getStatus()+" - "+claim.getDescription());
        FormControl.setSelectClaim(claim);
    }


    public void getListClaims(){

    }
}
































