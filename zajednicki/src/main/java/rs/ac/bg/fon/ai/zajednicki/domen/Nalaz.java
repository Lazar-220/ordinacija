/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import rs.ac.bg.fon.ai.zajednicki.domen.Enum.Pol;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.StarosnaDob;

/**
 * Predstavlja nalaz koji se izdaje pacijentu nakon pregleda kod fizijatra.
 * 
 * Klasa implementira interfejs ApstraktniDomenskiObjekat i omogućava komunikaciju sa bazom podataka.
 * 
 * <p>Nalaz sadrži informacije o:</p>
 * <ul>
 *   <li>Jedinstvenom identifikatoru nalaza</li>
 *   <li>Datumu izdavanja</li>
 *   <li>Opisu nalaza</li>
 *   <li>Ukupnoj ceni</li>
 *   <li>Fizijatru koji je izdao nalaz</li>
 *   <li>Pacijentu na koga se nalaz odnosi</li>
 * </ul>
 * 
 * @author Lazar Milosavljević
 */
public class Nalaz implements ApstraktniDomenskiObjekat {

    /** Identifikator nalaza */
    private int idNalaz;
    /** Datum izdavanja nalaza */
    private Date datumIzdavanja;
    /** Opis nalaza */
    private String opisNalaza;
    /** Ukupna cena nalaza */
    private double ukupnaCena;
    /** Fizijatar koji je izdao nalaz */
    private Fizijatar fizijatar;
    /** Pacijent na koga se odnosi nalaz */
    private Pacijent pacijent;
    /** Lista stavki koje pripadaju nalazu */
    private List<StavkaNalaza> listaStavki;

    /**
     * Podrazumevani konstruktor. Inicijalizuje nalaz bez vrednosti.
     */
    public Nalaz() {
    }

    /**
     * Parametrizovani konstruktor koji inicijalizuje sve atribute nalaza.
     * 
     * @param idNalaz identifikator nalaza
     * @param datumIzdavanja datum izdavanja nalaza
     * @param opisNalaza opis nalaza
     * @param ukupnaCena ukupna cena nalaza
     * @param fizijatar fizijatar koji je izdao nalaz
     * @param pacijent pacijent na koga se odnosi nalaz
     * @param listaStavki lista stavki koje pripadaju nalazu
     */
    public Nalaz(int idNalaz, Date datumIzdavanja, String opisNalaza, double ukupnaCena, Fizijatar fizijatar, Pacijent pacijent, List<StavkaNalaza> listaStavki) {
//        setIdNalaz(idNalaz);
//        setDatumIzdavanja(datumIzdavanja);
//        setOpisNalaza(opisNalaza);
//        setUkupnaCena(ukupnaCena);
//        setFizijatar(fizijatar);
//        setPacijent(pacijent);
//        setListaStavki(listaStavki);
    	this.idNalaz = idNalaz;
		this.datumIzdavanja = datumIzdavanja;
		this.opisNalaza = opisNalaza;
		this.ukupnaCena = ukupnaCena;
		this.fizijatar = fizijatar;
		this.pacijent = pacijent;
		this.listaStavki = listaStavki;
    }
    

	/**
     * Vraca ID nalaza.
     * 
     * @return idNalaz
     */
    public int getIdNalaz() {
        return idNalaz;
    }

    /**
     * Postavlja ID nalaza.
     * 
     * @param idNalaz ID nalaza koji ne sme biti negativan
     * @throws java.lang.IllegalArgumentException ako je id manji od 0
     */
    public void setIdNalaz(int idNalaz) {
        if(idNalaz<0) {
            throw new IllegalArgumentException("id nalaza ne sme biti manji od 0");
        }
        this.idNalaz = idNalaz;
    }

    /**
     * Vraca datum izdavanja nalaza.
     * 
     * @return datumIzdavanja
     */
    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    /**
     * Postavlja datum izdavanja nalaza.
     * 
     * @param datumIzdavanja datum izdavanja koji ne sme biti null niti u buducnosti
     * @throws java.lang.NullPointerException ako je datum null
     * @throws java.lang.IllegalArgumentException ako je datum u buducnosti
     */
    public void setDatumIzdavanja(Date datumIzdavanja) {
        if(datumIzdavanja==null) {
            throw new NullPointerException("datum izdavanja nalaza ne sme biti null");
        }
        if(datumIzdavanja.getTime()>new Date().getTime()) {
            throw new IllegalArgumentException("datum izdavanja nalaza ne sme biti u buducnosti");
        }
        this.datumIzdavanja = datumIzdavanja;
    }

    /**
     * Vraca opis nalaza.
     * 
     * @return opisNalaza
     */
    public String getOpisNalaza() {
        return opisNalaza;
    }

    /**
     * Postavlja opis nalaza.
     * 
     * @param opisNalaza opis koji ne sme biti null ni prazan
     * @throws java.lang.NullPointerException ako je opis null
     * @throws java.lang.IllegalArgumentException ako je opis prazan
     */
    public void setOpisNalaza(String opisNalaza) {
        if(opisNalaza==null) {
            throw new NullPointerException("opis nalaza ne sme biti null");
        }
        if(opisNalaza.isEmpty()) {
            throw new IllegalArgumentException("opis nalaza ne sme biti prazan");
        }
        this.opisNalaza = opisNalaza;
    }

    /**
     * Vraca ukupnu cenu nalaza.
     * 
     * @return ukupnaCena
     */
    public double getUkupnaCena() {
        return ukupnaCena;
    }

