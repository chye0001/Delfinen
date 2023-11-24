package domain_model.Members;

public class JuniorMember extends Member {
    private String type = "Junior";
    public JuniorMember(String name, String birthDate, String email, String discipline, double subscriptionValue) {
        super(name, birthDate, email, discipline, subscriptionValue);
    }

    public String getType() {
        return type;
    }
}
