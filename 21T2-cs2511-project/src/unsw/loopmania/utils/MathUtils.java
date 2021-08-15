package unsw.loopmania.utils;

/**
 * The type Math utils.
 */
public class MathUtils {
    /**
     * Gets distance.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     * @return the distance
     */
    public static double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
    }

    /**
     * Position is equal boolean.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     * @return the boolean
     */
    public static boolean positionIsEqual(int x1, int y1, int x2, int y2) {
        return (x1 == x2) && (y1 == y2);
    }

}
