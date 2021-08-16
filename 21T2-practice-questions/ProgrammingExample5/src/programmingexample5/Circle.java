package programmingexample5;

import java.awt.Color;

public class Circle extends Shape implements ShapeVisitable {

    protected static final double PI = 3.14159;
    private int radius;

    public Circle(int radius, Color colour) {
        super(colour);
        this.radius = radius;
    }

    /**
     * The method returns area of the circle.
     *
     * @return area of the circle
     */
    @Override
    public double area() {
        return PI * radius * radius;
    }

    @Override
    public void accept(ShapeVisitor v) {

        /*
         * TODO You need to implement this method, to answer the question.
         */
        v.visit(this);

    }

}
