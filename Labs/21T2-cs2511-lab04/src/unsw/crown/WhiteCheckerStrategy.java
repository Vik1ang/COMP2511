package unsw.crown;

import java.util.List;

public class WhiteCheckerStrategy extends ColorCheckerStrategy {
    CheckerColor color = CheckerColor.WHITE;

    public WhiteCheckerStrategy() {
        this.crownStrategy = new CrownJump();
    }

    @Override
    public void normalJump(Checkerboard board, Position position, List<Position> positions) {
        if (board.isInBounds(position.downLeft())) {
            Checker checker = board.getPieceAt(position.downLeft());
            if (checker == null) {
                positions.add(position.downLeft());
            } else if (!checker.getColor().equals(color)) {
                positions.add(position.downLeft().downLeft());
            }
        }

        if (board.isInBounds(position.downRight())) {
            Checker checker = board.getPieceAt(position.downRight());
            if (checker == null) {
                positions.add(position.downRight());
            } else if (!checker.getColor().equals(color)) {
                positions.add(position.downRight().downRight());
            }
        }
    }

    @Override
    public void madJump(Checkerboard board, Position position, List<Position> positions) {
        if (board.isInBounds(position.downLeft())) {
            Checker checker = board.getPieceAt(position.downLeft());
            if (checker != null) {
                if (board.isInBoundsAndEmpty(position.downLeft().downLeft())) {
                    positions.add(position.downLeft().downLeft());
                }
            }
        }

        if (board.isInBounds(position.downRight())) {
            Checker checker = board.getPieceAt(position.downRight());
            if (checker != null) {
                if (board.isInBoundsAndEmpty(position.downRight().downRight())) {
                    positions.add(position.downRight().downRight());
                }
            }
        }
    }
}
