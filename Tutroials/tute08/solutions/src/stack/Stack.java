package stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A Simple Stack.
 * @param <E>
 * 
 * @author Nick Patrikeos
 */
public class Stack<E> implements Iterable<E> {
    
    private List<E> elements = new ArrayList<E>();

    /**
     * Pushes an element onto the top of the stack.
     * @param element
     */
    public void push(E element) {
        elements.add(element);
    }

    /**
     * Removes the top element of the stack, and returns that element.
     * @precondition The stack is not empty.
     */
    public E pop() {
        E back = peek();
        elements.remove(elements.size() - 1);
        return back;
    }

    /**
     * Returns the top element of the stack, without removing it.
     */
    public E peek() {
        E back = elements.get(elements.size() - 1);
        return back;
    }

    /**
     * Returns an iterator to the internal data structure of the stack.
     */
    public Iterator<E> iterator() {
        List<E> copy = new ArrayList<E>(elements);
        Collections.reverse(copy);
        return copy.iterator();
    }

    /**
     * Returns the size of the stack.
     */
    public int size() {
        return elements.size();
    }

    /**
     * Returns the stack as an ArrayList
     */
    public ArrayList<E> toArrayList() {
        ArrayList<E> copy = new ArrayList<E>(elements); // important here that we don't return a pointer and give the user the keys!!
        return copy;
    }

    public static Integer sumStack(Stack<? extends Integer> stack) {
        int total = 0;
        for (Integer element : stack) {
            total += element;
        }
        return total;
    }

    public static void prettyPrint(Stack<?> stack) {
        System.out.print("[");
        if (stack.size() == 0) {
            System.out.println("]");
        }

        Iterator<?> iter = stack.iterator();

        for (int i = 0; i < stack.size() - 1; i++) {
            System.out.print(iter.next() + " ");
        }
        System.out.println(iter.next() + "]");
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        stack.push("hello");
        stack.push("how");
        stack.push("are");
        stack.push("you");
        stack.push("today");
        prettyPrint(stack);
    }

}