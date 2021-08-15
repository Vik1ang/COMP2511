package scrabble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Scrabble {

    private List<String> dictionary = new ArrayList<String>(Arrays.asList("ab", "abe", "able", "ad", "ae", "ae",
            "ah", "al", "ale", "at", "ate", "ba", "bad", "be", "be", "bead", "bed", "bra", "brad", "bread", "bred",
            "cabble", "cable", "ea", "ea", "eat", "eater", "ed", "ha", "hah", "hat", "hate", "hater", "hath", "he",
            "heat", "heater", "heath", "heather", "heathery", "het", "in", "io", "ion", "li", "lin", "lion", "on",
            "program", "ra", "rad", "re", "rea", "read", "red", "sa", "sat", "scabble", "scrabble", "se", "sea", "seat",
            "seathe", "set", "seth", "sh", "sha", "shat", "she", "shea", "sheat", "sheath", "sheathe", "sheather",
            "sheathery", "sheth", "st", "te"));

    private String word;
    private HashSet<String> hashSet;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        if (!dictionary.contains(word)) {
            return 0;
        }
        if (hashSet == null || hashSet.size() != 0) {
            hashSet = new HashSet<>();
        }
        int count = helpFunc(word, 0);
        hashSet.clear();
        return count;
    }

    private int helpFunc(String word, int count) {
        if (word.length() < 2) {
            return count;
        } else {
            if (dictionary.contains(word) && !hashSet.contains(word)) {
                count++;
                hashSet.add(word);
                for (int i = 0; i < word.length(); i++) {
                    // String newWord = word.substring(0, i) + word.substring(i + 1);
                    StringBuilder sb = new StringBuilder(word);
                    sb.deleteCharAt(i);
                    String newWord = sb.toString();
                    count += helpFunc(newWord, 0);
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(new Scrabble("lion").score());
    }
}