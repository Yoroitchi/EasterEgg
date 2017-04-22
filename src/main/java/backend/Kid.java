package org.easteregg;

/**
 * Created by r1leg on 22/04/2017.
 */
public class Kid extends Element {
    public String sequence;
    public Egg[] sac;

    //region Getters
    public String getSequence() {
        return sequence;
    }

    public Egg[] getSac() {
        return sac;
    }
    //endregion

    //region Setters
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public void setSac(Egg[] sac) {
        this.sac = sac;
    }
    //endregion

    //region Functions

    //endregion
}
