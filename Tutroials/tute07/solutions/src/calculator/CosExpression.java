package calculator;

public class CosExpression implements Expression {

    private Expression angle;

    public CosExpression(Expression of) {
        angle = of;
    }

    /**
     * @preconditions - angle is an number or Nan
     * @postconditions - NaN if angle is Nan, else cos(angle)
     */
    public double compute() {
        return Math.cos(angle.compute());
    }
        
}