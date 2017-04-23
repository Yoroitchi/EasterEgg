package easteregg;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Selim on 22/04/2017.
 */
public class GardenDao {
    public Garden findGarden() {
        Garden garden = null;

        try {
            InputStreamReader fr = new InputStreamReader(new FileInputStream("src/main/resources/garden.txt"));
            BufferedReader br = new BufferedReader(fr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                if (ligne.startsWith("J")) {
                    System.out.println(ligne);
                    garden = transformLigneToGarden(ligne);
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return garden;
    }

    private Garden transformLigneToGarden(final String ligne) throws Exception {

        String[] values = ligne.split(" ");
        Garden garden = new Garden (new Integer(values[1]), new Integer(values[2]));

        return garden;
    }
}
