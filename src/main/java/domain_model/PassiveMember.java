package domain_model;

public class PassiveMember extends Member{
    private String type = "Passive";
    public PassiveMember(String name, String birthDate, String email, String discipline, double subscriptionValue) {
        super(name, birthDate, email, discipline, subscriptionValue);
    }

    public String getType() {
        return type;
    }
}
