package unsw.loopmania.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Loop mania world care taker.
 */
public class LoopManiaWorldCareTaker {
    private final List<LoopManiaWorldMemento> mementoList;

    /**
     * Instantiates a new Loop mania world care taker.
     */
    public LoopManiaWorldCareTaker() {
        this.mementoList = new ArrayList<>();
    }

    /**
     * Add.
     *
     * @param state the state
     */
    public void add(LoopManiaWorldMemento state){
        if (mementoList.size() > 5)
            mementoList.remove(0);
        mementoList.add(state);
    }

    /**
     * Get loop mania world memento.
     *
     * @param index the index
     * @return the loop mania world memento
     */
    public LoopManiaWorldMemento get(int index){
        return mementoList.get(index);
    }

    /**
     * Gets memento list.
     *
     * @return the memento list
     */
    public List<LoopManiaWorldMemento> getMementoList() {
        return mementoList;
    }
}
