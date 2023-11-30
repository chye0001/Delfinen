package domain_model.members;

import domain_model.Subscription;

import java.time.LocalDate;

public class ExerciseMember extends Member {
    private MemberType type = MemberType.EXERCISE;

    public ExerciseMember(String name, LocalDate birthDate, String email, String discipline, Subscription subscription) {
        super(name, birthDate, email, discipline, subscription);
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
