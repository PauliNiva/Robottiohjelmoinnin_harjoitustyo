package sensorit;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

/**
 * Sensori joka erottaa tumman viivan vaaleammasta taustasta.
 * 
 * Valoarvot (viivan arvo ja taustan arvo) kalibroidaan valosensorilla ja
 * kynnysarvo lasketaan naiden lukujen mediaanina. Kynnysarvoa kaytetaan viivan
 * ja taustan erottamiseen.
 * 
 * @author Pauli Niva
 * @version 30122014
 */

public class Valosensori {

    private LightSensor valosensori;
    private int viivanArvo;
    private int taustanArvo;
    private int kynnysarvo;

    public Valosensori(SensorPort sensoriportti) {
	valosensori = new LightSensor(sensoriportti);
	valosensori.setFloodlight(true);
    }

    @SuppressWarnings("deprecation")
    private int lueArvo(String kohde) {
	int sensorinArvo = 0; // Arvon initialisointi
	LCD.clear();
	LCD.drawString("PAINA ORANSSIA", 0, 0);
	LCD.drawString("KALIBROIDAKSESI", 0, 1);
	LCD.drawString("kohde", 0, 2);
	while (!Button.ENTER.isPressed()) {
	    sensorinArvo = valosensori.readValue();
	    LCD.drawInt(sensorinArvo, 4, 8, 2);
	    LCD.refresh();
	}
	return sensorinArvo;
    }

    public void kalibroi() {
	this.viivanArvo = lueArvo("VIIVAN ARVO");
	this.taustanArvo = lueArvo("TAUSTAN ARVO");
	this.kynnysarvo = (viivanArvo + taustanArvo) / 2;
    }

    public int getViivanArvo() {
	return this.viivanArvo;
    }

    public int getTaustanArvo() {
	return this.taustanArvo;
    }

    public int getKynnysarvo() {
	return this.kynnysarvo;
    }
}
