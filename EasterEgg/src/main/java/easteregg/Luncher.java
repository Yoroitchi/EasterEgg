package easteregg;

/**
 * Created by Selim on 23/04/2017.
 */
public class Luncher {

    public static void main(String[] args){
        System.out.println("Hello World");

        GameManager EasterGame = new GameManager();
        EasterGame.instanceGame();
        EasterGame.garden.printTable();
    }
}
