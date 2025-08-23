/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja sertifikat koji povezuje klase Fizijatar i Specijalizacija i ima id i datum izdavanja takođe.
 * Sadrži informacije o ID-u sertifikata, fizijatru, specijalizaciji i datumu izdavanja sertifikata.
 * 
 * Ova klasa implementira interfejs ApstraktniDomenskiObjekat, što omogućava komunikaciju sa bazom podataka.
 * 
 * @author Lazar Milosavljević
 */
public class Fizijatar_specijalista implements ApstraktniDomenskiObjekat {

    
	/**
     * Jedinstveni identifikator sertifikata.
     */
    private int idSertifikat;

    /**
     * Fizijatar koji je završio specijalizaciju.
     */
    private Fizijatar fizijatar;

    /**
     * Specijalizacija koju je fizijatar završio.
     */
    private Specijalizacija specijalizacija;

    /**
     * Datum kada je sertifikat izdat.
     */
    private Date datumIzdavanja;
    
    /**
     * Podrazumevani konstruktor. Inicijalizuje sertifikat sa default vrednostima.
     */
    public Fizijatar_specijalista() {
    }

    /**
     * Parametrizovani konstruktor. Inicijalizuje sertifikat sa zadatim vrednostima.
     * 
     * @param idSertifikat Jedinstveni identifikator sertifikata. Ne sme biti manji od 0.
     * @param fizijatar Fizijatar koji je završio specijalizaciju. Ne sme biti null.
     * @param specijalizacija Specijalizacija koju je fizijatar završio. Ne sme biti null.
     * @param datumIzdavanja Datum kada je sertifikat izdat. Ne sme biti null niti u budućnosti.
     * 
     */
    public Fizijatar_specijalista(int idSertifikat, Fizijatar fizijatar, Specijalizacija specijalizacija, Date datumIzdavanja) {
        this.idSertifikat = idSertifikat;
        this.fizijatar = fizijatar;
        this.specijalizacija = specijalizacija;
        this.datumIzdavanja = datumIzdavanja;
//    	setIdSertifikat(idSertifikat);
//    	setFizijatar(fizijatar);
//    	setSpecijalizacija(specijalizacija);
//    	setDatumIzdavanja(datumIzdavanja);
    }

    
    /**
     * Vraća ID sertifikata.
     * 
     * @return ID sertifikata kao int.
     */
    public int getIdSertifikat() {
        return idSertifikat;
    }

    /**
     * Postavlja ID sertifikata.
     * 
     * @param idSertifikat ID sertifikata. Ne sme biti manji od 0.
     * 
     * @throws java.lang.IllegalArgumentException ako je id manji od 0.
     */
    public void setIdSertifikat(int idSertifikat) {
        if(idSertifikat < 0) {
            throw new IllegalArgumentException("id sertifikata ne sme biti manji od 0");
        }
        this.idSertifikat = idSertifikat;
    }
    
    /**
     * Vraća fizijatra koji je završio specijalizaciju.
     * 
     * @return fizijatrar kao Fizijatar.
     */
    public Fizijatar getFizijatar() {
        return fizijatar;
    }

    /**
     * Postavlja fizijatra.
     * 
     * @param fizijatar Fizijatar. Ne sme biti null.
     * 
     * @throws java.lang.NullPointerException ako je fizijatar null.
     */
    public void setFizijatar(Fizijatar fizijatar) {
        if(fizijatar == null) {
            throw new NullPointerException("fizijatar ne sme biti null");
        }
        this.fizijatar = fizijatar;
    }

    /**
     * Vraća specijalizaciju koju je fizijatar završio.
     * 
     * @return specijalizacija kao Specijalizacija.
     */
    public Specijalizacija getSpecijalizacija() {
        return specijalizacija;
    }

    /**
     * Postavlja specijalizaciju.
     * 
     * @param specijalizacija Specijalizacija. Ne sme biti null.
     * 
     * @throws java.lang.NullPointerException ako je specijalizacija null.
     */
    public void setSpecijalizacija(Specijalizacija specijalizacija) {
        if(specijalizacija == null) {
            throw new NullPointerException("specijalizacija ne sme biti null");
        }
        this.specijalizacija = specijalizacija;
    }

