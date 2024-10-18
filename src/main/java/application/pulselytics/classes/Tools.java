package application.pulselytics.classes;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;


public class Tools {
    public static String bpTypeIdentifier (int systolic, int diastolic){
        if (systolic >= 180 || diastolic >= 120) {
            return "Hypertensive Crisis";
        } else if (systolic >= 140 || diastolic >= 90) {
            return "Hypertension Stage 2";
        } else if (systolic >= 130 || diastolic > 80) {
            return "Hypertension Stage 1";
        } else if (systolic >= 120 && diastolic < 80) {
            return "Elevated";
        } else if (systolic >= 90 && diastolic < 80) {
            return "Normal";
        } else {
            return "Hypotension";
        }
    }

    public static void checkAndDisplayType(Label label, String type) {
        switch (type) {
            case "Hypotension": {
                label.setStyle("-fx-background-color: #007FFF; -fx-text-fill: white;");
                label.setText(type);
                break;
            }
            case "Normal": {
                label.setStyle("-fx-background-color: lightgreen; -fx-text-fill: black;");
                label.setText(type);
                break;
            }
            case "Elevated": {
                label.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
                label.setText(type);
                break;
            }
            case "Hypertension Stage 1": {
                label.setStyle("-fx-background-color: orange; -fx-text-fill: black;");
                label.setText(type);
                break;
            }
            case "Hypertension Stage 2": {
                label.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                label.setText(type);
                break;
            }
            case "Hypertensive Crisis": {
                label.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
                label.setText(type);
                break;
            }
            default: {
                label.setStyle("-fx-background-color: darkgrey; -fx-text-fill: white;");
                label.setText("none");
                break;
            }
        }
    }

    public static void alert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
