package banking;

/**
 * The type Bank account.
 */
public class BankAccount {
    private double balance;

    public BankAccount() {
    }

    /**
     * Deposits.
     * @precondition input value must > 0
     * @postcondition no postcondition
     * @param value the value
     */
    public void deposits(double value) throws InputValueInvalidException {
        if (value < 0) {
            throw new InputValueInvalidException("input value must greater than 0");
        }
        balance += value;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Withdrawals.
     * @precondition input value must > 0
     * @postcondition balance need > value
     * @param value the value
     */
    public void withdrawals(double value) throws BalanceNotEnoughException, InputValueInvalidException {
        if (value < 0) {
            throw new InputValueInvalidException("input value must greater than 0");
        }
        if (balance - value < 0) {
            throw new BalanceNotEnoughException("balance not enough");
        }
        balance -= value;
    }

}