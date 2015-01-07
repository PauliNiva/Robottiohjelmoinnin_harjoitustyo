package robotti;

import moottorit.OikeaMoottori;
import moottorit.VasenMoottori;

public class Ohjausyksikko {
    private VasenMoottori vasenMoottori;
    private OikeaMoottori oikeaMoottori;

    public Ohjausyksikko() {
	this.vasenMoottori = new VasenMoottori();
	this.oikeaMoottori = new OikeaMoottori();
    }

    public void liiku(int vasenTeho, int oikeaTeho, int Tp) {
	if (vasenTeho >= 0) {
	    vasenMoottori.asetaTeho(vasenTeho);
	    vasenMoottori.eteenpain();
	} else {
	    vasenMoottori.asetaTeho((vasenTeho * -1) + Tp);
	    vasenMoottori.taaksepain();
	}
	if (oikeaTeho >= 0) {
	    oikeaMoottori.asetaTeho(oikeaTeho);
	    oikeaMoottori.eteenpain();
	} else {
	    oikeaMoottori.asetaTeho((oikeaTeho * -1) + Tp);
	    oikeaMoottori.taaksepain();
	}
    }

    public void pysahdy() {
	vasenMoottori.pysahdy();
	oikeaMoottori.pysahdy();
    }
}
