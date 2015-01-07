package moottorit;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;

public class KaikuluotainMoottori {

    private NXTMotor moottori;

    public KaikuluotainMoottori() {
	moottori = new NXTMotor(MotorPort.B, MotorPort.STOP);
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
