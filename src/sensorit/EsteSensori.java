package sensorit;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class EsteSensori {

    private UltrasonicSensor estesensori;

    public EsteSensori() {
	this.estesensori = new UltrasonicSensor(SensorPort.S2);
    }

    public int haeEtaisyys() {
	return estesensori.getDistance();
    }
}
