package thrones;

import java.util.Random;

public class CharacterFactory {

    private static Random random = new Random(System.currentTimeMillis());

    public static int getRandomCoordinate() {
        return random.nextInt(5);
    }

    public static King createKing() {
        return new King(getRandomCoordinate(), getRandomCoordinate());
    }

    public static Queen createQueen() {
        return new Queen(getRandomCoordinate(), getRandomCoordinate());   
    }

    public static Dragon createDragon() {
        return new Dragon(getRandomCoordinate(), getRandomCoordinate());
    }

    // Notes on seeding
    // Could have a static method set seed which is called first
    // This could be used for unit testing
    // Otherwise seed is time by default

    // Notes on extending
    // We could add another method
    // We could make a subclass and add new methods to create new types of characters (open/closed)

}