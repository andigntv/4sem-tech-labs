package entities;

import entities.accounts.CreditAccount;
import entities.accounts.DebitAccount;
import entities.accounts.DepositAccount;
import entities.transactions.ReplenishmentTransaction;
import entities.transactions.TransferTransaction;
import entities.transactions.WithdrawingTransaction;
import exceptions.AccountException;
import exceptions.ClientException;
import exceptions.TransactionException;
import interfaces.AccountInterface;
import interfaces.TransactionInterface;
import models.BankInfo;
import models.CustomDateTime;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * class for bank
 */
public class Bank {
    private final double MinNonNegativeNumber = 0;
    private List<AccountInterface> accounts;
    private List<Client> clients;

    private List<TransactionInterface> transactions;
    private int transactionId = 1;
    private int accountId = 1;

    private final String name;
    private BankInfo info;

    public Bank(String name, double creditLimit, double creditRate, double interestOnBalance, double cumulativeInterest)
    {
        if (name.isEmpty())
            throw new IllegalArgumentException("Bank name cannot be empty");
        if (creditLimit < MinNonNegativeNumber)
            throw new IllegalArgumentException("Credit limit must be non-negative number");
        if (creditRate < MinNonNegativeNumber)
            throw new IllegalArgumentException("Credit rate must be non-negative number");
        if (interestOnBalance < MinNonNegativeNumber)
            throw new IllegalArgumentException("Interest on balance must be non-negative number");
        if (cumulativeInterest < MinNonNegativeNumber)
            throw new IllegalArgumentException("Cumulative interest must be non-negative number");

        this.name = name;
        info = new BankInfo(creditLimit, creditRate, interestOnBalance, cumulativeInterest);
        accounts = new ArrayList<>();
        transactions = new ArrayList<>();
        clients = new ArrayList<>();
    }

    public List<AccountInterface> getAccounts()
    {
        return Collections.unmodifiableList(accounts);
    }
    public List<Client> getClients()
    {
        return Collections.unmodifiableList(clients);
    }
    public List<TransactionInterface> getTransactions()
    {
        return Collections.unmodifiableList(transactions);
    }

    /**
     * connects the client with the bank
     */
    public void addClient(Client client)
    {
        if (client == null)
            throw new IllegalArgumentException("Client is null");
        clients.add(client);
    }

    /**
     * associates an account with a client
     */
    public void addAccountToClient(AccountInterface account, Client client) throws AccountException {
        if (client == null)
            throw new IllegalArgumentException("Client is null");
        client.addAccount(account);
        accounts.add(account);
    }

    /**
     * creates credit account and connects it to client
     */
    public CreditAccount createCreditAccount(Client client) throws AccountException {
        var result = new CreditAccount(client, info.getCreditRate(), info.getCreditLimit(), accountId++, CustomDateTime.now);
        accounts.add(result);
        client.addAccount(result);
        return result;
    }
    /**
     * creates debit account and connects it to client
     */
    public DebitAccount createDebitAccount(Client client) throws AccountException {
        var result = new DebitAccount(client, info.getInterestOnBalance(), accountId++, CustomDateTime.now);
        accounts.add(result);
        client.addAccount(result);
        return result;
    }
    /**
     * creates deposit account and connects it to client
     */
    public DepositAccount createDepositAccount(Client client, ZonedDateTime end) throws AccountException {
        var result = new DepositAccount(client, info.getCumulativeInterest(), end, accountId++, CustomDateTime.now);
        accounts.add(result);
        client.addAccount(result);
        return result;
    }

    /**
     * replenish account
     */
    public ReplenishmentTransaction replenish(AccountInterface account, double amount) throws AccountException, TransactionException {
        if (account == null)
            throw new IllegalArgumentException("Account cannot be null");
        account.replenishment(amount);
        var result = new ReplenishmentTransaction(account, CustomDateTime.now, transactionId++, amount);
        transactions.add(result);
        return result;
    }

    /**
     * transfer money from one account to another
     */
    public TransferTransaction transfer(AccountInterface account, AccountInterface recipientAccount,double amount) throws AccountException, TransactionException {
        if (account == null || recipientAccount == null)
            throw new IllegalArgumentException("Account cannot be null");
        account.transfer(amount, recipientAccount);
        var result = new TransferTransaction(account, CustomDateTime.now, transactionId++, amount, recipientAccount);
        transactions.add(result);
        return result;
    }

    /**
     * withdraws money from account
     */
    public WithdrawingTransaction withdraw(AccountInterface account, double amount) throws AccountException, TransactionException {
        if (account == null)
            throw new IllegalArgumentException("Account cannot be null");
        account.withdrawing(amount);
        var result = new WithdrawingTransaction(account, CustomDateTime.now, transactionId++, amount);
        transactions.add(result);
        return result;
    }

    /**
     * finds transaction by its id
     */
    public Optional<TransactionInterface> findTransaction(int id)
    {
        return transactions.stream().filter(x -> x.getId() == id).findFirst();
    }

    /**
     * cancels transaction by its id
     */
    public void cancelTransaction(int id) throws AccountException {
        Optional<TransactionInterface> transaction = findTransaction(id);
        if (transaction.isEmpty())
            throw new IllegalArgumentException("No such transaction");
        transaction.get().Cancel();
        transactions.remove(transaction.get());
    }

    /**
     * notifies each account that new day has arrived
     */
    public void update()
    {
        for (var account : accounts)
            account.recalculate();
    }

    /**
     * closes account
     */
    public void closeAccount(int id) throws ClientException {
        Optional<AccountInterface> account = findAccount(id);
        if (account.isEmpty())
            throw new IllegalArgumentException("No such account");

        account.get().getOwner().deleteAccount(id);
        accounts.remove(account.get());
    }

    /**
     * finds account by its id
     */
    public Optional<AccountInterface> findAccount(int id)
    {
        return accounts.stream().filter(x -> x.getId() == id).findFirst();
    }

    /**
     * finds client by id
     */
    public Optional<Client> findClient(int id)
    {
        return clients.stream().filter(x -> x.getId() == id).findFirst();
    }

    /**
     * send message to each client
     */
    public void notifySubscribers(String message)
    {
        if (message.isEmpty())
            throw new IllegalArgumentException("Message is null");
        for (var client : clients)
            client.notify(message);
    }

    public String getName() {
        return this.name;
    }

    public BankInfo getInfo() {
        return this.info;
    }
}
