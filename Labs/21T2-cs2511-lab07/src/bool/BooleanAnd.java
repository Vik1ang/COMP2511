package bool;

public class BooleanAnd extends BooleanNode {

    public BooleanAnd(BooleanNode left, BooleanNode right) {
        super(left, right);
        super.setValue(left.isValue() && right.isValue());
        super.setName("AND");
    }

}
