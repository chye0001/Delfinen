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
        int compareTime = Double.compare(time, result.getTime());
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

    public String getTimeInMSMFormat() {
        return "";
    }

    public String getDiscipline() {
        return discipline.toString();
    }

    public LocalDate getDate() {
        return date;
    }


}
