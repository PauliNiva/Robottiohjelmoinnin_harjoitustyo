package moottorit;

import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;

public class OikeaMoottori {

    private NXTMotor moottori;
    private NXTRegulatedMotor moottoriA;

    public OikeaMoottori() {
	moottori = new NXTMotor(MotorPort.A, MotorPort.STOP);
	moottoriA = Motor.A;
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
	return moottoriA;
    }
}
