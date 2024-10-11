package application.pulselytics.classes;

import java.util.Scanner;

public class Tools {
    public static String bpTypeIdentifier (int systolic, int diastolic){
        if (systolic < 90 && diastolic < 60)
        {
            return "Hypotension";
        }
        else if (systolic < 120 && diastolic < 80)
        {
            return "Normal";
        }
        else if (systolic >= 120 && systolic < 130 && diastolic < 80)
        {
            return "Elevated";
        }
        else if ((systolic >= 130 && systolic < 140) || (diastolic >= 80 && diastolic < 90))
        {
            return "Hypertension Stage 1";
        }
        else if ((systolic >= 140 && systolic < 180) || (diastolic >= 90 && diastolic < 120)) {
            return "Hypertension Stage 2";
        }
        else if ((systolic > 180 && diastolic > 120)) {
            return "Hypertensive Crisis";
        }
        else
        {
            return "Invalid Input";
        }
    }
}
