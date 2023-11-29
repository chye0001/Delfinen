package domain_model.members;

import java.time.LocalDate;

public class ExerciseMember extends Member {
    private String type = "Exercise";
    public ExerciseMember(String name, LocalDate birthDate, String email, String discipline, double subscriptionValue) {
        super(name, birthDate, email, discipline, subscriptionValue);
    }

    @Override
    public String getType() {
        return type;
    }
}
