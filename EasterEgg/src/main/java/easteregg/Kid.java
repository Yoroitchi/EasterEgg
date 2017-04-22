package easteregg;

/**
 * Created by Selim on 22/04/2017.
 */
public class Kid extends Element{
    String sequence;
    public Egg[] sac;

    public Kid(String sequence, Egg[] sac) {
        this.sequence = sequence;
        this.sac = sac;
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
