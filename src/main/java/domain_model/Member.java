package domain_model;

public abstract class Member {
    private String name;
    private String email;
    private int age;
    private String discipline;
    private Subscription subscription;

    public Member(String name, String email, int age, String discipline, Subscription subscription) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.discipline = discipline;
        this.subscription = subscription;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getDiscipline() {
        return discipline;
    }

    public Subscription getSubscription() {
        return subscription;
    }
}
