# Iterator Questions

a) Do you think making the Graph Iterable makes semantic sense? Discuss briefly, and think of both sides.

Yes. 


b) Is a Graph an iterator or an iterable in this case?

Yes

c) What would the .iterator method return in this case?

```java
public Iterator<E> iterator() {
    // your code here
    return iterator<E>
}
```

d) What is the problem with this approach?

It has to iterate at the beginning.