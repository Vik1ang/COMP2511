package bool;

public class BooleanNot extends BooleanNode{
    public BooleanNot(BooleanNode left, BooleanNode right) {
        super(left, right);
        super.setValue(!left.isValue() ? left != null : !right.isValue());
        super.setName("NOT");
    }

}
