package dao;

import object.EggStack;
import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;
import java.lang.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.fail;

/**
 * Created by Selim on 25/04/2017.
 * filled by Erwan.
 */
public class EggStackDaoTest {
    private static final Logger LOGGER = Logger.getLogger(EggStackDaoTest.class);

    private final static String RESOURCES_PATH = "src/test/resources/";
    private final static String EGGS_FILE_NAME = "garden.txt";

    private File file = new File(RESOURCES_PATH + EGGS_FILE_NAME);

    private ArrayList<EggStack> EggStackList = new EggStackDao().findAllEggs(file);

    @Before
    public void setUp() throws Exception {
        BasicConfigurator.configure();
        LOGGER.debug("setUp start");

        if(EggStackList == null) {fail("EggStackList is empty and shouldn't be.");}

        LOGGER.debug("setUp end");
    }

    @Test
    public void isNegativeStack() {
        LOGGER.debug("isNegativeStack start");

        for (EggStack eggStack : EggStackList){
            if(eggStack.eggsNb < 0) fail("Number of eggs less than 0, it is impossible");
        }

        LOGGER.debug("isNegativeStack end");
    }

    @Test
    public void findAllEggs() throws Exception {
        LOGGER.debug("findAllEggs start");

        int totalEggsExpected = 3;
        int totalEggs = 0;
        for (EggStack eggstack: EggStackList) {
            totalEggs += eggstack.getEggsNb();
        }
        if (totalEggs != totalEggsExpected) fail("Total number of eggs less than 0");

        LOGGER.debug("findAllEggs end");
    }

}