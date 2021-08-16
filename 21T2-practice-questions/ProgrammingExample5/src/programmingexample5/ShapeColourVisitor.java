package programmingexample5;

import java.awt.*;
import java.util.ArrayList;


public class ShapeColourVisitor implements ShapeVisitor {

    /*
     *
     * TODO In this class, you need to implement the required method(s), to
     * answer the question.
     *
     */

    @Override
    public void visit(Circle circle) {
        circle.setColour(Color.RED);
    }

    @Override
    public void visit(Rectangle rectangle) {
        rectangle.setColour(Color.GREEN);
    }

    @Override
    public void visit(Triangle triangle) {
        triangle.setColour(Color.BLUE);
    }

    @Override
    public void visit(ShapeGroup shapeGroup) {
        ArrayList<ShapeVisitable> elems = shapeGroup.getElems();
        for (ShapeVisitable elem : elems) {
            if (elem instanceof Circle) {
                visit((Circle) elem);
            } else if (elem instanceof Rectangle) {
                visit((Rectangle) elem);
            } else if (elem instanceof Triangle) {
                visit((Triangle) elem);
            } else if (elem instanceof ShapeGroup) {
                visit((ShapeGroup) elem);
            }
        }
    }


}
