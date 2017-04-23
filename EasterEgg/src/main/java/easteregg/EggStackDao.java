package easteregg;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Selim on 23/04/2017.
 */
public class EggStackDao {

    public ArrayList<EggStack> findAllEggs() {

        final ArrayList<EggStack> eggStacks = new ArrayList<EggStack>();

        try{
            InputStreamReader fr = new InputStreamReader(new FileInputStream("src/main/resources/garden.txt"));
            BufferedReader br = new BufferedReader(fr);
            String ligne;
            while ((ligne=br.readLine())!=null){
                if(ligne.startsWith("C")) {
                    System.out.println(ligne);
                    EggStack eggStack = transformLigneToEggStack(ligne);
                    eggStacks.add(eggStack);
                }
            }
            br.close();
            fr.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return eggStacks;
    }

    private EggStack transformLigneToEggStack(final String ligne) throws Exception {

        String[] values = ligne.split(" ");
        String[] positions = values[1].split("-");
        EggStack eggStack = new EggStack(new Integer(positions[0]), new Integer(positions[1]), new Integer(values[2]));

        return eggStack;
    }
}
