package toimintamallit;

import lejos.nxt.SensorPort;
import sensorit.Valosensori;

public class PIDSaadin {

    private static Valosensori valosensori;
    private static Moottorit moottori;
    private static int Kp = 220;
    private static int Ki = 7;
    private static int Kd = 500;
    private static int Tp = 65;
    private static int dT;
    private static int Pc;
    private static int etaisyys = 45;
    private static int MAX_POWER = 100;
    private static int MIN_POWER = 0;
    private static int integraali = 0;
    private static int derivaatta = 0;
    private static int viimeisinVirhe = 0;
    private static int tehoVasen;
    private static int tehoOikea;

    public PIDSaadin() {
	valosensori = new Valosensori(SensorPort.S1);
    }

    public void seuraaViivaa(int valoarvo) {
	int kaannos;
	int virhe;
	virhe = valoarvo - etaisyys;
	integraali = integraali * 2 / 3 + virhe;
	derivaatta = virhe - viimeisinVirhe;
	kaannos = Kp * virhe + Ki * integraali + Kd * derivaatta;
	kaannos = kaannos / 100;
	tehoVasen = tehonSaadin(Tp - kaannos);
	tehoOikea = tehonSaadin(Tp + kaannos);
	Moottorit.eteenpain(tehoVasen, tehoOikea);
	viimeisinVirhe = virhe;
    }

    public int tehonSaadin(int teho) {
	if (teho < MIN_POWER) {
	    return MIN_POWER;
	}
	if (teho > MAX_POWER) {
	    return MAX_POWER;
	}
	return teho;
    }
}
