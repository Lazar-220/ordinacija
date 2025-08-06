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
 * Predstavlja stavku nalaza koja sadrži redni broj, cenu, terapiju i pripadajući nalaz.
 * 
 * Klasa implementira interfejs ApstraktniDomenskiObjekat i omogućava komunikaciju sa bazom podataka.
 * 
 * @author Lazar Milosavljević
 */
public class StavkaNalaza implements ApstraktniDomenskiObjekat {

    /**
     * Redni broj stavke u okviru nalaza.
     */
    private int rb;

    /**
     * Cena stavke (terapije).
     */
    private double cena;

    /**
     * Terapija koja je deo ove stavke.
     */
    private Terapija terapija;

    /**
     * Nalaz kojem pripada ova stavka.
     */
    private Nalaz nalaz;

    /**
     * Podrazumevani konstruktor. Inicijalizuje stavku nalaza bez vrednosti.
     */
    public StavkaNalaza() {
    }

    /**
     * Parametrizovani konstruktor koji inicijalizuje sve atribute stavke.
     * 
     * @param rb Redni broj stavke. Mora biti veći od 0.
     * @param cena Cena terapije. Ne sme biti manja od 0.
     * @param terapija Terapija koja se primenjuje. Ne sme biti null.
     * @param nalaz Nalaz kojem stavka pripada. Ne sme biti null.
     * 
     * @throws java.lang.IllegalArgumentException ako je cena manja od 0 ili rb manji od 1.
     * @throws java.lang.NullPointerException ako je terapija ili nalaz null.
     */
    public StavkaNalaza(int rb, double cena, Terapija terapija, Nalaz nalaz) {
//        this.rb = rb;
//        this.cena = cena;
//        this.terapija = terapija;
//        this.nalaz = nalaz;
        setRb(rb);
        setCena(cena);
        setTerapija(terapija);
        setNalaz(nalaz);
    }

    /**
     * Konstruktor bez nalaza. Koristi se kada još nije poznat nalaz.
     * 
     * @param rb Redni broj stavke.
     * @param cena Cena terapije.
     * @param terapija Terapija u okviru stavke.
     * 
     * @throws java.lang.IllegalArgumentException ako je cena manja od 0 ili rb manji od 1.
     * @throws java.lang.NullPointerException ako je terapija null.
     */
    public StavkaNalaza(int rb, double cena, Terapija terapija) {
//        this.rb = rb;
//        this.cena = cena;
//        this.terapija = terapija;
        setRb(rb);
        setCena(cena);
        setTerapija(terapija);
    }

    /**
     * Vraća nalaz kojem pripada stavka.
     * 
     * @return Nalaz kao objekat klase Nalaz.
     */
    public Nalaz getNalaz() {
        return nalaz;
    }

    /**
     * Postavlja nalaz kojem stavka pripada.
     * 
     * @param nalaz Nalaz. Ne sme biti null.
     * 
     * @throws java.lang.NullPointerException ako je nalaz null.
     */
    public void setNalaz(Nalaz nalaz) {
        if (nalaz == null) {
            throw new NullPointerException("nalaz ne sme biti null");
        }
        this.nalaz = nalaz;
    }

    /**
     * Vraća redni broj stavke.
     * 
     * @return Redni broj kao int.
     */
    public int getRb() {
        return rb;
    }

    /**
     * Postavlja redni broj stavke.
     * 
     * @param rb Redni broj. Mora biti veći od 0.
     * 
     * @throws java.lang.IllegalArgumentException ako je rb manji od 1.
     */
    public void setRb(int rb) {
        if (rb < 1) {
            throw new IllegalArgumentException("rb stavke nalaza ne sme biti manji od 1");
        }
        this.rb = rb;
    }

    /**
     * Vraća cenu terapije.
     * 
     * @return Cena kao double.
     */
    public double getCena() {
        return cena;
    }

    /**
     * Postavlja cenu terapije.
     * 
     * @param cena Cena. Ne sme biti manja od 0.
     * 
     * @throws java.lang.IllegalArgumentException ako je cena manja od 0.
     */
    public void setCena(double cena) {
        if (cena < 0) {
            throw new IllegalArgumentException("cena ne sme biti manja od 0");
        }
        this.cena = cena;
    }

    /**
     * Vraća terapiju iz stavke.
     * 
     * @return Terapija kao objekat klase Terapija.
     */
    public Terapija getTerapija() {
        return terapija;
    }

    /**
     * Postavlja terapiju.
     * 
     * @param terapija Terapija. Ne sme biti null.
     * 
     * @throws java.lang.NullPointerException ako je terapija null.
     */
    public void setTerapija(Terapija terapija) {
        if (terapija == null) {
            throw new NullPointerException("terapija ne sme biti null");
        }
        this.terapija = terapija;
    }

    /**
     * Računa hash kod na osnovu nalaza, rednog broja i terapije.
     * 
     * @return Hash kod kao int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nalaz, rb, terapija);
    }

    /**
     * Poredi dve stavke nalaza po nalazu, rednom broju i terapiji.
     * 
     * @param obj Objekat sa kojim se poredi.
     * 
     * @return true ako su nalaz, rb i terapija isti, u suprotnom false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StavkaNalaza other = (StavkaNalaza) obj;
        return Objects.equals(nalaz, other.nalaz) && rb == other.rb && Objects.equals(terapija, other.terapija);
    }

    /**
     * Vraća string reprezentaciju stavke.
     * 
     * @return String u formatu: StavkaNalaza{rb=####, cena=####, terapija=####}
     */
    @Override
    public String toString() {
        return "StavkaNalaza{" + "rb=" + rb + ", cena=" + cena + ", terapija=" + terapija + '}';
    }

    
    
    @Override
    public String vratiNazivTabele() {
        return "stavka_nalaza";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
    	StavkaNalaza stavka=new StavkaNalaza();
        List<ApstraktniDomenskiObjekat>lista=new ArrayList<>();
        while(rs.next()){
            int rb=rs.getInt("stavka_nalaza.rb");
            double cena=rs.getDouble("stavka_nalaza.cena");
            
            int id=rs.getInt("nalaz.idNalaz");
            Date datum=new Date(rs.getDate("nalaz.datumIzdavanja").getTime());
            String opis=rs.getString("nalaz.opisNalaza");
            double ukupnaCena=rs.getDouble("nalaz.ukupnaCena");
            
            Nalaz n=new Nalaz(id, datum, opis, ukupnaCena, new Fizijatar(), new Pacijent(), Arrays.asList(stavka));
            
            int idTerapija=rs.getInt("terapija.idTerapija");
            String naziv=rs.getString("terapija.naziv");
            String opisTerapije=rs.getString("terapija.opis");
            double cenaTerapije=rs.getDouble("terapija.cena");
            Terapija t=new Terapija(idTerapija, naziv, opisTerapije, cenaTerapije);
            
            stavka=new StavkaNalaza(rb, cena, t, n);
            lista.add(stavka);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "rb,nalaz_id,cena,terapija_id";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return rb+","+nalaz.getIdNalaz()+","+cena+","+terapija.getIdTerapija();
    
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavka_nalaza.rb="+rb+" AND "+"stavka_nalaza.nalaz_id="+nalaz.getIdNalaz();
      
    }

//    @Override
//    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "rb= "+rb+",nalaz_id= "+nalaz.getIdNalaz()+",cena= "+cena+",terapija_id= "+terapija.getIdTerapija();
    
    }
    
}
