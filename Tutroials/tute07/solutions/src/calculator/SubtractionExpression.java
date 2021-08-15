package calculator;

public class SubtractionExpression implements Expression {
    
    private Expression e1;
    private Expression e2;

    public SubtractionExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    /**
     * @preconditions - e1, e2 != null
     * @postconditions - e1 - e2
     */
    public double compute() {
        return e1.compute() - e2.compute();
    }

}