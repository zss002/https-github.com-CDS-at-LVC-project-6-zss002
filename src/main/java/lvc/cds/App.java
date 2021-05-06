package lvc.cds;

import ods.ALGraph;

public final class App {
    public static void main(String[] args) {
        System.out.println("Hello World");
        ALGraph social = new ALGraph(8);
        social.addEdge(0, 4);
        social.addEdge(4, 0);
        social.addEdge(2, 0);
        social.addEdge(0, 1);
        social.addEdge(1, 0);
        social.addEdge(1, 2);
        social.addEdge(2, 1);
        social.addEdge(1, 3);
        social.addEdge(3, 2);
        social.addEdge(4, 5);
        social.addEdge(6, 1);
        social.addEdge(7, 5);
        // System.out.println(social.distance(1, 0));

        // for(int i = 0; i < social.bfs(1).length; i++){
        //     System.out.println(social.bfs(1)[i]);
        // }

        // social.printOutEdges(1);
        // System.out.println(social.distance(0, 5));
        System.out.println(social.influenceScore(1));
    
    }
}
