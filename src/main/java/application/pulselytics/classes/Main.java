package application.pulselytics.classes;


import application.pulselytics.HelloApplication;
import javafx.application.Application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class  Main {
    private static HashMap<String, User> userStorage = new HashMap<>();
    private static User currentUser;

    public static HashMap<String, User> getUserStorage() {
        return userStorage;
    }

    public static User getUser(String username){
        return userStorage.get(username);
    }

    public static void addUser(User user){
        userStorage.put(user.getUsername(), user);
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    public static void setCurrentUser(String username){
        currentUser = getUser(username);
    }

    public static void main (String[] args){
        LocalDate birthday = LocalDate.parse("2005-03-27");
        User admin = new User("Jose Neil Silagan Jr.", "ArjTheProgrammer", "rjpogi123", "Male", birthday);

        Main.addUser(admin);

        Main.setCurrentUser(admin.getUsername());
        User currentUser = Main.getCurrentUser();

        addDummyData(currentUser);

        for (Month month : Month.values()){
            for (LocalDateTime datestamp : currentUser.getBloodPressureLogs().keySet()){
                if (month == datestamp.getMonth()){
                    BloodPressureLog bp = currentUser.getBloodPressureLogByDate(datestamp);
                    System.out.printf("%s | %d | %d | %s", bp.getDateStamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), bp.getSystolic(), bp.getDiastolic(), bp.getType());
                    System.out.println();
                }
            }
        }

        Application.launch(HelloApplication.class, args);
    }

    public static void addDummyData(User currentUser){
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 1, 1, 8, 0), 90, 60, Tools.bpTypeIdentifier(90, 60)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 1, 2, 12, 0), 120, 80, Tools.bpTypeIdentifier(120, 80)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 1, 3, 18, 0), 125, 85, Tools.bpTypeIdentifier(125, 85)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 2, 1, 8, 0), 130, 90, Tools.bpTypeIdentifier(130, 90)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 2, 2, 12, 0), 140, 95, Tools.bpTypeIdentifier(140, 95)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 2, 3, 18, 0), 180, 110, Tools.bpTypeIdentifier(180, 110)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 3, 1, 8, 0), 115, 75, Tools.bpTypeIdentifier(115, 75)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 3, 2, 12, 0), 122, 82, Tools.bpTypeIdentifier(122, 82)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 3, 3, 18, 0), 135, 90, Tools.bpTypeIdentifier(135, 90)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 4, 1, 8, 0), 145, 95, Tools.bpTypeIdentifier(145, 95)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 4, 2, 12, 0), 185, 115, Tools.bpTypeIdentifier(185, 115)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 4, 3, 18, 0), 120, 80, Tools.bpTypeIdentifier(120, 80)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 5, 1, 8, 0), 110, 70, Tools.bpTypeIdentifier(110, 70)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 5, 2, 12, 0), 125, 85, Tools.bpTypeIdentifier(125, 85)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 5, 3, 18, 0), 130, 90, Tools.bpTypeIdentifier(130, 90)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 6, 1, 8, 0), 140, 95, Tools.bpTypeIdentifier(140, 95)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 6, 2, 12, 0), 180, 110, Tools.bpTypeIdentifier(180, 110)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 7, 1, 8, 0), 115, 75, Tools.bpTypeIdentifier(115, 75)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 7, 2, 12, 0), 122, 82, Tools.bpTypeIdentifier(122, 82)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 7, 3, 18, 0), 135, 90, Tools.bpTypeIdentifier(135, 90)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 8, 1, 8, 0), 145, 95, Tools.bpTypeIdentifier(145, 95)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 8, 2, 12, 0), 185, 115, Tools.bpTypeIdentifier(185, 115)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 8, 3, 18, 0), 120, 80, Tools.bpTypeIdentifier(120, 80)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 9, 1, 8, 0), 110, 70, Tools.bpTypeIdentifier(110, 70)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 9, 2, 12, 0), 125, 85, Tools.bpTypeIdentifier(125, 85)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 9, 3, 18, 0), 130, 90, Tools.bpTypeIdentifier(130, 90)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 10, 1, 8, 0), 140, 95, Tools.bpTypeIdentifier(140, 95)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 10, 2, 12, 0), 180, 110, Tools.bpTypeIdentifier(180, 110)));
        currentUser.addBloodPressureLog(new BloodPressureLog(LocalDateTime.of(2023, 10, 3, 18, 0), 115, 75, Tools.bpTypeIdentifier(115, 75)));
    }
}
