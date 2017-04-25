package dao;

import object.Rock;

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
            String line;
            while ((line=br.readLine())!=null){
                if(line.startsWith("R")) {
                    rocks.add(transformLineToRock(line));
                }
            }
            br.close();
            fr.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }

        return rocks;
    }

    private Rock transformLineToRock(final String line) throws Exception {

        String[] values = line.split(" ");
        String[] positions = values[1].split("-");

        return new Rock(new Integer(positions[0]), new Integer(positions[1]));
    }
}
