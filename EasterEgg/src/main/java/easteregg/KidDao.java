package easteregg;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Selim on 23/04/2017.
 */
public class KidDao {

    public ArrayList<Kid> findAllKids() {



        final ArrayList<Kid> kids = new ArrayList<Kid>();
        Kid kid = new Kid();

        try{
            InputStreamReader fr = new InputStreamReader(new FileInputStream("kids.txt"));
            BufferedReader br = new BufferedReader(fr);
            String ligne;
            while ((ligne=br.readLine())!=null){
                if(ligne.startsWith("E")) {
                    System.out.println(ligne);
                    kid = transformLigneToKid(ligne);
                    kid.printInfo();
                    kids.add(kid);
                }
            }
            br.close();
            fr.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return kids;
    }

    private Kid transformLigneToKid(final String ligne) throws Exception {

        String[] values = ligne.split(" ");
        String[] positions = values[1].split("-");
        Kid kid = new Kid(new Integer(positions[0]), new Integer(positions[1]), values[2].charAt(0), values[3], values[4]);

        return kid;
    }
}
