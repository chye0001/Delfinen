package domain_model;

public enum MemberType {
    NONE(0),
    PASSIVE(500),
    EXERCISE(1000),
    COMPETITIVE(1000);

    private final double cost;

    MemberType(int cost) {
        this.cost = cost;
    }

    public double getCost(){
        return cost;
    }
}
