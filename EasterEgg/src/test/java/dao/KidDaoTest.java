package dao;

import object.Garden;
import object.Kid;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.util.locale.provider.LocaleServiceProviderPool;

import java.util.ArrayList;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

/**
 * Created by Selim on 25/04/2017.
 */
public class KidDaoTest {

    private static final Logger LOGGER = Logger.getLogger(KidDaoTest.class);

    private ArrayList<Kid> kidStackList = new KidDao().findAllKids();

    @Before
    public void setUp() throws Exception {
        LOGGER.debug("setUp start");

        if(kidStackList == null) {fail("kidStackList is empty and shouldn't be.");}

        LOGGER.debug("setUp end");
    }

    @Before
    public void sameCoordonate() throws Exception {
        LOGGER.debug("sameCoordonate start");


        Kid next;
        int indexOfNext = 0;
        for (Kid now: kidStackList) {
            for(int i = kidStackList.indexOf(now); i < kidStackList.size() - 1; i++){
                indexOfNext = kidStackList.indexOf(now) + 1;
                next = kidStackList.get(indexOfNext);
                if(next.posX == now.posX || next.posY == now.posY){fail("Two children at the same place");}
            }
        }

        LOGGER.debug("sameCoordonate start");
    }
    @Test
    public void isThereTooManyChild() throws Exception {
        LOGGER.debug("isThereAChild start");

        Garden garden = new GardenDao().findGarden();
        if(kidStackList.size() >= garden.getSizeX() + garden.getSizeY()) {fail("Too many children");}

        LOGGER.debug("isThereAChild start");
    }

}