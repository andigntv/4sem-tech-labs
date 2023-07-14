package entities.accounts;

import entities.Client;
import exceptions.AccountException;
import interfaces.AccountInterface;
import models.CustomDateTime;

import java.time.ZonedDateTime;

/**
 * class for deposit account
 */
public class DepositAccount implements AccountInterface {
    private final double InitialAccountBalance = 0;
    private final double MinInterest = 0;
    private final double MinNonNegativeNumber = 0;
    private final double MonthsInYear = 12;
    private final double NumberOfPercentagesInOneWhole = 100;

    private final int id;
    private final ZonedDateTime openDate;
    private final ZonedDateTime end;
    private double balance;
    public Client owner;
    public double cumulativeInterest;

    public DepositAccount(Client owner, double cumulativeInterest, ZonedDateTime end, int id, ZonedDateTime openDate) throws AccountException {
        if (id <= MinNonNegativeNumber)
            throw new AccountException("Id cannot be negative");
        if (owner == null)
            throw new IllegalArgumentException("Owner cannot be null");
        if (cumulativeInterest < MinInterest)
            throw new AccountException("Interest on balance must be non-negative number");

        this.id = id;
        this.openDate = openDate;
        this.balance = InitialAccountBalance;
        this.owner = owner;
        this.cumulativeInterest = cumulativeInterest;
        this.end = end;
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
        if (CustomDateTime.now.compareTo(end) < 0)
            return 0;
        return balance;
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
        if (CustomDateTime.now.compareTo(end) < 0)
            throw new AccountException("You cannot transfer money from the deposit account before it ends");

        withdrawing(amount);
        anotherAccount.replenishment(amount);
    }

    @Override
    public void withdrawing(double amount) throws AccountException {
        if (amount > getWithdrawingLimit())
            throw new AccountException("You cannot withdraw money from this account");
    }

    @Override
    public void recalculate() {
        if (end.compareTo(CustomDateTime.now) < 0 || CustomDateTime.now.getDayOfMonth() != openDate.getDayOfMonth()) return;
        balance += balance * cumulativeInterest / NumberOfPercentagesInOneWhole / MonthsInYear;
    }
}
