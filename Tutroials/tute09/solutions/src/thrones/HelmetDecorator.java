package thrones;

public class HelmetDecorator extends CharacterDecorator {

    public HelmetDecorator(Character character) {
        super(character);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void damage(int points) {
        super.damage(Math.max(points - 1, 0));
    }

    
}
