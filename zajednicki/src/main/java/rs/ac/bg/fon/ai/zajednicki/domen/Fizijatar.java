/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Predstavlja fizijatra koji ima id, ime, prezime, korisničko ime i šifru.
 * 
 * Ova klasa implementira interfejs ApstraktniDomenskiObjekat, što omogućava komunikaciju sa bazom podataka.
 * 
 * @author Lazar Milosavljević
 */
public class Fizijatar implements ApstraktniDomenskiObjekat {

	/**
	 * Id fizijatra kao int.
	 */
    private int idFizijatra;
    
    /**
     * Ime fizijatra kao String.
     */
    @Expose
    private String ime;
    
    /**
     * Prezime fizijatra kao String.
     */
    @Expose
    private String prezime;
    
    /**
     * Korisničko ime fizijatra kao String.
     */
    @Expose
    @SerializedName("korisničko ime")
    private String korisnickoIme;
    
    /**
     * Šifra fizijatra kao String.
     */
    private String sifra;

    /**
     * Inicijalizuje objekat sa atributima koji imaju default vrednosti.
     */
    public Fizijatar() {
    }

    /**
     * Inicijalizuje objekat sa unetim id-em, imenom, prezimenom, korisničkim imenom i šifrom.
     * 
     * @param idFizijatra Id fizijatra koji se unosi u objekat. Id ne sme biti manji od 0.
     * @param ime Ime fizijatra koje se unosi u objekat. Ime ne sme biti null niti prazno.
     * @param prezime Prezime fizijatra koje se unosi u objekat. Prezime ne sme biti null niti prazno.
     * @param korisnickoIme Korisničko ime fizijatra koje se unosi u objekat. Korisničko ime ne sme biti null niti prazno.
     * @param sifra Šifra fizijatra koja se unosi u objekat. Šifra ne sme biti null niti prazna.
     */
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
    
    /**
     * Vraća id fizijatra.
     * 
     * @return Id fizijatra kao int.
     */
    public int getIdFizijatra() {
        return idFizijatra;
    }

    /**
     * Postavlja novi id fizijatra.
     * 
     * Uneti id ne sme biti manji od 0.
     * 
     * @param idFizijatra Id fizijatra kao int.
     * 
     * @throws java.lang.IllegalArgumentException ako je uneti id manji od 0.
     */
    public void setIdFizijatra(int idFizijatra) {
    	if(idFizijatra<0) {
    		throw new IllegalArgumentException("id fizijatra ne sme biti manji od 0");
    	}
        this.idFizijatra = idFizijatra;
    }

    /**
     * Vraća ime fizijatra.
     * 
     * @return Ime fizijatra kao String.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja novo ime fizijatra.
     * 
     * Uneto ime ne sme biti null niti prazno.
     * 
     * @param ime Ime fizijatra kao String.
     * 
     * @throws java.lang.NullPointerException ako je uneto ime null.
     * @throws java.lang.IllegalArgumentException ako je uneto ime prazan String.
     */
    public void setIme(String ime) {
    	if(ime==null) {
    		throw new NullPointerException("ime fizijatra ne sme biti null");
    	}
    	if(ime.isEmpty()) {
    		throw new IllegalArgumentException("ime fizijatra ne sme biti prazno");
    	}
        this.ime = ime;
    }

    /**
     * Vraća prezime fizijatra.
     * 
     * @return Prezime fizijatra kao String.
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja novo prezime fizijatra.
     * 
     * Uneto prezime ne sme biti null niti prazno.
     * 
     * @param prezime Prezime fizijatra kao String.
     * 
     * @throws java.lang.NullPointerException ako je uneto prezime null.
     * @throws java.lang.IllegalArgumentException ako je uneto prezime prazan String.
     */
    public void setPrezime(String prezime) {
    	if(prezime==null) {
    		throw new NullPointerException("prezime fizijatra ne sme biti null");
    	}
    	if(prezime.isEmpty()) {
    		throw new IllegalArgumentException("prezime fizijatra ne sme biti prazno");
    	}
        this.prezime = prezime;
    }

    /**
     * Vraća korisničko ime fizijatra.
     * 
     * @return Korisničko ime fizijatra kao String.
     */
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * Postavlja novo korisničko ime fizijatra.
     * 
     * Uneto korisničko ime ne sme biti null niti prazno.
     * 
     * @param korisnickoIme Korisničko ime fizijatra kao String.
     * 
     * @throws java.lang.NullPointerException ako je uneto korisničko ime null.
     * @throws java.lang.IllegalArgumentException ako je uneto korisničko ime prazan String.
     */
    public void setKorisnickoIme(String korisnickoIme) {
    	if(korisnickoIme==null) {
    		throw new NullPointerException("korisnicko ime fizijatra ne sme biti null");
    	}
    	if(korisnickoIme.isEmpty()) {
    		throw new IllegalArgumentException("korisnicko ime fizijatra ne sme biti prazno");
    	}
        this.korisnickoIme = korisnickoIme;
    }

    /**
     * Vraća šifru fizijatra.
     * 
     * @return Šifra fizijatra kao String.
     */
    public String getSifra() {
        return sifra;
    }

    /**
     * Postavlja novu šifru fizijatra.
     * 
     * Uneta šifra ne sme biti null niti prazna.
     * 
     * @param sifra Šifra fizijatra kao String.
     * 
     * @throws java.lang.NullPointerException ako je uneta šifra null.
     * @throws java.lang.IllegalArgumentException ako je uneta šifra prazan String.
     */
    public void setSifra(String sifra) {
    	if(sifra==null) {
    		throw new NullPointerException("sifra fizijatra ne sme biti null");
    	}
    	if(sifra.isEmpty()) {
    		throw new IllegalArgumentException("sifra fizijatra ne sme biti prazna");
    	}
        this.sifra = sifra;
    }
    
    /**
     * Vraća hash code zasnovan na korisničkom imenu i šifri fizijatra.
     * 
     * @return Hash code zasnovan na korisničkom imenu i šifri fizijatra.
     */
    @Override
	public int hashCode() {
		return Objects.hash(korisnickoIme, sifra);
	}

    /**
     * Poredi dva fizijatra prema korisničkom imenu i šifri.
     * 
     * @param obj Drugi fizijatar sa kojim se poredi.
     * 
     * @return
     * <ul>
     * 
     * <li><b>true</b> - ako je uneti objekat različit od null, ako je objekat tipa Fizijatar 
     * i ako su mu korisničko ime i šifra isti kao kod prvog fizijatra.</li>
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
		Fizijatar other = (Fizijatar) obj;
		return Objects.equals(korisnickoIme, other.korisnickoIme) && Objects.equals(sifra, other.sifra);
	}

    

    
	/**
	 * Vraća String sa imenom i prezimenom fizijatra.
	 * 
	 * @return String sa imenom i prezimenom fizijatra u formatu:
	 * "#### ####", npr. "Pera Perić"
	 */
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