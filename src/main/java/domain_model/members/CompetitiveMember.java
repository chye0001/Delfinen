package domain_model.members;

import domain_model.MemberType;

import domain_model.Result;
import domain_model.Subscription;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitiveMember extends Member {
    private MemberType type = MemberType.COMPETITIVE;
    private ArrayList<Result> backstrokeResults = new ArrayList<>();
    private ArrayList<Result> breaststrokeResults = new ArrayList<>();
    private ArrayList<Result> butterflyResults = new ArrayList<>();
    private ArrayList<Result> crawlResults = new ArrayList<>();

    public CompetitiveMember(String name, LocalDate birthDate, String email) {
        super(name, birthDate, email);
        setSubscriptionByTypeAndAge();
    }

    public CompetitiveMember(String name, LocalDate birthDate, String email, Subscription subscription) {
        super(name, birthDate, email, subscription);
    }

    public CompetitiveMember(String name, LocalDate birthDate, String email, String discipline, double subscriptionValue) {
        super(name, birthDate, email, discipline, subscriptionValue);
    }

    public CompetitiveMember(String name, LocalDate birthDate, String email, String discipline) {
        super(name, birthDate, email, discipline);
        setSubscriptionByTypeAndAge();
    }

    @Override
    public MemberType getType() {
        return type;
    }

}
