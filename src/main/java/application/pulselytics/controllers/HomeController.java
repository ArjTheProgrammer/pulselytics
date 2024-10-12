package application.pulselytics.controllers;

import application.pulselytics.HelloApplication;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private VBox addRecordBox;

    @FXML
    private Button addRecordButton;

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

    private User currentUser = Main.getCurrentUser();

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

        //personal information data
        piName.setText(currentUser.getName());
        piUsername.setText(currentUser.getUsername());
        piBirthday.setText(Objects.toString(currentUser.getBirthday()));
        piAge.setText(Objects.toString(currentUser.getAge()));
        piGender.setText(currentUser.getGender());

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
        inputDate.setValue(null);
        inputHour.getValueFactory().setValue(0);
        inputMinute.getValueFactory().setValue(0);
    }
}
