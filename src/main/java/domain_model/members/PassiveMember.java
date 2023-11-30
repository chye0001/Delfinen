package domain_model.members;

import domain_model.Subscription;

import domain_model.MemberType;

import java.time.LocalDate;

public class PassiveMember extends Member {
    private MemberType type = MemberType.PASSIVE;

    public PassiveMember(String name, LocalDate birthDate, String email, String discipline, Subscription subscription) {
        super(name, birthDate, email, discipline, subscription);
    }

    public PassiveMember(String name, LocalDate birthDate, String email, String discipline, double subscriptionValue) {
        super(name, birthDate, email, discipline, subscriptionValue);
    }

    @Override
    public MemberType getType() {
        return type;
    }
}
