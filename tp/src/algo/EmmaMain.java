package algo;

import java.util.Arrays;

public class EmmaMain {
    public static void main(String[] args) {
        testCasStandard();
        testCasCycleNegatif();
        testCasSommetIsole();
    }

    // Cas classique
    private static void testCasStandard() {
    	System.out.println("Test cas standard :");
    	BellmanFord graphe = new BellmanFord(6,9);
		graphe.addArrete(0, 1, 6);
		graphe.addArrete(0, 2, 4);
		graphe.addArrete(0, 3, 5);
		graphe.addArrete(1, 4, -1);
		graphe.addArrete(2, 1, -2);
		graphe.addArrete(3, 2, -2);
		graphe.addArrete(2, 4, 3);
		graphe.addArrete(2, 5, -1);
		graphe.addArrete(4, 5, 3);
		System.out.println(Arrays.toString(graphe.bellmanFordAlgo(graphe, 0)));
    }

    // Cas cycle négatif
    private static void testCasCycleNegatif() {
        System.out.println("Test cas cycle négatif :");
        BellmanFord graphe = new BellmanFord(3, 3);
        graphe.addArrete(0, 1, 1);
        graphe.addArrete(1, 2, -2);
        graphe.addArrete(2, 0, -1); 

        System.out.println(Arrays.toString(graphe.bellmanFordAlgo(graphe, 0)));
    }

    // Cas sommet isolé
    private static void testCasSommetIsole() {
        System.out.println("Test Cas Sommet Isolé :");
        BellmanFord graphe = new BellmanFord(3, 1);
        graphe.addArrete(1, 2, 5); 
        System.out.println(Arrays.toString(graphe.bellmanFordAlgo(graphe, 0)));
    }

}
