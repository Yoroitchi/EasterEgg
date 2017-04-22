package easteregg;

/**
 * Created by Selim on 22/04/2017.
 */
public class Kid extends Element{

    String sequence;
    public Egg[] sac;

    public Kid() {
    }

    public Kid(String sequence, int posX, int posY) {
        this.sequence = sequence;
        this.posX = posX;
        this.posY = posY;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Egg[] getSac() {
        return sac;
    }

    public void setSac(Egg[] sac) {
        this.sac = sac;
    }
}
