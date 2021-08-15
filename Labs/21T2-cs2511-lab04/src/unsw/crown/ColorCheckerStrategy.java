package unsw.crown;

import java.util.List;

public abstract class ColorCheckerStrategy implements CheckerStrategy{
    CrownStrategy crownStrategy;

    public void crownJump(Checkerboard board, Position position, List<Position> positions, boolean mad, CheckerColor color) {
        if (crownStrategy != null) {
            crownStrategy.crownJump(board, position, positions, mad, color);
        }
    }
}
