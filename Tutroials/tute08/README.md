# Tutorial 08

## A. Generic Programming (30 minutes)

### Part 1 - Implementation

Inside `src/stack`, there are a series of stubs for a `Stack` class which takes in a generic type. There are a series of tests inside `StackTest.java` which currently fail. 

Implement the methods so that the tests pass, using an `ArrayList` to store the internal data structure. Answer the following questions:

1. What is `E`? 
2. What is the `Iterable` interface? Why does it have an `E` as well? What methods does it force us to implement?
3. When completing `toArrayList`, why do we need to make a copy rather than just returning our internal ArrayList?
4. What does the `.iterator()` method allow us to do? Discuss the test inside `StackTest.java`.

### Part 2 - Utility Functions & Iterators

Inside `Stack.java`, add the following static utility functions:

```java
public static Integer sumStack(Stack<? extends Integer> stack);
```

5. What does the `<? extends Type>` and `<? super Type>` mean?
6. How could we change our class definition to restrict the parameterisation?

```java
public static void prettyPrint(Stack<?> stack);
```

Having pushed the words "hello", "how", "are", "you", "today" on, the following should be printed:

```
[today you are how hello]
```

7. What is the difference between `?` and `E`?
8. We need to go through the stack and print out each element with a space, except for the last one (which doesn't have a space). There are a couple of ways to do this, but create an `Iterator` and use the `.next()` method to traverse the stack.

## B. Refactoring to Patterns

### File System Viewer

Your task is to refactor a simple FileSystem viewer using both low level and design level refactoring to enable you to have an estimation of a file size (that updates as you open inner folders).  The original code consists of only 150 lines of code and you won't be writing significantly more than that.

The folder `fs` contains a very simple file system viewer using a TreeView that was pretty heavily modified from a beautiful guide given by; https://huguesjohnson.com/programming/java/javafx-treeview-browser/ (license in LICENSE).

Here is some hints:
- You'll want some backend structure to represent the file system.
- Since the file size estimation is going to be lazy you'll want some way to communicate changes into the model including re-estimations of file sizes.
- Don't just recalculate the file sizes with every changes, only stat what you need to.
