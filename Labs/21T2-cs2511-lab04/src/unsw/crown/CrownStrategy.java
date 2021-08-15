package unsw.crown;

import java.util.List;

public interface CrownStrategy {
    void crownJump(Checkerboard board, Position position, List<Position> positions, boolean mad, CheckerColor color);
}
