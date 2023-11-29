package domain_model.members;

import domain_model.MemberType;

import java.time.LocalDate;

public class ExerciseMember extends Member {
    private MemberType type = MemberType.EXERCISE;
    public ExerciseMember(String name, LocalDate birthDate, String email, String discipline, double subscriptionValue) {
        super(name, birthDate, email, discipline, subscriptionValue);
    }

    @Override
    public MemberType getType() {
        return type;
    }
}
