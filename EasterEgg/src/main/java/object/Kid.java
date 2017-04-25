package object;

/**
 * Created by Selim on 22/04/2017.
 */
public class Kid extends Element {

    public char direction;
    public String sequence = null;
    public int eggBag = 0;
    private String name = null;

    public Kid(int posX, int posY, char startDirection, String sequence, String name) {
        this.posX = posX;
        this.posY = posY;
        this.direction = startDirection;
        this.sequence = sequence;
        this.name = name;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char startDirection) {
        this.direction = startDirection;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public int getSac() {
        return eggBag;
    }

    public void setSac(int eggBag) {
        this.eggBag = eggBag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printInfo(){
        System.out.print("X = " + this.posX + ", ");
        System.out.print("Y = " + this.posY + ", ");
        System.out.print("Direction de Départ = " + this.direction + ", ");
        System.out.print("Séquence = " + this.sequence + ", ");
        System.out.println("Nom = " + this.name + ", ");
    }
}