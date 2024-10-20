package application.pulselytics.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashMap;

public class User {
    private String name;
    private String username;
    private String pass;
    private String gender;
    private LocalDate birthday;
    private Period age;
    private HashMap<LocalDateTime, BloodPressureLog> bloodPressureLogs;

    public User(String name, String username, String pass, String gender, LocalDate birthday) {
        this.name = name;
        this.username = username;
        this.pass = pass;
        this.gender = gender;
        this.birthday = birthday;
        this.age = Period.between(birthday, LocalDate.now());
        this.bloodPressureLogs = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getUsername(){
        return username;
    }

    public String getPass() {
        return pass;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getAge(){
        return age.getYears();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPass(String pass){
        this.pass = pass;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public HashMap<LocalDateTime, BloodPressureLog> getBloodPressureLogs() {
        return bloodPressureLogs;
    }

    public void addBloodPressureLog(BloodPressureLog log) {
        bloodPressureLogs.put(log.getDateStamp(), log);
    }

    public void editBloodPressureLog(BloodPressureLog log) {
        bloodPressureLogs.replace(log.getDateStamp(), log);
    }

    public void removeBloodPressureLog(BloodPressureLog log){
        bloodPressureLogs.remove(log.getDateStamp());
    }

    public BloodPressureLog getBloodPressureLogByDate(LocalDateTime date) {
        return bloodPressureLogs.get(date);
    }
}
