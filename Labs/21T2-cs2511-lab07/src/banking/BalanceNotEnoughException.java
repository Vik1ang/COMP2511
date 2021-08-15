package banking;

public class BalanceNotEnoughException extends Exception {
    public BalanceNotEnoughException(String message) {
        super(message);
    }
}
