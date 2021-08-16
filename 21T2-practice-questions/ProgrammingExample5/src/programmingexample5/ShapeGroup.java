package programmingexample5;

import java.util.ArrayList;

public class ShapeGroup implements ShapeVisitable {

    private ArrayList<ShapeVisitable> elems;

    public ShapeGroup() {
        elems = new ArrayList<ShapeVisitable>();
    }

    public void add(ShapeVisitable shape) {
        elems.add(shape);
    }

    public void remove(ShapeVisitable shape) {
        elems.remove(shape);
    }

    public ArrayList<ShapeVisitable> getElems() {
        return elems;
    }

    @Override
    public void accept(ShapeVisitor v) {

        /*
         * TODO You need to implement this method, to answer the question.
         */
        v.visit(this);

    }

}
