package domain_model;

import java.time.LocalDate;

public class Result implements Comparable<Result> {
    private String memberEmail;
    private double time;
    private Discipline discipline;
    private LocalDate date;

    public Result(String memberEmail, double time, Discipline discipline, LocalDate date) {
        this.memberEmail = memberEmail;
        this.time = time;
        this.discipline = discipline;
        this.date = date;
    }

    @Override
    public int compareTo(Result result) {
        if (result == null) return 1;
        System.out.println("time = " + time);
        System.out.println("Result " + result);
        int compareTime = Double.compare(time, result.getTime());
        System.out.println("compareTime = " + compareTime);
        if (compareTime == 0) {
            return date.compareTo(result.getDate());
        } else return compareTime;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public double getTime() {
        return time;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Result{" +
                "memberEmail='" + memberEmail + '\'' +
                ", time=" + time +
                ", discipline=" + discipline +
                ", date=" + date +
                '}';
    }
}
