package programmingexample3;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A hamper implemented using an ArrayList.
 *
 * @param <E>
 * @author Matthew Perry
 * @invariant for all c in counts, c.getCount() > 0
 */
public class ArrayListItemHamper<E extends Item> implements Hamper<E> {

    private ArrayList<Count<E>> counts;

    /**
     * Create a new empty hamper.
     */
    public ArrayListItemHamper() {
        this.counts = new ArrayList<Count<E>>();
    }

    private Count<E> getCount(Object o) {
        for (Count<E> c : counts)
            if (c.getElement().equals(o))
                return c;
        return null;
    }

    @Override
    public void add(E e) {
        add(e, 1);
    }

    @Override
    public void add(E e, int n) {
        Count<E> c = getCount(e);
        if (c != null) {
            c.incrementCount(n);
        } else if (n > 0) {
            counts.add(new Count<E>(e, n));
        }
    }

    @Override
    public void remove(E e) {
        remove(e, 1);
    }

    @Override
    public void remove(E e, int n) {
        // TODO implement this
        Count<E> c = getCount(e);
        if (c != null) {
            if (c.getCount() - n <= 0) {
                c.decrementCount(c.getCount());
                counts.remove(c);
            }
            c.decrementCount(n);

        }
    }

    @Override
    public int count(Object o) {
        Count<E> c = getCount(o);
        if (c != null)
            return c.getCount();
        return 0;
    }

    @Override
    public int size() {
        // TODO implement this
        int count = 0;
        for (Count<E> eCount : counts) {
            count += eCount.getCount();
        }
        return count;
    }

    @Override
    public Hamper<E> sum(Hamper<? extends E> hamper) {
        // TODO implement this
        ArrayListItemHamper<E> newHamper = new ArrayListItemHamper<>();
        for (Count<E> count : counts) {
            newHamper.add(count.getElement(), count.getCount());
        }
        for (Count<? extends E> count : hamper) {
            newHamper.add(count.getElement(), count.getCount());
        }
        return newHamper;
    }

    @Override
    public Iterator<Count<E>> iterator() {
        return counts.iterator();
    }

    /**
     * For this method, hampers should be the same class to be equal (ignore the generic type component). For example, a CreativeHamper cannot be equal to a FruitHamper,
     * And a FruitHamper cannot be equal to an ArrayListItemHamper<Fruit>,
     * However an ArrayListItemHamper<Fruit> can be equal to a ArrayListItemHamper<Item> if they both only contain fruit.
     * HINT: use getclass() to compare objects.
     */
    @Override
    public boolean equals(Object o) {
        // TODO implement this
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayListItemHamper<?> that = (ArrayListItemHamper<?>) o;

        if (counts.size() != that.counts.size()) return false;

        for (Count<E> count : counts) {
            for (Count<?> count1 : that.counts) {
                if (count.getElement().equals(count1.getElement())) {
                    if (count.getCount() != count1.getCount()) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    /**
     * @return price of the hamper - for ArrayListItemHamper, this should be the sum of the prices of items with each price multiplied by the number of times that item occurs
     */
    @Override
    public int getPrice() {
        // TODO implement this
        int sum = 0;
        for (Count<E> count : counts) {
            int price = count.getElement().getPrice();
            sum += price * count.getCount();
        }
        return sum;
    }

    @Override
    public String toString() {
        return counts.toString();
    }

    public int getAppleCount() {
        int appleCount = 0;
        for (Count<E> count : counts) {
            if (count.getElement().getClass().equals(Apple.class)) {
                appleCount++;
            }
        }
        return appleCount;
    }

    public int getAvocadoCount() {
        int avocadoCount = 0;
        for (Count<E> count : counts) {
            if (count.getElement().getClass().equals(Avocado.class)) {
                avocadoCount++;
            }
        }
        return avocadoCount;
    }

}
