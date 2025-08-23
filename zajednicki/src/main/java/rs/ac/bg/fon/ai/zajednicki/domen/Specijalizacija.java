/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja specijalizaciju koju je završio fizijatar. Specijalizacija ima id, naziv i instituciju.
 * 
 * Ova klasa implementira interfejs ApstraktniDomenskiObjekat, što omogućava komunikaciju sa bazom podataka.
 * 
 * @author Lazar Milosavljević
 */
public class Specijalizacija implements ApstraktniDomenskiObjekat {

    /**
     * Id specijalizacije kao int.
     */
    private int idSpecijalizacija;

    /**
     * Naziv specijalizacije kao String.
     */
    private String naziv;

    /**
     * Institucija na kojoj je specijalizacija obavljena, kao String.
     */
    private String institucija;

    /**
     * Podrazumevani konstruktor. Inicijalizuje specijalizaciju sa default vrednostima.
     */
    public Specijalizacija() {
    }

    /**
     * Parametrizovani konstruktor. Inicijalizuje specijalizaciju sa zadatim vrednostima.
     * 
     * @param idSpecijalizacija Id specijalizacije. Ne sme biti manji od 0.
     * @param naziv Naziv specijalizacije. Ne sme biti null niti prazan string.
     * @param institucija Institucija gde je obavljena specijalizacija. Ne sme biti null niti prazan string.
     */
    public Specijalizacija(int idSpecijalizacija, String naziv, String institucija) {
      this.idSpecijalizacija = idSpecijalizacija;
      this.naziv = naziv;
      this.institucija = institucija;
//    	setIdSpecijalizacija(idSpecijalizacija);
//        setNaziv(naziv);
//        setInstitucija(institucija);
    }

    /**
     * Vraća id specijalizacije.
     * 
     * @return Id specijalizacije kao int.
     */
    public int getIdSpecijalizacija() {
        return idSpecijalizacija;
    }

    /**
     * Postavlja novi id specijalizacije.
     * 
     * @param idSpecijalizacija Id specijalizacije kao int. Ne sme biti manji od 0.
     * 
     * @throws java.lang.IllegalArgumentException ako je uneti id manji od 0.
     */
    public void setIdSpecijalizacija(int idSpecijalizacija) {
        if (idSpecijalizacija < 0) {
            throw new IllegalArgumentException("id specijalizacije ne sme biti manji od 0");
        }
        this.idSpecijalizacija = idSpecijalizacija;
    }

    /**
     * Vraća naziv specijalizacije.
     * 
     * @return Naziv specijalizacije kao String.
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv specijalizacije.
     * 
     * @param naziv Naziv specijalizacije. Ne sme biti null niti prazan string.
     * 
     * @throws java.lang.NullPointerException ako je naziv null.
     * @throws java.lang.IllegalArgumentException ako je naziv prazan string.
     */
    public void setNaziv(String naziv) {
        if (naziv == null) {
            throw new NullPointerException("naziv specijalizacije ne sme biti null");
        }
        if (naziv.isEmpty()) {
            throw new IllegalArgumentException("naziv specijalizacije ne sme biti prazan");
        }
        this.naziv = naziv;
    }

    /**
     * Vraća instituciju gde je obavljena specijalizacija.
     * 
     * @return Institucija kao String.
     */
    public String getInstitucija() {
        return institucija;
    }

    /**
     * Postavlja instituciju gde je obavljena specijalizacija.
     * 
     * @param institucija Institucija kao String. Ne sme biti null niti prazan string.
     * 
     * @throws java.lang.NullPointerException ako je institucija null.
     * @throws java.lang.IllegalArgumentException ako je institucija prazan string.
     */
    public void setInstitucija(String institucija) {
        if (institucija == null) {
            throw new NullPointerException("institucija gde je obavljena specijalizacija ne sme biti null");
        }
        if (institucija.isEmpty()) {
            throw new IllegalArgumentException("institucija gde je obavljena specijalizacija ne sme biti prazna");
        }
        this.institucija = institucija;
    }

    /**
     * Računa hashCode na osnovu naziva i institucije.
     * 
     * @return Hash code vrednost objekta.
     */
    @Override
    public int hashCode() {
        return Objects.hash(institucija, naziv);
    }

    /**
     * Poredi dve specijalizacije na osnovu naziva i institucije.
     * 
     * @param obj Druga specijalizacija sa kojom se poredi.
     * 
     * @return 
     * <ul>
     * 
     * <li><b>true</b> - ako je uneti objekat različit od null, ako je objekat tipa Specijalizacija
     * i ako su mu naziv i institucija isti kao kod prve specijalizacije.</li>
     * 
     * <li><b>false</b> - u svim ostalim slučajevima.</li>
     * </ul>
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Specijalizacija other = (Specijalizacija) obj;
        return Objects.equals(institucija, other.institucija) && Objects.equals(naziv, other.naziv);
    }

    /**
     * Vraća naziv specijalizacije kao String.
     * 
     * @return Naziv specijalizacije.
     */
    @Override
    public String toString() {
        return naziv;
    }
    
    
    @Override
    public String vratiNazivTabele() {
        return "specijalizacija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next()){
            int idSpecijalizacija=rs.getInt("specijalizacija.idSpecijalizacija");
            String naziv=rs.getString("specijalizacija.naziv");
            String institucija=rs.getString("specijalizacija.institucija");
            Specijalizacija s=new Specijalizacija(idSpecijalizacija, naziv, institucija);
            lista.add(s);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,institucija";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+naziv+"','"+institucija+"'";
    
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "specijalizacija.idSpecijalizacija="+idSpecijalizacija;
    }

//    @Override
//    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv= '"+naziv+"',institucija= '"+institucija+"'";
    
    }
    
}
