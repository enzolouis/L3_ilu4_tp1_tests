package algo;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestLimites {

	// J1 : C7-Valide-1, C8-Valide-1, C9-Valide-1, C12-Valide-1
	@Test
	void testSommetSeulSansArete() {
		BellmanFord graphe = new BellmanFord(1,0);
		Integer[] distance = {0};
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 0),distance);
	}
	
	// J2 : C7-Valide-3, C8-Valide-2, C10-Valide, C11-Valide, C5-Valide, C12-Valide-3
	@Test
	void testGrapheClassique() {
		BellmanFord graphe = new BellmanFord(2,4);
		graphe.addArete(0, 0, Integer.MIN_VALUE);
		graphe.addArete(0, 1, Integer.MAX_VALUE);
		graphe.addArete(1, 0, Integer.MIN_VALUE + 1);
		graphe.addArete(1, 0, Integer.MAX_VALUE - 1);
		Integer[] distance = {-2147483647, 0};
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 1),distance);
	}
	
	// J3 : C12-Valide-2, C9-Valide-2
	@Test
	void testGrapheUnSeuleArete() {
		BellmanFord graphe = new BellmanFord(1,1);
		Integer[] distance = {0};
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 0),distance);
	}
	
	// J4 : C7-Valide-2
	@Test
	void testSommetDepartNbSommetsMoinsDeux() {
		BellmanFord graphe = new BellmanFord(4,4);
		graphe.addArete(2, 1, 2);
		graphe.addArete(2, 3, 2);
		graphe.addArete(2, 0, 2);
		Integer[] distance = {2,2,0,2};
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 2),distance);
	}
	
	// J5 : C8-Invalide-1
	@Test
	void testZeroSommets() {
		BellmanFord graphe = new BellmanFord(0,0);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 0);
	    });
	}
	
	// J6 : C8-Invalide-2
	@Test
	void testMaxIntPlusUnSommets() {
		BellmanFord graphe = new BellmanFord(Integer.MAX_VALUE+1,0);
		assertThrows(NegativeArraySizeException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 0);
	    });
	}
	
	// J7 : C9-Invalide-1
	@Test
	void testNbAretesNegatif() {
		assertThrows(NegativeArraySizeException.class, () -> {
			BellmanFord graphe = new BellmanFord(1,-1);
	    });
	}
	
	// J8 : C9-Invalide-2
	@Test
	void testMaxIntPlusUnAretes() {
		assertThrows(NegativeArraySizeException.class, () -> {
			BellmanFord graphe = new BellmanFord(1,Integer.MAX_VALUE + 1);
	    });
	}
	
	// J9 : C7-Invalide-1
	@Test
	void testSommetDepartNegatif() {
		BellmanFord graphe = new BellmanFord(1,0);
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, -1);
	    });
	}
	
	// J10 : C7-Invalide-2
	@Test
	void testSommetDepartInconnu() {
		BellmanFord graphe = new BellmanFord(1,0);
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 1);
	    });
	}
	
	// J11 : C10-Invalide-1
	@Test
	void testSourceAreteInvalideInferieur() {
		BellmanFord graphe = new BellmanFord(2,2);
		graphe.addArete(-1, 1, 6);
		graphe.addArete(1, 1, 6);
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 1);
	    });
	}
	
	// J12 : C11-invalide-1
	@Test
	void testDestinationAreteInvalideInferieur() {
		BellmanFord graphe = new BellmanFord(2,2);
		graphe.addArete(1, -1, 6);
		graphe.addArete(1, 1, 6);
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 1);
	    });
	}
	
	// J13 : C10-Invalide-2
	@Test
	void testSourceAreteInvalideSuperieur() {
		BellmanFord graphe = new BellmanFord(2,2);
		graphe.addArete(2, 1, 6);
		graphe.addArete(1, 1, 6);
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 1);
	    });
	}
	
	// J14 : C11-Invalide-2
	@Test
	void testDestinationAreteInvalideSuperieur() {
		BellmanFord graphe = new BellmanFord(2,2);
		graphe.addArete(1, 2, 6);
		graphe.addArete(1, 1, 6);
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 1);
	    });
	}
	
	// J15 : C12-Invalide
	@Test
	void testNbAreteAjouteInvalide() {
		BellmanFord graphe = new BellmanFord(2,1);
		graphe.addArete(0, 1, 6);
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			graphe.addArete(1, 0, 3);
	    });
	}
	
	// J16 : C5-Invalide-1
	@Test
	void testPoidsDepassementSuperieur() {
		BellmanFord graphe = new BellmanFord(2,1);
		graphe.addArete(0, 1, Integer.MAX_VALUE+1);
		Integer[] distance = {0,-2147483648};
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 0),distance); // on s'attend à un dépassement de valeur ici MAN INT + 1 = MIN INT
	}
	
	// J17 : C5-Invalide-2
	@Test
	void testPoidsDepassementInferieur() {
		BellmanFord graphe = new BellmanFord(2,1);
		graphe.addArete(0, 1, Integer.MIN_VALUE-1);
		Integer[] distance = {0,2147483647};
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 0),distance); // on s'attend à un dépassement de valeur ici MIN INT - 1 = MAX INT
	}
	
	
	// J18 : C8-Valide-3
	/*
	@Test
	void testNbSommetMax() {
		BellmanFord graphe = new BellmanFord(Integer.MAX_VALUE,1);
		graphe.addArete(0, 0, 3);
		Integer[] distance = new Integer[Integer.MAX_VALUE];
		for (int i = 0; i<Integer.MAX_VALUE; i++) {
			distance[i] = 2147483647;
		}
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 1),distance);
	}
	
	// J19 : C8-Valide-4
	@Test
	void testNbSommetMaxMoinsUn() {
		BellmanFord graphe = new BellmanFord(Integer.MAX_VALUE-1,1);
		graphe.addArete(0, 0, 3);
		Integer[] distance = new Integer[Integer.MAX_VALUE-1];
		for (int i = 0; i<Integer.MAX_VALUE; i++) {
			distance[i] = 2147483647;
		}
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 1),distance);
	}
	
	// J20 : C9-Valide-3
	@Test
	void testNbAretesMax() {
		BellmanFord graphe = new BellmanFord(1,Integer.MAX_VALUE);
		graphe.addArete(0, 0, 3);
		Integer[] distance = {0};
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 0),distance);
	}
	
	// J21 : C9-Valide-4
	@Test
	void testNbAretesMax() {
		BellmanFord graphe = new BellmanFord(1,Integer.MAX_VALUE-1);
		graphe.addArete(0, 0, 3);
		Integer[] distance = {0};
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 0),distance);
	}
	*/
}
