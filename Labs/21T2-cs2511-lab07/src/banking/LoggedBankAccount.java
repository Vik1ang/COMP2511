package banking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoggedBankAccount extends BankAccount {
    List<String> logs;
    SimpleDateFormat simpleDateFormat;

    public LoggedBankAccount() {
        logs = new ArrayList<>();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logs.add(simpleDateFormat.format(new Date()) + " create account");
    }

    public List<String> getLogs() {
        return logs;
    }

    @Override
    public void deposits(double value) throws InputValueInvalidException {
        super.deposits(value);
        logs.add(simpleDateFormat.format(new Date()) + " deposits: " + value + " balance = " + getBalance());

    }

    @Override
    public void withdrawals(double value) throws BalanceNotEnoughException, InputValueInvalidException {
        super.withdrawals(value);
        logs.add(simpleDateFormat.format(new Date()) + " withdraw: " + value + " balance = " + getBalance());
    }
}
