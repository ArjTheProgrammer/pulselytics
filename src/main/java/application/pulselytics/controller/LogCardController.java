package application.pulselytics.controller;

import application.pulselytics.model.BloodPressureLog;
import application.pulselytics.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;

public class LogCardController {
//    @FXML
//    private VBox logLayout = HistoryController;

    @FXML
    private HBox cardBloodPressure;

    @FXML
    private Label cardDate;

    @FXML
    private Label cardTime;

    @FXML
    private Button cardDelete;

    @FXML
    private Label cardDiastolic;

    @FXML
    private Label cardSystolic;

    @FXML
    private Label cardType;


    public void setData(User user,BloodPressureLog bloodPressureLog, VBox layout){

        cardDate.setText(bloodPressureLog.getDateStamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        cardTime.setText(bloodPressureLog.getDateStamp().format(DateTimeFormatter.ofPattern("HH:mm")));
        cardSystolic.setText(Integer.toString(bloodPressureLog.getSystolic()));
        cardDiastolic.setText(Integer.toString(bloodPressureLog.getDiastolic()));
        cardType.setText(bloodPressureLog.getType());

       cardDelete.setOnAction(event -> {
           handleDelete(bloodPressureLog, user, layout, cardBloodPressure);
       });
    }

   private void handleDelete(BloodPressureLog bloodPressureLog, User user,VBox layout, HBox card){
       user.removeBloodPressureLog(bloodPressureLog);

        layout.getChildren().remove(card);
    }
}
