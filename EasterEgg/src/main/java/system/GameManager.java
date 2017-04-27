package system;

import dao.EggStackDao;
import dao.GardenDao;
import dao.KidDao;
import dao.RockDao;
import display.Frame;
import object.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

/**
 * Created by Selim on 22/04/2017.
 */
public class GameManager {

    private static final Logger LOGGER = Logger.getLogger(GameManager.class);

    private static final long delayTimeInSec = 3;

    static Garden garden = new GardenDao().findGarden();

    private static ArrayList<EggStack> eggStacks = new EggStackDao().findAllEggs();
    private static ArrayList<Kid> kids = new KidDao().findAllKids();
    private static ArrayList<Rock> rocks = new RockDao().findAllRocks();

    public void start(){

        LOGGER.debug("start : début");

        LOGGER.debug("start : premier chargement du jardin");
        loadGarden();

        Frame frame = new Frame(garden.getTable());

        LOGGER.debug("start : execution des tours");
        while(isKidWithSequence()) {

            delay();

            LOGGER.debug("start : déplacement de chaques enfants");
            for (Kid kid : kids) {

                LOGGER.debug("start : déplacement de "+ kid.getName());
                moveKid(kid);
            }

            LOGGER.debug("start : nettoyage du Jardin");
            garden.setTable(new Element[garden.getSizeX()][garden.getSizeY()]);

            LOGGER.debug("start : rechargement du Jardin");
            loadGarden();
        }

        System.out.println("End of Game");
        LOGGER.debug("start : fin");
    }

    private void loadGarden(){

        LOGGER.debug("loadGarden : début");

        LOGGER.debug("loadGarden : ajout des oeufs au jardin");
        for (EggStack eggStack : eggStacks) {

            LOGGER.debug("loadGarden : "+ eggStack +" oeufs ont été placer dans le jardin");
            garden.addEgg(eggStack);
        }

        LOGGER.debug("loadGarden : ajout des enfants au jardin");
        for(Kid kid : kids){

            LOGGER.debug("loadGarden : "+ kid.getName() +" est entré dans le jardin");
            garden.addKid(kid);
        }

        LOGGER.debug("loadGarden : ajout des rochers au jardin");
        for(Rock rock : rocks){

            LOGGER.debug("loadGarden : un rocher est présent dans le jardin");
            garden.addRock(rock);
        }

        LOGGER.debug("loadGarden : affichage");
        garden.printTable();

        LOGGER.debug("loadGarden : fin");
    }

    private void moveKid(Kid kid) {

        LOGGER.debug("moveKid : début");

        if (anEggIsGetBy(kid)){
            System.out.println("An Egg was find by " + kid.getName());
        }else if(kid.sequence.startsWith("A")){
            switch (kid.direction){
                case 'E' :
                    if(!isThereAObstacleAt(kid.posX, kid.posY + 1))kid.posY += 1;
                    break;
                case 'O' :
                    if(!isThereAObstacleAt(kid.posX, kid.posY - 1))kid.posY -= 1;
                    break;
                case 'N' :
                    if(!isThereAObstacleAt(kid.posX - 1, kid.posY))kid.posX -= 1;
                    break;
                case 'S' :
                    if(!isThereAObstacleAt(kid.posX + 1, kid.posY))kid.posX += 1;
                    break;
            }
            kid.sequence = kid.sequence.substring(1);
        }else if(kid.sequence.startsWith("D")){
            switch(kid.direction){
                case 'N' :
                    kid.setDirection('E');
                    break;
                case 'E' :
                    kid.setDirection('S');
                    break;
                case 'S' :
                    kid.setDirection('O');
                    break;
                case 'O' :
                    kid.setDirection('N');
                    break;
            }
            kid.sequence = kid.sequence.substring(1);
        }else if(kid.sequence.startsWith("G")) {
            switch (kid.direction) {
                case 'N':
                    kid.setDirection('O');
                    break;
                case 'O':
                    kid.setDirection('S');
                    break;
                case 'S':
                    kid.setDirection('E');
                    break;
                case 'E':
                    kid.setDirection('N');
                    break;
            }
            kid.sequence = kid.sequence.substring(1);
        }

        LOGGER.debug("moveKid : fin");
    }

    private boolean isThereAObstacleAt(int posX, int posY){
        try {
            return (garden.table[posX][posY] instanceof Rock || garden.table[posX][posY] instanceof Kid);
        }catch (ArrayIndexOutOfBoundsException e){
            LOGGER.debug("isThereAObstacleAt : un enfant essaye de sortir du jardin");
            return true;
        }
    }

    private boolean anEggIsGetBy(Kid kid){

        for (EggStack eggStack : eggStacks){
            if(eggStack.posX == kid.posX && eggStack.posY == kid.posY && eggStack.getEggsNb() > 0){
                kid.eggBag += 1;
                eggStack.eggsNb -=1;
                LOGGER.debug("anEggIsGetBy : ajout d'un oeuf dans le panier de "+ kid.getName());
                return true;
            }
        }
        return false;
    }

    private boolean isKidWithSequence(){

        for (Kid kid : kids) {
            if (kid.getSequence().equals("")) return false;
        }

        LOGGER.debug("isKidWithSequence : les enfants ont terminé leur scéquences");
        return true;
    }

    private void delay(){
        try {
            LOGGER.debug("delay : délais de "+delayTimeInSec+" secondes");
            TimeUnit.SECONDS.sleep(delayTimeInSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
