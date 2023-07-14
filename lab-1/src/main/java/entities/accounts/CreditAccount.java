package entities.accounts;

import entities.Client;
import exceptions.AccountException;
import interfaces.AccountInterface;

import java.time.ZonedDateTime;

/**
 * class for credit account
 */
public class CreditAccount implements AccountInterface {
    private final double InitialAccountBalance = 0;
    private final double MinAnnualInterestRate = 0;
    private final double MinCreditLimit = 0;
    private final double MinNonNegativeNumber = 0;
    private final int DaysInYear = 365;
    private final int NumberOfPercentagesInOneWhole = 100;

    private final int id;
    private final ZonedDateTime openDate;
    private double balance;
    public Client owner;
    public double annualInterestRate;
    public double creditLimit;

    public CreditAccount(Client owner, double annualInterestRate, double creditLimit, int id, ZonedDateTime openDate) throws AccountException {
        if (id <= MinNonNegativeNumber)
            throw new AccountException("Id cannot be negative");
        if (owner == null)
            throw new IllegalArgumentException("Owner cannot be null");
        if (annualInterestRate < MinAnnualInterestRate)
            throw new AccountException("Annual Interest Rate must be non-negative number");
        if (creditLimit < MinCreditLimit)
            throw new AccountException("Credit limit must be non-negative number");

        this.id = id;
        this.openDate = openDate;
        this.owner = owner;
        this.balance = InitialAccountBalance;
        this.annualInterestRate = annualInterestRate;
        this.creditLimit = creditLimit;

    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public ZonedDateTime getOpenDate() {
        return openDate;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public Client getOwner() {
        return owner;
    }

    @Override
    public double getWithdrawingLimit() {
        return balance + creditLimit;
    }

    @Override
    public void replenishment(double amount) throws AccountException {
        if (amount <= MinNonNegativeNumber)
            throw new AccountException("Replenishment can only be for a positive amount");
        balance += amount;
    }

    @Override
    public void transfer(double amount, AccountInterface anotherAccount) throws AccountException {
        if (anotherAccount == null)
            throw new IllegalArgumentException("Account cannot be null");
        withdrawing(amount);
        anotherAccount.replenishment(amount);
    }

    @Override
    public void withdrawing(double amount) throws AccountException {
        if (amount <= MinNonNegativeNumber)
            throw new AccountException("Withdrawing can only be for a negative amount");
        if (balance - amount < -creditLimit)
            throw new AccountException("You cannot exceed the credit limit");
        balance -= amount;
    }

    public void setCreditLimit(double value) throws AccountException {
        if (value < MinCreditLimit)
            throw new AccountException("Credit limit must be non-negative number");
        creditLimit = value;
    }
    public void setAnnualInterestRate(double value) throws AccountException {
        if (value < MinAnnualInterestRate)
            throw new AccountException("Annual Interest Rate must be non-negative number");
        annualInterestRate = value;
    }
    @Override
    public void recalculate() {
        if (balance >= 0)
            return;
        balance += -balance * annualInterestRate / NumberOfPercentagesInOneWhole / DaysInYear;
    }
}
