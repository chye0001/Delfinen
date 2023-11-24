package domain_model.Members;

public class ExerciseMember extends Member {
    private String type = "Exercise";
    public ExerciseMember(String name, String birthDate, String email, String discipline, double subscriptionValue) {
        super(name, birthDate, email, discipline, subscriptionValue);
    }

    @Override
    public String getType() {
        return type;
    }
}
