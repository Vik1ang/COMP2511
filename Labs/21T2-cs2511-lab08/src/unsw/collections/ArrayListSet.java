package unsw.collections;


import java.util.*;
import java.util.function.Consumer;

public class ArrayListSet<E> implements Set<E> {

    private ArrayList<E> list;

    public ArrayListSet() {
        list = new ArrayList<>();
    }

    /**
     * Add an element to the set. Set is unchanged if it already contains the
     * element.
     *
     * @param e The element to add
     * @post contains(e)
     */
    @Override
    public void add(E e) {
        if (!list.contains(e)) {
            list.add(e);
        }
    }

    /**
     * Remove an element from the set.
     *
     * @param e The element to remove
     * @post !contains(e)
     */
    @Override
    public void remove(E e) {
        list.remove(e);
    }

    /**
     * Determine if the given element is in the set.
     *
     * @param e The element to test against
     * @return true - if the element is in the set, false otherwise
     */
    @Override
    public boolean contains(Object e) {
        return list.contains(e);
    }

    /**
     * Get the number of elements in the set.
     *
     * @return size - the number of elements in the set
     * @post size >= 0
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Determine if this set is a subset of another set.
     *
     * @param other The possible super set.
     * @return subset - Whether or not the subset relation holds.
     * @post subset if and only if (forall e. contains(e) => other.contains(e))
     */
    @Override
    public boolean subsetOf(Set<?> other) {
        for (E e : list) {
            if (!other.contains(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return a new set that is the union of this set and the given set
     *
     * @param other The other set operand.
     * @return result - A new set that is the union of these two sets.
     * @post for all e in result, contains(e) or other.contains(e)
     */
    @Override
    public Set<E> union(Set<? extends E> other) {
        Set<E> listSet = new ArrayListSet<>();
        for (E e : other) {
            listSet.add(e);
        }
        for (E e : list) {
            listSet.add(e);
        }
        return listSet;
    }

    /**
     * Return a new set that is the intersection of this set and the given set
     *
     * @param other The other set operand.
     * @return result - A new set that is the intersection of these two sets.
     * @post for all e in result, contains(e) and other.contains(e)
     */
    @Override
    public Set<E> intersection(Set<? extends E> other) {
        ArrayListSet<E> listSet = new ArrayListSet<>();
        for (E e : other) {
            if (list.contains(e)) {
                listSet.add(e);
            }
        }
        return listSet;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Set.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Set.super.spliterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ArrayListSet)) {
            return false;
        }
        List<?> objList = ((ArrayListSet<?>) obj).list;
        if (objList.size() != size()) {
            return false;
        }
        for (Object o : objList) {
            if (!list.contains(o)) {
                return false;
            }
        }
        return true;
    }
}
