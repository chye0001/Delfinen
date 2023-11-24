package domain_model.Members;

import domain_model.Members.Member;

public class PassiveMember extends Member {
    private String type = "Passive";
    public PassiveMember(String name, String birthDate, String email, String discipline, double subscriptionValue) {
        super(name, birthDate, email, discipline, subscriptionValue);
    }

    public String getType() {
        return type;
    }
}
