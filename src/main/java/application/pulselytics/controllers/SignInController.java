package application.pulselytics.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import application.pulselytics.HelloApplication;
import application.pulselytics.classes.Main;
import application.pulselytics.classes.Tools;
import application.pulselytics.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController {

    @FXML
    private PasswordField signInPassword;

    @FXML
    private TextField signInUsername;

    @FXML
    public void switchToSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("scenes/SignUp.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("scenes/Home.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void validateUser(ActionEvent event) throws IOException{
        HashMap<String, User> userStorage = Main.getUserStorage();

        if (userStorage.containsKey(signInUsername.getText())){
            User user = Main.getUser(signInUsername.getText());
            if (user.getPass().equals(signInPassword.getText())){
                Main.setCurrentUser(user.getUsername());
                switchToHome(event);
            }
            else{
                Tools.alert("Wrong password");
                signInPassword.setText("");

                for (String username : Main.getUserStorage().keySet()){
                    System.out.print(username + " ");
                }
            }
        }
        else {
            Tools.alert("Username not found");
            signInUsername.setText("");
            signInPassword.setText("");
        }
    }
}