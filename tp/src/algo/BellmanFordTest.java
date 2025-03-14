package algo;

import java.io.*;
import java.util.*;

public class BellmanFordTest {
    public static void main(String[] args) {
        String testFile = "test_cases.txt";
        String reportFile = "test_report.txt";
        runTests(testFile, reportFile);
    }

    public static void runTests(String testFile, String reportFile) {
        List<String> results = new ArrayList<>();
        int totalTests = 0, passedTests = 0;
        //System.out.println("Chemin du fichier: " + new File("test_cases.txt").getAbsolutePath());

        try (BufferedReader br = new BufferedReader(new FileReader(testFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(reportFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;
                totalTests++;
                
                String[] parts = line.split(";");
                int nbSommets = Integer.parseInt(parts[0]);
                int nbAretes = Integer.parseInt(parts[1]);
                BellmanFord graph = new BellmanFord(nbSommets, nbAretes);
                
                for (int i = 0; i < nbAretes; i++) {
                    String[] edgeData = parts[i + 2].split(",");
                    graph.aretes[i].source = Integer.parseInt(edgeData[0]);
                    graph.aretes[i].destination = Integer.parseInt(edgeData[1]);
                    graph.aretes[i].weight = Integer.parseInt(edgeData[2]);
                }
                
                int source = Integer.parseInt(parts[nbAretes + 2]);
                String[] expectedDistancesRaw = parts[nbAretes + 3].split(",");

                int[] expectedDistances;
                if (expectedDistancesRaw[0].equals("Negative Cycle")) {
                    expectedDistances = new int[0]; //Cycle négatif
                } else {
                    expectedDistances = Arrays.stream(expectedDistancesRaw)
                                              .mapToInt(Integer::parseInt)
                                              .toArray();
                }
                
                int[] actualDistances = runBellmanFord(graph, source);
                boolean testPassed = Arrays.equals(actualDistances, expectedDistances);
                
                results.add("Test " + totalTests + ": " + (testPassed ? "PASS" : "FAIL"));
                if (testPassed) passedTests++;
            }
            
            // Écriture du rapport
            bw.write("Rapport de Test:\n");
            bw.write("Total tests: " + totalTests + "\n");
            bw.write("Tests réussis: " + passedTests + "\n");
            bw.write("Taux de réussite: " + (100.0 * passedTests / totalTests) + "%\n\n");
            
            for (String result : results) {
                bw.write(result + "\n");
            }
            System.out.println("Rapport de test généré: " + reportFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] runBellmanFord(BellmanFord graph, int source) {
        int nbSommets = graph.nb_sommets;
        int[] dist = new int[nbSommets];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int i = 1; i < nbSommets; i++) {
            for (BellmanFord.Arete arete : graph.aretes) {
                if (dist[arete.source] != Integer.MAX_VALUE && dist[arete.source] + arete.weight < dist[arete.destination]) {
                    dist[arete.destination] = dist[arete.source] + arete.weight;
                }
            }
        }

        for (BellmanFord.Arete arete : graph.aretes) {
            if (dist[arete.source] != Integer.MAX_VALUE && dist[arete.source] + arete.weight < dist[arete.destination]) {
                return new int[0]; // Indique un cycle négatif
            }
        }
        return dist;
    }
}
