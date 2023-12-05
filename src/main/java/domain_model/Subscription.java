package domain_model;

import java.time.LocalDate;
import java.time.Period;

public class Subscription {
    private LocalDate lastPayment;
    private LocalDate nextPayment;
    private double price;
    private double debt;

    public Subscription (LocalDate lastPayment, LocalDate nextPayment, double price, double debt){
        this.lastPayment = lastPayment;
        this.nextPayment = nextPayment;
        this.price = price;
        this.debt = debt;
    }

    public Subscription (double price){
        this.lastPayment = LocalDate.now();
        this.nextPayment = lastPayment.plusYears(1);
        this.price = price;
        this.debt = 0;
    }



    public boolean isPaid () {
        return (Period.between(lastPayment,LocalDate.now()).getYears() < 1);
    }

    public LocalDate getLastPayment() {
        return lastPayment;
    }

    public LocalDate getNextPayment() {
        return nextPayment;
    }
    public double getPrice() {
        return price;
    }



    public double getDebt() {
        return debt;
    }

    @Override
    public String toString() {
        return  "Price: " + price +
                ", Last payment date: " + lastPayment +
                ", Next payment date: " + nextPayment +
                ", Payment status: " + (isPaid() ? "Paid" : "Not paid") +
                ", Debt: " + debt;
    }
}
