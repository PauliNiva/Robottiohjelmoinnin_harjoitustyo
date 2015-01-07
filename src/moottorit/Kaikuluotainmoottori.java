package moottorit;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;

/**
 * Luokka moottorin, johon kaikuluotain on kiinnitetty käyttämiseen. Moottori
 * kytkettävä porttiin B.
 * 
 * @author Pauli
 * @version 07012015
 */
public class Kaikuluotainmoottori {

    private NXTMotor moottori;

    public Kaikuluotainmoottori() {
	moottori = new NXTMotor(MotorPort.B, MotorPort.STOP);
	moottori.setPower(30);
	moottori.stop();
    }

    public void eteenpain() {
	moottori.forward();
    }

    public void taaksepain() {
	moottori.backward();
    }

    public void pysahdy() {
	moottori.stop();
    }

    public void asetaTeho(int teho) {
	moottori.setPower(teho);
    }

    /**
     * Metodi ultraäänisensorin kääntämiseen vasemmalle sensoria käännetään
     * parametrina saadun arvon verran asteina.
     * 
     * @param aste
     */
    public void kaannyVasen(int aste) {
	moottori.resetTachoCount();
	while (Math.abs(haeTakometriLuku()) < aste) {
	    moottori.forward();
	}
	moottori.stop();
    }

    /**
     * Metodi ultraäänisensorin kääntämiseen vasemmalle sensoria käännetään
     * parametrina saadun arvon verran asteina.
     * 
     * @param aste
     */
    public void kaannyOikea(int aste) {
	moottori.resetTachoCount();
	while (Math.abs(haeTakometriLuku()) < aste) {
	    moottori.backward();
	}
	moottori.stop();
    }

    public int haeTakometriLuku() {
	return moottori.getTachoCount();
    }
}