    /**
     * Postavlja ukupnu cenu nalaza.
     * 
     * @param ukupnaCena cena koja ne sme biti manja od 0
     * @throws java.lang.IllegalArgumentException ako je cena manja od 0
     */
    public void setUkupnaCena(double ukupnaCena) {
        if(ukupnaCena<0) {
            throw new IllegalArgumentException("ukupna cena nalaza ne sme biti manja od 0");
        }
        this.ukupnaCena = ukupnaCena;
    }

    /**
     * Vraca fizijatra koji je izdao nalaz.
     * 
     * @return fizijatar
     */
    public Fizijatar getFizijatar() {
        return fizijatar;
    }

    /**
     * Postavlja fizijatra.
     * 
     * @param fizijatar koji ne sme biti null
     * @throws java.lang.NullPointerException ako je fizijatar null
     */
    public void setFizijatar(Fizijatar fizijatar) {
        if(fizijatar==null) {
            throw new NullPointerException("fizijatar ne sme biti null");
        }
        this.fizijatar = fizijatar;
    }

    /**
     * Vraca pacijenta na koga se odnosi nalaz.
     * 
     * @return pacijent
     */
    public Pacijent getPacijent() {
        return pacijent;
    }

    /**
     * Postavlja pacijenta.
     * 
     * @param pacijent koji ne sme biti null
     * @throws java.lang.NullPointerException ako je pacijent null
     */
    public void setPacijent(Pacijent pacijent) {
        if(pacijent==null) {
            throw new NullPointerException("pacijent ne sme biti null");
        }
        this.pacijent = pacijent;
    }

    /**
     * Vraca listu stavki nalaza.
     * 
     * @return listaStavki
     */
    public List<StavkaNalaza> getListaStavki() {
        return listaStavki;
    }

    /**
     * Postavlja listu stavki nalaza.
     * 
     * @param listaStavki lista koja ne sme biti null niti prazna
     * @throws java.lang.NullPointerException ako je lista null
     * @throws java.lang.IllegalArgumentException ako je lista prazna
     */
    public void setListaStavki(List<StavkaNalaza> listaStavki) {
        if(listaStavki==null) {
            throw new NullPointerException("lista stavki ne sme biti null");
        }
        if(listaStavki.isEmpty()) {
            throw new IllegalArgumentException("lista stavki nalaza ne sme biti prazna");
        }
        this.listaStavki = listaStavki;
    }

    

   
    /**
     * Računa hash kod na osnovu ID-a nalaza.
     * 
     * @return Hash kod kao int.
     */
    @Override
	public int hashCode() {
		return Objects.hash(idNalaz);
	}

    /**
     * Poredi ovaj nalaz sa drugim objektom na osnovu ID-ja.
     *
     * @param obj Objekat za poređenje
     * @return true ako su oba objekta tipa Nalaz i imaju isti ID, inače false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Nalaz)) return false;
        Nalaz other = (Nalaz) obj;
        return idNalaz == other.idNalaz;
    }

    /**
     * Vraća string reprezentaciju nalaza.
     *
     * @return Opis i ukupna cena nalaza u formatu: Nalaz{opisNalaza=####, ukupnaCena=####}
     */
    @Override
    public String toString() {
        return "Nalaz{" + "opisNalaza=" + opisNalaza + ", ukupnaCena=" + ukupnaCena + '}';
    }
    
    
    
    @Override
    public String vratiNazivTabele() {
        return "nalaz";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat>lista=new ArrayList<>();
        while(rs.next()){
            int id=rs.getInt("nalaz.idNalaz");
            Date datum=new Date(rs.getDate("nalaz.datumIzdavanja").getTime());
            String opis=rs.getString("nalaz.opisNalaza");
            double ukupnaCena=rs.getDouble("nalaz.ukupnaCena");
            Fizijatar f=new Fizijatar(rs.getInt("fizijatar.idFizijatra"), rs.getString("fizijatar.ime"), rs.getString("fizijatar.prezime"), rs.getString("fizijatar.korisnickoIme"), rs.getString("fizijatar.sifra"));
            //TipPacijenta tipPacijenta=new TipPacijenta(rs.getInt("tip_pacijenta.idTipPacijenta"), StarosnaDob.valueOf(rs.getString("tip_pacijenta.starosnaDob")), Pol.valueOf(rs.getString("tip_pacijenta.pol")));
            Pacijent p=new Pacijent(rs.getInt("pacijent.idPacijent"), rs.getString("pacijent.ime"), rs.getString("pacijent.prezime"), rs.getString("pacijent.email"), new TipPacijenta());
            Nalaz n=new Nalaz(id, datum, opis, ukupnaCena, f, p, Arrays.asList(new StavkaNalaza()));
            lista.add(n);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumIzdavanja,opisNalaza,ukupnaCena,fizijatar_id,pacijent_id";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        java.sql.Date datumSql=new java.sql.Date(datumIzdavanja.getTime());
        return "'"+datumSql+"','"+opisNalaza+"',"+ukupnaCena+","+fizijatar.getIdFizijatra()+","+pacijent.getIdPacijent();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "nalaz.idNalaz="+idNalaz;
    }

//    @Override
//    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        java.sql.Date datumSql=new java.sql.Date(datumIzdavanja.getTime());
        return "datumIzdavanja= '"+datumSql+"',opisNalaza= '"+opisNalaza+"',ukupnaCena= "+ukupnaCena+",fizijatar_id= "+fizijatar.getIdFizijatra()+",pacijent_id="+pacijent.getIdPacijent();
    
    }
    
}
