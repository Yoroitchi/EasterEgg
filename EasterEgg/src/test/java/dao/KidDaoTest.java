package dao;

import object.Garden;
import object.Kid;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.util.locale.provider.LocaleServiceProviderPool;

import java.io.File;
import java.util.ArrayList;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

/**
 * Created by Selim on 25/04/2017.
 * filled by Erwan.
 */
public class KidDaoTest {

    private static final Logger LOGGER = Logger.getLogger(KidDaoTest.class);

    private final static String RESOURCES_PATH = "src/test/resources/";
    private final static String KID_FILE_NAME = "kids.txt";
    private final static String GARDEN_FILE_NAME = "garden.txt";

    private File kidsFile = new File(RESOURCES_PATH + KID_FILE_NAME);
    private File gardenFile = new File(RESOURCES_PATH + GARDEN_FILE_NAME);


    @Before
    public void setUp() throws Exception {
        BasicConfigurator.configure();
        LOGGER.debug("setUp start");

        ArrayList<Kid> kidStackList = new KidDao().findAllKids(kidsFile);
        LOGGER.debug("setUp end");
    }

    @Test
    public void isListEmpty() throws Exception{
        LOGGER.debug("isListEmpty start");

        ArrayList<Kid> kidStackList = new KidDao().findAllKids(kidsFile);
        if(kidStackList == null) {fail("kidStackList is empty and shouldn't be.");}

        LOGGER.debug("isListEmpty end");

    }

    @Test
    public void sameCoordonate() throws Exception {
        LOGGER.debug("sameCoordonate start");

        ArrayList<Kid> kidStackList = new KidDao().findAllKids(kidsFile);

        Kid next;
        int indexOfNext;
        for (Kid now: kidStackList) {
            for(int i = kidStackList.indexOf(now); i < kidStackList.size() - 1; i++){
                indexOfNext = kidStackList.indexOf(now) + 1;
                next = kidStackList.get(indexOfNext);
                if(next.posX == now.posX && next.posY == now.posY){fail("Two children at the same place");}
            }
        }

        LOGGER.debug("sameCoordonate start");
    }
    @Test
    public void isThereTooManyChild() throws Exception {
        LOGGER.debug("isThereAChild start");

        ArrayList<Kid> kidStackList = new KidDao().findAllKids(kidsFile);
        Garden garden = new GardenDao().findGarden(gardenFile);

        if(kidStackList.size() >= garden.getSizeX() + garden.getSizeY()) {fail("Too many children");}

        LOGGER.debug("isThereAChild start");
    }

}