/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import rs.ac.bg.fon.ai.zajednicki.domen.Enum.Pol;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.StarosnaDob;

/**
 * Predstavlja tip pacijenta koji je definisan starosnom dobi i polom.
 * 
 * Ova klasa implementira interfejs ApstraktniDomenskiObjekat, što omogućava komunikaciju sa bazom podataka.
 * 
 * @author Lazar Milosavljević
 */
public class TipPacijenta implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator tipa pacijenta.
     */
    private int idTipPacijenta;

    /**
     * Starosna dob pacijenta.
     */
    private StarosnaDob starosnaDob;

    /**
     * Pol pacijenta.
     */
    private Pol pol;

    /**
     * Podrazumevani konstruktor. Inicijalizuje tip pacijenta sa podrazumevanim vrednostima.
     */
    public TipPacijenta() {
    }

    /**
     * Parametrizovani konstruktor. Inicijalizuje tip pacijenta sa zadatim vrednostima.
     * 
     * @param idTipPacijenta Jedinstveni identifikator tipa pacijenta. Ne sme biti manji od 0.
     * @param starosnaDob Starosna dob pacijenta. Ne sme biti null.
     * @param pol Pol pacijenta. Ne sme biti null.
     * 
     * @throws java.lang.IllegalArgumentException ako je id manji od 0.
     * @throws java.lang.NullPointerException ako je starosna dob ili pol null.
     */
    public TipPacijenta(int idTipPacijenta, StarosnaDob starosnaDob, Pol pol) {
//        this.idTipPacijenta = idTipPacijenta;
//        this.starosnaDob = starosnaDob;
//        this.pol = pol;
    	setIdTipPacijenta(idTipPacijenta);
    	setPol(pol);
    	setStarosnaDob(starosnaDob);
    }

    /**
     * Vraća ID tipa pacijenta.
     * 
     * @return ID tipa pacijenta kao int.
     */
    public int getIdTipPacijenta() {
        return idTipPacijenta;
    }

    /**
     * Postavlja ID tipa pacijenta.
     * 
     * @param idTipPacijenta ID tipa pacijenta. Ne sme biti manji od 0.
     * 
     * @throws java.lang.IllegalArgumentException ako je id manji od 0.
     */
    public void setIdTipPacijenta(int idTipPacijenta) {
    	if(idTipPacijenta < 0) {
    		throw new IllegalArgumentException("id tipa pacijenta ne sme biti manji od 0");
    	}
        this.idTipPacijenta = idTipPacijenta;
    }

    /**
     * Vraća starosnu dob pacijenta.
     * 
     * @return Starosna dob kao StarosnaDob.
     */
    public StarosnaDob getStarosnaDob() {
        return starosnaDob;
    }

    /**
     * Postavlja starosnu dob pacijenta.
     * 
     * @param starosnaDob Starosna dob. Ne sme biti null.
     * 
     * @throws java.lang.NullPointerException ako je starosna dob null.
     */
    public void setStarosnaDob(StarosnaDob starosnaDob) {
    	if(starosnaDob == null) {
    		throw new NullPointerException("starosna dob pacijenta ne sme biti null");
    	}
        this.starosnaDob = starosnaDob;
    }

    /**
     * Vraća pol pacijenta.
     * 
     * @return Pol kao Pol.
     */
    public Pol getPol() {
        return pol;
    }

    /**
     * Postavlja pol pacijenta.
     * 
     * @param pol Pol pacijenta. Ne sme biti null.
     * 
     * @throws java.lang.NullPointerException ako je pol null.
     */
    public void setPol(Pol pol) {
    	if(pol == null) {
    		throw new NullPointerException("pol pacijenta ne sme biti null");
    	}
        this.pol = pol;
    }

    /**
     * Računa hashCode na osnovu starosne dobi i pola.
     * 
     * @return Hash code vrednost objekta.
     */
    @Override
    public int hashCode() {
    	return Objects.hash(starosnaDob, pol);
    }

    /**
     * Poredi dva objekta TipPacijenta na osnovu starosne dobi i pola.
     * 
     * @param obj Objekat za poređenje.
     * 
     * @return 
     * <ul>
     * <li><b>true</b> - ako objekti imaju istu starosnu dob i pol.</li>
     * <li><b>false</b> - u suprotnom.</li>
     * </ul>
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipPacijenta other = (TipPacijenta) obj;
        if (this.starosnaDob != other.starosnaDob) {
            return false;
        }
        return this.pol == other.pol;
    }

    /**
     * Vraća String reprezentaciju objekta TipPacijenta.
     * 
     * @return Reprezentacija u formatu: "TipPacijenta{starosnaDob=####, pol=####}"
     */
    @Override
    public String toString() {
        return "TipPacijenta{" + "starosnaDob=" + starosnaDob + ", pol=" + pol + '}';
    }
    
    
    
    @Override
    public String vratiNazivTabele() {
        return "tip_pacijenta";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next()){
            int idTipPacijenta=rs.getInt("tip_pacijenta.idTipPacijenta");
            StarosnaDob starosnaDob=StarosnaDob.valueOf(rs.getString("tip_pacijenta.starosnaDob"));
            Pol pol=Pol.valueOf(rs.getString("tip_pacijenta.pol"));
            TipPacijenta tp=new TipPacijenta(idTipPacijenta, starosnaDob, pol);
            lista.add(tp);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "starosnaDob,pol";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+starosnaDob+"','"+pol+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "tip_pacijenta.idTipPacijenta="+idTipPacijenta;
    }

//    @Override
//    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "starosnaDob= '"+starosnaDob+"',pol= '"+pol+"'";
    
    }
    
}
