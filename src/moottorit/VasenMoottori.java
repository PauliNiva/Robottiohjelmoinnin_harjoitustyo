package moottorit;

import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;

public class VasenMoottori {

    private NXTRegulatedMotor moottoriC;
    private NXTMotor moottori;

    public VasenMoottori() {
	moottoriC = Motor.C;
	moottori = new NXTMotor(MotorPort.C, MotorPort.STOP);
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

    public NXTRegulatedMotor haeMoottori() {
	return moottoriC;
    }

    public void asetaTeho(int teho) {
	moottori.setPower(teho);
    }
}