package algo;

import java.util.Arrays;

public class Question2 {
	public static void main(String[] args) {
    	BellmanFord graphe = new BellmanFord(6,9);
		graphe.addArete(0, 1, 6);
		graphe.addArete(0, 2, 4);
		graphe.addArete(0, 3, 5);
		graphe.addArete(1, 4, -1);
		graphe.addArete(2, 1, -2);
		graphe.addArete(2, 4, 3);
		graphe.addArete(3, 2, -2);
		graphe.addArete(3, 5, -1);
		graphe.addArete(4, 5, 3);
		System.out.println(Arrays.toString(graphe.bellmanFordAlgo(graphe, 0)));
	}
}
