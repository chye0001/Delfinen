package domain_model;

import java.time.LocalDate;
import java.time.Period;

public class Subscription {
    private LocalDate lastPayment;
    private LocalDate nextPayment;
    private double price;
    private double debt;
    private boolean isPaid = false;

    public Subscription (LocalDate lastPayment, LocalDate nextPayment, double price, double debt){
        this.lastPayment = lastPayment;
        this.nextPayment = nextPayment;
        this.price = price;
        this.debt = debt;
    }

    public Subscription (double price){
        this.lastPayment = null;
        this.nextPayment = null;
        this.price = price;
        this.debt = price;
    }

    public boolean havePaid() {
        return isPaid;
    }
    public void setIsPaid() {
        this.isPaid = !isPaid;
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
    public void setDebt(int amount) {
        this.debt = amount;
    }
    public void setLastPayment(LocalDate paymentDate) {
        this.lastPayment = paymentDate;
    }
    public void setNextPayment(LocalDate nextPaymentDate) {
        this.nextPayment = nextPaymentDate;
    }

    @Override
    public String toString() {
        if (lastPayment == null){
            return  "Price: " + price +
                    ", Last payment date: " + "-" +
                    ", Next payment date: " + "-" +
                    ", Payment status: " + (havePaid() ? "Paid" : "Not paid") +
                    ", Debt: " + debt;

        } else {
           return  "Price: " + price +
                    ", Last payment date: " + lastPayment +
                    ", Next payment date: " + nextPayment +
                    ", Payment status: " + (havePaid() ? "Paid" : "Not paid") +
                    ", Debt: " + debt;
        }

    }
}
