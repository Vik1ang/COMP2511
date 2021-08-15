package calculator;

public class SinExpression implements Expression {

    private Expression angle;

    public SinExpression(Expression of) {
        angle = of;
    }

    /**
     * @preconditions - number is an number or Nan
     * @postconditions - NaN if angle is Nan, else sin(angle)
     */
    public double compute() {
        return Math.sin(angle.compute());
    }
        
}