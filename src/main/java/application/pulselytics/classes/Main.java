package application.pulselytics.classes;


import java.time.LocalDate;
import java.util.HashMap;

public class Main {
    private static HashMap<String, User> userStorage = new HashMap<>();

    public static HashMap<String, User> getUserStorage() {
        return userStorage;
    }

    public static User getUser(String username){
        return userStorage.get(username);
    }

    public static void addUser(User user){
        userStorage.put(user.getUsername(), user);
    }

    public static void main (String[] args){
        LocalDate birthday = LocalDate.parse("2005-03-27");
        User admin = new User("Rj", "ArjTheProgrammer", "rjpogi123", "Male", birthday);

        Main.addUser(admin);
    }
}
