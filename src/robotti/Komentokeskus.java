package robotti;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import sensorit.Valosensori;
import toimintamallit.PIDSaadin;

public class Komentokeskus {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
	Valosensori valosensori = new Valosensori(SensorPort.S1);
	valosensori.kalibroi();
	PIDSaadin pidSaadin = new PIDSaadin();
	int valoarvo;
	int etaisyys = valosensori.getKynnysarvo();
	while (!Button.ESCAPE.isPressed()) {
	    valoarvo = valosensori.lueValoarvo();
	    pidSaadin.seuraaViivaa(valoarvo);
	}
    }
}
