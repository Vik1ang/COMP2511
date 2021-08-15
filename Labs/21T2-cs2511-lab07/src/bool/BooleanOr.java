package bool;

public class BooleanOr extends BooleanNode {

    public BooleanOr(BooleanNode left, BooleanNode right) {
        super(left, right);
        super.setValue(left.isValue() || right.isValue());
        super.setName("OR");
    }

}
