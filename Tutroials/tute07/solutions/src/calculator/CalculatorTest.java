package calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for composite calculator.
 * @author Nick Patrikeos 
 */
public class CalculatorTest {

    @Test
    public void testAddition() {
        Expression e = new AdditionExpression(new Number(1), new Number(2));
        assertEquals(3, e.compute(), 0.001);
    }

    @Test
    public void testSubtraction() {
        Expression e = new SubtractionExpression(new Number(1), new Number(2));
        assertEquals(-1, e.compute(), 0.001);
    }

    @Test
    public void testMultiplication() {
        Expression e = new MultiplicationExpression(new Number(4), new Number(3));
        assertEquals(12, e.compute(), 0.001);
    }

    @Test
    public void testDivision() {
        Expression e = new DivisionExpression(new Number(5), new Number(2));
        assertEquals(2.5, e.compute(), 0.001);
    }

    @Test
    public void testSin() {
        Expression e = new SinExpression(new Number(Math.PI / 2));
        assertEquals(1, e.compute(), 0.001);
    }

    @Test
    public void testTrigIntegration() {
        Expression e = new SinExpression(new AdditionExpression(new Number(Math.PI), new Number(Math.PI)));
        assertEquals(0, e.compute(), 0.001);
    }

    @Test
    public void testIntegration() {
        // ((1 + 2) + (3 / 4)) - ((5 / 10) * (7 * 8)) = -24.25
        Calculator calculator = new Calculator(new SubtractionExpression(
            new AdditionExpression(
                new AdditionExpression(new Number(1), new Number(2)), 
                new DivisionExpression(new Number(3), new Number(4))
            ), 
            new MultiplicationExpression(
                new DivisionExpression(new Number(5), new Number(10)),
                new MultiplicationExpression(new Number(7), new Number(8))    
            )
        ));
        assertEquals(-24.25, calculator.calculate(), 0.001);
    }
}