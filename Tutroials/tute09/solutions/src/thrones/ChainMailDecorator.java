package thrones;

public class ChainMailDecorator extends CharacterDecorator{
    public ChainMailDecorator(Character character) {
        super(character);
    }

    @Override
    public void damage(int points) {
        super.damage(points/2);
    }

}
