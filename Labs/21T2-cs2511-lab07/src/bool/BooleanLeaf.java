package bool;

public class BooleanLeaf extends BooleanNode {
    public BooleanLeaf(boolean value) {
        super(value);
        if (value) {
            super.setName("true");
        } else {
            super.setName("false");
        }
    }
}
