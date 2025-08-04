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
 *
 * @author milos
 */
public class Fizijatar_specijalista implements ApstraktniDomenskiObjekat {

    
    private int idSertifikat;
    private Fizijatar fizijatar;
    private Specijalizacija specijalizacija;
    private Date datumIzdavanja;

    public Fizijatar_specijalista() {
    }

    public Fizijatar_specijalista(int idSertifikat, Fizijatar fizijatar, Specijalizacija specijalizacija, Date datumIzdavanja) {
//        this.idSertifikat = idSertifikat;
//        this.fizijatar = fizijatar;
//        this.specijalizacija = specijalizacija;
//        this.datumIzdavanja = datumIzdavanja;
    	setIdSertifikat(idSertifikat);
    	setFizijatar(fizijatar);
    	setSpecijalizacija(specijalizacija);
    	setDatumIzdavanja(datumIzdavanja);
    }

    public int getIdSertifikat() {
        return idSertifikat;
    }

    public void setIdSertifikat(int idSertifikat) {
    	if(idSertifikat<0) {
    		throw new IllegalArgumentException("id sertifikata ne sme biti manji od 0");
    	}
        this.idSertifikat = idSertifikat;
    }

    public Fizijatar getFizijatar() {
        return fizijatar;
    }

    public void setFizijatar(Fizijatar fizijatar) {
    	if(fizijatar==null) {
    		throw new NullPointerException("fizijatar ne sme biti null");
    	}
        this.fizijatar = fizijatar;
    }

    public Specijalizacija getSpecijalizacija() {
        return specijalizacija;
    }

    public void setSpecijalizacija(Specijalizacija specijalizacija) {
    	if(specijalizacija==null) {
    		throw new NullPointerException("specijalizacija ne sme biti null");
    	}
        this.specijalizacija = specijalizacija;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
    	if(datumIzdavanja==null) {
    		throw new NullPointerException("datum izdavanja sertifikata ne sme biti null");
    	}
    	if(datumIzdavanja.getTime()>new Date().getTime()) {
    		throw new IllegalArgumentException("datum izdavanja sertifikata ne sme biti u buducnosti");
    	}
        this.datumIzdavanja = datumIzdavanja;
    }

    

    @Override
	public int hashCode() {
		return Objects.hash(specijalizacija);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fizijatar_specijalista other = (Fizijatar_specijalista) obj;
		return Objects.equals(specijalizacija, other.specijalizacija);
	}

    

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
            Specijalizacija s=new Specijalizacija(rs.getInt("specijalizacija.idSpecijalizacija"), rs.getString("specijalizacija.naziv"), rs.getString("specijalizacija.institucija"));
            Fizijatar f=new Fizijatar(rs.getInt("fizijatar.idFizijatra"), rs.getString("fizijatar.ime"), rs.getString("fizijatar.prezime"), rs.getString("fizijatar.korisnickoIme"), rs.getString("fizijatar.sifra"));
            Fizijatar_specijalista fs=new Fizijatar_specijalista(id, f, s, datum);
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
