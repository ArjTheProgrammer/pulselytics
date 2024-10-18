package application.pulselytics.controllers;

import application.pulselytics.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AnalyticsController implements Initializable {

    @FXML
    private VBox settingsBox;

    @FXML
    private ChoiceBox<String> analyticsChoice;

    @FXML
    private BarChart<String, Number> barChart;

    XYChart.Series<String, Number> series = new XYChart.Series<>();

    private final String[] period = {"Day", "Week", "Month", "Year"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        analyticsChoice.getItems().addAll(period);

        series.getData().add(new XYChart.Data<>("Hydro", 15));
        series.getData().add(new XYChart.Data<>("Normal", 10));
        series.getData().add(new XYChart.Data<>("Elevated", 15));
        series.getData().add(new XYChart.Data<>("Stage 1", 12));
        series.getData().add(new XYChart.Data<>("Stage 2", 25));
        series.getData().add(new XYChart.Data<>("Hypertensive Crisis", 12));

        barChart.getData().add(series);

        Node node = barChart.lookup(".data0.chart-bar");
        node.setStyle("-fx-bar-fill: #007FFF;");
        node = barChart.lookup(".data1.chart-bar");
        node.setStyle("-fx-bar-fill: lightgreen;");
        node = barChart.lookup(".data2.chart-bar");
        node.setStyle("-fx-bar-fill: yellow;");
        node = barChart.lookup(".data3.chart-bar");
        node.setStyle("-fx-bar-fill: orange;");
        node = barChart.lookup(".data4.chart-bar");
        node.setStyle("-fx-bar-fill: red;");
        node = barChart.lookup(".data5.chart-bar");
        node.setStyle("-fx-bar-fill: maroon;");

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
}
