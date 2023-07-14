package interfaces;

import entities.Client;
import exceptions.AccountException;

import java.time.ZonedDateTime;

/**
 * interface for accounts
 */
public interface AccountInterface {
    int getId();
    ZonedDateTime getOpenDate();
    double getBalance();
    Client getOwner();
    double getWithdrawingLimit();

    /**
     * replenishes account
     */
    void replenishment(double amount) throws AccountException;

    /**
     * transfers money from one account to another
     */
    void transfer(double amount, AccountInterface anotherAccount) throws AccountException;

    /**
     * withdraws money from account
     */
    void withdrawing(double amount) throws AccountException;

    /**
     * recalculates balance
     */
    void recalculate();
}
