package banking;

import org.junit.jupiter.api.Test;

import java.io.Console;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class bankTest {
    @Test
    public void testBankAccount() {
        BankAccount account = new BankAccount();
        try {
            account.deposits(1000);
            account.withdrawals(1000);
            account.withdrawals(-1);
            account.deposits(0);
        } catch (InputValueInvalidException | BalanceNotEnoughException e) {
            // System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testBankAccountThrowException() throws InputValueInvalidException, BalanceNotEnoughException {
        BankAccount account = new BankAccount();
        account.deposits(1000);
        account.withdrawals(1000);
        account.withdrawals(-1);
        account.deposits(0);
    }

    @Test
    public void testLogged() {
        LoggedBankAccount account = new LoggedBankAccount();
        try {
            account.deposits(1000);
            account.withdrawals(100);
            account.withdrawals(100);
            account.withdrawals(-1000);
        } catch (InputValueInvalidException | BalanceNotEnoughException e) {
            e.printStackTrace();
        }
        List<String> logs = account.getLogs();
        for (String log : logs) {
            System.out.println(log);
        }
    }
}
