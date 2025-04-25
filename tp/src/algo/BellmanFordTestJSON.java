package algo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BellmanFordTestJSON {
	static class Test {
		List<Integer> attendu;
		List<Integer> observe;
		boolean isSame;
		
		Test(List<Integer> attendu, List<Integer> observe, boolean isSame) {
			this.attendu = attendu;
			this.observe = observe;
			this.isSame = isSame;
		}
	}
    public static void main(String[] args) {
    	List<Test> tests = new ArrayList<>();
    	
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File fichier = new File("test_cases.json");
            List<JeuDeTestEtOracleFormat> fic = objectMapper.readValue(fichier, new TypeReference<>() {});
            
            for (JeuDeTestEtOracleFormat j : fic) {
            	BellmanFord b = new BellmanFord(j.nbSommets, j.nbArretes);
            	for (List<Integer> a : j.arretes) {
            		b.addArete(a.get(0), a.get(1), a.get(2));
            	}
            	
            	Integer[] result = b.bellmanFordAlgo(b, j.source);
            	List<Integer> distanceObservees = Arrays.asList(result);
            	
            	// si la distance attendue (dans le json) n'est pas un cycle de poids négatif
            	if (j.distanceAttendues instanceof List<?>) {
            		List<Integer> distanceAttendues = (List<Integer>) j.distanceAttendues;
            		
            		tests.add(new Test(distanceAttendues, distanceObservees, distanceAttendues.equals(distanceObservees)));
            	// si la distance attendue est un cycle de poids négatif
                } else {
                	String distanceAttendues = (String) j.distanceAttendues;
                	
                	tests.add(new Test(new ArrayList<Integer>(), distanceObservees, distanceObservees.size() == 0));
                }
            }
            
            // statistiques
            System.out.println("-- Rapport de test --");
            float total = tests.size();
            System.out.println("Total test : " + total);
            float reussis = tests.stream().filter(k -> k.isSame).collect(Collectors.toList()).size();
            System.out.println("Tests réussis : " + reussis);
            System.out.println("Tests réussis : " + (total - reussis));
            System.out.println("Taux de réussite : " + (reussis / total * 100) + "%");
            
            for (int x = 0; x < tests.size(); x++) {
            	System.out.println("Test : " + (tests.get(x).isSame ? "RÉUSSITE" : "ÉCHEC"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
