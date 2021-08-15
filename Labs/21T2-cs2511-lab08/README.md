# Lab 08

### Due: Week 8 Sunday, 5pm

### Value: 2 marks towards the Class Mark

## Aim

* Gain experience in Generic Programming
* Understand the Iterator Pattern
* Apply the Iterator Pattern to non-linear data structures
* Revise Graphs, Depth-First Search and Breadth-First Search
* Understand the concepts of Iterators and Iterables

## Setup

An individual repository for you for this lab has been created for you on the CSE GitLab server. You can find it at this URL (substituting z5555555 for your own zID):

[https://gitlab.cse.unsw.edu.au/COMP2511/21T2/students/z5555555/21T2-cs2511-lab08](https://gitlab.cse.unsw.edu.au/COMP2511/21T2/students/z5555555/21T2-cs2511-lab08)

**REMEMBER** to replace the zID below with your own.

`git clone gitlab@gitlab.cse.unsw.EDU.AU:COMP2511/21T2/students/z555555/21T2-cs2511-lab08.git`

## Lab 08 - Exercise - Set 📃

Inside `src/unsw/collections`, there is a `Set` interface which represents a generic [mathematical set](https://en.wikipedia.org/wiki/Set_(mathematics)). There's already an existing `Set` object in `java.util`, but in this exercise we are going to implement our own set that uses Generic Programming. 

Create a new file called `ArrayListSet.java` which implements the `Set` interface, and models a Set that is implemented using an `ArrayList` (i.e. the class is simply a wrapper around a Java `ArrayList`, the underlying data structure, and provides a few abstractions that allow it to be conceptually treated as a Set). Your task is to implement the methods without making any changes to the `Set` interface and ensuring it is consistent with the documentation and contract, including the invariant specified in the interface docstring. 

Your `ArrayListSet` also needs to override the `equals` method. 

Some basic tests have been provided which don't currently compile as the classes themselves do not exist. Feel free to add more tests if you wish.

<details>
<summary>Hint</summary>

* The constructor for `ArrayListSet` should take in no arguments.
* Use your knowledge of Set definitions from Discrete Maths to help you implement the class, or research online.
* For `equals(...)`, you will need to use `instanceof` and wildcards (`?`) in the body of the method.

</details>

## Lab 08 - Exercise - Graph Iterator 🔗

The Iterator Pattern allows us to traverse a data structure whilst maintaining the abstraction of the data structure's underlying details. An iterator is a black-box that we can keep asking to give us elements, until it runs out. This occurs in a *linear* fashion (we ask for elements one at a time), and the Iterator Pattern hence allows us to *linearise* any data structure. 

For `Set`s, `ArrayList`s and plenty of other data structures this is all very well because the ADT itself is already linear, conceptually. But what about something that's non-linear, like a graph? So long as we have some sort of sequence to accessing elements of the data structure, we can build an iterator.

In this exercise, you will be using the Iterator Pattern to write two iterators - one which traverses a graph using **Breadth-First Search** and one that traverses a graph using **Depth-First Search**.

Inside `src/graph/Graph.java`, we have written a Generic `Graph` class which models an undirected graph using an **adjacency list** with a `HashMap`. To recall, an adjacency list stores the graph in the following format:

```
Node : [ All the nodes the node is adjacent to ]
```

### BFS

Create a new class called `BreadthFirstGraphIterator.java` that uses BFS to traverse a graph, given that graph and a starting node. Each subsequent call to the `next` method should 'tick' the BFS by one (i.e. the next element is looked at). You may not pre-traverse the graph and store the nodes to visit in an `ArrayList` or similar and simply pawn off the job to that.

BFS Pseudocode:

```
queue = []
visited = set()
while queue:
    vertex = queue.dequeue()
    visited.add(vertex)
    queue.extend(graph.get_adjacent(vertex) - visited)
```

Inside `Graph.java`, write a method called `breadthFirstIterator` which returns a new BFS iterator.

### DFS

Create a new class called `DepthFirstGraphIterator.java` that uses DFS to traverse a graph, given that graph and a starting node. Each subsequent call to the `next` method should 'tick' the DFS by one (i.e. the next element is looked at). You may not pre-traverse the graph and store the nodes to visit in an `ArrayList` or similar and simply pawn off the job to that.

Inside `Graph.java`, write a method called `depthFirstIterator` which returns a new DFS iterator.

If you need to brush up on Graphs, here are a few links to COMP2521 lectures:
* [Graph ADT](https://www.youtube.com/watch?v=4s_3uirIGM8&list=PLi2pCZz5m6GEftzPIxVH1ylwytux9WOGN&index=16)
* [Graph Implementations](https://www.youtube.com/watch?v=2hbR-aez1E4&list=PLi2pCZz5m6GEftzPIxVH1ylwytux9WOGN&index=17)
* [Graph Traversal](https://www.youtube.com/watch?v=DzdztZboQ6w&list=PLi2pCZz5m6GEftzPIxVH1ylwytux9WOGN&index=18)

Some simple tests have been provided for you inside `GraphTest.java`, they don't currently compile as the Iterator classes themselves do not exist.

The second test uses this Graph:

<img src='graph.png' width='500' />


<details>
<summary>Hints</summary>

* You will not be able to use recursion to do the DFS.
* Java provides collections which will help you with the implementation of the algorithm.

</details>

### Iterators & Iterables

Change the definition of `Graph` so that it is `Iterable`. By default, the graph will traverse itself using a BFS, starting with the first node that was added to the graph. Write a test for this that loops through a graph. 

Inside `iterator.md`, answer the following questions:

* Do you think making the `Graph` `Iterable` makes semantic sense? Discuss briefly, and think of both sides.
* We could change the definition of our `Graph` so that the traversal logic is done internally, i.e:

    ```java
    public class Graph<N extends Comparable<N>> implements Iterable<N>, Iterator<N>
    ```

* Is a `Graph` an iterator or an iterable in this case? 
* What would the `.iterator` method return in this case?
* There is a problem with this approach though. Inside `iterator.md`, describe a test that would cause this implementation to fail.


## Lab 08 - Challenge Exercise - Set 2.0 📜

In this exercise, we will be writing another implementation of the `Set` interface - this time, using a primitive array to store our set of generic elements. We'd never actually do this in a Java program such as your project as you would always use an `ArrayList` or some other Java-provided collection object. This exercise aims to make you think like someone who would write the `ArrayList` library - because under the hood, an `ArrayList` is just a primitive array with some fancy abstractions that make it nice for us to use. 

Create a new file called `ArraySet.java` which implements `Set`, using a primitive array to store elements. Your `ArraySet` also needs to override the `equals` method. 

Most of the implementation will be relatively straightforward; you'll just need to keep track of more things in a C-style of programming in utilising the array. There are a few curvy parts, however:

* Java, for all its glorious purpose [doesn't allow us to instantiate arrays of generic objects, among other restrictions](https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html). We can get around this using a small hack in the `ArraySet` constructor:

    ```java
    public ArraySet(Class<E> cls, int length) {
        elements = (E[]) Array.newInstance(cls, length);
        // ...
    }
    ```

    The `Class` class is the class that models classes themselves (that was a mouthful). In simpler terms, if you want to talk about the definition of a class type itself in Java, as an object that you can see and touch like a `String`, `Random`, `SpaceXSatellite` or `LoopManiaWorld`, `Class` is the type of these objects. 

* Constructing an `ArraySet` is a bit ugly because of this. We pass in `String.class` which is our `Class` object, and the length of the array. Ideally, we wouldn't have to pass in any arguments (like with `ArrayListSet`) and instead do something like `new ArraySet<String, 3>` which is much cleaner, but as you have probably figured out by now, Java can be quite clunky at times. Here is how we can instantiate our object.

    ```java
    Set<String> set = new ArraySet<String>(String.class, 3);
    ```

* What about the `.iterator` method? We will need to write this ourselves. Use the Iterator Pattern by defining a class `ArraySetIterator` which is parameterised with a generic type. The `.iterator` method in `ArraySet` should return a new instance of `ArraySetIterator`.

Some basic tests have been provided. They don't compile as the classes themselves do not exist. Feel free to add more tests if you wish.

## Lab 08 - Challenge Exercise - Tribute 🎸

Consider the song [Tribute](https://www.youtube.com/watch?v=_lK4cX5xGiQ). What Object Oriented Design Principle does the song embody? Write it down inside `tribute.md`.

## Submission

To submit, make a tag to show that your code at the current commit is ready for your submission using the command:

```bash
$ git tag -fa submission -m "Submission for Lab-08"
$ git push -f origin submission
```

Or, you can create one via the GitLab website by going to **Repository > Tags > New Tag**.

We will take the last commit on your `master` branch before the deadline for your submission.

