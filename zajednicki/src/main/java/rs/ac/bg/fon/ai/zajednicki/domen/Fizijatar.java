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
public class Fizijatar implements ApstraktniDomenskiObjekat {//POCINJEMO DOKUMENTOVANJE

    
    private int idFizijatra;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;

    public Fizijatar() {
    }

    public Fizijatar(int idFizijatra, String ime, String prezime, String korisnickoIme, String sifra) {
//        this.idFizijatra = idFizijatra;
//        this.ime = ime;
//        this.prezime = prezime;
//        this.korisnickoIme = korisnickoIme;
//        this.sifra = sifra;
    	setIdFizijatra(idFizijatra);
    	setIme(ime);
    	setPrezime(prezime);
    	setKorisnickoIme(korisnickoIme);
    	setSifra(sifra);
    }
    
    public int getIdFizijatra() {
        return idFizijatra;
    }

    public void setIdFizijatra(int idFizijatra) {
    	if(idFizijatra<0) {
    		throw new IllegalArgumentException("id fizijatra ne sme biti manji od 0");
    	}
        this.idFizijatra = idFizijatra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
    	if(ime==null) {
    		throw new NullPointerException("ime fizijatra ne sme biti null");
    	}
    	if(ime.isEmpty()) {
    		throw new IllegalArgumentException("ime fizijatra ne sme biti prazno");
    	}
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
    	if(prezime==null) {
    		throw new NullPointerException("prezime fizijatra ne sme biti null");
    	}
    	if(prezime.isEmpty()) {
    		throw new IllegalArgumentException("prezime fizijatra ne sme biti prazno");
    	}
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
    	if(korisnickoIme==null) {
    		throw new NullPointerException("korisnicko ime fizijatra ne sme biti null");
    	}
    	if(korisnickoIme.isEmpty()) {
    		throw new IllegalArgumentException("korisnicko ime fizijatra ne sme biti prazno");
    	}
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
    	if(sifra==null) {
    		throw new NullPointerException("sifra fizijatra ne sme biti null");
    	}
    	if(sifra.isEmpty()) {
    		throw new IllegalArgumentException("sifra fizijatra ne sme biti prazna");
    	}
        this.sifra = sifra;
    }
    

    @Override
	public int hashCode() {
		return Objects.hash(korisnickoIme, sifra);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fizijatar other = (Fizijatar) obj;
		return Objects.equals(korisnickoIme, other.korisnickoIme) && Objects.equals(sifra, other.sifra);
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

//    @Override
//    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime= '"+ime+"',prezime= '"+prezime+"',korisnickoIme= '"+korisnickoIme+"',sifra= '"+sifra+"'";
    }
    
}
