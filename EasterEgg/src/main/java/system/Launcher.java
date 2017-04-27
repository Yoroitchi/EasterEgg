package system;

import display.Frame;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Created by Selim on 23/04/2017.
 */
public class Launcher {

    //Logger definition
    private static final Logger LOGGER = Logger.getLogger(Launcher.class);

    public static void main (String[] args){

        BasicConfigurator.configure();

        LOGGER.debug("main : start project");
        GameManager EasterGame = new GameManager();

        LOGGER.debug("main : start game");
        EasterGame.start();
    }
}
