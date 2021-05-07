package ods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
//lets do this thing
import java.util.Scanner;


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


    public int distance(int v, int v2){
        int [] preds = bfs(v);
        if (preds[v2] == -1){
            return 0;
        }
        else if (preds[v2] == v){
            return 1;

        }
        else{
            return 1 + distance(v, preds[v2]);
            
        }   
    }

    public double influenceScore(int v){
        int[] preds = bfs(v);
        ArrayDeque<Integer> R = new ArrayDeque<>();
        for(int i = 0; i <preds.length; i++){
            if (preds[i] != -1){
                R.add(i);
            }
        }
        double total = 0;
        double len = R.size()-1;
        // System.out.println("Reachable Vertices " +len);

        while(!R.isEmpty()){
            total += distance(v, R.poll());

        }

        total = total -1;
        if(total == 0 && len == 0){
            return -1;
        }
        double averagedist =total/len;
        // System.out.println( "Average Distance " + averagedist);
        double value = len/(graph.length-1);



        return value/averagedist;

    }

    public double[] influencerRanking() {
        double[] rankings = new double[graph.length];
        for(int i = 0; i<rankings.length; i++){
            rankings[i] = influenceScore(i);
        }
     
        return rankings;
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
    public void printOutEdges(int i){
        for(var e: graph[i].edges){
            System.out.println(e.target);
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
    // Create a class that has a constructor that takes a filename as parameter. 
    // The constructor should open that file, and create some data structures (in particular, an ALGraph, but you'll need more). 
    // This class should also have an instance method that I can call, passing no parameters and receiving back an array of pairs of strings and doubles. 
    // The strings should be user names and the doubles should be influencer scores. The array should be sorted by score.

    public static class Influencer {
        public String[][] users;
        public int numusers;
        
            public Influencer(String file){
                try {
                    File myObj = new File(file);
                    Scanner myReader = new Scanner(myObj);
                    String tempuser = myReader.nextLine();
                    numusers = Integer.parseInt(tempuser);
                    users = new String[numusers][];
                    for (int i = 0; i< users.length; i++){
                        users[i] = myReader.nextLine().split(":");
                    }
                    myReader.close();
                  } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                  }
                }
            }
        
}


    
