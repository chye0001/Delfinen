package domain_model;

import java.time.LocalDate;

public class Result {
    private String memberEmail;
    private double time;
    private String discipline;
    private LocalDate date;

    public Result(String memberEmail, double time, String discipline, LocalDate date) {
        this.memberEmail = memberEmail;
        this.time = time;
        this.discipline = discipline;
        this.date = date;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public double getTime() {
        return time;
    }

    public String getTimeInMSMFormat(){
        return "";
    }

    public String getDiscipline() {
        return discipline;
    }

    public LocalDate getDate() {
        return date;
    }
}
