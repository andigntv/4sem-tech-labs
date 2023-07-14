import entities.Bank;
import entities.CentralBank;
import entities.Client;
import entities.accounts.DebitAccount;
import entities.accounts.DepositAccount;
import exceptions.*;
import models.CustomDateTime;
import models.clientBuilder.ClientDirector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.ZonedDateTime;

public class BanksTest {
    private final ClientDirector director;

    {
        director = new ClientDirector();
    }

    @Test
    public void MoneyAccumulationTest() throws ClientException, BankException, TimeException, AccountException, TransactionException {
        Client client = director.buildClient_OnlyName(1, "Me", "Me");
        Bank bank = CentralBank.getInstance().createBank("tinkoff", 5000, 3.7, 3, 5);
        bank.addClient(client);
        CustomDateTime.setNow(ZonedDateTime.parse("2024-02-12T10:15:30+01:00[Europe/Paris]"));
        DepositAccount account = bank.createDepositAccount(client, ZonedDateTime.parse("2025-02-08T10:15:30+01:00[Europe/Paris]"));
        bank.replenish(account, 1000);
        var dateTime = ZonedDateTime.parse("2025-03-08T10:15:30+01:00[Europe/Paris]");
        Duration difference = Duration.between(CustomDateTime.now, dateTime);
        for (long days = difference.toDays(); days > 0; days--)
        {
            CustomDateTime.setNow(CustomDateTime.now.plusDays(1));
            bank.update();
        }
        assertTrue(1046.8 <= account.getBalance() && account.getBalance() <= 1046.81);
    }
    @Test
    public void MoneyTransfer_TransactionCanceled() throws ClientException, AccountException, TransactionException, BankException {
        Client firstClient = director.buildClient_OnlyName(2, "Me", "Me");
        Client secondClient = director.buildClient_OnlyName(3, "NotMe", "NotMe");
        Bank tinkoff = CentralBank.getInstance().createBank("Tinkoff", 5000, 3.7, 3, 5);
        DebitAccount firstAcc = tinkoff.createDebitAccount(firstClient);
        Bank sber = CentralBank.getInstance().createBank("Sber", 5000, 3.7, 3, 5);
        DebitAccount secondAcc = sber.createDebitAccount(secondClient);

        tinkoff.replenish(firstAcc, 10000);
        int id = tinkoff.transfer(firstAcc, secondAcc, 5000).getId();

        assertEquals(5000, firstAcc.getBalance());
        assertEquals(5000, secondAcc.getBalance());

        tinkoff.cancelTransaction(id);

        assertEquals(10000, firstAcc.getBalance());
        assertEquals(0, secondAcc.getBalance());
    }
}
