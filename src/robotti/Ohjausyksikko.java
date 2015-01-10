package robotti;

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import moottorit.Kaikuluotainmoottori;
import moottorit.Moottori;
import moottorit.OikeaMoottori;
import moottorit.VasenMoottori;
import sensorit.Estesensori;
import sensorit.Viivasensori;

/**
 * Luokka robotin liikuttamiseen.
 * 
 * @author Pauli Niva
 * @version 07012015
 */

public class Ohjausyksikko {
    private Moottori vasenMoottori;
    private Moottori oikeaMoottori;
    private DifferentialPilot ohjaaja;
    private Kaikuluotainmoottori kaikuluotainmoottori;
    private Estesensori estesensori;
    private Viivasensori viivasensori;

    public Ohjausyksikko() {
	this.viivasensori = new Viivasensori();
	this.kaikuluotainmoottori = new Kaikuluotainmoottori();
	this.estesensori = new Estesensori();
	this.vasenMoottori = new VasenMoottori();
	this.oikeaMoottori = new OikeaMoottori();
	this.ohjaaja = new DifferentialPilot(5.6F, 12F, // arvot senttimetreinä
		this.vasenMoottori.haeMoottori(),
		this.oikeaMoottori.haeMoottori());
	this.ohjaaja.setRotateSpeed(50);
	this.ohjaaja.setTravelSpeed(5);
    }

    /**
     * Metodi robotin etenemiseen viivaa seuraten. Metodi pyörittää kumpaakin
     * vetävää servomoottoria joko eteen- tai taaksepäin itsenäisesti riippuen
     * parametrina tulevasta halutusta tehosta. Positiivisella arvolla vedetään
     * eteenpäin ja negatiivisella arvolla muutetaan arvo positiiviseksi ja
     * käännetään moottorin kulkusuunta taaksepäin.
     * 
     * @param vasenTeho
     *            on haluttu teho vasemmalle moottorille, negatiivinen teho
     *            muutetaan positiiviseksi ja käännetään moottorin kulkusuunta
     * @param oikeaTeho
     *            on haluttu teho oikealle moottorille, negatiivinen teho
     *            muutetaan positiiviseksi ja käännetään moottorin kulkusuunta
     * @param tehoSuoraanKulkiessa
     *            on teho jolla kummatkin moottorit vetävät kun robotti kulkee
     *            suoraan viivaan seuraamiskohtaa pitkin, eli kun virhe on
     *            nolla.
     */
    public void liiku(int vasenTeho, int oikeaTeho, int tehoSuoraanKulkiessa) {
	if (vasenTeho >= 0) {
	    vasenMoottori.asetaTeho(vasenTeho);
	    vasenMoottori.eteenpain();
	} else {
	    vasenMoottori.asetaTeho((vasenTeho * -1) + tehoSuoraanKulkiessa);
	    vasenMoottori.taaksepain();
	}
	if (oikeaTeho >= 0) {
	    oikeaMoottori.asetaTeho(oikeaTeho);
	    oikeaMoottori.eteenpain();
	} else {
	    oikeaMoottori.asetaTeho((oikeaTeho * -1) + tehoSuoraanKulkiessa);
	    oikeaMoottori.taaksepain();
	}
    }

    public void pysahdy() {
	vasenMoottori.pysahdy();
	oikeaMoottori.pysahdy();
    }

    /**
     * Metodi on algoritmi joka toisia metodeja kutsumalla ohittaa esteen ja
     * palaa viivalle ja on lopuksi valmis jatkamaan viivan seuraamista. Koska
     * robotissa on välitykset, eikä suora veto, niin käännösten arvot eivät ole
     * suoraan vastaa kulmaa jonka robotti kääntyy.
     * 
     * @param seurattavaArvo
     *            on arvo, joka ilmaisee missä robotin halutaan olevan metodin
     *            suorituksen päättyessä.
     */
    public void kierraEste(int seurattavaArvo) {
	resetoiMoottorit(); // poisteaan takometriluvut moottoreista.
	ohjaaja.reset(); // poistetaan differentialpilotin omat laskut.
	ohjaaja.rotate(-63); // käännytään oikealla. Asteet eivät mene yksi
			     // yhteen välitysten takia.
	kaikuluotainmoottori.kaannyVasen(90);
	ajaOhi();
	ohjaaja.rotate(57); // käännytään vasemmalle. Asteet eivät mene yksi
			    // yhteen välitysten takia.
	etsiKohde();
	ajaOhi();
	ohjaaja.travel(12); // otetaan turvaväli kääntymiseen.
	ohjaaja.rotate(55); // käännytään oikealla. Asteet eivät mene yksi
			    // yhteen välitysten takia.
	etsiViiva(seurattavaArvo);
	ohjaaja.travel(8); // mennään vähän viivan yli, jotta viivasensori on
			   // käännöksen jälkeen viivan päällä.
	kaikuluotainmoottori.kaannyOikea(90);
	ohjaaja.rotate(-50); // käännytään oikealla. Asteet eivät mene yksi
			     // yhteen välitysten takia.
	vapautaMoottorit(); // vapautetaan moottori ohjaajalta, jotta viivan
			    // seuraaminen onnistuu.
    }

    /**
     * Metodi ajaa eteenpäin niin kauan kunnes estesensori ei enää havaitse
     * estettä, jonka jälkeen robotti ajaa vielä 15 senttiä eteenpäin, jotta
     * varmasti on tilaa ohitukselle.
     */
    public void ajaOhi() {
	while (estesensori.haeEtaisyys() != 255) {
	    ohjaaja.forward();
	}
	ohjaaja.stop();
	ohjaaja.travel(15);
    }

    /**
     * Metodi etsii esteen kulkemalla niin kauan eteenpäin, kunnes estesensori
     * löytää sen, jonka jälkeen robotti pysähtyy.
     */
    public void etsiKohde() {
	while (estesensori.haeEtaisyys() > 50) {
	    ohjaaja.forward();
	}
	ohjaaja.stop();
    }

    /**
     * Metodi etsii viivan ajamalla suoraan niin kauan kunnes viiva tulee
     * vastaan.
     * 
     * @param seurattavaArvo
     *            on arvo jota robotti koittaa seurata, eli arvo jota robotti
     *            etsii.
     */
    public void etsiViiva(int seurattavaArvo) {
	while (viivasensori.lueArvo() > seurattavaArvo) {
	    ohjaaja.forward();
	}
	ohjaaja.stop();
    }

    /**
     * Metodi moottorien vapauttamiseen ohjaajalta, jotta moottorit toimivat
     * viivanseuraamistilassa, joka ei käytä differential pilot:ia.
     */
    public void vapautaMoottorit() {
	Motor.A.suspendRegulation();
	Motor.B.suspendRegulation();
	Motor.C.suspendRegulation();
    }

    /**
     * Metodi jolla poistetaan takometriluvut moottoreista, jotta vältytään
     * kahden eri ohjaussysteemin ja kahden eri robotintilan aiheuttamilta
     * mahdollisilta häiriötekijöiltä.
     */
    public void resetoiMoottorit() {
	Motor.A.resetTachoCount();
	Motor.C.resetTachoCount();
    }
}
