package domain_model.members;

import domain_model.DisciplineType;
import domain_model.Subscription;

import domain_model.MemberType;

import java.time.LocalDate;

public class PassiveMember extends Member {
    private MemberType type = MemberType.PASSIVE;

    public PassiveMember(String name, LocalDate birthDate, String email) {
        super(name, birthDate, email);
        setSubscriptionByTypeAndAge();
    }

    public PassiveMember(String name, LocalDate birthDate, String email, Subscription subscription) {
        super(name, birthDate, email, subscription);
    }

    public PassiveMember(String name, LocalDate birthDate, String email, DisciplineType discipline, double subscriptionValue) {
        super(name, birthDate, email, discipline, subscriptionValue);
    }

    public PassiveMember(String name, LocalDate birthDate, String email, DisciplineType discipline) {
        super(name, birthDate, email, discipline);
        setSubscriptionByTypeAndAge();
    }

    @Override
    public MemberType getType() {
        return type;
    }
}
