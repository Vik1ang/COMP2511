package unsw.loopmania;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;


/**
 * a Draggable Icon
 * which adds an additional relocation method
 * so that the drag icon centres on the provided coordinates (rather than the top-left coordinates)
 */
public class DragIcon extends ImageView{
	public DragIcon() {
		super();
	}

	/**
	 * relocate the ImageView so centre is at point p (e.g. if point p is mouse cursor coordinates)
	 * @param p coordinates to move to
	 */
	public void relocateToPoint (Point2D p) {
		//relocates the object to a point that has been converted to
		//scene coordinates
		Point2D localCoords = getParent().sceneToLocal(p);
		
		relocate ( 
				(int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
				(int) (localCoords.getY() - (getBoundsInLocal().getHeight() / 2))
			);
	}
}