package application.pulselytics.controller;

import application.pulselytics.HelloApplication;
import application.pulselytics.model.Main;
import application.pulselytics.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.ResourceBundle;

public class AnalyticsController implements Initializable {

    @FXML
    private VBox settingsBox;

    @FXML
    private ChoiceBox<String> analyticsChoice;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private VBox editBox;

    @FXML
    private Label hypoLabel;

    @FXML
    private Label normalLabel;

    @FXML
     private Label elevatedLabel;

    @FXML
    private Label stage1Label;

    @FXML
    private Label stage2Label;

    @FXML
    private Label crisisLabel;


    XYChart.Series<String, Number> series = new XYChart.Series<>();

    private final String[] period = {"Day", "Week", "Month", "Year"};

    private final User currentUser = Main.getCurrentUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        analyticsChoice.getItems().addAll(period);

        series.getData().add(new XYChart.Data<>("Hypotension", 0));
        series.getData().add(new XYChart.Data<>("Normal", 0));
        series.getData().add(new XYChart.Data<>("Elevated", 0));
        series.getData().add(new XYChart.Data<>("Hypertension Stage 1", 0));
        series.getData().add(new XYChart.Data<>("Hypertension Stage 2", 0));
        series.getData().add(new XYChart.Data<>("Hypertensive Crisis", 0));

        barChart.getData().add(series);

        analyticsChoice.setOnAction(this::analyzeChoice);


        barChart.setLegendVisible(false);
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

    public void analyzeChoice(ActionEvent event){
        if (Objects.equals(analyticsChoice.getValue(), "Day")) analyzeByDay();
        else if (Objects.equals(analyticsChoice.getValue(), "Week")) analyzeByWeek();
        else if (Objects.equals(analyticsChoice.getValue(), "Month")) analyzeByMonth();
        else if (Objects.equals(analyticsChoice.getValue(), "Year")) analyzeByYear();
    }

    private void updateSeries(long countHypotension, long countNormal, long countElevated, long countStage1, long countStage2, long countCrisis) {
        series.getData().clear();

        XYChart.Data<String, Number> dataHypotension = new XYChart.Data<>("Hypotension", Math.round(countHypotension));
        dataHypotension.nodeProperty().addListener((obs, oldNode, newNode) -> {
            if (newNode != null) {
                newNode.setStyle("-fx-bar-fill: #007FFF;");
            }
        });

        XYChart.Data<String, Number> dataNormal = new XYChart.Data<>("Normal", Math.round(countNormal));
        dataNormal.nodeProperty().addListener((obs, oldNode, newNode) -> {
            if (newNode != null) {
                newNode.setStyle("-fx-bar-fill: lightgreen;");
            }
        });

        XYChart.Data<String, Number> dataElevated = new XYChart.Data<>("Elevated", Math.round(countElevated));
        dataElevated.nodeProperty().addListener((obs, oldNode, newNode) -> {
            if (newNode != null) {
                newNode.setStyle("-fx-bar-fill: yellow;");
            }
        });

        XYChart.Data<String, Number> dataStage1 = new XYChart.Data<>("Hypertension Stage 1", Math.round(countStage1));
        dataStage1.nodeProperty().addListener((obs, oldNode, newNode) -> {
            if (newNode != null) {
                newNode.setStyle("-fx-bar-fill: orange;");
            }
        });

        XYChart.Data<String, Number> dataStage2 = new XYChart.Data<>("Hypertension Stage 2", Math.round(countStage2));
        dataStage2.nodeProperty().addListener((obs, oldNode, newNode) -> {
            if (newNode != null) {
                newNode.setStyle("-fx-bar-fill: red;");
            }
        });

        XYChart.Data<String, Number> dataCrisis = new XYChart.Data<>("Hypertensive Crisis", Math.round(countCrisis));
        dataCrisis.nodeProperty().addListener((obs, oldNode, newNode) -> {
            if (newNode != null) {
                newNode.setStyle("-fx-bar-fill: maroon;");
            }
        });

        series.getData().add(dataHypotension);
        series.getData().add(dataNormal);
        series.getData().add(dataElevated);
        series.getData().add(dataStage1);
        series.getData().add(dataStage2);
        series.getData().add(dataCrisis);
    }

