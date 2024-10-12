package application.pulselytics.controllers;

import application.pulselytics.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class HomeController {
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
    private TextField inputDiastolic;

    @FXML
    private Spinner<?> inputHour;

    @FXML
    private Spinner<?> inputMinute;

    @FXML
    private TextField inputSystolic;

    @FXML
    private Object[] inputArray = {inputSystolic, inputDiastolic, inputDate, inputHour, inputMinute};


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
        inputSystolic.setText("");
        inputDiastolic.setText("");
        inputDate.setValue(LocalDate.now());
    }
}
