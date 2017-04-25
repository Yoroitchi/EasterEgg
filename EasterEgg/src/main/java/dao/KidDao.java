package dao;

import object.Kid;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Selim on 23/04/2017.
 */
public class KidDao {

    public ArrayList<Kid> findAllKids() {

        final ArrayList<Kid> kids = new ArrayList<Kid>();

        try{
            InputStreamReader fr = new InputStreamReader(new FileInputStream("src/main/resources/kids.txt"));
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line=br.readLine())!=null){
                if(line.startsWith("E")) {
                    kids.add(transformLineToKid(line));
                }
            }
            br.close();
            fr.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }

        return kids;
    }

    private Kid transformLineToKid(final String line) throws Exception {

        String[] values = line.split(" ");
        String[] positions = values[1].split("-");

        return new Kid(new Integer(positions[0]), new Integer(positions[1]), values[2].charAt(0), values[3], values[4]);
    }
}
