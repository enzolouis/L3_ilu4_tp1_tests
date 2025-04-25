package algo;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testLimites {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSommetSeulSansArete() {
		BellmanFord graphe = new BellmanFord(1,0);
		Integer[] distance = {0};
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 0),distance);
	}
	
	@Test
	void testGrapheClassique() {
		BellmanFord graphe = new BellmanFord(2,4);
		graphe.addArrete(0, 0, Integer.MIN_VALUE);
		graphe.addArrete(0, 1, Integer.MAX_VALUE);
		graphe.addArrete(1, 0, Integer.MIN_VALUE + 1);
		graphe.addArrete(1, 0, Integer.MAX_VALUE - 1);
		Integer[] distance = {-2147483647, 0};
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 1),distance);
	}
	
	@Test
	void testZeroSommets() {
		BellmanFord graphe = new BellmanFord(0,0);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 0);
	    });
	}
	
	@Test
	void testMaxIntPlusUnSommets() {
		BellmanFord graphe = new BellmanFord(Integer.MAX_VALUE+1,0);
		assertThrows(NegativeArraySizeException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 0);
	    });
	}
	
	@Test
	void testNbAretesNegatif() {
		assertThrows(NegativeArraySizeException.class, () -> {
			BellmanFord graphe = new BellmanFord(1,-1);
	    });
	}
	@Test
	void testMaxIntPlusUnAretes() {
		assertThrows(NegativeArraySizeException.class, () -> {
			BellmanFord graphe = new BellmanFord(1,Integer.MAX_VALUE + 1);
	    });
	}
	
	@Test
	void testSommetDepartNegatif() {
		BellmanFord graphe = new BellmanFord(1,0);
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, -1);
	    });
	}
	
	@Test
	void testSommetDepartInconnu() {
		BellmanFord graphe = new BellmanFord(1,0);
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 1);
	    });
	}
	
	@Test
	void testSourceDestinationSommetInvalideInferieur() {
		BellmanFord graphe = new BellmanFord(2,2);
		graphe.addArrete(-1, -1, 6);
		graphe.addArrete(1, 1, 6);
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 1);
	    });
	}
	
	@Test
	void testSourceDestinationSommetInvalideSuperieur() {
		BellmanFord graphe = new BellmanFord(2,2);
		graphe.addArrete(2, 2, 6);
		graphe.addArrete(1, 1, 6);
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			graphe.bellmanFordAlgo(graphe, 1);
	    });
	}
	
	@Test
	void testNbAreteAjouteInvalide() {
		BellmanFord graphe = new BellmanFord(2,1);
		graphe.addArrete(0, 1, 6);
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			graphe.addArrete(1, 0, 3);
	    });
	}
	
	@Test
	void testNbSommetMax() {
		BellmanFord graphe = new BellmanFord(Integer.MAX_VALUE,1);
		graphe.addArrete(0, 0, 3);
		Integer[] distance = new Integer[Integer.MAX_VALUE];
		for (int i = 0; i<Integer.MAX_VALUE; i++) {
			distance[i] = 2147483647;
		}
		assertArrayEquals(graphe.bellmanFordAlgo(graphe, 1),distance);
	}


}
