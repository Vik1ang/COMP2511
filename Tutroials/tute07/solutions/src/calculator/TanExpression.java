package calculator;

public class TanExpression implements Expression {

    private Expression angle;

    public TanExpression(Expression of) {
        angle = of;
    }

    /**
     * @preconditions - angle is an int or Nan, angle != 90
     * @postconditions - NaN if angle is Nan, else tan(angle)
     */
    public double compute() {
        return Math.tan(angle.compute());
    }
        
}