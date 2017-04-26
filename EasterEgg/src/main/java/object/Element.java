package object;

/**
 * Created by Selim on 22/04/2017.
 */
public abstract class Element {
    //This object will define all element of our game
    public int posX;
    public int posY;

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
