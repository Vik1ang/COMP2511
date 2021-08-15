package unsw.calculator.model;

import unsw.calculator.model.tree.BinaryOperatorNode;
import unsw.calculator.model.tree.NumericNode;
import unsw.calculator.model.tree.TreeNode;

public class EvaluatorVisitor implements Visitor {

    private int value;

    @Override
    public void visitBinaryOperatorNode(BinaryOperatorNode node) {
        value = getResultValue(node);
    }

    public int getResultValue(BinaryOperatorNode node) {
        int leftNodeValue = 0;
        int rightNodeValue = 0;
        if (node.getLeft() != null) {
            TreeNode leftNode = node.getLeft();
            if (leftNode instanceof BinaryOperatorNode) {
                leftNodeValue = getResultValue((BinaryOperatorNode) leftNode);
            } else if (leftNode instanceof NumericNode) {
                leftNodeValue = ((NumericNode) leftNode).getValue();
            }
        }
        if (node.getRight() != null) {
            TreeNode rightNode = node.getRight();
            if (rightNode instanceof BinaryOperatorNode) {
                rightNodeValue = getResultValue((BinaryOperatorNode) rightNode);
            } else if (rightNode instanceof NumericNode) {
                rightNodeValue = ((NumericNode) rightNode).getValue();
            }
        }
        return node.compute(leftNodeValue, rightNodeValue);
    }

    @Override
    public void visitNumericNode(NumericNode node) {
        value = node.getValue();
    }

    public int getValue() {
        return value;
    }
}