package entities.transactions;

import exceptions.AccountException;
import exceptions.TransactionException;
import interfaces.AccountInterface;
import interfaces.TransactionInterface;

import java.time.ZonedDateTime;

/**
 * class for replenishment transaction
 */
public class ReplenishmentTransaction implements TransactionInterface {
    private final double MinNonNegativeNumber = 0;
    private final int id;
    private final AccountInterface account;
    private final ZonedDateTime dateTime;
    private final double amount;

    public ReplenishmentTransaction(AccountInterface account, ZonedDateTime dateTime, int id, double amount) throws TransactionException {
        if (account == null)
            throw new IllegalArgumentException("Account is null");
        if (id < MinNonNegativeNumber)
            throw new TransactionException("Transaction's id cannot be negative");
        if (amount < MinNonNegativeNumber)
            throw new TransactionException("Transaction's amount cannot be negative");

        this.account = account;
        this.dateTime = dateTime;
        this.id = id;
        this.amount = amount;
    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public AccountInterface getAccount() {
        return account;
    }

    @Override
    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void Cancel() throws AccountException {
        account.withdrawing(amount);
    }
}
