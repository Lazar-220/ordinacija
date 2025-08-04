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
public class Specijalizacija implements ApstraktniDomenskiObjekat {

    private int idSpecijalizacija;
    private String naziv;
    private String institucija;

    public Specijalizacija() {
    }

    public Specijalizacija(int idSpecijalizacija, String naziv, String institucija) {
//        this.idSpecijalizacija = idSpecijalizacija;
//        this.naziv = naziv;
//        this.institucija = institucija;
    	setIdSpecijalizacija(idSpecijalizacija);
    	setInstitucija(institucija);
    	setNaziv(naziv);
    }

    public int getIdSpecijalizacija() {
        return idSpecijalizacija;
    }

    public void setIdSpecijalizacija(int idSpecijalizacija) {
    	if(idSpecijalizacija<0) {
    		throw new IllegalArgumentException("id specijalizacije ne sme biti manji od 0");
    	}
        this.idSpecijalizacija = idSpecijalizacija;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
    	if(naziv==null) {
    		throw new NullPointerException("naziv specijalizacije ne sme biti null");
    	}
    	if(naziv.isEmpty()) {
    		throw new IllegalArgumentException("naziv specijalizacije ne sme biti prazan");
    	}
        this.naziv = naziv;
    }

    public String getInstitucija() {
        return institucija;
    }

    public void setInstitucija(String institucija) {
    	if(naziv==null) {
    		throw new NullPointerException("institucija gde je obavljena specijalizacija ne sme biti null");
    	}
    	if(naziv.isEmpty()) {
    		throw new IllegalArgumentException("institucija gde je obavljena specijalizacija ne sme biti prazna");
    	}
        this.institucija = institucija;
    }

    

    @Override
	public int hashCode() {
		return Objects.hash(idSpecijalizacija, institucija, naziv);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Specijalizacija other = (Specijalizacija) obj;
		return idSpecijalizacija == other.idSpecijalizacija && Objects.equals(institucija, other.institucija)
				&& Objects.equals(naziv, other.naziv);
	}

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
