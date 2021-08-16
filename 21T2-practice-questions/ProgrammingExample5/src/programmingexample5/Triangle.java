package programmingexample5;

import java.awt.Color;

public class Triangle extends Shape implements ShapeVisitable {

    private int base, height;

    public Triangle(int base, int height, Color c) {
        super(c);
        this.base = base;
        this.height = height;
    }

    @Override
    public double area() {
        return 0.5 * base * height;
    }

    @Override
    public void accept(ShapeVisitor v) {

        /*
         * TODO You need to implement this method, to answer the question.
         */

        v.visit(this);
    }

}
