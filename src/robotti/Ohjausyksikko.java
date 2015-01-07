package robotti;

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import moottorit.Kaikuluotainmoottori;
import moottorit.OikeaMoottori;
import moottorit.VasenMoottori;
import sensorit.Estesensori;
import sensorit.Viivasensori;

/**
 * Luokka robotin liikuttamiseen.
 * 
 * @author Pauli
 * @version 07012015
 */

public class Ohjausyksikko {
    private VasenMoottori vasenMoottori;
    private OikeaMoottori oikeaMoottori;
    private DifferentialPilot ohjaaja;
    private Kaikuluotainmoottori kaikuluotainmoottori;
    private Estesensori estesensori;
    private Viivasensori viivasensori;
    private PIDSaadin pidSaadin;

    public Ohjausyksikko() {
	this.viivasensori = new Viivasensori();
	this.kaikuluotainmoottori = new Kaikuluotainmoottori();
	this.estesensori = new Estesensori();
	this.vasenMoottori = new VasenMoottori();
	this.oikeaMoottori = new OikeaMoottori();
	this.ohjaaja = new DifferentialPilot(5.6F, 12F,
		this.vasenMoottori.haeMoottori(),
		this.oikeaMoottori.haeMoottori());
	this.ohjaaja.setRotateSpeed(50);
	this.ohjaaja.setTravelSpeed(5);
	this.pidSaadin = new PIDSaadin();
    }

    public void liiku(int vasenTeho, int oikeaTeho, int Tp) {
	if (vasenTeho >= 0) {
	    vasenMoottori.asetaTeho(vasenTeho);
	    vasenMoottori.eteenpain();
	} else {
	    vasenMoottori.asetaTeho((vasenTeho * -1) + Tp);
	    vasenMoottori.taaksepain();
	}
	if (oikeaTeho >= 0) {
	    oikeaMoottori.asetaTeho(oikeaTeho);
	    oikeaMoottori.eteenpain();
	} else {
	    oikeaMoottori.asetaTeho((oikeaTeho * -1) + Tp);
	    oikeaMoottori.taaksepain();
	}
    }

    public void pysahdy() {
	vasenMoottori.pysahdy();
	oikeaMoottori.pysahdy();
    }

    public void kierraEste(int seurattavaArvo) {
	Motor.A.resetTachoCount();
	Motor.C.resetTachoCount();
	ohjaaja.reset();
	ohjaaja.rotate(-63);
	kaikuluotainmoottori.kaannyVasen(90);
	ajaOhi();
	ohjaaja.rotate(55);
	etsiKohde();
	ajaOhi();
	ohjaaja.rotate(55);
	etsiViiva(seurattavaArvo);
	kaikuluotainmoottori.kaannyOikea(90);
	ohjaaja.rotate(-50);
	Motor.A.suspendRegulation();
	Motor.B.suspendRegulation();
	Motor.C.suspendRegulation();
    }

    public void ajaOhi() {
	while (estesensori.haeEtaisyys() != 255) {
	    ohjaaja.forward();
	}
	ohjaaja.stop();
	ohjaaja.travel(15);
    }

    public void etsiKohde() {
	while (estesensori.haeEtaisyys() == 255) {
	    ohjaaja.forward();
	}
	ohjaaja.stop();
    }

    public void etsiViiva(int seurattavaArvo) {
	while (viivasensori.lueArvo() > seurattavaArvo) {
	    ohjaaja.forward();
	}
	ohjaaja.stop();
    }
}