    /**
     * Vraća datum izdavanja sertifikata.
     * 
     * @return Datum izdavanja kao Date.
     */
    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    /**
     * Postavlja datum izdavanja sertifikata.
     * 
     * @param datumIzdavanja Datum izdavanja. Ne sme biti null niti u budućnosti.
     * 
     * @throws java.lang.NullPointerException ako je datum null.
     * @throws java.lang.IllegalArgumentException ako je datum u budućnosti.
     */
    public void setDatumIzdavanja(Date datumIzdavanja) {
        if(datumIzdavanja == null) {
            throw new NullPointerException("datum izdavanja sertifikata ne sme biti null");
        }
        if(datumIzdavanja.getTime() > new Date().getTime()) {
            throw new IllegalArgumentException("datum izdavanja sertifikata ne sme biti u buducnosti");
        }
        this.datumIzdavanja = datumIzdavanja;
    }

    

    

    
    /**
     * Računa hashCode na osnovu fizijatra i specijalizacije.
     * 
     * @return Hash code vrednost objekta.
     */
    @Override
	public int hashCode() {
		return Objects.hash(fizijatar, specijalizacija);
	}

    /**
     * Poredi dva sertifikata na osnovu fizijatra i specijalizacije.
     * 
     * @param obj Drugi objekat koji se poredi sa ovim.
     * 
     * @return 
     * <ul>
     * <li><b>true</b> - ako je uneti objekat različit od null, ako je objekat tipa Fizijatar_specijalista
     * i ako su mu fizijatar i specijalizacija isti kao kod prvog objekta.</li>
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
		Fizijatar_specijalista other = (Fizijatar_specijalista) obj;
		return Objects.equals(fizijatar, other.fizijatar) && Objects.equals(specijalizacija, other.specijalizacija);
	}

	/**
     * Vraća sertifikat kao String.
     * 
     * @return Sertifikat u formatu:
	 * "Fizijatar_specijalista{fizijatar=####, specijalizacija=####}", npr. "Fizijatar_specijalista{fizijatar=Pera Peric, specijalizacija=kineziterapija}"
     */
	@Override
    public String toString() {
        return "Fizijatar_specijalista{" + "fizijatar=" + fizijatar + ", specijalizacija=" + specijalizacija + '}';
    }
    
    
    @Override
    public String vratiNazivTabele() {
        return "fizijatar_specijalista";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat>lista=new ArrayList<>();
        while(rs.next()){
            int id=rs.getInt("fizijatar_specijalista.idSertifikat");
            Date datum=new Date(rs.getDate("fizijatar_specijalista.datumIzdavanja").getTime());
           // Specijalizacija s=new Specijalizacija(rs.getInt("specijalizacija.idSpecijalizacija"), rs.getString("specijalizacija.naziv"), rs.getString("specijalizacija.institucija"));
            Fizijatar f=new Fizijatar(rs.getInt("fizijatar.idFizijatra"), rs.getString("fizijatar.ime"), rs.getString("fizijatar.prezime"), rs.getString("fizijatar.korisnickoIme"), rs.getString("fizijatar.sifra"));
            Fizijatar_specijalista fs=new Fizijatar_specijalista(id, f, new Specijalizacija(), datum);
            lista.add(fs);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumIzdavanja,fizijatar_id,specijalizacija_id";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        java.sql.Date datumSql=new java.sql.Date(datumIzdavanja.getTime());
        return "'"+datumSql+"',"+fizijatar.getIdFizijatra()+","+specijalizacija.getIdSpecijalizacija();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "fizijatar_specijalista.idSertifikat="+idSertifikat;
    }

//    @Override
//    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumIzdavanja= '"+datumIzdavanja+"',fizijatar_id= "+fizijatar.getIdFizijatra()+",specijalizacija_id= "+specijalizacija.getIdSpecijalizacija();
    
    }
    
}