    public void analyzeByDay(){
        long countHypotension = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypotension"))
                .count();


        long countNormal = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Normal"))
                .count();


        long countElevated = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Elevated"))
                .count();


        long countStage1 = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypertension Stage 1"))
                .count();


        long countStage2 = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypertension Stage 2"))
                .count();



        long countCrisis = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypertensive Crisis"))
                .count();

        displayCount(countHypotension, countNormal, countElevated, countStage1, countStage2, countCrisis);
    }


    public void analyzeByWeek(){
        long countHypotension = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> ChronoUnit.DAYS.between(entry.getKey().toLocalDate().minusDays(entry.getKey().getDayOfWeek().getValue() - 1),  LocalDate.now()) <= 6)
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypotension"))
                .count();


        long countNormal = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> ChronoUnit.DAYS.between(entry.getKey().toLocalDate().minusDays(entry.getKey().getDayOfWeek().getValue() - 1),  LocalDate.now()) <= 6)
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Normal"))
                .count();


        long countElevated = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> ChronoUnit.DAYS.between(entry.getKey().toLocalDate().minusDays(entry.getKey().getDayOfWeek().getValue() - 1),  LocalDate.now()) <= 6)
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Elevated"))
                .count();


        long countStage1 = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> ChronoUnit.DAYS.between(entry.getKey().toLocalDate().minusDays(entry.getKey().getDayOfWeek().getValue() - 1),  LocalDate.now()) <= 6)
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypertension Stage 1"))
                .count();


        long countStage2 = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> ChronoUnit.DAYS.between(entry.getKey().toLocalDate().minusDays(entry.getKey().getDayOfWeek().getValue() - 1),  LocalDate.now()) <= 6)
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypertension Stage 2"))
                .count();



        long countCrisis = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> ChronoUnit.DAYS.between(entry.getKey().toLocalDate().minusDays(entry.getKey().getDayOfWeek().getValue() - 1),  LocalDate.now()) <= 6)
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypertensive Crisis"))
                .count();

        displayCount(countHypotension, countNormal, countElevated, countStage1, countStage2, countCrisis);
    }

    public void analyzeByMonth(){
        long countHypotension = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getMonth() == LocalDate.now().getMonth())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypotension"))
                .count();


        long countNormal = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getMonth() == LocalDate.now().getMonth())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Normal"))
                .count();


        long countElevated = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getMonth() == LocalDate.now().getMonth())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Elevated"))
                .count();


        long countStage1 = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getMonth() == LocalDate.now().getMonth())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypertension Stage 1"))
                .count();


        long countStage2 = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getMonth() == LocalDate.now().getMonth())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypertension Stage 2"))
                .count();



        long countCrisis = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getMonth() == LocalDate.now().getMonth())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypertensive Crisis"))
                .count();

        displayCount(countHypotension, countNormal, countElevated, countStage1, countStage2, countCrisis);
    }

    public void analyzeByYear(){
        long countHypotension = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getYear() == LocalDate.now().getYear())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypotension"))
                .count();


        long countNormal = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getYear() == LocalDate.now().getYear())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Normal"))
                .count();


        long countElevated = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getYear() == LocalDate.now().getYear())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Elevated"))
                .count();


        long countStage1 = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getYear() == LocalDate.now().getYear())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypertension Stage 1"))
                .count();


        long countStage2 = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getYear() == LocalDate.now().getYear())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypertension Stage 2"))
                .count();



        long countCrisis = currentUser.getBloodPressureLogs().entrySet().stream()
                .filter(entry -> entry.getKey().getYear() == LocalDate.now().getYear())
                .filter(entry -> Objects.equals(entry.getValue().getType(), "Hypertensive Crisis"))
                .count();

        displayCount(countHypotension, countNormal, countElevated, countStage1, countStage2, countCrisis);
    }

    private void displayCount(long countHypotension, long countNormal, long countElevated, long countStage1, long countStage2, long countCrisis) {
        hypoLabel.setText(Integer.toString(Math.round(countHypotension)));
        normalLabel.setText(Integer.toString(Math.round(countNormal)));
        elevatedLabel.setText(Integer.toString(Math.round(countElevated)));
        stage1Label.setText(Integer.toString(Math.round(countStage1)));
        stage2Label.setText(Integer.toString(Math.round(countStage2)));
        crisisLabel.setText(Integer.toString(Math.round(countCrisis)));

        updateSeries(countHypotension, countNormal, countElevated, countStage1, countStage2, countCrisis);
    }
}
