package dao;

import object.Rock;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Selim on 23/04/2017.
 */
public class RockDao {

    //Logger definition
    private static final Logger LOGGER = Logger.getLogger(RockDao.class);

    //Method to find rocks parameters in "src/main/resources/garden.txt"
    public ArrayList<Rock> findAllRocks() {

        LOGGER.debug("findAllRocks : debut");

        //Definition of a list of rocks
        final ArrayList<Rock> rocks = new ArrayList<Rock>();

        try{
            InputStreamReader fr = new InputStreamReader(new FileInputStream("src/main/resources/garden.txt"));
            BufferedReader br = new BufferedReader(fr);
            String line;

            //Reading and extraction of each lines of garden.txt
            while ((line=br.readLine())!=null){

                //If a rock is find
                if(line.startsWith("R")) {

                    LOGGER.debug("findAllRocks : un rocher a été trouvé dans garden.txt --> "+ line);

                    //Conversion of text to rock and adding to list of rocks
                    rocks.add(transformLineToRock(line));

                    LOGGER.debug("findAllRocks : le rocher  a été ajouté a la liste");
                }
            }
            br.close();
            fr.close();

        }catch (Exception e){
            System.out.println(e.toString());
        }

        LOGGER.debug("findAllRocks : fin");

        //Return the list of rocks
        return rocks;
    }

    private Rock transformLineToRock(final String line) throws Exception {

        LOGGER.debug("transformLineToRock : conversion du texte en objet Rock");

        String[] values = line.split(" ");
        String[] positions = values[1].split("-");

        return new Rock(new Integer(positions[0]), new Integer(positions[1]));
    }
}
