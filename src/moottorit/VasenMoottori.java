package moottorit;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;

public class VasenMoottori {

    private NXTMotor moottori;

    public VasenMoottori() {
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

    public void asetaTeho(int teho) {
	moottori.setPower(teho);
    }
}