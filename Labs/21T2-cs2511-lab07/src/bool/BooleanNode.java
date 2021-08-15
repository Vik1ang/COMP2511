package bool;

/**
 * A boolean BooleanNode.
 *
 * @author Nick Patrikeos + @your name
 * <p>
 * Feel free to change this to an abstract class/interface as you see fit.
 */
public abstract class BooleanNode implements BooleanInterface {
    private BooleanNode left;
    private BooleanNode right;
    private boolean value;
    private String name;

    public BooleanNode(BooleanNode left, BooleanNode right) {
        this.left = left;
        this.right = right;
    }

    public BooleanNode(boolean value) {
        this.value = value;
    }

    @Override
    public boolean getBooleanValue() {
        return value;
    }

    public BooleanNode getLeft() {
        return left;
    }

    public void setLeft(BooleanNode left) {
        this.left = left;
    }

    public BooleanNode getRight() {
        return right;
    }

    public void setRight(BooleanNode right) {
        this.right = right;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}