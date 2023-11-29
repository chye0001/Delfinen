package domain_model;

import java.time.LocalDate;

public class Subscription {
    private LocalDate paymentDate;
    private double cost;
    private boolean paymentStatus;
    private double debt;

    public Subscription (LocalDate paymentDate, double cost, boolean paymentStatus, double debt){
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.debt = debt;
        this.cost = cost;
    }

    public Subscription (double cost){
        this.paymentDate = LocalDate.now();
        this.cost = cost;
        this.paymentStatus = true;
        this.debt = 0;
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

    @Override
    public String toString() {
        return "{" +
                "paymentDate=" + paymentDate +
                ", cost=" + cost + "kr" +
                ", paymentStatus=" + paymentStatus +
                ", debt=" + debt +
                '}';
    }
}
