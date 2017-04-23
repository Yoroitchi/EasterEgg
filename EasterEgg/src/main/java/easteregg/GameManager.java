package easteregg;

/**
 * Created by Selim on 22/04/2017.
 */
public class GameManager {

    public static void main(String[] args){
        System.out.println("Hello World");
        Garden garden = new Garden(4, 6);
        System.out.println(" "+garden.sizeX +" "+garden.sizeY);
        garden.printTable();
        KidDao kidDao = new KidDao();
        kidDao.findAllKids();
    }
}
