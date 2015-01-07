package moottorit;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;

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

    public void kaannyVasen(int aste) {
	moottori.resetTachoCount();
	while (Math.abs(haeTakometriLuku()) < aste) {
	    moottori.forward();
	}
	moottori.stop();
    }

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
