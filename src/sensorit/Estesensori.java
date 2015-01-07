package sensorit;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

/**
 * Luokka esteiden etäisyyden mittaamiseen. Ultraäänisensori kiinnitettävä
 * porttiin 2.
 * 
 * @author Pauli
 * @version 06012015
 */

public class Estesensori {

    private UltrasonicSensor estesensori;

    public Estesensori() {
	this.estesensori = new UltrasonicSensor(SensorPort.S2);
    }

    public int haeEtaisyys() {
	return estesensori.getDistance();
    }
}
