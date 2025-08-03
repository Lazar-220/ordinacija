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
 *
 * @author milos
 */
public class Fizijatar implements ApstraktniDomenskiObjekat {

    
    private int idFizijatra;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;

    public Fizijatar() {
    }

    public Fizijatar(int idFizijatra, String ime, String prezime, String korisnickoIme, String sifra) {
        this.idFizijatra = idFizijatra;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }
    
    public int getIdFizijatra() {
        return idFizijatra;
    }

    public void setIdFizijatra(int idFizijatra) {
        this.idFizijatra = idFizijatra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

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
        final Fizijatar other = (Fizijatar) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    

    

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    
    @Override
    public String vratiNazivTabele() {
        return "fizijatar";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next()){
            int idFizijatra=rs.getInt("fizijatar.idFizijatra");
            String ime=rs.getString("fizijatar.ime");
            String prezime=rs.getString("fizijatar.prezime");
            String korisnickoIme=rs.getString("fizijatar.korisnickoIme");
            String sifra=rs.getString("fizijatar.sifra");
            Fizijatar f=new Fizijatar(idFizijatra, ime, prezime, korisnickoIme, sifra);
            lista.add(f);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,korisnickoIme,sifra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+korisnickoIme+"','"+sifra+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "fizijatar.idFizijatra="+idFizijatra;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime= '"+ime+"',prezime= '"+prezime+"',korisnickoIme= '"+korisnickoIme+"',sifra= '"+sifra+"'";
    }
    
}
