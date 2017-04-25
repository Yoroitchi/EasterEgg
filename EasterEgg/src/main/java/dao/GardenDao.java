package dao;

import object.Garden;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Selim on 22/04/2017.
 */
public class GardenDao {

    public Garden findGarden() {

        Garden garden = null;

        try{
            InputStreamReader fr = new InputStreamReader(new FileInputStream("src/main/resources/garden.txt"));
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("J")) {
                    garden = transformLineToGarden(line);
                }
            }
            br.close();
            fr.close();
        }catch (Exception e) {
            System.out.println(e.toString());
        }

        return garden;
    }

    private Garden transformLineToGarden(final String line) throws Exception {

        String[] values = line.split(" ");

        return new Garden (new Integer(values[1]), new Integer(values[2]));
    }
}
