package application.pulselytics.controllers;

import application.pulselytics.HelloApplication;
import application.pulselytics.classes.BloodPressureLog;
import application.pulselytics.classes.Main;
import application.pulselytics.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    @FXML
    private VBox settingsBox;

    @FXML
    private VBox logLayout;

    private final User currentUser = Main.getCurrentUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HashMap<LocalDateTime, BloodPressureLog> userBloodPressureLogs = currentUser.getBloodPressureLogs();

        try {
            for (LocalDateTime datestamp : userBloodPressureLogs.keySet()){
                FXMLLoader  fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(HelloApplication.class.getResource("scenes/LogCard.fxml"));
                HBox logCard = fxmlLoader.load();
                LogCardController logCardController = fxmlLoader.getController();
                logCardController.setData(currentUser, userBloodPressureLogs.get(datestamp), logLayout);
                logLayout.getChildren().add(logCard);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
}
