package entities;

import exceptions.BankException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * class for central bank
 */
public class CentralBank {
    private static CentralBank centralBank = new CentralBank();

    private List<Bank> banks;

    private CentralBank() {
        banks = new ArrayList<>();
    }
    public static CentralBank getInstance()
    {
        return centralBank;
    }

    /**
     * creates bank
     */
    public Bank createBank(String name, double creditLimit, double creditRate, double interestOnBalance, double cumulativeInterest) throws BankException {
        if (findBank(name).isPresent())
            throw new BankException("Bank with this name already exists");

        var bank = new Bank(name, creditLimit, creditRate, interestOnBalance, cumulativeInterest);
        banks.add(bank);
        return bank;
    }

    /**
     * finds bank by its name
     */
    public Optional<Bank> findBank(String name)
    {
        return banks.stream().filter(x -> x.getName().equals(name)).findFirst();
    }

    /**
     * notifies all banks that a new day has arrived
     */
    public void update()
    {
        for (var bank : banks)
            bank.update();
    }
}
