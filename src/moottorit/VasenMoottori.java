package moottorit;

import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;

/**
 * Luokka vasemman moottorin pyörittämiseen. Moottori kiinnitettävä porttiin C.
 * Luokka toteuttaa rajapinnan Moottori.
 * 
 * @author Pauli Niva
 * @version 07012015
 */
public class VasenMoottori implements Moottori {

    private NXTMotor moottori;
    private NXTRegulatedMotor moottoriC;

    public VasenMoottori() {
	moottori = new NXTMotor(MotorPort.C, MotorPort.STOP);
	moottoriC = Motor.C;
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
	return moottoriC;
    }
}