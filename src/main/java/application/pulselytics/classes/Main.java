package application.pulselytics.classes;


import application.pulselytics.HelloApplication;
import javafx.application.Application;

import java.time.LocalDate;
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
        User admin = new User("Rj", "ArjTheProgrammer", "rjpogi123", "Male", birthday);

        Main.addUser(admin);

        Application.launch(HelloApplication.class, args);
    }
}
