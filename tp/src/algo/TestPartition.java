package algo;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TestPartition {
	// C1 C2 C3 C4 C5 C6 C5, C6, C7-Valide, C8-Valide, C9-Valide, C10-Valide, 
	// C11-Valide, C12-Valide, C13-Valide
	@Test
	void testSansPoidsNegatifSansSommetIsole() {
	    BellmanFord graphe = new BellmanFord(3, 3);
	    graphe.addArete(0, 1, 4);
	    graphe.addArete(1, 2, 3);
	    graphe.addArete(2, 0, 10);
		Integer[] distance = {0, 4, 7};
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 0), distance);
	}
	
	// C7-Invalide-1
	@Test
	void testSommetDeDepartNegatif() {
		BellmanFord graphe = new BellmanFord(3, 3);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, -1);
	    });
	}
	
	// C7-Invalide-2
	@Test
	void testSommetDeDepartSupOuEgalANbSommets() {
		BellmanFord graphe = new BellmanFord(3, 0);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 3);
	    });
	}
	
	// C8-Invalide : 2 comportements différents selon si egal ou inférieure
	@Test
	void testNbSommetsInfOuEgalA0() {
		BellmanFord graphe = new BellmanFord(0,3);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 0);
	    });
		BellmanFord graphe2 = new BellmanFord(-2,3);
		assertThrows(NegativeArraySizeException.class, () -> {
			graphe2.bellmanFordAlgo(graphe2, 0);
	    });
	}
	
	// C9-Invalide
	@Test
	void testNbAretesNegatif() {
		assertThrows(NegativeArraySizeException.class, () -> {
			BellmanFord graphe = new BellmanFord(3, -1);
	    });
	}
	
	// C10-Invalide-1
	@Test
	void testUneAreteAvecSourceNegatif() {
		BellmanFord graphe = new BellmanFord(3, 1);
	    graphe.addArete(-2, 0, 10);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 0);
	    });
	}
	
	// C10-Invalide-2
	@Test
	void testUneAreteAvecSourceSupOuEgalANbSommets() {
		BellmanFord graphe = new BellmanFord(3, 1);
	    graphe.addArete(5, 0, 10);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 0);
	    });
	}
	
	// C11-Invalide-1
	@Test
	void testUneAreteAvecDestinationNegatif() {
		BellmanFord graphe = new BellmanFord(3, 1);
	    graphe.addArete(0, -2, 10);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 0);
	    });
	}
	
	// C11-Invalide-2
	@Test
	void testUneAreteAvecDestinationSupOuEgalANbSommets() {
		BellmanFord graphe = new BellmanFord(3, 1);
	    graphe.addArete(0, 5, 10);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 0);
	    });
	}
	
	// C12-Invalide
	void testLongueurListeAreteSupANbArete() {
		BellmanFord graphe = new BellmanFord(2, 1);
		graphe.addArete(0, 1, 6);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			graphe.addArete(1, 0, 3);
	    });
	}
	
	// C14
	@Test
	void testCycleDePoidsNegatif() {
		BellmanFord graphe = new BellmanFord(3, 3);
	    graphe.addArete(0, 1, -4);
	    graphe.addArete(1, 2, -3);
	    graphe.addArete(2, 0, 1);
		Integer[] distance = {}; // cycle de poids négatif
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 0), distance);
	}
	
	// C15
	@Test
	void testPoidsNegatifSansCycleDePoidsNegatif() {
		BellmanFord graphe = new BellmanFord(3, 3);
	    graphe.addArete(0, 1, 4);
	    graphe.addArete(1, 2, -3);
	    graphe.addArete(2, 0, 10);
		Integer[] distance = {0, 4, 1};
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 0), distance);
	}
	
	// C16
	@Test
	void testSommetIsoleAvecGrapheValide() {
		BellmanFord graphe = new BellmanFord(3, 1);
	    graphe.addArete(0, 1, 4);
		Integer[] distance = {0, 4, 2147483647};
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 0), distance);
	}
}