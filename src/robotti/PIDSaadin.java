package robotti;

public class PIDSaadin {

    private int Kp;
    private int Ki;
    private int Kd;
    private int Tp;
    private int integraali;
    private int derivaatta;
    private int virhe;
    private int viimeisinVirhe;

    public PIDSaadin() {
	this.Kp = 300;
	this.Ki = 7;
	this.Kd = 600;
	this.Tp = 65;
	this.integraali = 0;
	this.derivaatta = 0;
	this.virhe = 0;
	this.viimeisinVirhe = 0;
    }

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

    public int getTp() {
	return this.Tp;
    }

    public void nollaaVirhe() {
	this.virhe = 0;
    }

    public void nollaaViimeisinVirhe() {
	this.viimeisinVirhe = 0;
    }
}
