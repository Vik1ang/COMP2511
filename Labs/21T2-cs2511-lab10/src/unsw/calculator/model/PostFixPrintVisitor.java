package unsw.calculator.model;

import unsw.calculator.model.tree.BinaryOperatorNode;
import unsw.calculator.model.tree.NumericNode;

public class PostFixPrintVisitor implements Visitor {

    @Override
    public void visitBinaryOperatorNode(BinaryOperatorNode node) {
        node.postfixPrint();
    }

    @Override
    public void visitNumericNode(NumericNode node) {
        node.postfixPrint();
    }
}