package programmingexample5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class ShapeVisitorTest {

    @Test
    public void testSimpleColourVisitor() {
        ShapeColourVisitor visitor = new ShapeColourVisitor();

        Circle s1 = new Circle(5, Color.CYAN);
        s1.accept(visitor);

        assertEquals(s1.getColour(), Color.RED);

        Rectangle s2 = new Rectangle(4, 3, Color.MAGENTA);
        s2.accept(visitor);

        assertEquals(s2.getColour(), Color.GREEN);

        Triangle s3 = new Triangle(2, 1, Color.YELLOW);
        s3.accept(visitor);

        assertEquals(s3.getColour(), Color.BLUE);
    }

    @Test
    void testComplexColourVisitor() {
        ShapeColourVisitor visitor = new ShapeColourVisitor();

        Circle s1 = new Circle(5, Color.CYAN);
        Rectangle s2 = new Rectangle(4, 3, Color.MAGENTA);
        Triangle s3 = new Triangle(2, 1, Color.YELLOW);

        ShapeGroup g1 = new ShapeGroup();
        g1.add(s1);
        g1.add(s2);

        ShapeGroup g2 = new ShapeGroup();
        g2.add(s3);
        g2.add(g1);

        g2.accept(visitor);
        assertEquals(s1.getColour(), Color.RED);
        assertEquals(s2.getColour(), Color.GREEN);
        assertEquals(s3.getColour(), Color.BLUE);
    }

    @Test
    public void testColourAreaVisitor() {
        ShapeColourAreaVisitor visitor = new ShapeColourAreaVisitor(Color.BLUE);

        Circle s1 = new Circle(5, Color.GREEN);
        s1.accept(visitor);

        assertEquals(visitor.getTotalArea(), 0);

        visitor = new ShapeColourAreaVisitor(Color.BLUE);
        Rectangle s2 = new Rectangle(4, 3, Color.BLUE);
        s2.accept(visitor);

        assertEquals(visitor.getTotalArea(), 12);

        visitor = new ShapeColourAreaVisitor(Color.YELLOW);
        Triangle s3 = new Triangle(6, 3, Color.YELLOW);
        s3.accept(visitor);

        assertEquals(visitor.getTotalArea(), 9);

        ShapeGroup g1 = new ShapeGroup();
        g1.add(s1);
        g1.add(s2);

        ShapeGroup g2 = new ShapeGroup();
        g2.add(s3);
        g2.add(g1);

        visitor = new ShapeColourAreaVisitor(Color.YELLOW);
        g2.accept(visitor);
        assertEquals(visitor.getTotalArea(), 9);
    }

    @Test
    public void testDualVisitors() {
        ShapeGroup s1 = new ShapeGroup();
        s1.add(new Circle(2, Color.YELLOW));
        s1.add(new Rectangle(2, 3, Color.GREEN));
        s1.add(new Circle(3, Color.YELLOW));
        s1.add(new Circle(1, Color.BLUE));
        s1.add(new Triangle(1, 2, Color.GREEN));

        ShapeColourAreaVisitor visitor1 = new ShapeColourAreaVisitor(Color.GREEN);
        s1.accept(visitor1);

        assertEquals(7, visitor1.getTotalArea());

        ShapeColourVisitor visitor2 = new ShapeColourVisitor();
        s1.accept(visitor2);

        ShapeColourAreaVisitor visitor3 = new ShapeColourAreaVisitor(Color.GREEN);
        s1.accept(visitor3);

        assertEquals(6, visitor3.getTotalArea());
    }
}
