package wordnet;

import java.util.*;

public class Graph {
    private final int VERTEXNO;
    private List<Integer>[] adj;


    public Graph(int vertex) {
        this.VERTEXNO = vertex;
        adj = (List<Integer>[]) new ArrayList[vertex];

        for (int v = 0; v < vertex; v++) {
            adj[v] = new ArrayList<Integer>();
        }
    }

    //add an edge of v pointing towards w
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public Iterable<Integer> neighbor(int v) {
        return adj[v];
    }

    //explore the graph starting from node synsetID
    public Set<Integer> visitedNodes(int synsetID) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(synsetID);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                for (int neighbor : neighbor(current)) {
                    stack.push(neighbor);
                }
            }
        }
        return visited;
    }



}
