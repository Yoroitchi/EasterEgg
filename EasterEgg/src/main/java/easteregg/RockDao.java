package easteregg;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Selim on 23/04/2017.
 */
public class RockDao {

    public ArrayList<Rock> findAllRocks() {

        final ArrayList<Rock> rocks = new ArrayList<Rock>();

        try{
            InputStreamReader fr = new InputStreamReader(new FileInputStream("src/main/resources/garden.txt"));
            BufferedReader br = new BufferedReader(fr);
            String ligne;
            while ((ligne=br.readLine())!=null){
                if(ligne.startsWith("R")) {
                    System.out.println(ligne);
                    Rock rock = transformLigneToRock(ligne);
                    rocks.add(rock);
                }
            }
            br.close();
            fr.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return rocks;
    }

    private Rock transformLigneToRock(final String ligne) throws Exception {

        String[] values = ligne.split(" ");
        String[] positions = values[1].split("-");
        Rock rock = new Rock(new Integer(positions[0]), new Integer(positions[1]));

        return rock;
    }
}
