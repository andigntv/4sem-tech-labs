package models;

import lombok.Value;

/**
 * class for address
 */
@Value
public class Address {
    String _city;
    String _street;
    String _building;


    public Address(String city, String street, String building)
    {
        if (city.isEmpty())
            throw new IllegalArgumentException("Name of city cannot be empty");
        if (street.isEmpty())
            throw new IllegalArgumentException("Name of street cannot be empty");
        if (building.isEmpty())
            throw new IllegalArgumentException("Number of building cannot be empty");

        _city = city;
        _street = street;
        _building = building;
    }
}
