package toimintamallit;

import lejos.nxt.SensorPort;
import sensorit.Valosensori;

public class PIDSaadin {

    private static Valosensori valosensori;
    private static Moottorit moottori;
    private static int Kp = 1000;
    private static int Ki = 100;
    private static int Kd = 10000;
    private static int Tp = 50;
    private static int dT;
    private static int Pc;
    private static int etaisyys = 45;
    private static int MAX_POWER;
    private static int MIN_POWER;
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
	integraali = integraali + virhe;
	derivaatta = virhe - viimeisinVirhe;
	kaannos = Kp * virhe + Ki * integraali + Kd * derivaatta;
	kaannos = kaannos / 100;
	tehoVasen = Tp - kaannos;
	tehoOikea = Tp + kaannos;
	Moottorit.eteenpain(tehoVasen, tehoOikea);
	viimeisinVirhe = virhe;
    }
}
