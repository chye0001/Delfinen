package domain_model;

import java.time.LocalDate;

public class Result {
    private String memberEmail;
    private double time;
    private LocalDate date;

    public Result(String memberEmail, double time, LocalDate date) {
        this.memberEmail = memberEmail;
        this.time = time;
        this.date = date;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public double getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }
}
