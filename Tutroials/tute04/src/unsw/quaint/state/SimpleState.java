package unsw.quaint.state;

import java.text.DecimalFormat;
import unsw.quaint.ICanvasChange;

/**
 * Represents just a simple cursor state that shows a pretty cursor
 * moving around as the user moves their mouse around.
 * @author Braedon Wooding
 */
public class SimpleState extends State {
    public SimpleState(ICanvasChange canvasEvents, double x, double y) {
        super(canvasEvents, x, y);

        // clear special canvas
        canvasEvents.getFrontDrawingContext();
    }

    @Override
    public void handleMouseMove(double newX, double newY) {
        super.handleMouseMove(newX, newY);
        DecimalFormat df = new DecimalFormat("0");

        // draw x + y above cursor
        getCanvasEvents().getFrontDrawingContext()
            .fillText(String.format("(%s, %s)", df.format(newX), df.format(newY)), newX, newY);
    }
}
