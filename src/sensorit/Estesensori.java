package sensorit;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class Estesensori {

    private UltrasonicSensor estesensori;

    public Estesensori() {
	this.estesensori = new UltrasonicSensor(SensorPort.S2);
    }

    public int haeEtaisyys() {
	return estesensori.getDistance();
    }
}
