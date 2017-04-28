package system;

import display.Frame;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Created by Selim on 23/04/2017.
 */
public class Launcher {

    //Logger definition
    private static final Logger LOGGER = Logger.getLogger(Launcher.class);
    private final static String RESOURCES_PATH = "src/main/resources/";
    private final static String GARDEN_FILE_NAME = "garden.txt";
    private final static File file = new File(RESOURCES_PATH + GARDEN_FILE_NAME);


    public static void main (String[] args){

        BasicConfigurator.configure();

        LOGGER.debug("main : start project");
        GameManager EasterGame = new GameManager(file);

        LOGGER.debug("main : start game");
        EasterGame.start();
    }
}
