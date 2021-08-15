package stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A Simple Stack.
 * @param <E>
 * 
 */
public class Stack<E> implements Iterable<E> {
    
    /**
     * Pushes an element onto the top of the stack.
     * @param element
     */
    public void push(E element) {}

    /**
     * Removes the top element of the stack, and returns that element.
     * @precondition The stack is not empty.
     */
    public E pop() {
        return null;
    }

    /**
     * Returns the top element of the stack, without removing it.
     */
    public E peek() {
        return null;
    }

    /**
     * Returns an iterator to the internal data structure of the stack.
     */
    public Iterator<E> iterator() {
        return null;
    }

    /**
     * Returns the size of the stack.
     */
    public int size() {
        return 0;
    }
    
    /**
     * Returns the stack as an ArrayList
     */
    public ArrayList<E> toArrayList() {
        return null;
    }

    public static Integer sumStack(Stack<? extends Integer> stack) {
        return 0;
    }

    public static void prettyPrint(Stack<?> stack) {}
        

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