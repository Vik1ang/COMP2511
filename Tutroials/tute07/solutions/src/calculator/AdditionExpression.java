package calculator;

public class AdditionExpression implements Expression {
    
    private Expression e1;
    private Expression e2;

    public AdditionExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    /**
     * @precondition - expressions passed into constructor are not null
     * @postcondition - sum of expressions
     */
    public double compute() {
        return e1.compute() + e2.compute();
    }

}