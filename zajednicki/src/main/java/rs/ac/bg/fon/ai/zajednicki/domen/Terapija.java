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
 * Predstavlja terapiju koju fizijatar pripisuje pacijentu.
 * 
 * Sadrži informacije o ID-u terapije, nazivu, opisu i ceni.
 * 
 * Ova klasa implementira interfejs ApstraktniDomenskiObjekat, što omogućava komunikaciju sa bazom podataka.
 * 
 * @author Lazar Milosavljević
 */
public class Terapija implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator terapije.
     */
    private int idTerapija;

    /**
     * Naziv terapije.
     */
    private String naziv;

    /**
     * Opis terapije.
     */
    private String opis;

    /**
     * Cena terapije.
     */
    private double cena;

    /**
     * Podrazumevani konstruktor. Inicijalizuje terapiju sa podrazumevanim vrednostima.
     */
    public Terapija() {
    }

    /**
     * Parametrizovani konstruktor. Inicijalizuje terapiju sa zadatim vrednostima.
     * 
     * @param idTerapija Jedinstveni identifikator terapije. Ne sme biti manji od 0.
     * @param naziv Naziv terapije. Ne sme biti null niti prazan.
     * @param opis Opis terapije. Ne sme biti null niti prazan.
     * @param cena Cena terapije. Ne sme biti manja od 0.
     * 
     * @throws java.lang.IllegalArgumentException ako je id manji od 0, naziv ili opis prazan string, ili cena manja od 0.
     * @throws java.lang.NullPointerException ako je naziv ili opis null.
     */
    public Terapija(int idTerapija, String naziv, String opis, double cena) {
//        this.idTerapija = idTerapija;
//        this.naziv = naziv;
//        this.opis = opis;
//        this.cena = cena;

        setIdTerapija(idTerapija);
        setNaziv(naziv);
        setOpis(opis);
        setCena(cena);
    }

    /**
     * Vraća ID terapije.
     * 
     * @return ID terapije kao int.
     */
    public int getIdTerapija() {
        return idTerapija;
    }

    /**
     * Postavlja ID terapije.
     * 
     * @param idTerapija ID terapije. Ne sme biti manji od 0.
     * 
     * @throws java.lang.IllegalArgumentException ako je id manji od 0.
     */
    public void setIdTerapija(int idTerapija) {
        if(idTerapija < 0) {
            throw new IllegalArgumentException("id terapije ne sme biti manji od 0");
        }
        this.idTerapija = idTerapija;
    }

    /**
     * Vraća naziv terapije.
     * 
     * @return Naziv terapije kao String.
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv terapije.
     * 
     * @param naziv Naziv terapije. Ne sme biti null niti prazan.
     * 
     * @throws java.lang.NullPointerException ako je naziv null.
     * @throws java.lang.IllegalArgumentException ako je naziv prazan.
     */
    public void setNaziv(String naziv) {
        if(naziv == null) {
            throw new NullPointerException("naziv terapije ne sme biti null");
        }
        if(naziv.isEmpty()) {
            throw new IllegalArgumentException("naziv terapije ne sme biti prazan");
        }
        this.naziv = naziv;
    }

    /**
     * Vraća opis terapije.
     * 
     * @return Opis terapije kao String.
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Postavlja opis terapije.
     * 
     * @param opis Opis terapije. Ne sme biti null niti prazan.
     * 
     * @throws java.lang.NullPointerException ako je opis null.
     * @throws java.lang.IllegalArgumentException ako je opis prazan.
     */
    public void setOpis(String opis) {
        if(opis == null) {
            throw new NullPointerException("opis terapije ne sme biti null");
        }
        if(opis.isEmpty()) {
            throw new IllegalArgumentException("opis terapije ne sme biti prazan");
        }
        this.opis = opis;
    }

    /**
     * Vraća cenu terapije.
     * 
     * @return Cena terapije kao double.
     */
    public double getCena() {
        return cena;
    }

    /**
     * Postavlja cenu terapije.
     * 
     * @param cena Cena terapije. Ne sme biti manja od 0.
     * 
     * @throws java.lang.IllegalArgumentException ako je cena manja od 0.
     */
    public void setCena(double cena) {
        if(cena < 0) {
            throw new IllegalArgumentException("cena terapije ne sme biti manja od 0");
        }
        this.cena = cena;
    }

    /**
     * Računa hashCode na osnovu ID-a terapije.
     * 
     * @return Hash code vrednost objekta.
     */
    @Override
    public int hashCode() {
        return Objects.hash(idTerapija);
    }

    /**
     * Poredi dve terapije na osnovu ID-a.
     * 
     * @param obj Drugi objekat koji se poredi sa ovim.
     * 
     * @return 
     * <ul>
     * <li><b>true</b> - ako je objekat različit od null, instanca klase Terapija i ima isti ID.</li>
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
        Terapija other = (Terapija) obj;
        return idTerapija == other.idTerapija;
    }

    /**
     * Vraća naziv terapije kao njen String prikaz.
     * 
     * @return Naziv terapije kao String.
     */
    @Override
    public String toString() {
        return naziv;
    }
    
    @Override
    public String vratiNazivTabele() {
        return "terapija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next()){
            int idTerapija=rs.getInt("terapija.idTerapija");
            String naziv=rs.getString("terapija.naziv");
            String opis=rs.getString("terapija.opis");
            double cena=rs.getDouble("terapija.cena");
            Terapija t=new Terapija(idTerapija, naziv, opis, cena);
            lista.add(t);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,cena,opis";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+naziv+"',"+cena+",'"+opis+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "terapija.idTerapija="+idTerapija;
    }

//    @Override
//    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv= '"+naziv+"',cena= "+cena+",opis= '"+opis+"'";
    
    }
    
}
