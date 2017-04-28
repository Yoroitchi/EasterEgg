package system;


import dao.EggStackDaoTest;
import dao.KidDaoTest;
import object.Kid;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Selim on 25/04/2017.
 */
public class GameManagerTest {
    private static final Logger LOGGER = Logger.getLogger(EggStackDaoTest.class);

    private final static String RESOURCES_PATH = "src/test/resources/";
    private final static String EGGS_FILE_NAME = "garden.txt";

    private File file = new File(RESOURCES_PATH + EGGS_FILE_NAME);
    private GameManager gameManager = new GameManager(file);

    @Before
    public void setUp() throws Exception {
        BasicConfigurator.configure();
    }

    @Test
    public void MoveKid() throws Exception {
        LOGGER.debug("MoveKid start");
        Kid kid = new Kid(1,1,'E',"AD","EnfantCrashTest", 'M');

        gameManager.moveKid(kid);
        if(kid.posY != 2) {fail("Didn't move well");}
        gameManager.moveKid(kid);
        if(kid.getDirection() != 'S'){fail("Didn't turn well");}

        LOGGER.debug("MoveKid end");
    }


}