package unsw.calculator.model;

import unsw.calculator.model.tree.BinaryOperatorNode;
import unsw.calculator.model.tree.NumericNode;

public class InFixPrintVisitor implements Visitor {

    @Override
    public void visitBinaryOperatorNode(BinaryOperatorNode node) {
        node.infixPrint();
    }

    @Override
    public void visitNumericNode(NumericNode node) {
        node.infixPrint();
    }


}