package robotti;

import lejos.nxt.LCD;

/**
 * Luokka robotin käynnistämiseen.
 * 
 * @author Pauli Niva
 * @version 08012015
 */

public class Kaynnistys {

    public static void main(String[] args) {
	Komentokeskus komentokeskus = new Komentokeskus();
	try {
	    komentokeskus.kaynnista();
	} catch (Exception e) {
	    LCD.drawString("KESKEYTIT", 0, 0);
	    LCD.drawString("ODOTTAMISEN", 0, 1);
	}
    }
}
