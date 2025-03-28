package algo;

import java.util.Arrays;

public class EmmaMain {
	public static void main (String args[]) {
		
		/*
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
		*/
		
		BellmanFord graphe = new BellmanFord(6,9);
		graphe.addArrete(0, 1, 6);
		graphe.addArrete(0, 2, 4);
		graphe.addArrete(0, 3, 5);
		graphe.addArrete(1, 4, 1);
		graphe.addArrete(2, 1, 2);
		graphe.addArrete(3, 2, 2);
		graphe.addArrete(2, 4, 3);
		graphe.addArrete(2, 1, 1);
		graphe.addArrete(4, 3, 3);
		
		BellmanFord graphe2 = new BellmanFord(6,10);
		graphe2.addArrete(0, 1, -6);
		graphe2.addArrete(1, 0, -10);
		graphe2.addArrete(4, 0, -4);
		graphe2.addArrete(2, 3, -5);
		graphe2.addArrete(3, 2, -1);
		graphe2.addArrete(0, 3, -2);
		graphe2.addArrete(0, 2, -2);
		graphe2.addArrete(3, 4, -3);
		graphe2.addArrete(3, 1, -1);
		graphe2.addArrete(2, 1, -3);
		
		System.out.println(Arrays.toString(graphe.bellmanFordAlgo(graphe, 0)));
		System.out.println(Arrays.toString(graphe2.bellmanFordAlgo(graphe2, 0)));
	}
}
