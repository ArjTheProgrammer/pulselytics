package application.pulselytics.classes;

import java.time.LocalDateTime;

public class BloodPressureLog {
    private LocalDateTime dateStamp;
    private int systolic;
    private int diastolic;
    private String type;
    private String note;

    public BloodPressureLog(LocalDateTime dateStamp, int systolic, int diastolic, String type, String note) {
        this.dateStamp = dateStamp;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.type = type;
        this.note = note;
    }

    public LocalDateTime getDateStamp() {
        return dateStamp;
    }

    public int getSystolic() {
        return systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public String getType(){
        return type;
    }

    public String getNote() {
        return note;
    }

    public void setDateStamp(LocalDateTime dateStamp) {
        this.dateStamp = dateStamp;
    }

    public void setSystolic(int systolic){
        this.systolic = systolic;
    }

    public void setDiastolic (int diastolic){
        this.diastolic = diastolic;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
