package unsw.calculator.model.tree;

import unsw.calculator.model.Visitor;

/*
 * Tree node that contains two children
 */
public abstract class BinaryOperatorNode implements TreeNode  {

    private TreeNode left, right;

    public BinaryOperatorNode() {
        this.left = null;
        this.right = null;
    }

    public BinaryOperatorNode(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
    }

    public TreeNode getLeft() {
        return this.left;
    }

    public TreeNode getRight()  {
        return this.right;
    }

    public void infixPrint()  {
        System.out.print("(");

        if (this.left != null) {
            this.left.infixPrint();
        }

        System.out.print(" " + getLabel() + " ");

        if (this.right != null) {
            this.right.infixPrint();
        }
        
        System.out.print(")");
    }

    public void postfixPrint() {
        if (this.left != null) {
            this.left.postfixPrint();
            System.out.print(" ");
        }
        if (this.right != null) {
            this.right.postfixPrint();
            System.out.print(" ");
        }
        System.out.print(this.getLabel());
    }

    /**
     * Apply this operator (+,-,*,/ etc.) to the given operands
     */
    public abstract int compute(int a, int b);
    
    /**
     * Returns a textual representation of the node.
     */
    public abstract String getLabel();

    public void acceptLeft(Visitor visitor) {
        visitor.visitBinaryOperatorNode(this);
    }

    public void acceptRight(Visitor visitor) {
        visitor.visitBinaryOperatorNode(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitBinaryOperatorNode(this);
    }
}