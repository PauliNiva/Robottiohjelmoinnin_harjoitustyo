package robotti;

import lejos.nxt.LCD;

/**
 * Luokka robotin käynnistämiseen.
 * 
 * @author Pauli
 * @version 06012015
 */

public class Kaynnistys {

    public static void main(String[] args) {
	Komentokeskus komentokeskus = new Komentokeskus();
	try {
	    komentokeskus.kaynnista();
	} catch (Exception e) {
	    LCD.drawString("KESKEYTIT", 0, 0);
	    LCD.drawString("ODOTUKSEN", 0, 1);
	}
    }
}
