package algo;

import java.util.Arrays;

// Algo from https://www.geeksforgeeks.org/java-program-to-implement-bellman-ford-algorithm/
public class BellmanFord {
	// Création d'une arête du graphe
	static class Arete {
		int source, destination, poids;

		Arete() {
			source = destination = poids = 0;
		}
	}
	
	int nb_sommets, nb_aretes, currentAreteAjoute;
	Arete aretes[];
	
	// Constructeur qui permet d'initialiser la liste des arêtes
	BellmanFord(int nb_sommets, int nb_aretes) {
		this.nb_sommets = nb_sommets;
	    this.nb_aretes = nb_aretes;
	    this.aretes = new Arete[nb_aretes];
	    for (int i = 0; i < nb_aretes; ++i)
	    	aretes[i] = new Arete();
	    
	    currentAreteAjoute = 0;
	}
	
	void addArete(int source, int destination, int weight) {
		aretes[currentAreteAjoute].source = source;
		aretes[currentAreteAjoute].destination  = destination;
		aretes[currentAreteAjoute].poids = weight;
		currentAreteAjoute++;
	}
	
	// Implémentation de l'algorithme de Bellman-Ford
	Integer[] bellmanFordAlgo(BellmanFord graph, int source) {
	    int nb_sommets = graph.nb_sommets, nb_aretes = graph.nb_aretes;
	    Integer dist[] = new Integer[nb_sommets];
	
	     // Etape 1: Initialise la distance du sommet source à tous les autres sommets à une valeur infinie
	     Arrays.fill(dist, Integer.MAX_VALUE);
	     dist[source] = 0;
	     
	     // Etape 2: Parcours de toutes les arêtes |nb_sommets| - 1 fois.
	     for (int i = 1; i < nb_sommets; ++i) {
	         for (int j = 0; j < nb_aretes; ++j) {
	             int u = graph.aretes[j].source;
	             int v = graph.aretes[j].destination;
	             int poids = graph.aretes[j].poids;
	             if (dist[u] != Integer.MAX_VALUE && dist[u] + poids < dist[v])
	                 dist[v] = dist[u] + poids;
	         }
	     }
	
	     // Etape 3: Verification de la non existence d'un cycle négatif
	     for (int j = 0; j < nb_aretes; ++j) {
	         int u = graph.aretes[j].source;
	         int v = graph.aretes[j].destination;
	         int poids = graph.aretes[j].poids;
	         if (dist[u] != Integer.MAX_VALUE && dist[u] + poids < dist[v])
	             return new Integer[0];
	     }
	
	     return dist;
	 }
}