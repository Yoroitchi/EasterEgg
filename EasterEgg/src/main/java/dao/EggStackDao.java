package dao;

import object.EggStack;

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
            String line;
            while ((line=br.readLine())!=null){
                if(line.startsWith("C")) {
                    eggStacks.add(transformLineToEggStack(line));
                }
            }
            br.close();
            fr.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }

        return eggStacks;
    }

    private EggStack transformLineToEggStack(final String line) throws Exception {

        String[] values = line.split(" ");
        String[] positions = values[1].split("-");

        return new EggStack(new Integer(positions[0]), new Integer(positions[1]), new Integer(values[2]));
    }
}
