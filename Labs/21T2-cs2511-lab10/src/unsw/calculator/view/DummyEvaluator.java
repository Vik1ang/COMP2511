package unsw.calculator.view;

import unsw.calculator.model.EvaluatorVisitor;
import unsw.calculator.model.Parser;

class DummyEvaluator implements Evaluator {

    /**
     * Ignores the String expression to be evaluated
     * and simply returns the integer 0
     *
     * @ return the dummy value 0
     */
    public int evaluate(String expression) {
        Parser parser = new Parser(expression);
        EvaluatorVisitor evaluatorVisitor = new EvaluatorVisitor();
        parser.parse().accept(evaluatorVisitor);
        return evaluatorVisitor.getValue();
    }
}