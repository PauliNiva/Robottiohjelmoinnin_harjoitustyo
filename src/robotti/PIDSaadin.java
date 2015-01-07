package robotti;

/**
 * Luokka jossa lasketaan tarvittava virheen korjaus, eli robotin kääntö
 * derivaatan ja integraalin avulla.
 * 
 * @author Pauli
 * @version 07012015
 */
public class PIDSaadin {

    private int Kp;
    private int Ki;
    private int Kd;
    private int TehoSuoraanKulkiessa;
    private int integraali;
    private int derivaatta;
    private int virhe;
    private int viimeisinVirhe;

    public PIDSaadin() {
	this.Kp = 300;
	this.Ki = 7;
	this.Kd = 600;
	this.TehoSuoraanKulkiessa = 65; // Teho jolla robotin olisi tarkoitus
					// kulkea, kun virhe on nolla.
	this.integraali = 0;
	this.derivaatta = 0;
	this.virhe = 0;
	this.viimeisinVirhe = 0;
    }

    /**
     * Metodi robotin virheellisen sijainnin korjaamiseen, eli metodi laskee
     * arvion seuraavasta tarvittavasta käännöksestä.
     * 
     * @param valoarvo
     * @param seurattavaArvo
     * @return
     */
    public int pidLaske(int valoarvo, int seurattavaArvo) {
	int kaannos;
	virhe = valoarvo - seurattavaArvo;
	integraali = integraali * 2 / 3 + virhe;
	derivaatta = virhe - viimeisinVirhe;
	kaannos = Kp * virhe + Ki * integraali + Kd * derivaatta;
	kaannos = kaannos / 100;
	viimeisinVirhe = virhe;
	return kaannos;
    }

    public int haeTehoSuoraanKulkiessa() {
	return this.TehoSuoraanKulkiessa;
    }

    public void nollaaVirhe() {
	this.virhe = 0;
    }

    public void nollaaViimeisinVirhe() {
	this.viimeisinVirhe = 0;
    }
}
