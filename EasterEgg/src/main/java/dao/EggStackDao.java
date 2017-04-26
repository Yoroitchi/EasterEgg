package dao;

import object.EggStack;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Selim on 23/04/2017.
 */
public class EggStackDao {

    //Logger definition
    private static final Logger LOGGER = Logger.getLogger(EggStackDao.class);

    //Method to find egg stacks parameters in "src/main/resources/garden.txt"
    public ArrayList<EggStack> findAllEggs() {

        LOGGER.debug("findAllEggs : debut");

        //Definition of a list of eggStacks
        final ArrayList<EggStack> eggStacks = new ArrayList<EggStack>();

        try{
            InputStreamReader fr = new InputStreamReader(new FileInputStream("src/main/resources/garden.txt"));
            BufferedReader br = new BufferedReader(fr);
            String line;

            //Reading and extraction of each lines of garden.txt
            while ((line=br.readLine())!=null){

                //If an eggStack is find
                if(line.startsWith("C")) {

                    LOGGER.debug("findAllEggs : un groupe d'oeuf a été trouvé dans garden.txt --> "+ line);

                    //Conversion of text to eggStack and adding to list of eggStacks
                    eggStacks.add(transformLineToEggStack(line));

                    LOGGER.debug("findAllEggs : un groupe d'oeuf a été ajouter a la liste");
                }
            }
            br.close();
            fr.close();

        }catch (Exception e){
            System.out.println(e.toString());
        }

        LOGGER.debug("findAllEggs : fin");

        //Return the list of eggStacks
        return eggStacks;
    }

    private EggStack transformLineToEggStack(final String line) throws Exception {

        LOGGER.debug("transformLineToEggStack : conversion du texte en objet EggStack");

        String[] values = line.split(" ");
        String[] positions = values[1].split("-");

        return new EggStack(new Integer(positions[0]), new Integer(positions[1]), new Integer(values[2]));
    }
}
