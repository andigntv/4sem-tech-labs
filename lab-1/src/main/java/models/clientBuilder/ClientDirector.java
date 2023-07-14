package models.clientBuilder;

import entities.Client;
import exceptions.ClientException;
import lombok.Getter;
import models.Address;
import models.Passport;

/**
 * director of the builder for the client class
 */
public class ClientDirector {
    @Getter
    public ClientBuilder builder;
    public ClientDirector()
    {
        builder = new ClientBuilder();
    }

    /**
     * builds client with only id, first and second names
     */
    public Client buildClient_OnlyName(int id, String firstName, String secondName) throws ClientException {
        builder.buildNameAndId(firstName, secondName, id);
        return builder.getClient();
    }

    /**
     * builds client with id, first name, second name and passport
     */
    public Client buildClient_NameAndPassport(int id, String firstName, String secondName, Passport passport) throws ClientException {
        builder.buildNameAndId(firstName, secondName, id);
        builder.buildPassport(passport);
        return builder.getClient();
    }

    /**
     * builds client with id, first name, second name and address
     */
    public Client buildClient_NameAndAddress(int id, String firstName, String secondName, Address address) throws ClientException {
        builder.buildNameAndId(firstName, secondName, id);
        builder.buildAddress(address);
        return builder.getClient();
    }

    /**
     * builds client with id, first name, second name, passport and address
     */
    public Client buildClient_Full(int id, String firstName, String secondName, Passport passport, Address address) throws ClientException {
        builder.buildNameAndId(firstName, secondName, id);
        builder.buildPassport(passport);
        builder.buildAddress(address);
        return builder.getClient();
    }
}
