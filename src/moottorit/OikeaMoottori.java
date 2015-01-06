package moottorit;

import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;

public class OikeaMoottori {

    private NXTRegulatedMotor moottoriA;
    private NXTMotor moottori;

    public OikeaMoottori() {
	moottoriA = Motor.A;
	moottori = new NXTMotor(MotorPort.A, MotorPort.STOP);
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
	return moottoriA;
    }

    public void asetaTeho(int teho) {
	moottori.setPower(teho);
    }
}
