package domain_model.members;

public class CompetitiveMember extends Member {
    private String type = "Competitive";
    public CompetitiveMember(String name, String birthDate, String email, String discipline, double subscriptionValue) {
        super(name, birthDate, email, discipline, subscriptionValue);
    }

    @Override
    public String getType() {
        return type;
    }
}
