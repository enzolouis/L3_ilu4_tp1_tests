package algo;

public class BellmanFordTestPartition {
	public static void main(String[] args) {
        testNbSommetsNegatif();
        testNbAretesNegatif();
        testSourceInvalide();
        testAretesMalDefinies();
        testDepassementNombreAretes();
        testCycleNegatif();
        testSansCycleNegatif();
        testSommetIsole();
    }

    static void testNbSommetsNegatif() {
        try {
        	BellmanFord g = new BellmanFord(0, 2); // Nb sommets <= 0
            g.bellmanFordAlgo(g, 0);
            System.out.println("testNbSommetsNegatif échoué");
        } catch (Exception e) {
            System.out.println("testNbSommetsNegatif passé");
        }
    }

    static void testNbAretesNegatif() {
        try {
        	BellmanFord g = new BellmanFord(3, -1); // Nb arêtes < 0
        	g.bellmanFordAlgo(g, 0);
            System.out.println("testNbAretesNegatif échoué");
        } catch (Exception e) {
            System.out.println("testNbAretesNegatif passé");
        }
    }

    static void testSourceInvalide() {
        try {
            BellmanFord g = new BellmanFord(3, 1);
            g.addArete(0, 1, 4);
            g.bellmanFordAlgo(g, 5); // Source > nb_sommets
            System.out.println("testSourceInvalide échoué");
        } catch (Exception e) {
            System.out.println("testSourceInvalide passé");
        }
    }

    static void testAretesMalDefinies() {
        try {
            BellmanFord g = new BellmanFord(2, 1);
            g.addArete(2, 1, 1); // Source > nb_sommets
            g.bellmanFordAlgo(g, 0);
            System.out.println("testAretesMalDefinies échoué");
        } catch (Exception e) {
            System.out.println("testAretesMalDefinies passé");
        }
    }

    static void testDepassementNombreAretes() {
        try {
            BellmanFord g = new BellmanFord(2, 1);
            g.addArete(0, 1, 1);
            g.addArete(1, 0, -1); // Dépassement
            System.out.println("testDepassementNombreAretes échoué");
        } catch (Exception e) {
            System.out.println("testDepassementNombreAretes passé");
        }
    }

    static void testCycleNegatif() {
        BellmanFord g = new BellmanFord(3, 3);
        g.addArete(0, 1, 1);
        g.addArete(1, 2, -1);
        g.addArete(2, 0, -1); // Cycle négatif
        Integer[] r = g.bellmanFordAlgo(g, 0);
        if (r.length == 0) {
            System.out.println("testCycleNegatif passé");
        } else {
            System.out.println("testCycleNegatif échoué");
        }
    }

    static void testSansCycleNegatif() {
        BellmanFord g = new BellmanFord(3, 2);
        g.addArete(0, 1, 4);
        g.addArete(1, 2, 5);
        Integer[] r = g.bellmanFordAlgo(g, 0);
        if (r.length == 3) {
            System.out.println("testSansCycleNegatif passé");
        } else {
            System.out.println("testSansCycleNegatif échoué");
        }
    }

    static void testSommetIsole() {
        BellmanFord g = new BellmanFord(3, 1);
        g.addArete(0, 1, 2); // Sommet 2 est isolé
        Integer[] r = g.bellmanFordAlgo(g, 0);
        if (r[2] == Integer.MAX_VALUE) {
            System.out.println("testSommetIsole passé");
        } else {
            System.out.println("testSommetIsole échoué");
        }
    }
}
