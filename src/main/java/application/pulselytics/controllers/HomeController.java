package application.pulselytics.controllers;

import application.pulselytics.HelloApplication;
import application.pulselytics.classes.BloodPressureLog;
import application.pulselytics.classes.Main;
import application.pulselytics.classes.Tools;
import application.pulselytics.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private VBox addRecordBox;

    @FXML
    private Button addRecordButton;

    @FXML
    private ChoiceBox<String> aveChoice;

    @FXML
    private Label aveDiastolic;

    @FXML
    private Label aveSystolic;

    @FXML
    private Label aveType;

    @FXML
    private Label piAge;

    @FXML
    private Label piBirthday;

    @FXML
    private Label piGender;

    @FXML
    private Label piName;

    @FXML
    private Label piUsername;

    @FXML
    private DatePicker inputDate;

    @FXML
    private Spinner<Integer> inputDiastolic;

    @FXML
    private Spinner<Integer> inputHour;

    @FXML
    private Spinner<Integer> inputMinute;

    @FXML
    private Spinner<Integer> inputSystolic;

    @FXML
    private Label inputTypeLabel;

    private final String[] period = {"Day", "Week", "Month", "Year"};

    private final User currentUser = Main.getCurrentUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize the spinner
        SpinnerValueFactory<Integer> hourFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        inputHour.setValueFactory(hourFactory);

        SpinnerValueFactory<Integer> minFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        inputMinute.setValueFactory(minFactory);

        //initialize the systolic and diastolic input
        SpinnerValueFactory<Integer> systolicFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 300);
        inputSystolic.setValueFactory(systolicFactory);

        SpinnerValueFactory<Integer> diastolicFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200);
        inputDiastolic.setValueFactory(diastolicFactory);

        //Initialize the average choice box
        aveChoice.getItems().addAll(period);

        //personal information data
        piName.setText(currentUser.getName());
        piUsername.setText(currentUser.getUsername());
        piBirthday.setText(Objects.toString(currentUser.getBirthday()));
        piAge.setText(Objects.toString(currentUser.getAge()));
        piGender.setText(currentUser.getGender());

        //initialize the value of the date input
        inputDate.setValue(LocalDate.now());

        //event listener for the systolic and diastolic input
        inputSystolic.valueProperty().addListener((observable) -> {
            if (inputSystolic.getValue() != null && inputDiastolic.getValue() != null) {
                Tools.checkAndDisplayType(inputTypeLabel, Tools.bpTypeIdentifier(inputSystolic.getValue(), inputDiastolic.getValue()));
            } else {
                System.out.println("null");
            }
        });

        inputDiastolic.valueProperty().addListener((observable) -> {
            if (inputSystolic.getValue() != null && inputDiastolic.getValue() != null) {
                Tools.checkAndDisplayType(inputTypeLabel, Tools.bpTypeIdentifier(inputSystolic.getValue(), inputDiastolic.getValue()));
            } else {
                System.out.println("null");
            }
        });

        //add event listener in aveChoice choice box
        aveChoice.setOnAction(this::computeAverage);
    }

    @FXML
    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("scenes/Home.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToHistory(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("scenes/History.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToAnalytics(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("scenes/Analytics.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void closeAddRecord(){
        addRecordBox.setVisible(false);
        addRecordButton.setVisible(true);
        clearInput();
    }

    @FXML
    public void showAddRecord(){
        addRecordBox.setVisible(true);
        addRecordButton.setVisible(false);
    }

    private void clearInput(){
        inputSystolic.getValueFactory().setValue(0);
        inputDiastolic.getValueFactory().setValue(0);
        inputDate.setValue(LocalDate.now());
        inputHour.getValueFactory().setValue(0);
        inputMinute.getValueFactory().setValue(0);
        inputTypeLabel.setStyle("-fx-background-color: darkgrey; -fx-text-fill: black;");
        inputTypeLabel.setText("none");
    }

    public void setAddRecord() {
        if ((!inputSystolic.getValue().equals(0) && !inputDiastolic.getValue().equals(0))
            && (inputSystolic.getValue() > inputDiastolic.getValue())){
            BloodPressureLog log = getLog();
            currentUser.addBloodPressureLog(log);
            closeAddRecord(); 
        }
        else {
            Tools.alert("Invalid Systolic and Diastolic");
        }
    }

    private BloodPressureLog getLog() {
        LocalDateTime localDateTime = LocalDateTime.of(
                inputDate.getValue().getYear(),
                inputDate.getValue().getMonthValue(),
                inputDate.getValue().getDayOfMonth(),
                inputHour.getValue(),
                inputMinute.getValue()
        );

        return new BloodPressureLog(localDateTime, inputSystolic.getValue(), inputDiastolic.getValue(), Tools.bpTypeIdentifier(inputSystolic.getValue(), inputDiastolic.getValue()));
    }

    public void computeAverage(ActionEvent event){
        //"Day", "Week", "Month", "Year"
        if (Objects.equals(aveChoice.getValue(), "Day")){
            int computedSystolic = 0;
            int computedDiastolic = 0;
            int logCount = 0;
            for (LocalDateTime datestamp : currentUser.getBloodPressureLogs().keySet()){
                if (datestamp.getDayOfMonth() == LocalDateTime.now().getDayOfMonth()){
                    logCount++;
                    computedSystolic += currentUser.getBloodPressureLogByDate(datestamp).getSystolic();
                    computedDiastolic += currentUser.getBloodPressureLogByDate(datestamp).getDiastolic();
                }
            }
            int comAveSys = computedSystolic / logCount;
            int comAveDia = computedDiastolic / logCount;

            aveSystolic.setText(String.valueOf(comAveSys));
            aveDiastolic.setText(String.valueOf(computedDiastolic / logCount));
            Tools.checkAndDisplayType(aveType, Tools.bpTypeIdentifier(comAveSys, comAveDia));
        }

        else if (Objects.equals(aveChoice.getValue(), "Week")){
            int computedSystolic = 0;
            int computedDiastolic = 0;
            int logCount = 0;
            for (LocalDateTime datestamp : currentUser.getBloodPressureLogs().keySet()){
                LocalDate weekStart = datestamp.toLocalDate().minusDays(datestamp.getDayOfWeek().getValue() - 1);
                if (ChronoUnit.DAYS.between(weekStart, LocalDate.now()) <= 6){
                    logCount++;
                    computedSystolic += currentUser.getBloodPressureLogByDate(datestamp).getSystolic();
                    computedDiastolic += currentUser.getBloodPressureLogByDate(datestamp).getDiastolic();
                }
            }
            int comAveSys = computedSystolic / logCount;
            int comAveDia = computedDiastolic / logCount;

            aveSystolic.setText(String.valueOf(comAveSys));
            aveDiastolic.setText(String.valueOf(computedDiastolic / logCount));
            Tools.checkAndDisplayType(aveType, Tools.bpTypeIdentifier(comAveSys, comAveDia));
        }

        else if (Objects.equals(aveChoice.getValue(), "Month")){
            int computedSystolic = 0;
            int computedDiastolic = 0;
            int logCount = 0;
            for (LocalDateTime datestamp : currentUser.getBloodPressureLogs().keySet()){
                if (datestamp.getMonth() == LocalDate.now().getMonth()){
                    logCount++;
                    computedSystolic += currentUser.getBloodPressureLogByDate(datestamp).getSystolic();
                    computedDiastolic += currentUser.getBloodPressureLogByDate(datestamp).getDiastolic();
                }
            }
            int comAveSys = computedSystolic / logCount;
            int comAveDia = computedDiastolic / logCount;

            aveSystolic.setText(String.valueOf(comAveSys));
            aveDiastolic.setText(String.valueOf(computedDiastolic / logCount));
            Tools.checkAndDisplayType(aveType, Tools.bpTypeIdentifier(comAveSys, comAveDia));
        }

        else if (Objects.equals(aveChoice.getValue(), "Year")){
            int computedSystolic = 0;
            int computedDiastolic = 0;
            int logCount = 0;
            for (LocalDateTime datestamp : currentUser.getBloodPressureLogs().keySet()){
                if (datestamp.getYear() == LocalDate.now().getYear()){
                    logCount++;
                    computedSystolic += currentUser.getBloodPressureLogByDate(datestamp).getSystolic();
                    computedDiastolic += currentUser.getBloodPressureLogByDate(datestamp).getDiastolic();
                }
            }
            int comAveSys = computedSystolic / logCount;
            int comAveDia = computedDiastolic / logCount;

            aveSystolic.setText(String.valueOf(comAveSys));
            aveDiastolic.setText(String.valueOf(computedDiastolic / logCount));
            Tools.checkAndDisplayType(aveType, Tools.bpTypeIdentifier(comAveSys, comAveDia));
        }
    }
}
