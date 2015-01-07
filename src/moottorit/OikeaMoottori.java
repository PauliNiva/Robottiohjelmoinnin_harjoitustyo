package moottorit;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;

public class OikeaMoottori {

    private NXTMotor moottori;

    public OikeaMoottori() {
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

    public void asetaTeho(int teho) {
	moottori.setPower(teho);
    }
}
