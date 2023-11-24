package domain_model;

import java.time.LocalDate;

public class Result {
    private Member member;
    private double time;
    private LocalDate date;

    public Result(Member member, double time, LocalDate date) {
        this.member = member;
        this.time = time;
        this.date = date;
    }

    public Member getMember() {
        return member;
    }

    public double getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }
}
