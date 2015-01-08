package robotti;

/**
 * Luokka jossa lasketaan tarvittava virheen korjaus, eli robotin kääntö
 * derivaatan ja integraalin avulla. Kulmakertoimet syötetään satakertaisina,
 * jotta laskuista saadaan tarkempia kokonaislukuja käytettäessä.
 * 
 * @author Pauli Niva
 * @version 07012015
 */
public class PIDSaadin {

    private int suhteellisenKorjauksenKulmakerroin;
    private int integraalinKulmakerroin;
    private int derivaatanKulmakerroin;
    private int tehoSuoraanKulkiessa;
    private int integraali;
    private int derivaatta;
    private int virhe; // kuinka kaukana robotti on halutusta paikasta.
    private int viimeisinVirhe;

    public PIDSaadin() {
	this.suhteellisenKorjauksenKulmakerroin = 275; // satakertainen arvo.
	this.integraalinKulmakerroin = 7; // satakertainen arvo.
	this.derivaatanKulmakerroin = 500; // satakertainen arvo.
	this.tehoSuoraanKulkiessa = 65; // Teho jolla robotin olisi tarkoitus
					// kulkea, kun virhe on nolla.
	this.integraali = 0;
	this.derivaatta = 0;
	this.virhe = 0;
	this.viimeisinVirhe = 0;
    }

    /**
     * Metodi robotin virheellisen sijainnin korjaamiseen, eli metodi laskee
     * arvion seuraavasta tarvittavasta käännöksestä, jotta saadaan virhe
     * lähestymään nollaa.
     * 
     * @param valoarvo
     *            on valosensorin (viivasensorin) viimeksi lukema arvo.
     *            Käytetään virheen laskemiseen.
     * @param seurattavaArvo
     *            on kalibroinnista saatu viivan arvon ja taustan arvon
     *            mediaani. Robotti pyrkii seuraamaan tätä arvoa (viivan ja
     *            taustan rajakohtaa). Käytetään virheen laskemiseen.
     * @return käännös palauttaa arvon joka robotin pitää arviolta kääntyä
     *         päästäkseen viivan ja taustan rajakohtaan.
     */
    public int pidLaske(int valoarvo, int seurattavaArvo) {
	int kaannos;
	virhe = valoarvo - seurattavaArvo;
	integraali = integraali * 2 / 3 + virhe; // Koska aika valoarvon
						 // mittauksien välissä on
						 // suurinpiirtein vakio, niin
						 // ajanmuutosta ei tarvitse
						 // huomioida.
	derivaatta = virhe - viimeisinVirhe; // Koska aika valoarvon mittauksien
					     // välissä on suurinpiirtein vakio,
					     // niin ajanmuutosta ei tarvitse
					     // huomioida.
	kaannos = suhteellisenKorjauksenKulmakerroin * virhe
		+ integraalinKulmakerroin * integraali + derivaatanKulmakerroin
		* derivaatta;
	kaannos = kaannos / 100; // Poistetaan satakertaisuus.
	viimeisinVirhe = virhe;
	return kaannos;
    }

    public int haeTehoSuoraanKulkiessa() {
	return this.tehoSuoraanKulkiessa;
    }
}