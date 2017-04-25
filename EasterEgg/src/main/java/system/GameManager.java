package system;

import dao.EggStackDao;
import dao.GardenDao;
import dao.KidDao;
import dao.RockDao;
import object.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

/**
 * Created by Selim on 22/04/2017.
 */
public class GameManager {

    private static Garden garden = new GardenDao().findGarden();

    private static ArrayList<EggStack> eggStacks = new EggStackDao().findAllEggs();
    private static ArrayList<Kid> kids = new KidDao().findAllKids();
    private static ArrayList<Rock> rocks = new RockDao().findAllRocks();

    public void start(){
        loadGarden();
        while(!isKidWithSequence()) {
            delay(3);
            for (Kid kid : kids) {
                moveKid(kid);
            }
            garden.setTable(new Element[garden.getSizeX()][garden.getSizeY()]);
            loadGarden();
        }
        System.out.println("End of Game");
    }

    private void loadGarden(){

        for (EggStack eggStack : eggStacks) {
            garden.addEgg(eggStack);
        }

        for(Kid kid : kids){
            garden.addKid(kid);
        }

        for(Rock rock : rocks){
            garden.addRock(rock);
        }

        garden.printTable();
    }

    private void moveKid(Kid kid) {
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
        System.out.println(kid.getSequence());
        System.out.println(kid.getDirection());
    }

    private boolean isThereAObstacleAt(int posX, int posY){
        try {
            return (garden.table[posX][posY] instanceof Rock || garden.table[posX][posY] instanceof Kid);
        }catch (ArrayIndexOutOfBoundsException e){
            //System.out.println("\n" + "A Kid try to move out of garden\nPlease check resources files");
            return true;
        }
    }

    private boolean anEggIsGetBy(Kid kid){

        for (EggStack eggStack : eggStacks){
            if(eggStack.posX == kid.posX && eggStack.posY == kid.posY && eggStack.getEggsNb() > 0){
                kid.eggBag += 1;
                eggStack.eggsNb -=1;
                return true;
            }
        }
        return false;
    }

    private boolean isKidWithSequence(){

        for (Kid kid : kids) {
            if (kid.getSequence().equals("")) return true;
        }
        return false;
    }

    private void delay (long delayTimeInSec){
        try {
            TimeUnit.SECONDS.sleep(delayTimeInSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
