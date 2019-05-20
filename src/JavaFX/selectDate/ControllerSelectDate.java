package JavaFX.selectDate;
import JavaFX.FormControl;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;


public class ControllerSelectDate {
    @FXML
    private DatePicker dataPickerBegin;
	@FXML private DatePicker dataPickerEnd;
    @FXML private Label labelMessage;
    public  void update(){

       
    }

    // инициализация приложения
    @FXML
    public void initialize() {
   

    }

    public void sendData() {

/************ Проверки *************/
        //даты не введены
        if (dataPickerBegin.getValue()==null) {
            labelMessage.setText("Введите начальную дату");
            return;
        }
//если описание заявки не введено
        if (dataPickerEnd.getValue()==null) {
            labelMessage.setText("Введите конечную дату");
            return;
        }

        FormControl.setDataBegin(dataPickerBegin.getValue());
        FormControl.setDataEnd(dataPickerEnd.getValue());
        FormControl.getSelectDateForm().close();

    }


 
}
































