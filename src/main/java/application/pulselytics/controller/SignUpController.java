package application.pulselytics.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

import application.pulselytics.HelloApplication;
import application.pulselytics.model.Main;
import application.pulselytics.model.Tool;
import application.pulselytics.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignUpController implements Initializable {
    @FXML
    private DatePicker signUpBirthday;

    @FXML
    private ComboBox<String> signUpGender;

    @FXML
    private TextField signUpName;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private TextField signUpUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signUpGender.getItems().addAll("Male", "Female");
    }

    @FXML
    public void switchToSignIn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("scenes/SignIn.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setSignUpUser(ActionEvent event) throws IOException {
        if (Main.getUserStorage().containsKey(signUpUsername.getText())){
            Tool.alert("Username already exist!");
        }
        else {
            if (!Objects.equals(signUpName.getText(), "") && !Objects.equals(signUpUsername.getText(), "") && !Objects.equals(signUpPassword.getText(), "") && !Objects.equals(signUpGender.getValue(), null) && !Objects.equals(signUpBirthday.getValue(), null)){
                User user = new User(signUpName.getText(), signUpUsername.getText(), signUpPassword.getText(), signUpGender.getValue(), signUpBirthday.getValue());
                Main.addUser(user);
                switchToSignIn(event);
            }
            else {
                Tool.alert("Field/Input is empty/null");
            }
        }
    }
}