package moottorit;

import lejos.nxt.NXTRegulatedMotor;

/**
 * Rajapinta moottoreille.
 * 
 * @author Pauli Niva
 * @version 08012015
 */

public interface Moottori {

    public void eteenpain();

    public void taaksepain();

    public void pysahdy();

    public void asetaTeho(int teho);

    public NXTRegulatedMotor haeMoottori();
}
