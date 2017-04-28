package object;

import dao.EggStackDao;
import dao.GardenDao;
import dao.KidDao;
import dao.RockDao;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import system.GameManager;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Selim on 25/04/2017.
 * filled by Erwan.
 */
public class GardenTest {
    private static final Logger LOGGER = Logger.getLogger(GardenTest.class);

    private final static File gardenFile = new File("src/test/resources/garden.txt");
    private Garden garden = new GardenDao().findGarden(gardenFile);
    private ArrayList<EggStack> eggStackList = new EggStackDao().findAllEggs(gardenFile);
    private ArrayList<Rock> rocksList = new RockDao().findAllRocks(gardenFile);

    private final static File kidsFile = new File("src/test/resources/kids.txt");
    private ArrayList<Kid> kidStackList = new KidDao().findAllKids(kidsFile);


    @Before
    public void setUp() throws Exception {
        BasicConfigurator.configure();
        LOGGER.debug("setUp start");
        if(garden.sizeX < 1 || garden.sizeY < 1){fail("Garden too small.");}
        for (EggStack eggStack : eggStackList) {
            garden.addEgg(eggStack);
        }
        for(Kid kid : kidStackList){
            garden.addKid(kid);
        }
        for(Rock rock : rocksList){
            garden.addRock(rock);
        }
        LOGGER.debug("setUp end");
    }

    @Test
    public void HowManyEggStacks() throws Exception{
        LOGGER.debug("HowManyEggStacks start");

        int eggStacksExpected = eggStackList.size();
        int eggStack = 0;

        for(int i = 0; i < garden.sizeX; i++){
            for(int j = 0; j < garden.sizeY; j++){
                if(garden.table[i][j] instanceof EggStack) {eggStack++;}
            }
        }
        if(eggStack != eggStacksExpected) fail("Not enough eggs stacks");
        LOGGER.debug("HowManyEggStacks end");

    }

    @Test
    public void HowManyKids() throws Exception{
        LOGGER.debug("HowManyKids start");

        int KidsExpected = kidStackList.size();
        int nbKids = 0;
        for(int i = 0; i < garden.sizeX; i++){
            for(int j = 0; j < garden.sizeY; j++) {
                    if (garden.table[i][j] instanceof Kid) nbKids++;
            }
        }
        if(nbKids != KidsExpected) fail("Not enough Kids");
        LOGGER.debug("HowManyKids end");
    }

    @Test
    public void HowManyRocks() throws Exception{
        LOGGER.debug("HowManyRocks start");

        int rocksExpected = rocksList.size();
        int nbRocks = 0;
        for(int i = 0; i < garden.sizeX; i++){
            for(int j = 0; j < garden.sizeY; j++) {
                if (garden.table[i][j] instanceof Rock) nbRocks++;
            }
        }
        if(nbRocks != rocksExpected) fail("Not enough Rocks");
        LOGGER.debug("HowManyROcks end");
    }

}