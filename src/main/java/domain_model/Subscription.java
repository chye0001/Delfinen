package domain_model;

import java.time.LocalDate;
import java.time.Period;

public class Subscription {
    private LocalDate lastPayment;
    private LocalDate nextPayment;
    private double price;
    private double debt;

    public Subscription (LocalDate lastPayment, LocalDate nextPayment, double debt, double price){
        this.lastPayment = lastPayment;
        this.nextPayment = nextPayment;
        this.debt = debt;
        this.price = price;
    }

    public Subscription (double price){
        this.lastPayment = LocalDate.now();
        this.nextPayment = lastPayment.plusYears(1);
        this.price = price;
        this.debt = 0;
    }



    private boolean isPaid () {
        return (Period.between(lastPayment,LocalDate.now()).getYears() < 1);
    }

    public LocalDate getLastPayment() {
        return lastPayment;
    }

    public double getPrice() {
        return price;
    }



    public double getDebt() {
        return debt;
    }

    @Override
    public String toString() {
        return "Subscription: " +
                ", Price: " + price +
                ", Last payment date: " + lastPayment +
                ", Next payment date: " + nextPayment +
                ", Payment status: " + (isPaid() ? "Paid" : "Not paid") +
                ", Debt: " + debt;
    }
}
