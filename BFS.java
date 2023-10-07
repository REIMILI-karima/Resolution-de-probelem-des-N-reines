package projet1;

import java.util.*;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BFS {
    //ecrire dans un fichier les valeurs de variables : 
    //static = s'execute une seule fois 
	private static int[] nbnodesgen;
	private static int[]nbnodesexp;
    static {
        	try {
                // initialiser le fichier 
        		FileWriter fw = new FileWriter("BFS.txt",true);
        		//ecrire les headers 
                fw.write("taille de tableau"+","+"temps d'execution");
                //fermer le fichier  
                fw.close();
        }
            catch (IOException e) {
                e.printStackTrace();
            }

    }



    //methode pour ecrire dans un fichier
    public static void sauvegarder(int i,long temps) {
    	try {
    		FileWriter fw = new FileWriter("BFS.txt",true);
    		fw.write("\n"+i+","+temps);
            fw.close();
    }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static int[] solveBFS(int n) {
        //contient les positions de chaque reine (colonne)
        int[] board = new int[n];
        //stockera les tableaux représentant les différentes configurations possibles du plateau
        Queue<int[]> queue = new LinkedList<>();
        //ajouter le tableau initial
        queue.offer(board);
        //initialise deux compteurs compter 
        // pour compter le nombre de noeuds générés et le nombre de noeuds explorés.
        int generatedNodes = 1;
        int expandedNodes = 0;
        //si queue est vide alors il n'y a plus de configurations à explorer
        while (!queue.isEmpty()) {
            int[] currentBoard = queue.poll();
            expandedNodes++;
            int row = 0;
            //chercher case vide 
            while (row < n && currentBoard[row] != 0) {
                row++;
            }
            //Si toutes les lignes ont été remplies, cela signifie qu'une solution a été trouvée
            if (row == n) {
                // Solution trouvée
            	
                printBoard(currentBoard);
                System.out.println("Generated nodes: " + generatedNodes);
                System.out.println("Expanded nodes: " + expandedNodes);
                return currentBoard;
            }
            //si la solution n a pas encoure trouvee 
            for (int col = 1; col <= n; col++) { // boucle sur toutes les colonnes           	
                if (BFS.isValid(currentBoard, row, col)) {// vérification si la case est valide pour placer une reine
                	
                    int[] newBoard = Arrays.copyOf(currentBoard, n); // création d'un nouveau plateau avec une reine de plus
                    newBoard[row] = col; // placement de la reine
                    queue.offer(newBoard);
                    generatedNodes++;
                }
            }
        }
        return board;
    }


    public static boolean isValid(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) { // boucle sur toutes les lignes précédentes
            if (board[i] == col || Math.abs(board[i] - col) == row - i) { // vérification des conflits
                return false; // la case n'est pas valide
            }
        }
        return true; // la case est valide
    }

    public static void printBoard(int[] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) { // boucle sur toutes les lignes
            for (int j = 0; j < n; j++) { // boucle sur toutes les colonnes
                if (board[i] == j + 1) { // si la case contient une reine
                    System.out.print("Q ");
                } else { // sinon
                    System.out.print(". ");
                }
            }
            System.out.println(); // saut de ligne à la fin de chaque ligne
        }
        System.out.println(); // saut de ligne à la fin du plateau
        
    }

}
