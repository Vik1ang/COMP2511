package unsw.crown;

import java.util.List;

public class CrownJump implements CrownStrategy {

    @Override
    public void crownJump(Checkerboard board, Position position, List<Position> positions, boolean mad, CheckerColor color) {
        if (board.isInBounds(position.upLeft())) {
            Checker upLeft = board.getPieceAt(position.upLeft());
            if (upLeft == null) {
                positions.add(position.upLeft());
            } else if (!upLeft.getColor().equals(color) || mad) {
                if (board.isInBoundsAndEmpty(position.upLeft().upLeft())) {
                    positions.add(position.upLeft().upLeft());
                }
            }
        }

        if (board.isInBounds(position.upRight())) {
            Checker upRight = board.getPieceAt(position.upRight());
            if (upRight == null) {
                positions.add(position.upLeft());
            } else if (!upRight.getColor().equals(color) || mad) {
                if (board.isInBoundsAndEmpty(position.upRight().upRight())) {
                    positions.add(position.upRight().upRight());
                }
            }
        }

        if (board.isInBounds(position.downLeft())) {
            Checker downLeft = board.getPieceAt(position.downLeft());
            if (downLeft == null) {
                positions.add(position.downLeft());
            } else if (!downLeft.getColor().equals(color) || mad) {
                if (board.isInBoundsAndEmpty(position.downLeft().downLeft())) {
                    positions.add(position.downLeft().downLeft());
                }
            }
        }

        if (board.isInBounds(position.downRight())) {
            Checker downLeft = board.getPieceAt(position.downRight());
            if (downLeft == null) {
                positions.add(position.downRight());
            } else if (!downLeft.getColor().equals(color) || mad) {
                if (board.isInBoundsAndEmpty(position.downRight().downRight())) {
                    positions.add(position.downRight().downRight());
                }
            }
        }
    }
}
