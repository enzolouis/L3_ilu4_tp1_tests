package algo;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class BellmanFordTestJSON {
	    public static void main(String[] args) {
	        ObjectMapper objectMapper = new ObjectMapper();

	        try {
	            File fichier = new File("test_cases.json");
	            JeuDeTestEtOracleFormat fic = objectMapper.readValue(fichier, JeuDeTestEtOracleFormat.class);
	            
	            System.out.println(fic);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
