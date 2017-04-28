package dao;

import object.Kid;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Selim on 23/04/2017.
 */
public class KidDao {

    //Logger definition
    private static final Logger LOGGER = Logger.getLogger(KidDao.class);

    //Method to find kids parameters in "src/main/resources/kids.txt"
    public ArrayList<Kid> findAllKids(File file) {

        LOGGER.debug("findAllKids : debut");

        //Definition of a list of kids
        final ArrayList<Kid> kids = new ArrayList<Kid>();

        try{
            InputStreamReader fr = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(fr);
            String line;

            //Reading and extraction of each lines of kids.txt
            while ((line=br.readLine())!=null){

                //If a kid is find
                if(line.startsWith("E")) {

                    LOGGER.debug("findAllKids : un enfant a été trouvé dans kids.txt --> "+ line);

                    //Conversion of text to kid and adding to list of kids
                    kids.add(transformLineToKid(line));

                    LOGGER.debug("findAllKids : l'enfant a été ajouté a la liste");
                }
            }
            br.close();
            fr.close();

        }catch (Exception e){
            System.out.println(e.toString());
        }

        LOGGER.debug("findAllKids : fin");

        //Return the list of kids
        return kids;
    }

    private Kid transformLineToKid(final String line) throws Exception {

        LOGGER.debug("transformLineToKid : conversion du texte en objet Kid");

        String[] values = line.split(" ");
        String[] positions = values[1].split("-");

        return new Kid(new Integer(positions[0]), new Integer(positions[1]), values[2].charAt(0), values[3], values[4], values[5].charAt(0));
    }
}
