package lvc.cds;


import java.util.ArrayList;
import java.util.Collections;
import ods.ALGraph;
import ods.ALGraph.Influencer;
import ods.ALGraph.InfluencerRank;
//finished

public final class App {
    public static void main(String[] args) {
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
      
        //social distance lol!
        System.out.println("An Arbitrary example");
        System.out.println("==========================================");
        double [] ranks = social.influencerRanking();
        for(int i = 0; i < ranks.length; i++){
            System.out.println("The influencer score of influencer #" +i+ " is: " +ranks[i]);
        }
        System.out.println();
        System.out.println("Now let's look at Bubbas Website!");
        System.out.println("Each user and their respective influencer score is listed below.");
        System.out.println("==========================================");

        Influencer list = new Influencer("users.txt");
        ArrayList<InfluencerRank> influence = new ArrayList<>();
        influence = list.rankcreator();
        //an arraylist is returned of InfluencerRank objects containing each user's name and score.
        Collections.sort(influence);
        for(int i =0; i<influence.size(); i++){
            System.out.println(influence.get(i).name + ": " +influence.get(i).rank);
        }

        System.out.println();
        System.out.println("The Top 10 Influencers on Bubba's site are");
        System.out.println("==========================================");
        int j = 1;
        for(int i =influence.size()-1; i>influence.size()-11; i--){
            System.out.println(j + ". " + influence.get(i).name);
            j++;
        }    
    }
}

//the runtime of influencer ranking is O(v*(v+e)).
// This is because we are calling BFS on each vertex in the graph.
//The rest of the operations are constant and do not add to the run time.