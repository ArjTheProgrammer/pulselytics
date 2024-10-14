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
import java.util.OptionalDouble;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private VBox settingsBox;

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

    public void switchToSignIn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("scenes/SignIn.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showSettings(){
        settingsBox.setVisible(!settingsBox.isVisible());
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

        if (aveChoice.getValue().equals("Day")) {
            OptionalDouble avgSys = currentUser.getBloodPressureLogs().entrySet().stream()
                    .filter(entry -> entry.getKey().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                    .mapToInt(entry -> entry.getValue().getSystolic())
                    .average();

            OptionalDouble avgDia = currentUser.getBloodPressureLogs().entrySet().stream()
                    .filter(entry -> entry.getKey().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                    .mapToInt(entry -> entry.getValue().getDiastolic())
                    .average();

            displaySysDiaAve(avgSys, avgDia);
        }

        else if (aveChoice.getValue().equals("Week")){
            OptionalDouble avgSys = currentUser.getBloodPressureLogs().entrySet().stream()
                    .filter(entry -> ChronoUnit.DAYS.between(entry.getKey().toLocalDate().minusDays(entry.getKey().getDayOfWeek().getValue() - 1),  LocalDate.now()) <= 6)
                    .mapToInt(entry -> entry.getValue().getSystolic())
                    .average();

            OptionalDouble avgDia = currentUser.getBloodPressureLogs().entrySet().stream()
                    .filter(entry -> ChronoUnit.DAYS.between(entry.getKey().toLocalDate().minusDays(entry.getKey().getDayOfWeek().getValue() - 1), LocalDate.now()) <= 6)
                    .mapToInt(entry -> entry.getValue().getDiastolic())
                    .average();

            displaySysDiaAve(avgSys, avgDia);
        }

        else if (Objects.equals(aveChoice.getValue(), "Month")){
            OptionalDouble avgSys = currentUser.getBloodPressureLogs().entrySet().stream()
                    .filter(entry -> entry.getKey().getMonth() == LocalDate.now().getMonth())
                    .mapToInt(entry -> entry.getValue().getSystolic())
                    .average();

            OptionalDouble avgDia = currentUser.getBloodPressureLogs().entrySet().stream()
                    .filter(entry -> entry.getKey().getMonth() == LocalDate.now().getMonth())
                    .mapToInt(entry -> entry.getValue().getDiastolic())
                    .average();

            displaySysDiaAve(avgSys, avgDia);
        }

        else if (Objects.equals(aveChoice.getValue(), "Year")){
            OptionalDouble avgSys = currentUser.getBloodPressureLogs().entrySet().stream()
                    .filter(entry -> entry.getKey().getYear() == LocalDate.now().getYear())
                    .mapToInt(entry -> entry.getValue().getSystolic())
                    .average();

            OptionalDouble avgDia = currentUser.getBloodPressureLogs().entrySet().stream()
                    .filter(entry -> entry.getKey().getYear() == LocalDate.now().getYear())
                    .mapToInt(entry -> entry.getValue().getDiastolic())
                    .average();

            displaySysDiaAve(avgSys, avgDia);
        }
    }

    private void displaySysDiaAve(OptionalDouble avgSys, OptionalDouble avgDia) {
        if (avgSys.isPresent() && avgDia.isPresent()) {
            aveSystolic.setText(String.valueOf(Math.round(avgSys.getAsDouble())));
            aveDiastolic.setText(String.valueOf(Math.round(avgDia.getAsDouble())));
            Tools.checkAndDisplayType(aveType, Tools.bpTypeIdentifier((int) avgSys.getAsDouble(), (int) avgDia.getAsDouble()));
        }
    }
}
