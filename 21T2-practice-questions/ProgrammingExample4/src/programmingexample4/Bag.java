package programmingexample4;

import java.util.Iterator;

/**
 * Interface for a bag. A bag is similar to a set but allows for
 * duplicates.
 *
 * @author Robert Clifton-Everest
 */
public interface Bag<E> extends Iterable<Count<E>> {

	/**
	 * Adds a single copy of an element to the bag
	 * @param e The element to add
	 * @postcondition count(e) = old count(e) + 1
	 */
	public void add(E e);

	/**
	 * Adds multiple copies of an element to the bag.
	 * @param e The element to add
	 * @param n The number of copies
	 * @precondition n >= 0
     * @postcondition count(e) = old count(e) + n
	 */
	public void add(E e, int n);

	/**
	 * Remove one copy of the given element from the bag.
	 * @param e
     * @postcondition count(e) = max(old count(e) - 1, 0)
	 */
	public void remove(E e);

	/**
     * Remove multiple copies of an element from the bag, or all copies if there
     * are not that many copies in the bag.
     * @param e The element to remove
     * @param n The number of copies to remove.
     * @precondition n >= 0
     * @postcondition count(e) = max(old count(e) - n, 0)
     */
    public void remove(E e, int n);

    /**
     * Returns the number of times the given object occurs in the bag
     * @param o The object to get the count of
     * @return count
     * @postcondition count >= 0
     */
    public int count(Object o);

	/**
	 * The total number of items in the bag
	 * @return size
     * @postcondition size >= 0
	 */
	public int size();

	/**
	 * The sum of this bag and the given bag. If e occurs N times
	 * in this bag and M times in the given bag then it will occur N+M times
	 * in the resultant bag.
	 * @param bag
	 * @return result
	 * @postcondition for all e, result.count(e) = count(e) + bag.count(e)
	 */
	public Bag<E> sum(Bag<? extends E> bag);

	/**
	 * The iterator method. The iterator should yield a Count for each element
	 * that occurs at least once in the bag.
	 */
	public Iterator<Count<E>> iterator();


}
