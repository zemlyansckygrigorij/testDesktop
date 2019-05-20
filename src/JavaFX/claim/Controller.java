package JavaFX.claim;

import Java.objects.Claim;
import Java.objects.Client;
import Java.objects.Employer;
import Java.objects.Status;
import Java.repositories.ClaimRepository;
import Java.repositories.ClientRepository;
import Java.repositories.EmployerRepository;
import Java.repositories.StatusRepository;
import JavaFX.FormControl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Connection.ConnectionClass;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;



public class Controller {
    public Button buttonListClaims;
    public DatePicker dataPicker;
    public TextField timeTextField;
    public TextField idTextField;
    public ComboBox employerTextField;
    public ComboBox clientTextField;
    public Label labelDate;
    public Label labelTime;
    public Label labelclient;
    public Label labelemployer;
    public Label labelStatus;

    public TextArea descriptionTextArea;
    public ComboBox statusTextFiled;
    public Label labelDescription;
    public Label labelMessage;
    Connection connection;
	Claim selectClaim = null;
	@FXML
    AnchorPane mainForm;

    @FXML
    public  void update(){//обновить полученную строку
        selectClaim = FormControl.getSelectClaim();
        if(selectClaim ==null)return;
        idTextField.setText(String.valueOf(selectClaim.getId()));
        dataPicker.setValue(LocalDate.parse(selectClaim.getDateTime().substring(0,10)));
        timeTextField.setText(selectClaim.getDateTime().substring(11,19) );
        employerTextField.setValue(selectClaim.getEmployer())   ;
        clientTextField.setValue(selectClaim.getClient() );
        statusTextFiled.setValue(selectClaim.getStatus() );
        descriptionTextArea.setText(selectClaim.getDescription() );
    }

    // инициализация приложения
    @FXML
    public void initialize() {
        FormControl.setController(this);

        //если id не передан то создаем заявку
        if (FormControl.getSelectClaim()!=null) {
            update();

        }else{
			// Получить дату время
			LocalDateTime dateTime = LocalDateTime.now();

			//вставка текущей даты в поле "Дата заявки"
			dataPicker.setValue(dateTime.toLocalDate());

			//вставка текущей времени в поле "Время заявки"
			timeTextField.setText(dateTime.toLocalTime().toString().substring(0, 8));
		}

      //  вставка обьектов в списки  "Отправитель" и "Исполнитель" "Статус"
        employerTextField.getItems().addAll(EmployerRepository.getEmployerList());
        clientTextField.getItems().addAll(ClientRepository.getClientList());
        statusTextFiled.getItems().addAll(StatusRepository.getStatusList());

    }

    public void sendData() {


        String description = descriptionTextArea.getText();
        Client client = (Client) clientTextField.getValue();
        Employer employer = (Employer) employerTextField.getValue();
        Status status = (Status)statusTextFiled.getValue();
        //текущая время дата
        LocalDateTime dateTime = LocalDateTime.now();

        String timeNow = dateTime.toLocalTime().toString().substring(0, 8);

        //время-дата для вставки в MySQL
        String dateTimeClaim = dataPicker.getValue()+" "+timeNow;

        //соединение с сервером
        connection = ConnectionClass.getConnection();

/************ Проверки *************/
        //если описание заявки не введено
        if (description.equals("")) {
            labelMessage.setText("Введите текст заявки");
            return;
        }

//если исполнитель не выбран
        if (employer == null) {
             labelMessage.setText("Выберите исполнителя");
             return;
        }

//если отправитель не выбран
        if (client == null) {
             labelMessage.setText("Выберите отправителя");
             return;
        }

        //если статус не выбран
        if (status == null) {
             labelMessage.setText("Выберите отправителя");
             return;
        }

//работа с таблицей
        try (Statement statement = connection.createStatement()) {
			if (selectClaim == null) {


                ArrayList<Claim> claimList =  ClaimRepository.getClaimList();
                int maxId = claimList.stream().max((claim1, claim2) -> Integer.compare( claim1.getId(), claim2.getId())).get().getId();

			   // вставка
				String sql = "insert into claims (date_time,id_employer,id_client,id_status,description) values('"
                        +  dateTimeClaim +  "','" + employer.getId() + "','" + client.getId() + "','" + status.getId() + "','" + description + "')";
				statement.executeUpdate(sql);

				//вставка в хранилище
                ClaimRepository.getClaimList().add(new Claim(maxId +1 , dateTimeClaim, employer, client, status, description));

		    }else{
			    //обновление
				String sql = "UPDATE claims SET date_time = '"
                        +  dateTimeClaim +  "', id_employer = '" + employer.getId() + "',id_client =  '"
                        + client.getId() + "',id_status = '" + status.getId() + "', description ='"
                        + description + "' WHERE claims.id_claim = "+FormControl.getSelectClaim().getId()+";";
				statement.executeUpdate(sql);
				selectClaim.setEmployer(employer);
				selectClaim.setClient(client);
				selectClaim.setStatus(status);
				selectClaim.setDescription(description);
                selectClaim=null;
				FormControl.setSelectClaim(null);
		    }
        } catch (SQLException e) {
            e.printStackTrace();
        }



// обнуление полей
         descriptionTextArea.setText("");
         clientTextField.setValue(null);
         employerTextField.setValue(null);
         statusTextFiled.setValue(null);

    }


    public void getListClaims(){
        //закрываем данное окно
        FormControl.getClaimForm().close();
        //открываем окно "список заявок"
        new FormControl().runListClaimsForm();

    }
}
































