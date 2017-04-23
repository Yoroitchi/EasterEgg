package easteregg;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Selim on 22/04/2017.
 */
public class GameManager {

    public final Garden garden = new GardenDao().findGarden();
    public final ArrayList<Kid> kids = new KidDao().findAllKids();
    public final ArrayList<EggStack> eggStacks = new EggStackDao().findAllEggs();
    public final ArrayList<Rock> rocks = new RockDao().findAllRocks();

    public void instanceGame(){
        Iterator<Kid> itKid = kids.iterator();
        Iterator<EggStack> itEggStack = eggStacks.iterator();
        Iterator<Rock> itRock = rocks.iterator();

        while(itKid.hasNext()){
            garden.addKid(itKid.next());
        }

        while(itEggStack.hasNext()){
            garden.addEgg(itEggStack.next());
        }

        while(itRock.hasNext()){
            garden.addRock(itRock.next());
        }
    }
}
