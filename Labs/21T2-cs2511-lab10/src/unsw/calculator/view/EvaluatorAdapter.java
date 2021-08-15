package unsw.calculator.view;

import unsw.calculator.model.EvaluatorVisitor;
import unsw.calculator.model.Parser;

public class EvaluatorAdapter implements Evaluator {

    @Override
    public int evaluate(String expression) {
        Parser parser = new Parser(expression);
        EvaluatorVisitor evaluatorVisitor = new EvaluatorVisitor();
        parser.parse().accept(evaluatorVisitor);
        return evaluatorVisitor.getValue();
    }
}