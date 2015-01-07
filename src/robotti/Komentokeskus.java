package robotti;

import lejos.nxt.Button;
import sensorit.Estesensori;
import sensorit.Viivasensori;

/**
 * Robotin "aivot".
 * 
 * @author Pauli
 * @version 03012015
 */

public class Komentokeskus {

    private PIDSaadin pidSaadin;
    private Ohjausyksikko ohjausyksikko;
    private Viivasensori viivasensori;
    private Estesensori estesensori;

    public Komentokeskus() {
	viivasensori = new Viivasensori();
	ohjausyksikko = new Ohjausyksikko();
	pidSaadin = new PIDSaadin();
	estesensori = new Estesensori();
    }

    @SuppressWarnings("deprecation")
    public void kaynnista() throws Exception {
	int seurattavaArvo = viivasensori.kalibroi();
	while (Button.ENTER.isPressed())
	    ;
	while (!Button.ENTER.isPressed()) {
	    if (estesensori.haeEtaisyys() < 30) {
		ohjausyksikko.pysahdy();
		Thread.sleep(700);
		ohjausyksikko.kierraEste(seurattavaArvo);
	    } else {
		int valoarvo = viivasensori.lueArvo();
		int kaannos = pidSaadin.pidLaske(valoarvo, seurattavaArvo);
		int Tp = pidSaadin.getTp();
		int vasenTeho = Tp - kaannos;
		int oikeaTeho = Tp + kaannos;
		ohjausyksikko.liiku(vasenTeho, oikeaTeho, Tp);
	    }
	}
    }
}
