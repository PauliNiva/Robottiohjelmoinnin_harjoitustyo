package toimintamallit;

import lejos.nxt.MotorPort;

/**
 * Luokka moottorien kontrollointiin.
 * 
 * PID-saatimessa lasketaan tehot kummallekin moottorille, jotka annetaan
 * parametrina metodille joka ohjaa moottoreita joka eteen- tai taaksepain.
 * Lisaksi controlMotor -metodin int moden numeeriset arvot korvataan
 * staattisilla muuttujanimilla eteenpain, taaksepain ja pysahdy selkeyden
 * vuoksi.
 * 
 * @author Pauli Niva
 * @version 31122014
 */

public class Moottorit {

    private static MotorPort vasenMoottori = MotorPort.C;
    private static MotorPort oikeaMoottori = MotorPort.A;
    private static int eteenpain = 1;
    private static int taaksepain = 2;
    private static int pysahdy = 3;

    private Moottorit() {

    }

    public static void eteenpain(int tehoVasen, int tehoOikea) {
	vasenMoottori.controlMotor(tehoVasen, eteenpain);
	oikeaMoottori.controlMotor(tehoOikea, eteenpain);
    }

    public static void taaksepain(int tehoVasen, int tehoOikea) {
	vasenMoottori.controlMotor(tehoVasen, taaksepain);
	oikeaMoottori.controlMotor(tehoOikea, taaksepain);
    }

    public static void pysahdy() {
	vasenMoottori.controlMotor(0, pysahdy);
	oikeaMoottori.controlMotor(0, pysahdy);
    }
}
