package dao;

import object.Garden;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Selim on 22/04/2017.
 */
public class GardenDao {

    //Logger definition
    private static final Logger LOGGER = Logger.getLogger(GardenDao.class);

    //Method to find garden parameters in "src/main/resources/garden.txt"
    public Garden findGarden(File file) {

        LOGGER.debug("findGarden : debut");

        //Definition of garden to keep parameters
        Garden garden = null;

        try{
            InputStreamReader fr = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(fr);
            String line;

            //Reading and extraction of each lines of garden.txt
            while ((line = br.readLine()) != null) {

                //If a garden is find
                if (line.startsWith("J")) {

                    LOGGER.debug("findGarden : des paramètres de jardin on été trouvé --> " + line);

                    //Conversion of text to garden
                    garden = transformLineToGarden(line);

                    LOGGER.debug("findGarden : définition des paramètres de jardin"+ line);
                }
            }
            br.close();
            fr.close();

        }catch (Exception e) {
            System.out.println(e.toString());
        }

        LOGGER.debug("findGarden : fin");

        //If there is many garden in garden.txt, only the last will be used
        return garden;
    }

    private Garden transformLineToGarden(final String line) throws Exception {

        LOGGER.debug("transformLineToGarden : conversion du texte en objet Garden");

        String[] values = line.split(" ");

        return new Garden (new Integer(values[1]), new Integer(values[2]));
    }
}
