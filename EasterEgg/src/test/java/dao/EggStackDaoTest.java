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
 */
public class EggStackDaoTest {
    private static final Logger LOGGER = Logger.getLogger(EggStackDaoTest.class);


    private ArrayList<EggStack> EggStackList = new EggStackDao().findAllEggs();

    @Before
    public void setUp() throws Exception {
        LOGGER.debug("setUp start");

        if(EggStackList == null) {fail("EggStackList is empty and shouldn't be.");}

        LOGGER.debug("setUp end");
    }

    @Before
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

        int totalEggsExpected = 0;
        for (EggStack eggstack: EggStackList) {
            totalEggsExpected += eggstack.eggsNb;
        }
        if (totalEggsExpected < 0) fail("Total number of eggs less than 0");

        LOGGER.debug("findAllEggs end");
    }

}