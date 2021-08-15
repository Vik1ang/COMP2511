package unsw.loopmania.utils;

import org.javatuples.Pair;

import java.util.List;
import java.util.Random;

/**
 * The type Random utils.
 */
public class RandomUtils {

    /**
     * Generate integer random by seed.
     *
     * @param max    the max
     * @param min    the min
     * @param random the random
     * @return the int
     */
    public static int generateIntegerRandomBySeed(int max, int min, Random random) {
        return random.nextInt(max) % (max - min + 1) + min;
    }


    /**
     * Generate random valid pair.
     *
     * @param orderedPath the ordered path
     * @param random      the random
     * @return the pair
     */
    public static Pair<Integer, Integer> generateRandomValid(List<Pair<Integer, Integer>> orderedPath, Random random) {
        int size = orderedPath.size();
        int index = generateIntegerRandomBySeed(size, 0, random);
        return orderedPath.get(index);
    }

}
