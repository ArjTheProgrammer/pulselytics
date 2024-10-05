package application.pulselytics.controllers;

import java.io.IOException;
import java.util.Objects;

import application.pulselytics.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void switchToSignIn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("scenes/SignIn.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("scenes/SignUp.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}