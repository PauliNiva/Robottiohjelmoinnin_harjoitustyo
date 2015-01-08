package moottorit;

import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;

/**
 * Luokka oikean moottorin pyörittämiseen. Moottori kytkettävä porttiin A.
 * Luokka toteuttaa rajapinnan Moottori.
 * 
 * @author Pauli Niva
 * @version 07012015
 */
public class OikeaMoottori implements Moottori {

    private NXTMotor moottori;
    private NXTRegulatedMotor moottoriA;

    public OikeaMoottori() {
	moottori = new NXTMotor(MotorPort.A, MotorPort.STOP);
	moottoriA = Motor.A;
    }

    @Override
    public void eteenpain() {
	moottori.forward();
    }

    @Override
    public void taaksepain() {
	moottori.backward();
    }

    @Override
    public void pysahdy() {
	moottori.stop();
    }

    @Override
    public void asetaTeho(int teho) {
	moottori.setPower(teho);
    }

    @Override
    public NXTRegulatedMotor haeMoottori() {
	return moottoriA;
    }
}
