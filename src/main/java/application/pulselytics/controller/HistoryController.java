package application.pulselytics.controller;

import application.pulselytics.HelloApplication;
import application.pulselytics.model.BloodPressureLog;
import application.pulselytics.model.Main;
import application.pulselytics.model.Tool;
import application.pulselytics.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class HistoryController implements Initializable {

    @FXML
    private Spinner<Integer> inputHistoryMonth;

    @FXML
    private Button dateButton;

    @FXML
    private DatePicker inputHistoryDate;

    @FXML
    private Spinner<Integer> inputHistoryYear;

    @FXML
    private VBox logLayout;

    @FXML
    private VBox settingsBox;

    @FXML
    private Button yearMonthButton;

    @FXML
    private VBox editBox;

    private final User currentUser = Main.getCurrentUser();

    HashMap<LocalDateTime, BloodPressureLog> storage = currentUser.getBloodPressureLogs();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpinnerValueFactory<Integer> yearFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, LocalDate.now().getYear());
        inputHistoryYear.setValueFactory(yearFactory);
        inputHistoryYear.getValueFactory().setValue(LocalDate.now().getYear());

        SpinnerValueFactory<Integer> monthFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12);
        inputHistoryMonth.setValueFactory(monthFactory);
        inputHistoryMonth.getValueFactory().setValue(LocalDate.now().getMonthValue());

        //set value of date
        inputHistoryDate.setValue(LocalDate.now());

        yearMonthButton.setOnAction(event -> {
            System.out.println(inputHistoryDate.getValue());
            displayLogByYearMonth();
        });

        dateButton.setOnAction(event -> {
         displayLogByDate();
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

    public void showEditBox(){
        editBox.setVisible(true);
    }

    private void displayLogByYearMonth(){
        boolean containsYearMonth = storage.keySet().stream()
                .anyMatch(dateTime -> dateTime.getYear() == inputHistoryYear.getValue() && dateTime.getMonthValue() == inputHistoryMonth.getValue());

        if (containsYearMonth) {
            HashMap<LocalDateTime, BloodPressureLog> filteredBp = storage.entrySet().stream()
                    .filter(entry -> entry.getKey().getYear() == inputHistoryYear.getValue() && entry.getKey().getMonthValue() == inputHistoryMonth.getValue())
                    .sorted(Map.Entry.<LocalDateTime, BloodPressureLog>comparingByKey().reversed())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            displayLogs(filteredBp);
        }
        else {
            Tool.alert("Doesn't have any record");
        }
    }

    private void displayLogByDate(){
        if (inputHistoryDate.getValue() != null) {
            boolean containsDate = storage.keySet().stream()
                    .anyMatch(dateTime -> dateTime.getYear() == inputHistoryDate.getValue().getYear() && dateTime.getMonthValue() == inputHistoryDate.getValue().getMonthValue() && dateTime.getDayOfMonth() == inputHistoryDate.getValue().getDayOfMonth());

            if (containsDate && inputHistoryDate.getValue() != null) {
                HashMap<LocalDateTime, BloodPressureLog> filteredBp = storage.entrySet().stream()
                        .filter(entry -> entry.getKey().getYear() == inputHistoryDate.getValue().getYear() && entry.getKey().getMonthValue() == inputHistoryDate.getValue().getMonthValue() && entry.getKey().getDayOfMonth() == inputHistoryDate.getValue().getDayOfMonth())
                        .sorted(Map.Entry.<LocalDateTime, BloodPressureLog>comparingByKey().reversed())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
                displayLogs(filteredBp);
            } else {
                Tool.alert("Doesn't have any record");
            }
        } else {
            Tool.alert("Input a date!");
        }
    }

    private void displayLogs(HashMap<LocalDateTime, BloodPressureLog> filteredBp) {
        logLayout.getChildren().clear();

        filteredBp.forEach((key, value) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(HelloApplication.class.getResource("scenes/LogCard.fxml"));
                HBox logCard = fxmlLoader.load();
                LogCardController logCardController = fxmlLoader.getController();
                logCardController.setData(currentUser, currentUser.getBloodPressureLogs().get(key), logLayout);
                logLayout.getChildren().add(logCard);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
