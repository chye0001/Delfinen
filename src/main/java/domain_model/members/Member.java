package domain_model.members;

import domain_model.MemberType;
import domain_model.Subscription;

import java.time.LocalDate;
import java.time.Period;

public abstract class Member {

    private String name;
    private LocalDate birthDate;
    private String email;
    private String discipline;
    private Subscription subscription;

    public Member(String name, LocalDate birthDate, String email) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
    }

    public Member(String name, LocalDate birthDate, String email, Subscription subscription) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.subscription = subscription;
    }

    public Member(String name,
                  LocalDate birthDate,
                  String email,
                  String discipline,
                  double subscriptionCost) {

        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.discipline = discipline;
        this.subscription = new Subscription(subscriptionCost);
    }

    public Member(String name,
                  LocalDate birthDate,
                  String email,
                  String discipline) {

        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.discipline = discipline;
    }


    //used for setting the subscription cost within the constructor of the sub-classes
    public void setSubscriptionByTypeAndAge() {
        if (getType() == MemberType.PASSIVE) {
            this.subscription = new Subscription(getType().getCost());
        } else {
            if (getAge() < 18) {
                this.subscription = new Subscription(getType().getCost());
            } else if (getAge() < 60) {
                double seniorCost = getType().getCost() + 600;
                this.subscription = new Subscription(seniorCost);
            } else {
                double pensionerCost = (getType().getCost() + 600) * 0.75;
                this.subscription = new Subscription(pensionerCost);
            }
        }
    }

    public double getSubscriptionCost() {
        return subscription.getPrice();
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getDiscipline() {
        return discipline;
    }

    public boolean isPaid() {
        return subscription.isPaid();
    }

    public abstract MemberType getType();

    public String getLastPaymentDate() {
        if (subscription.getLastPayment() == null) {
            return "-";

        } else {
            return subscription.getLastPayment().toString();
        }

    }

    public String getNextPaymentDate() {
        if (subscription.getNextPayment() == null) {
            return "-";

        } else {
            return subscription.getNextPayment().toString();
        }
    }

    public double getSubscriptDebt() {
        return subscription.getDebt();
    }


    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public void pay() {
        subscription.pay();
    }
}
