package sensorit;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

/**
 * Sensori joka erottaa tumman viivan vaaleammasta taustasta.
 * 
 * Valoarvot (viivan arvo ja taustan arvo) kalibroidaan valosensorilla ja
 * kynnysarvo lasketaan naiden lukujen mediaanina. Seurattavaa arvoa kaytetaan
 * viivan ja taustan erottamiseen ja robotti pyrkii pitämään sensorin kyseisen
 * arvon päällä.
 * 
 * Valosensori kiinnitettävä porttiin 1.
 * 
 * @author Pauli Niva
 * @version 06012015
 */

public class Viivasensori {

    private LightSensor viivasensori;
    private int taustanArvo;
    private int viivanArvo;
    private int seurattavaArvo;

    public Viivasensori() {
	viivasensori = new LightSensor(SensorPort.S1);
	viivasensori.setFloodlight(true);
    }

    public int lueArvo() {
	return viivasensori.readValue();
    }

    public int haeSeurattavaArvo() {
	return this.seurattavaArvo;
    }

    public int asetaSeurattavaArvo() {
	this.seurattavaArvo = (this.taustanArvo + this.viivanArvo) / 2;
	return this.seurattavaArvo;

    }

    public int kalibroi() {
	this.taustanArvo = kalibroiArvo("TAUSTA");
	this.viivanArvo = kalibroiArvo("VIIVA");
	return asetaSeurattavaArvo();
    }

    /**
     * Metodi jossa annetaan viivan ja taustan arvot kalibrointimetodille
     * painamalla oranssia "Enter" napppia. Metodi piirtää lcd-näytölle
     * viivasensorin lukemaa lukua käyttäjän tarkkailtavaksi.
     * 
     * @param kohde
     *            saadaan kalibroi -metodilta, joka piirretään käyttäjän
     *            opastukseksi lcd-näytölle.
     * @return sensorinArvo palautetaan kalibroi -metodille, joka tallettaa sen
     *         kohdetta vastaavaan muuttujaan.
     */
    @SuppressWarnings("deprecation")
    public int kalibroiArvo(String kohde) {
	int sensorinArvo = 0; // Arvon initialisointi
	while (Button.ENTER.isPressed())
	    ;
	LCD.clear();
	LCD.drawString("PAINA ORANSSIA", 0, 0);
	LCD.drawString("KALIBROIDAKSESI", 0, 1);
	LCD.drawString(kohde, 0, 2);
	while (!Button.ENTER.isPressed()) {
	    sensorinArvo = lueArvo(); // luetaan mitattavan kohteen arvoa
	    LCD.drawInt(sensorinArvo, 4, 8, 2); // ja piirretään se lcd-näytölle
	    LCD.refresh();
	}
	return sensorinArvo;
    }
}
