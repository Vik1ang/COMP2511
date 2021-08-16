package programmingexample5;

import java.awt.Color;

public abstract class Shape {

    private Color colour;
    private int borderWidth;

    public Shape(Color colour) {
        this.colour = colour;
        this.borderWidth = 0;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public abstract double area();

}
