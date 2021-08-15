package unsw.crown;


import java.util.List;

public class RedCheckerStrategy extends ColorCheckerStrategy {
    CheckerColor color = CheckerColor.RED;

    public RedCheckerStrategy() {
        this.crownStrategy = new CrownJump();
    }

    @Override
    public void normalJump(Checkerboard board, Position position, List<Position> positions) {
        if (board.isInBounds(position.upLeft())) {
            Checker checker = board.getPieceAt(position.upLeft());
            if (checker == null) {
                positions.add(position.upLeft());
            } else if (!checker.getColor().equals(color)) {
                positions.add(position.upLeft().upLeft());
            }
        }

        if (board.isInBounds(position.upRight())) {
            Checker checker = board.getPieceAt(position.upRight());
            if (checker == null) {
                positions.add(position.upRight());
            } else if (!checker.getColor().equals(color)) {
                positions.add(position.upRight().upRight());
            }
        }
    }

    @Override
    public void madJump(Checkerboard board, Position position, List<Position> positions) {
        if (board.isInBounds(position.upLeft())) {
            Checker checker = board.getPieceAt(position.upLeft());
            if (checker != null) {
                if (board.isInBoundsAndEmpty(position.upLeft().upLeft())) {
                    positions.add(position.upLeft().upLeft());
                }
            }
        }

        if (board.isInBounds(position.upRight())) {
            Checker checker = board.getPieceAt(position.upRight());
            if (checker != null) {
                if (board.isInBoundsAndEmpty(position.upRight().upRight())) {
                    positions.add(position.upRight().upRight());
                }
            }
        }
    }
}

