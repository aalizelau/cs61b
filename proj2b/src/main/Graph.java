package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Graph {
    private final int VERTEXNO;
    private int source;
    private List<Integer>[] adj;
    private boolean[] marked;
    private int[] edgeTo;


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

    public int getVERTEXNO(){
        return VERTEXNO;
    }

    public void DepthFirstPaths(int source) {
        marked = new boolean[VERTEXNO]; // assuming Graph has a method vertex() to get the number of vertices
        edgeTo = new int[VERTEXNO];
        this.source = source;
        dfsHelper(source);
    }

    private void dfsHelper(int v) {
        marked[v] = true;
        for (int w : neighbor(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfsHelper(w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;  // No path if vertex is not connected

        Stack<Integer> path = new Stack<Integer>();

        for (int x = v; x != source; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(source);
        return path;
    }
}
