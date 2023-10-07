package projet1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DFS {
	//ecrire dans un fichier les valeurs de variables : 
    //static = s'execute une seule fois 
	
    static {
    	try {
            // initialiser le fichier 
    		FileWriter fw = new FileWriter("DFS.txt",true);
    		//ecrire les headers 
            fw.write("taille de tableau"+","+"temps d'execution");
            //fermer le fichier  
            fw.close();
    }
        catch (IOException e) {
            e.printStackTrace();
        }

}
    public static void sauvegarder(int i,long temps) {
    	try {
    		FileWriter fw = new FileWriter("DFS.txt",true);
    		fw.write("\n"+i+","+temps);
            fw.close();
    }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] solveDFS(int n) {
    	//initialise deux compteurs compter 
        // pour compter le nombre de noeuds g�n�r�s et le nombre de noeuds explor�s.
    	int generatedNodes = 1;
        int expandedNodes = 0;
      //contient les positions de chaque reine (colonne)
        int[] board = new int[n];
     // initialisation de la pile pour stocker les �tats du plateau
        Stack<int[]> stack = new Stack<>();      
     // ajout du plateau initial � la pile
        stack.push(board); 
     // boucle tant que la pile n'est pas vide
        while (!stack.isEmpty()) { 
            int[] currentBoard = stack.pop(); // r�cup�ration du dernier plateau ajout� � la pile
            expandedNodes++;
            int row = 0;
            while (row < n && currentBoard[row] != 0) { // recherche de la prochaine case vide
                row++;
            }
            if (row == n) { // si toutes les reines ont �t� plac�es, une solution a �t� trouv�e
                BFS.printBoard(currentBoard); // affichage de la solution
                
                System.out.println("Generated nodes: " + generatedNodes);
                System.out.println("Expanded nodes: " + expandedNodes);
                return currentBoard; // sortie de la fonction
            }
           
            for (int col = 1; col <= n; col++) { // boucle sur toutes les colonnes
            	
                if (BFS.isValid(currentBoard, row, col)) {// v�rification si la case est valide pour placer une reine
                	
                	int[] newBoard = Arrays.copyOf(currentBoard, n); // cr�ation d'un nouveau plateau avec une reine de plus
                    newBoard[row] = col; // placement de la reine
                    stack.push(newBoard);// ajout du nouveau plateau � la pile
                    generatedNodes++;
                }
            }
        }
        return board;
    }

    
}
