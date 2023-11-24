package domain_model.Members;

import domain_model.Members.Member;

public class SeniorMember extends Member {
    private String type = "Senior";
    public SeniorMember(String name, String birthDate, String email, String discipline, double subscriptionValue) {
        super(name, birthDate, email, discipline, subscriptionValue);
    }

    @Override
    public String getType() {
        return type;
    }
}
