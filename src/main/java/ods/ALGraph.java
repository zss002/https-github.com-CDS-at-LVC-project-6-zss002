package ods;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
//lets do this thing

public class ALGraph {
    private Vertex[] graph;
    private boolean isDirected;

    public ALGraph(int numVertices) {
        this(numVertices, true);
    }

    public ALGraph(int numVertices, boolean isDirected) {
        graph = new Vertex[numVertices];
        for (int i = 0; i < graph.length; ++i) {
            graph[i] = new Vertex();
        }
        this.isDirected = isDirected;
    }

    public void addEdge(int i, int j) {
        addEdge(i, j, 1.0);
    }

    public void addEdge(int i, int j, double w) {
        if (!vertexCheck(i) || !vertexCheck(j))
            throw new ArrayIndexOutOfBoundsException();
        if (i == j || hasEdge(i, j))
            return;
        graph[i].edges.add(new Edge(j, w));

         if (!isDirected) {
            graph[j].edges.add(new Edge(i));
        }
    }

    public int[] bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] pred = new int[graph.length];
        Arrays.fill(pred, -1);

        // prime our data structures
        queue.add(start);
        pred[start] = start;

        while (!queue.isEmpty()) {
            var cur = queue.poll();

            for (var e : graph[cur].edges) {
                if (pred[e.target] == -1) {
                    pred[e.target] = cur;
                    queue.add(e.target);
                }
            }
        }

        return pred;
    }

    public boolean[] dfs(int start) {
        boolean[] reachable = new boolean[graph.length];
        reachable[start] = true;
        dfs(start, reachable);
        return reachable;
    }

    private void dfs(int start, boolean[] reachable) {
        for (var e : graph[start].edges) {
            if (!reachable[e.target]) {
                reachable[e.target] = true;
                dfs(e.target, reachable);
            }
        }
    }

    private boolean vertexCheck(int i) {
        return i >= 0 && i < graph.length;
    }

    private boolean hasEdge(int i, int j) {
        var edges = graph[i].edges;
        for (var e : edges) {
            if (e.target == j) {
                return true;
            }
        }
        return false;
    }


    private static class Vertex {
        ArrayList<Edge> edges;
        // should we store its int id?

        Vertex() {
            edges = new ArrayList<>();
        }
    }

    private static class Edge {
        int target;
        // should we store its source?
        double weight;

        Edge(int target, double weight) {
            this.target = target;
            this.weight = weight;
        }

        Edge(int target) {
            this.target = target;
            this.weight = 1.0;
        }
    }
}
