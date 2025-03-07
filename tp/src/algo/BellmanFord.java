package algo;

import java.util.Arrays;

// Algo from https://www.geeksforgeeks.org/java-program-to-implement-bellman-ford-algorithm/
public class BellmanFord {
	// Création d'une arête du graphe
	static class Arete {
		int source, destination, weight;

		Arete() {
			source = destination = weight = 0;
		}
	}
	
	int nb_sommets, nb_aretes;
	Arete aretes[];
	
	// Constructeur qui permet d'initialiser la liste des arêtes
	BellmanFord(int v, int e) {
		nb_sommets = v;
	    nb_aretes = e;
	    aretes = new Arete[e];
	    for (int i = 0; i < e; ++i)
	    	aretes[i] = new Arete();
	}
	
	// Implémentation de l'algorithme de Bellman-Ford
	void BellmanFordAlgo(BellmanFord graph, int source) {
	    int nb_sommets = graph.nb_sommets, nb_aretes = graph.nb_aretes;
	    int dist[] = new int[nb_sommets];
	
	     // Etape 1: Initialise la distance du sommet source à tous les autres sommets à une valeur infinie
	     Arrays.fill(dist, Integer.MAX_VALUE);
	     dist[source] = 0;
	
	     // Etape 2: Parcours de toutes les arêtes |nb_sommets| - 1 fois.
	     for (int i = 1; i < nb_sommets; ++i) {
	         for (int j = 0; j < nb_aretes; ++j) {
	             int u = graph.aretes[j].source;
	             int v = graph.aretes[j].destination;
	             int weight = graph.aretes[j].weight;
	             if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
	                 dist[v] = dist[u] + weight;
	         }
	     }
	
	     // Etape 3: Verification de la non existence d'un cycle négatif
	     for (int j = 0; j < nb_aretes; ++j) {
	         int u = graph.aretes[j].source;
	         int v = graph.aretes[j].destination;
	         int weight = graph.aretes[j].weight;
	         if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
	             System.out.println("Graph contains negative weight cycle");
	             return;
	         }
	     }
	
	     printDistances(dist, nb_sommets);
	 }
	
	 // Affichage de la distance du sommet source à tous les autres sommets
	void printDistances(int dist[], int nb_sommets) {
		System.out.println("Sommets Distance from Source:");
	    for (int i = 0; i < nb_sommets; ++i)
	    	System.out.println(i + "\t\t" + dist[i]);
	}
	
	public static void main(String[] args) {
		int nb_sommets = 5;
		int nb_aretes = 8;
		BellmanFord graph = new BellmanFord(nb_sommets, nb_aretes);
	
		// Définition des arêtes
		// Arêtes 0-1
		graph.aretes[0].source = 0;
		graph.aretes[0].destination = 1;
		graph.aretes[0].weight = -1;
		 
		// Arêtes 0-2
	     graph.aretes[1].source = 0;
	     graph.aretes[1].destination = 2;
	     graph.aretes[1].weight = 4;
	   
	     // Arêtes 1-2
	     graph.aretes[2].source = 1;
	     graph.aretes[2].destination = 2;
	     graph.aretes[2].weight = 3;
	   
	     // Arêtes 1-3
	     graph.aretes[3].source = 1;
	     graph.aretes[3].destination = 3;
	     graph.aretes[3].weight = 2;
	   
	     // nb_aretesdge 1-4
	     graph.aretes[4].source = 1;
	     graph.aretes[4].destination = 4;
	     graph.aretes[4].weight = 2;
	   
	     // Arêtes 3-2
	     graph.aretes[5].source = 3;
	     graph.aretes[5].destination = 2;
	     graph.aretes[5].weight = 5;
	   
	     // Arêtes 3-1
	     graph.aretes[6].source = 3;
	     graph.aretes[6].destination = 1;
	     graph.aretes[6].weight = 1;
	   
	     // Arêtes 4-3
	     graph.aretes[7].source = 4;
	     graph.aretes[7].destination = 3;
	     graph.aretes[7].weight = -3;
	
	     // Execute l'algorithme de Bellman-Ford
	     graph.BellmanFordAlgo(graph, 0);
	 }
}