package moottorit;

import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;

public class VasenMoottori {

    private NXTMotor moottori;
    private NXTRegulatedMotor moottoriC;

    public VasenMoottori() {
	moottori = new NXTMotor(MotorPort.C, MotorPort.STOP);
	moottoriC = Motor.C;
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

    public NXTRegulatedMotor haeMoottori() {
	return moottoriC;
    }
}