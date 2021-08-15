package unsw.training;

import java.util.List;

/**
 * A trainer that runs in person seminars.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Trainer {

    private String name;
    private String room;

    private List<Seminar> seminars;

    public List<Seminar> getSeminars() {
        return seminars;
    }
}
