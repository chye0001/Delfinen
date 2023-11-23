package domain_model;

import java.time.LocalDate;

public class Subscription {
    private LocalDate paymentDate;
    private double cost;
    private boolean paymentStatus;
    private double debt;

    public Subscription (LocalDate paymentDate, double cost, boolean paymentStatus, double debt){
        this.paymentDate = paymentDate;
        this.cost = cost;
        this.paymentStatus = paymentStatus;
        this.debt = debt;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public double getCost() {
        return cost;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public double getDebt() {
        return debt;
    }
}
