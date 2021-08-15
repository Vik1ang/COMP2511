package unsw.heist;

/**
 * A bank account.
 *
 * @invariant balance >= 0
 *
 * @author Robert Clifton-Everest
 *
 */
public class BankAccount {
    
    private int balance;

    public BankAccount() {
        this.balance = 0;
    }

    /**
     * Deposit the given amount into the bank account.
     *
     * @precondition amount > 0
     * @postcondition balance = old balance + amount
     *
     * @param amount The amount of money to deposit.
     */
    public void deposit(int amount) {
        balance += amount;
    }

    /**
     * Withdraw the given amount from the account.
     *
     * @precondition amount > 0
     *
     * @param amount
     * @return True if withdrawal succeeded, false otherwise.
     */
    public boolean withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    /**
     * Get the balance of the account.
     * @return
     */
    public int getBalance() {
        return balance;
    }
}
