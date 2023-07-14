package models;

/**
 * class that contains all the necessary information for the bank
 */
public class BankInfo {
    private double creditLimit;
    private double creditRate;
    private double interestOnBalance;
    private double cumulativeInterest;

    public BankInfo(double creditLimit, double creditRate, double interestOnBalance, double cumulativeInterest) {
        this.creditLimit = creditLimit;
        this.creditRate = creditRate;
        this.interestOnBalance = interestOnBalance;
        this.cumulativeInterest = cumulativeInterest;
    }

    public double getCreditLimit() {
        return this.creditLimit;
    }

    public double getCreditRate() {
        return this.creditRate;
    }

    public double getInterestOnBalance() {
        return this.interestOnBalance;
    }

    public double getCumulativeInterest() {
        return this.cumulativeInterest;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setCreditRate(double creditRate) {
        this.creditRate = creditRate;
    }

    public void setInterestOnBalance(double interestOnBalance) {
        this.interestOnBalance = interestOnBalance;
    }

    public void setCumulativeInterest(double cumulativeInterest) {
        this.cumulativeInterest = cumulativeInterest;
    }
}
