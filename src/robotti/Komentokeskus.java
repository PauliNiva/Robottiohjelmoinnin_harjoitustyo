package robotti;

import lejos.nxt.Button;
import sensorit.Estesensori;
import sensorit.Viivasensori;

/**
 * Robotin "aivot". Luokassa on kaksi tilaa. Toisessa tilassa seurataan viivaa
 * niin kauan aikaa, kunnes robotti havaitsee esteen polullaan. Tällöin
 * siirryään esteen kierto -tilaan, josta palataan viivan seuraamis -tilaan, kun
 * este on kierretty.
 * 
 * @author Pauli
 * @version 06012015
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
    public void kaynnista() throws InterruptedException {
	int seurattavaArvo = viivasensori.kalibroi();
	while (Button.ENTER.isPressed())
	    //
	    ;
	while (!Button.ENTER.isPressed()) {
	    if (estesensori.haeEtaisyys() < 30) {
		ohjausyksikko.pysahdy();
		Thread.sleep(700); // Mahdollinen poikkeus heitetään eteenpäin
		ohjausyksikko.kierraEste(seurattavaArvo);
	    } else {
		int valoarvo = viivasensori.lueArvo();
		int kaannos = pidSaadin.pidLaske(valoarvo, seurattavaArvo);
		int tehoSuoraanKulkiessa = pidSaadin.haeTehoSuoraanKulkiessa();
		int vasenTeho = tehoSuoraanKulkiessa - kaannos;
		int oikeaTeho = tehoSuoraanKulkiessa + kaannos;
		ohjausyksikko.liiku(vasenTeho, oikeaTeho, tehoSuoraanKulkiessa);
	    }
	}
    }
}
