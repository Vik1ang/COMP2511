package unsw.heist;

/**
 * A Thread which constructs access instances to make transactions.
 * 
 * @author Nick Patrikeos + @your name
 */
public class BankAccountThreadedAccessor extends Thread {

    private String user;
    private BankAccount account;
    private int numberOfWithdrawals;
    private int amountPerWithdrawal;

    public BankAccountThreadedAccessor(String user, BankAccount account, int numberOfWithdrawals, int amountPerWithdrawal) {
        this.user = user;
        this.account = account;
        this.numberOfWithdrawals = numberOfWithdrawals;
        this.amountPerWithdrawal = amountPerWithdrawal;
    }

    public void run() {
        BankAccountAccessor accessor = new BankAccountAccessor(account);
        accessor.withdraw(user, numberOfWithdrawals, amountPerWithdrawal);
    }

    public static void main(String[] args) {
        BankAccount goldMint = new BankAccount();
        goldMint.deposit(100);
        
        BankAccountThreadedAccessor accessor1 = new BankAccountThreadedAccessor("Rio", goldMint, 5, 20);
        accessor1.start();

        BankAccountThreadedAccessor accessor2 = new BankAccountThreadedAccessor("Tokyo", goldMint, 6, 6);
        accessor2.start();

        BankAccountThreadedAccessor accessor3 = new BankAccountThreadedAccessor("Denver", goldMint, 2, 49);
        accessor3.start();

        System.out.println("The balance is: $" + goldMint.getBalance());
    }

}