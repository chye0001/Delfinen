package domain_model.members;

public class PassiveMember extends Member {
    private String type = "Passive";
    public PassiveMember(String name, String birthDate, String email, String discipline, double subscriptionValue) {
        super(name, birthDate, email, discipline, subscriptionValue);
    }

    @Override
    public String getType() {
        return type;
    }
}
