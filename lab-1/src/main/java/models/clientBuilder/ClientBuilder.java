package models.clientBuilder;

import entities.Client;
import exceptions.ClientException;
import models.Address;
import models.Passport;

import java.util.Optional;

/**
 * builder for client class
 */
public class ClientBuilder {
    private final int MinNonNegativeNumber = 0;
    private String firstName = "default";
    private String secondName = "default";
    private int id;
    private Optional<Address> address = null;
    private Optional<Passport> passport = null;
    private Client client;

    public void buildNameAndId(String firstName, String secondName, int id) throws ClientException {
        if (firstName.isEmpty())
            throw new IllegalArgumentException("First name is null");
        if (secondName.isEmpty())
            throw new IllegalArgumentException("Second name is null");
        if (id <= MinNonNegativeNumber)
            throw new ClientException("Id must be positive");

        this.firstName = firstName;
        this.secondName = secondName;
        this.id = id;
    }

    public void buildPassport(Passport passport)
    {
        if (passport == null)
            throw new IllegalArgumentException("Passport is null");
        this.passport = Optional.of(passport);
    }

    public void buildAddress(Address address)
    {
        if (address == null)
            throw new IllegalArgumentException("Address is null");
        this.address = Optional.of(address);
    }

    public Client getClient() throws ClientException {
        client = new Client(firstName, secondName, id, passport, address);
        return client;
    }
}
