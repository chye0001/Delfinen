package domain_model.members;

import domain_model.Subscription;

import java.time.LocalDate;
import java.time.Period;

public abstract class Member {

    private String name;
    private LocalDate birthDate;
    private String email;
    private String discipline;
    private Subscription subscription;

    public Member(String name, LocalDate birthDate, String email, String discipline, Subscription subscription) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.discipline = discipline;
        this.subscription = subscription;
    }

    public Member(String name,
                  LocalDate birthDate,
                  String email,
                  String discipline,
                  double subscriptionCost){

        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.discipline = discipline;
        this.subscription = new Subscription(subscriptionCost);
    }

    public String getName(){
        return name;
    }
    public LocalDate getBirthDate(){
        return birthDate;
    }
    public String getEmail(){
        return email;
    }
    public String getDiscipline(){
        return discipline;
    }
    public double getSubscriptionCost(){
        return subscription.getPrice();
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public abstract String getType();

    public int getAge() {
        return Period.between(birthDate,LocalDate.now()).getYears();
    }
}
