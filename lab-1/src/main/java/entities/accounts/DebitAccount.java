package entities.accounts;

import entities.Client;
import exceptions.AccountException;
import interfaces.AccountInterface;
import models.CustomDateTime;

import java.time.ZonedDateTime;

/**
 * class for debit account
 */
public class DebitAccount implements AccountInterface {
    private final double InitialAccountBalance = 0;
    private final double MinInterestOnBalance = 0;
    private final double MinNonNegativeNumber = 0;
    private final int DaysInYear = 365;
    private final int NumberOfPercentagesInOneWhole = 100;

    private final int id;
    private final ZonedDateTime openDate;
    private double balance;
    public Client owner;
    public double interestOnBalance;
    private double accumulatedBalance;

    public DebitAccount(Client owner, double interestOnBalance, int id, ZonedDateTime openDate) throws AccountException {
        if (id <= MinNonNegativeNumber)
            throw new AccountException("Id cannot be negative");
        if (owner == null)
            throw new IllegalArgumentException("Owner cannot be null");
        if (interestOnBalance < MinInterestOnBalance)
            throw new AccountException("Interest on balance must be non-negative number");

        this.id = id;
        this.openDate = openDate;
        this.balance = InitialAccountBalance;
        this.owner = owner;
        this.interestOnBalance = interestOnBalance;
        this.accumulatedBalance = MinNonNegativeNumber;
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
        return balance;
    }

    public double getInterestOnBalance() {
        return interestOnBalance;
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
            throw new AccountException("Withdrawing can only be for a positive amount");
        if (balance - amount < MinNonNegativeNumber)
            throw new AccountException("You have not enough money");
        balance -= amount;
    }

    @Override
    public void recalculate() {
        accumulatedBalance += balance * interestOnBalance / NumberOfPercentagesInOneWhole / DaysInYear;
        if (CustomDateTime.now.getDayOfMonth() != openDate.getDayOfMonth()) return;
        balance += accumulatedBalance;
        accumulatedBalance = 0;
    }
}
