package robotti;

import lejos.nxt.Button;
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

    public Komentokeskus() {
	viivasensori = new Viivasensori();
	ohjausyksikko = new Ohjausyksikko();
	pidSaadin = new PIDSaadin();
    }

    @SuppressWarnings("deprecation")
    public void kaynnista() throws Exception {
	int seurattavaArvo = viivasensori.kalibroi();
	while (Button.ENTER.isPressed())
	    ;
	while (!Button.ENTER.isPressed()) {
	    int valoarvo = viivasensori.lueArvo();
	    int kaannos = pidSaadin.pidLaske(valoarvo, seurattavaArvo);
	    int Tp = pidSaadin.getTp();
	    int vasenTeho = Tp - kaannos;
	    int oikeaTeho = Tp + kaannos;
	    ohjausyksikko.liiku(vasenTeho, oikeaTeho, Tp);
	    Thread.sleep(5);
	}
    }
}
