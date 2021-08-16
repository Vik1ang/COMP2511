package programmingexample5;

import java.awt.Color;
import java.util.ArrayList;


public class ShapeColourAreaVisitor implements ShapeVisitor {

    /*
     *
     * TODO In this class, you need to implement the required method(s), to
     * answer the question.
     *
     * You also need to implement the following constructor and declare
     * variables, if required.
     *
     */
    private Color colour;
    private double totalArea;

    public ShapeColourAreaVisitor(Color colour) {
        this.colour = colour;
        totalArea = 0;
    }

    public double getTotalArea() {
        // TODO Implement this method as well
        return totalArea;
    }

    @Override
    public void visit(Circle circle) {
        if (circle.getColour().equals(colour)) {
            totalArea += circle.area();
        }
    }

    @Override
    public void visit(Rectangle rectangle) {
        if (rectangle.getColour().equals(colour)) {
            totalArea += rectangle.area();
        }
    }

    @Override
    public void visit(Triangle triangle) {
        if (triangle.getColour().equals(colour)) {
            totalArea += triangle.area();
        }
    }

    @Override
    public void visit(ShapeGroup shapeGroup) {
        final ArrayList<ShapeVisitable> elems = shapeGroup.getElems();
        for (ShapeVisitable elem : elems) {
            if (elem instanceof Circle) {
                visit((Circle) elem);
            } else if (elem instanceof Rectangle) {
                visit((Rectangle)elem);
            } else if (elem instanceof Triangle) {
                visit((Triangle) elem);
            } else if (elem instanceof ShapeGroup) {
                visit((ShapeGroup) elem);
            }
        }
    }
}
