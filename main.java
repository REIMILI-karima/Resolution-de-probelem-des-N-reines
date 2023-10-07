package projet1;

import java.util.*;
public class main {
   public static void main(String[] args) {
	/////////méthode BFS///////////////
	 System.out.println("###########resolution avec la methode BFS...#######");  
	int n=15;
   	for (int i=8;i<n;i++) {
   	   long start=System.currentTimeMillis();
   	      int[] board = BFS.solveBFS(i);
   	      long endtime=System.currentTimeMillis();
          long time=endtime-start;
          BFS.sauvegarder(i,time);
          System.out.println(Arrays.toString(board));
          
   	}
   	
   	/////////méthode DFS //////////////////
    System.out.println("###########resolution avec la methode DFS...#######");
	for (int i=8;i<n;i++) {
	   long start=System.currentTimeMillis();
	   int[] board = DFS.solveDFS(i);
       long endtime=System.currentTimeMillis();
       long time=endtime-start;
       DFS.sauvegarder(i,time);
       System.out.println(Arrays.toString(board));
       
	}
   }
}
