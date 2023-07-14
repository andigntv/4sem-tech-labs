package entities;

import exceptions.AccountException;
import exceptions.ClientException;
import interfaces.AccountInterface;
import models.Address;
import models.Passport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * class for client
 */
public class Client {
    private final double MinNonNegativeNumber = 0;
    private List<AccountInterface> accounts;
    private List<String> messages;

    private String firstName;
    private String secondName;
    private int id;
    private Optional<Passport> passport;
    private Optional<Address> address;
    private boolean subscribed;

    public Client(String firstName, String secondName, int id, Optional<Passport> passport, Optional<Address> address) throws ClientException {
        if (firstName.isEmpty())
            throw new IllegalArgumentException("First name cannot be empty");
        if (secondName.isEmpty())
            throw new IllegalArgumentException("Second name cannot be empty");
        if (id <= MinNonNegativeNumber)
            throw new ClientException("Id cannot be negative");

        this.firstName = firstName;
        this.secondName = secondName;
        this.id = id;
        this.passport = passport;
        this.address = address;
        this.accounts = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    /**
     * associates an account with a client
     */
    public void addAccount(AccountInterface account) throws AccountException {
        if (account == null)
            throw new IllegalArgumentException("Account is null");
        if (!account.getOwner().equals(this))
            throw new AccountException("Account doesn't belong to this client");
        accounts.add(account);
    }

    /**
     * removes the connection of the client with the account
     */
    public void deleteAccount(int id) throws ClientException {
        Optional<AccountInterface> account = findAccount(id);
        if (account.isEmpty())
            throw new ClientException("No such account");
        accounts.remove(account.get());
    }

    /**
     * finds client's account by id
     */
    public Optional<AccountInterface> findAccount(int id)
    {
        return accounts.stream().filter(x -> x.getId() == id).findFirst();
    }

    public void setPassport(Passport passport)
    {
        if (passport == null)
            throw new IllegalArgumentException("Passport is null");
        this.passport = Optional.of(passport);
    }

    public void setAddress(Address address)
    {
        if (address == null)
            throw new IllegalArgumentException("Address is null");
        this.address = Optional.of(address);
    }

    /**
     * adds a message to the messages queue
     */
    public void notify(String message)
    {
        if (message.isEmpty())
            throw new IllegalArgumentException();
        if (!subscribed)
            return;
        messages.add(message);
    }
    public String getFirstName() {
        return this.firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public int getId() {
        return this.id;
    }

    public Optional<Passport> getPassport() {
        return this.passport;
    }

    public Optional<Address> getAddress() {
        return this.address;
    }

    public boolean isSubscribed() {
        return this.subscribed;
    }
}
