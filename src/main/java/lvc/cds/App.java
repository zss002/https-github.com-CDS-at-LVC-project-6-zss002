package lvc.cds;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

import org.graalvm.compiler.core.common.util.ReversedList;

import ods.ALGraph;
import ods.ALGraph.Influencer;

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
        // System.out.println(social.distance(1, 0));

        // for(int i = 0; i < social.bfs(1).length; i++){
        //     System.out.println(social.bfs(1)[i]);
        // }

        // social.printOutEdges(1);
        // System.out.println(social.distance(0, 5));
        // double [] ranks = social.influencerRanking();
        // for(int i = 0; i < ranks.length; i++){
        //     System.out.println("The influencer score of influencer #" +i+ " is: " +ranks[i]);
        // }

        Influencer list = new Influencer("users.txt");
        String[] userlist = new String[list.numusers];
        String[][] followers = new String[list.numusers][];
        


        for(int i = 0; i < list.users.length; i++){
            userlist[i] = list.users[i][0];
            // System.out.println(userlist[i]);
            followers[i] = list.users[i][1].split("\\s+");
            // System.out.println(followers[i]);

        }

        Hashtable<String, Integer>  number = new Hashtable<String, Integer>();
        for(int i = 0; i<list.numusers; i++){
            number.put(userlist[i] , i);
        }

       



        ALGraph influencers = new ALGraph(list.numusers);
        for(int i = 0; i<list.numusers; i++){
            for(int k = 0; k<followers[i].length; k++)
                influencers.addEdge(i, number.get(followers[i][k]));
        }

        double [] ranks = influencers.influencerRanking();
        // for(int i = 0; i < ranks.length; i++){
        //     System.out.println("The influencer score of influencer " + userlist[i] + " is: " +ranks[i]);
        // }

        //top 10 influencers
        Hashtable<String, Double> topinfluencers = new Hashtable<String, Double>();
        for(int i = 0; i<list.numusers; i++) {
            topinfluencers.put(userlist[i], ranks[i]);
        }

        // Hashtable<String, Double> top = new Hashtable<String, Double>();

        // topinfluencers.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        // .forEachOrdered(x -> top.put(x.getKey(), x.getValue()));


        // top.entrySet().stream().forEach(System.out::println);


    
        
        
        // Arrays.sort(ranks);

        // double[] sortedranks = new double[ranks.length];
        // for(int k = 0; k<sortedranks.length; k++){
        //         sortedranks[k] = ranks[sortedranks.length - 1 - k];
        //     }
          

        // for(int q =0; q<10; q++){
        //     System.out.println(topinfluencers.get(sortedranks[q]));
        // }

        


        


       
        

   





        
        
    
    }
}
