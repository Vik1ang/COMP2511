package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * this class represents a path tile.
 * It holds internally an enumerated value representing a direction.
 */
public class PathTile extends StaticEntity {
    /**
     * The enum Direction.
     */
    public enum Direction {
        /**
         * Up direction.
         */
        UP(0, -1),
        /**
         * Down direction.
         */
        DOWN(0, 1),
        /**
         * Left direction.
         */
        LEFT(-1, 0),
        /**
         * Right direction.
         */
        RIGHT(1, 0);

        private int xOffset;
        private int yOffset;

        /**
         * Gets x offset.
         *
         * @return the x offset
         */
        public int getXOffset() { return xOffset; }

        /**
         * Gets y offset.
         *
         * @return the y offset
         */
        public int getYOffset() { return yOffset; }

        /**
         * Invert direction.
         *
         * @return the direction
         */
        public Direction invert() {
            switch (this) {
                case UP: return DOWN;
                case DOWN: return UP;
                case LEFT: return RIGHT;
                case RIGHT: return LEFT;
                default: throw new IllegalArgumentException("Invalid direction to invert");
            }
        }

        /**
         * Calculates a direction from the provided points, given the direction goes from x1, y1 -> x2, y2
         *
         * @param x1 the x 1
         * @param y1 the y 1
         * @param x2 the x 2
         * @param y2 the y 2
         * @return the direction from offset
         */
        public static Direction getDirectionFromOffset(int x1, int y1, int x2, int y2) {
            int xOffset = x2 - x1;
            int yOffset = y2 - y1;
            switch (xOffset) {
                case 1: return RIGHT;
                case -1: return LEFT;
            }
            switch (yOffset) {
                case -1: return UP;
                case 1: return DOWN;
            }
            throw new IllegalArgumentException(String.format("Invalid Direction formed from offset %d and %d", xOffset, yOffset));
        }

        private Direction(int xOffset, int yOffset) {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
        }
    }

    /**
     * Instantiates a new Path tile.
     *
     * @param x the x
     * @param y the y
     */
    public PathTile(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
}
