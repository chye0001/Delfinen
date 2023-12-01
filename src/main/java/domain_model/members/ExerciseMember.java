package domain_model.members;

import domain_model.Subscription;

import domain_model.MemberType;

import java.time.LocalDate;

public class ExerciseMember extends Member {
    private MemberType type = MemberType.EXERCISE;

    public ExerciseMember(String name, LocalDate birthDate, String email) {
        super(name, birthDate, email);
        setSubscriptionByTypeAndAge();
    }

    public ExerciseMember(String name, LocalDate birthDate, String email, Subscription subscription) {
        super(name, birthDate, email, subscription);
    }

    public ExerciseMember(String name, LocalDate birthDate, String email, String discipline, double subscriptionValue) {
        super(name, birthDate, email, discipline, subscriptionValue);
    }

    public ExerciseMember(String name, LocalDate birthDate, String email, String discipline) {
        super(name, birthDate, email, discipline);
        setSubscriptionByTypeAndAge();
    }

    @Override
    public MemberType getType() {
        return type;
    }
}
