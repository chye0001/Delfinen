package domain_model.members;

import domain_model.MemberType;

import domain_model.Subscription;

import java.time.LocalDate;

public class CompetitiveMember extends Member {
    private MemberType type = MemberType.COMPETITIVE;

    public CompetitiveMember(String name, LocalDate birthDate, String email, String discipline, Subscription subscription) {
        super(name, birthDate, email, discipline, subscription);
    }

    public CompetitiveMember(String name, LocalDate birthDate, String email, String discipline, double subscriptionValue) {
        super(name, birthDate, email, discipline, subscriptionValue);
    }

    @Override
    public MemberType getType() {
        return type;
    }

}
