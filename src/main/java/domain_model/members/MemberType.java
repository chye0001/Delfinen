package domain_model.members;

public enum MemberType {
    NONE(0),
    PASSIVE(500),
    EXERCISE(1000),
    COMPETITIVE(1000);

    private final int cost;

    MemberType(int cost) {
        this.cost = cost;
    }

    public int getCost(){
        return cost;
    }
}
