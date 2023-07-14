package interfaces;

import exceptions.AccountException;

import java.time.ZonedDateTime;

/**
 * interface for transactions
 */
public interface TransactionInterface {
    int getId();
    AccountInterface getAccount();
    ZonedDateTime getDateTime();
    double getAmount();

    /**
     * cancels transaction
     */
    void Cancel() throws AccountException;
}
