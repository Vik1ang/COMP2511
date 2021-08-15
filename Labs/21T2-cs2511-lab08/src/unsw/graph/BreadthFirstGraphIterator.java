package unsw.graph;

import java.util.*;

public class BreadthFirstGraphIterator<N extends Comparable<N>> implements Iterator<N> {

    private Graph<N> graph;
    Deque<N> queue;
    Set<N> visited;

    public BreadthFirstGraphIterator(Graph<N> graph, N first) {
        this.graph = graph;
        queue = new LinkedList<>();
        visited = new HashSet<>();
        queue.add(first);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public N next() {
        if (!hasNext()) {
            return null;
        }
        N node = queue.pollFirst();
        visited.add(node);
        List<N> adjacentNodes = graph.getAdjacentNodes(node);
        for (N adjacentNode : adjacentNodes) {
            if (!visited.contains(adjacentNode) && !queue.contains(adjacentNode)) {
                queue.add(adjacentNode);
            }
        }
        return node;
    }
}
