package domain_model.members;

import domain_model.Result;
import domain_model.Subscription;

import java.time.LocalDate;

public class CompetitiveMember extends Member {
    private MemberType type = MemberType.COMPETITIVE;
    private Result result;

    public CompetitiveMember(String name, LocalDate birthDate, String email, String discipline, Subscription subscription) {
        super(name, birthDate, email, discipline, subscription);
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

    @Override
    public Result getResult() {
        return result;
    }

}
