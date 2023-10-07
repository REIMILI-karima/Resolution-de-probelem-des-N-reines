import java.util.*;

public class GA {
    private int n;
    private int populationSize;
    private int maxGenerations;
    private double crossoverProbability;
    private double mutationProbability;
    private int[] bestSolution;
    
    public GA(int n, int populationSize, int maxGenerations, double crossoverProbability, double mutationProbability) {
        this.n = n;
        this.populationSize = populationSize;
        this.maxGenerations = maxGenerations;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
        this.bestSolution = new int[n];
    }
    
    private int fitness(int[] solution) {
        int conflicts = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
            	//meme colonne 
                if (solution[i] == solution[j]) {
                    conflicts++;
                }
                //meme diagunale
                if (Math.abs(solution[i] - solution[j]) == j - i) {
                    conflicts++;
                }
            }
        }
        return  conflicts;
    }
    
    private int[] createRandomSolution() {
        int[] solution = new int[n];
        for (int i = 0; i < n; i++) {
            solution[i] = (int) (Math.random() * n);
        }
        return  solution;
    }
    
    private int[][] createRandomPopulation() {
        int[][] population = new int[populationSize][n];
        for (int i = 0; i < populationSize; i++) {
            population[i] = createRandomSolution();
        }
        return population;
    }
    

    public int[] selectParents(int[][] population) {
        	Random random = new Random();
            int index1 = random.nextInt(population.length);
            int index2 = random.nextInt(population.length);
            //choisir celui qui a la meilleure performance
            return (fitness(population[index1]) < fitness(population[index2])) ? population[index1] : population[index2];
    }
    
    
    private int[] crossover(int[] parent1, int[] parent2) {
        int[] child = new int[n];
        int crossoverPoint = (int) (Math.random() * (n - 1));
        for (int i = 0; i <= crossoverPoint; i++) {
            child[i] = parent1[i];
        }
        for (int i = crossoverPoint + 1; i < n; i++) {
            child[i] = parent2[i];
        }
        return child;
    }
    
    private int[] mutation(int[] solution) {
        int[] mutatedSolution = Arrays.copyOf(solution, n);
        int mutationPoint = (int) (Math.random() * n);
        mutatedSolution[mutationPoint] = (int) (Math.random() * n);
        return mutatedSolution;
    }

    public int[] geneticAlgorithm() {
        // Création de la population initiale
        int[][] population = createRandomPopulation();
        for (int generation = 0; generation < maxGenerations; generation++) {
            // Création d'une nouvelle population vide
            int[][] newPopulation = new int[populationSize][n];
            // Initialisation des variables de fitness pour le meilleur individu
            int bestFitness = -1;
            int[] bestIndividual = null;
            // Boucle pour chaque individu de la population
            for (int i = 0; i < populationSize; i++) {
                // Sélection de deux parents
                int[] parent1 = selectParents(population);
                int[] parent2 = selectParents(population);
                int[][] parents = new int[2][n];
                parents[0] = parent1;
                parents[1] = parent2;
                // Initialisation de l'enfant
                int[] child = new int[n];
                // Crossover (croisement) avec une certaine probabilité
                if (Math.random() < crossoverProbability) {
                    child = crossover(parents[0], parents[1]);
                }
                // Mutation avec une certaine probabilité
                if (Math.random() < mutationProbability) {
                    child = mutation(child);
                }
                // Ajout de l'enfant à la nouvelle population
                int index = (int) (Math.random() * populationSize);
                newPopulation[index] = child;
                // Calcul du fitness de l'enfant
                int fitness = fitness(child);
                // Mise à jour du meilleur individu si nécessaire
                if (fitness < bestFitness) {
                    bestFitness = fitness;
                    bestIndividual = child;
                }
            }
            // Remplacement de la population actuelle par la nouvelle population
            population = newPopulation;
            // Si le meilleur individu de cette génération est la solution optimale, on retourne la solution
            if (bestFitness == n * (n - 1) / 2) {
                bestSolution = bestIndividual;
                return bestSolution;
            }
        }
        // Si la boucle se termine sans avoir trouvé la solution optimale, on retourne null
        bestSolution = null;
        return bestSolution;
    }


    public void printSolution() {
        if (bestSolution == null) {
            System.out.println("No solution found.");
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (bestSolution[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 20;//taille max de probleme      
        int populationSize = 100;
        int maxGenerations = 100;
        float[] TEcrossever =new float[11];
        float[] TEmutation =new float[11];
        int j=0;
        double mutationProbability = 0.1;
        long tempsmoy= 0;
        float moy =0 ;
        double crossoverProbability=0;
        for (crossoverProbability=0;crossoverProbability<=1;crossoverProbability=crossoverProbability+0.1) {
        	for (int i=8;i<n;i++) {
            	for (int k=0;k<5;k++) {
                	GA nQueensGA = new GA(n, populationSize, maxGenerations, crossoverProbability, mutationProbability);
                	long start=System.currentTimeMillis();
                	nQueensGA.geneticAlgorithm();
                	long endtime=System.currentTimeMillis();
                    long temps=endtime-start;
                    //nQueensGA.printSolution();
                    tempsmoy=tempsmoy+temps;
                    
            	}
            	moy = moy +tempsmoy/5;
            }
        	TEcrossever[j]=moy/20;
            j++;
        }
        j=0;
        crossoverProbability=0.8;
        for (mutationProbability =0;mutationProbability<=1;mutationProbability=mutationProbability+0.1) {
        	for (int i=8;i<n;i++) {
            	for (int k=0;k<5;k++) {
                	GA nQueensGA = new GA(n, populationSize, maxGenerations, crossoverProbability, mutationProbability);
                	long start=System.currentTimeMillis();
                	nQueensGA.geneticAlgorithm();
                	long endtime=System.currentTimeMillis();
                    long temps=endtime-start;
                    //nQueensGA.printSolution();
                    tempsmoy=tempsmoy+temps;
                    
            	}
            	moy = moy +tempsmoy/5;
            }
        	TEmutation[j]=moy/20;
            j++;
        }
       /* double crossoverProbability = 0.8;
        
        for (mutationProbability=0;mutationProbability<=1;mutationProbability=mutationProbability+0.1) {
        	GA nQueensGA = new GA(n, populationSize, maxGenerations, crossoverProbability, mutationProbability);
        	long start=System.currentTimeMillis();
        	nQueensGA.geneticAlgorithm();
        	long endtime=System.currentTimeMillis();
            long temps=endtime-start;
            TEmutation[j]=temps;
            j++;
            //nQueensGA.printSolution();
        }*/
        System.out.println( Arrays.toString(TEmutation)+"\n");
        
        System.out.println( Arrays.toString(TEcrossever)+"\n");
    }
}