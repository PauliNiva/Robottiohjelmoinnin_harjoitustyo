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
 * @author Pauli Niva
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

    /**
     * Metodi pyörittää robottia sen kahden eri tilan välillä. Jos havaitaan
     * este, siirrytään väistämistilaan, muuten ollaan perustilassa joka seuraa
     * viivaa. Tämä metodi sisältää robotin hätäpysäytyksen. Eli robotti
     * pyörittää tiloja niin kauan aikaa kunnes oranssia enter -nappulaa
     * painetaan.
     * 
     * @throws InterruptedException
     *             Thread.sleep -metodi voi heittää poikkeuksen, jos odottaminen
     *             keskeytetään. Poikkeus heitetään main -metodille käynnistys
     *             -luokassa, joka käsittelee poikkeuksen.
     */
    @SuppressWarnings("deprecation")
    public void kaynnista() throws InterruptedException {
	int seurattavaArvo = viivasensori.kalibroi();
	while (Button.ENTER.isPressed())
	    // odotetaan kalibrointia, eikä oteta varaslähtöä.
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
