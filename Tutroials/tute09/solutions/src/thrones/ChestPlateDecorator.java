package thrones;

public class ChestPlateDecorator extends CharacterDecorator{
    public ChestPlateDecorator(Character character) {
        super(character);
    }

    @Override
    public void damage(int points) {
        super.damage(Math.min(points, 7));
    }

    @Override
    public boolean canMove(int dx, int dy) {
        if (dx + dy <= 3)
            return super.canMove(dx, dy);
        else
            return false;
    }
}
