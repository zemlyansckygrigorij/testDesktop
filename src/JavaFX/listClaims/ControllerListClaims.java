package JavaFX.listClaims;

import Java.objects.Claim;
import Java.objects.Employer;
import Java.objects.Status;
import Java.repositories.ClaimRepository;
import Java.repositories.EmployerRepository;
import Java.repositories.StatusRepository;
import JavaFX.FormControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @FXML private  DatePicker dateStart;
    @FXML private DatePicker dateEnd;

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
        labelMessage.setText("");
        Claim claim = tableView.getSelectionModel().getSelectedItem();
        FormControl.setSelectClaim(claim);
        returnFormClaim();

    }


    public void getListClaims(){

    }

    @FXML private void returnFormClaim(){
        labelMessage.setText("");
        FormControl.getListClaimsForm().close();
        FormControl.getClaimForm().show();
    }
    @FXML private void createReportClaims(){
     //   new FormControl().runSelectDateForm();

        if (dateStart.getValue()==null) {
            labelMessage.setText("Введите начальную дату");
            return;
        }
//если описание заявки не введено
        if (dateEnd.getValue()==null) {
            labelMessage.setText("Введите конечную дату");
            return;
        }
        String filePath = createFile("Отчет_по_заявкам");
        if(filePath==null)return;
        try(FileWriter writer = new FileWriter(filePath, false))
        {

            ArrayList<Claim> claimList = ClaimRepository.getClaimList();
          //  LocalDate dateBegin = FormControl.getDataBegin();
           // LocalDate dateEnd = FormControl.getDataEnd();
            writer.write("Отчет по заявкам"+ "\n");
            List<Claim> claimListFilterDate = claimList.stream().filter((claim)->{
                return ((LocalDate.parse(claim.getDateTime().substring(0,10)).isAfter(dateStart.getValue())&
                        LocalDate.parse(claim.getDateTime().substring(0,10)).isBefore(dateEnd.getValue())));
            }).collect(Collectors.toList());

            claimListFilterDate.addAll(claimList.stream().filter((claim)->{
                return (
                        LocalDate.parse(claim.getDateTime().substring(0,10)).isEqual(dateStart.getValue())|
                        LocalDate.parse(claim.getDateTime().substring(0,10)).isEqual(dateEnd.getValue())
                );
            }).collect(Collectors.toList()));
            ArrayList<Status> statusList = StatusRepository.getStatusList();
            for(int i = 0; i<statusList.size();i++){
                Status status = statusList.get(i);
                writer.append(status.getName()+"\n");
                claimListFilterDate.stream().filter((claim)->{
                    return claim.getStatus() == status;
                }).forEach((claim)->{
                    try {
                        writer.append(claim.getDateTime().toString() +" " +claim.getEmployer()+" "+claim.getClient()
                                +" "+claim.getStatus()+'\n');
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writer.append('\n');
            }


            writer.flush();
        }
        catch(IOException ex){

            ex.printStackTrace();
        }



    }
    @FXML private void createReportEmployers(){
        String filePath = createFile("Отчет_по_исполнителям");
        if(filePath==null)return;
        try(FileWriter writer = new FileWriter(filePath, false))
        {
            // запись всей строки

            writer.write("Отчет по исполнителям" +'\n');
            writer.append('\n');

            ArrayList<Claim> claimList = ClaimRepository.getClaimList();
            ArrayList<Employer> employerList = EmployerRepository.getEmployerList();

            for(int i = 0; i<employerList.size(); i++){
                Employer employer = employerList.get(i);
                writer.append(employer.getName()+'\n');

                 claimList.stream().filter((claim)->{
                    return claim.getEmployer() ==employer;
                }).forEach((claim)->{
                     try {
                         String description;
                         if(claim.getDescription().length()>10){
                             description = claim.getDescription().substring(0,10);
                         }else{
                             description = claim.getDescription();
                         }
                         writer.append(claim.getDateTime().toString() +" " +claim.getEmployer()+" "+claim.getClient()
                                 +" "+claim.getStatus()+" "+description+'\n');
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 });
                writer.append('\n');
            }



            writer.flush();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }





    }


    private String createFile(String fileName){
        labelMessage.setText("");
        String path = configuringDirectoryChooser();
        String filePath = path+"/"+fileName+".txt";
        File file = new File(filePath);
        if(file.exists()){
            labelMessage.setText("файл уже существует !");
            return null;
        }
        try {
            if(!file.createNewFile()){
                labelMessage.setText("Ошибка создания файла !");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
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
































