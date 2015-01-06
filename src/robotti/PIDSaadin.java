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
	this.Kp = 350;
	this.Ki = 7;
	this.Kd = 500;
	this.Tp = 45;
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
}
