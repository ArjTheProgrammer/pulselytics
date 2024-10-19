package application.pulselytics.controllers;

import application.pulselytics.classes.Main;
import application.pulselytics.classes.Tools;
import application.pulselytics.classes.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class EditController {

    @FXML
    private VBox editBox;

    @FXML
    private TextField editNameField;

    @FXML
    private TextField editPasswordField;

    @FXML
    private TextField editUsernameField;

    User user = Main.getCurrentUser();

    public void closeEditBox(){
        editBox.setVisible(false);
    }

    public void setEdit(){
        if (!Objects.equals(editNameField.getText(), "") || !Objects.equals(editUsernameField.getText(), "") || !Objects.equals(editPasswordField.getText(), "")){
            if (!Objects.equals(editNameField.getText(), "")){
                user.setName(editNameField.getText());
            }

            if (!Objects.equals(editUsernameField.getText(), "")){
                user.setName(editNameField.getText());
            }

            if (!Objects.equals(editPasswordField.getText(), "")){
                user.setName(editNameField.getText());
            }
            closeEditBox();
        }
        else {
            Tools.alert("No edit");
        }
    }
}
