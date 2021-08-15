package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tests for the Stack ADT. 
 * @author Nick Patrikeos
 */
public class StackTest {
    @Test
    public void testStackPush() {
        Stack<String> stack = new Stack<String>();
        stack.push("hello");
        stack.push("how");
        assertEquals(new ArrayList<String>(Arrays.asList("hello", "how")), stack.toArrayList());
    }

    @Test
    public void testStackPop() {
        Stack<String> stack = new Stack<String>();
        stack.push("hello");
        stack.push("how");
        assertEquals("how", stack.pop());
        assertEquals("hello", stack.pop());
        assertEquals(new ArrayList<String>(), stack.toArrayList());
    }

    @Test
    public void testStackPeek() {
        Stack<String> stack = new Stack<String>();
        stack.push("hello");
        stack.push("how");
        assertEquals("how", stack.peek());
        assertEquals(new ArrayList<String>(Arrays.asList("hello", "how")), stack.toArrayList());
    }

    @Test
    public void testIterator() {
        Stack<String> stack = new Stack<String>();
        stack.push("hello");
        stack.push("how");
        stack.push("are");
        stack.push("you");
        stack.push("today");
        List<String> expected = new ArrayList<String>(Arrays.asList("today",
        "you", "are", "how", "hello"));
        int i = 0;

        for (String element : stack) {
            assertEquals(expected.get(i), element);
            i++;
        }
    }

    @Test
    public void testSum() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertEquals(10, Stack.sumStack(stack));
    }

}