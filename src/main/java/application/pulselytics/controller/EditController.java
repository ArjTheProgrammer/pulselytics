package application.pulselytics.controller;

import application.pulselytics.model.Main;
import application.pulselytics.model.Tool;
import application.pulselytics.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
        clear();
        editBox.setVisible(false);
    }

    public void setEdit(){
        if (!Objects.equals(editNameField.getText(), "") || !Objects.equals(editUsernameField.getText(), "") || !Objects.equals(editPasswordField.getText(), "")){
            if (!Objects.equals(editNameField.getText(), "")){
                user.setName(editNameField.getText());
            }

            if (!Objects.equals(editUsernameField.getText(), "")){
                user.setUsername(editNameField.getText());
            }

            if (!Objects.equals(editPasswordField.getText(), "")){
                user.setPass(editNameField.getText());
            }
            clear();
            closeEditBox();
        }
        else {
            Tool.alert("No edit");
        }
    }

    private void clear(){
        editNameField.setText("");
        editPasswordField.setText("");
        editUsernameField.setText("");
    }
}
