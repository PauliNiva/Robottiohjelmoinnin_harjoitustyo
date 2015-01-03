package robotti;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import sensorit.Valosensori;
import toimintamallit.PIDSaadin;

public class Komentokeskus {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
	Valosensori valosensori = new Valosensori(SensorPort.S1);
	valosensori.kalibroi();
	LCD.clear();
	PIDSaadin pidSaadin = new PIDSaadin();
	int valoarvo;
	boolean suunta = true;
	int valkoiset = 0;
	int etaisyys = valosensori.getKynnysarvo();
	while (Button.ENTER.isPressed())
	    ;
	while (!Button.ESCAPE.isPressed()) {
	    valoarvo = valosensori.lueValoarvo();
	    pidSaadin.seuraaViivaa(valoarvo);
	    if (valosensori.getKynnysarvo() > valoarvo) {
		valkoiset = 0;
	    } else {
		valkoiset++;
	    }
	    if (valkoiset == 500) {
		suunta = !suunta;
	    }
	    Thread.sleep(5);
	}
    }
}
