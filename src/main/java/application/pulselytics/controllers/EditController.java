package application.pulselytics.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class EditController {

    @FXML
    private VBox editBox;

        public void closeEditBox(){
        editBox.setVisible(false);
    }
}
