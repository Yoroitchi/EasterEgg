package easteregg;

import java.lang.reflect.Type;

/**
 * Created by Selim on 22/04/2017.
 */
public class Garden {
    int sizeX;
    int sizeY;
    Element[][] table;

    public Garden(int sizeX, int sizeY){
        setSizeX(sizeX);
        setSizeY(sizeY);
        setGarden(new Element[sizeX][sizeY]);
        for (int i = 0; i < sizeX; i++){
            for (int j = 0; j < sizeY; j++){
                this.table[i][j] = null;
            }
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public Object[] getGarden() {
        return table;
    }

    public void setGarden(Element[][] garden) {
        this.table = garden;
    }

    /*public void addEgg (Egg egg){
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if(egg.posX == i && egg.posY == j){
                    this.table[i][j] = egg;
                }
            }
        }
    }

    public void addKid (Kid kid){
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if(kid.posX == i && kid.posY == j){
                    this.table[i][j] = kid;
                }
            }
        }
    }

    public void addRock (Rock rock){
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if(rock.posX == i && rock.posY == j){
                    this.table[i][j] = rock;
                }
            }
        }
    }*/

    public void printTable(){
        for(int i = 0; i < this.sizeX; i++){
            for(int j = 0; j < this.sizeY; j++) {
                if(this.table[i][j] instanceof Egg){

                    System.out.print(" Egg ");

                } else if (this.table[i][j] instanceof Kid){

                    System.out.print(" Kid ");

                } else if (this.table[i][j] instanceof Rock){

                    System.out.print("Rock");
                } else {

                    System.out.print("Grass");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
