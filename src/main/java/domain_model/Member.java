package domain_model;

public class Member {

    private String name;
    private String birthDate;
    private String email;
    private String discipline;
    private Subscription subscription;

    public Member(String name,
                  String birthDate,
                  String email,
                  String discipline,
                  double subscriptionValue){

        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.discipline = discipline;
        this.subscription = new Subscription(subscriptionValue);
    }

    public String getName(){
        return name;
    }
    public String getBirthDate(){
        return birthDate;
    }
    public String getEmail(){
        return email;
    }
    public String getDiscipline(){
        return discipline;
    }
    public double getSubscriptionValue(){
        return subscription.getCost();
    }

    public Subscription getSubscription() {
        return subscription;
    }
}
