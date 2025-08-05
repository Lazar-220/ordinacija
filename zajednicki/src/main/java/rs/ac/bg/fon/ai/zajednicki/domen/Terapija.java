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
public class Terapija implements ApstraktniDomenskiObjekat {

    private int idTerapija;
    private String naziv;
    private String opis;
    private double cena;

    public Terapija() {
    }

    public Terapija(int idTerapija, String naziv, String opis, double cena) {
    	
    	setIdTerapija(idTerapija);
    	setNaziv(naziv);
    	setOpis(opis);
    	setCena(cena);
    	
//        this.idTerapija = idTerapija;
//        this.naziv = naziv;
//        this.opis = opis;
//        this.cena = cena;
    }

    public int getIdTerapija() {
        return idTerapija;
    }

    public void setIdTerapija(int idTerapija) {
    	if(idTerapija<0) {
    		throw new IllegalArgumentException("id terapije ne sme biti manji od 0");
    	}
        this.idTerapija = idTerapija;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
    	if(naziv==null) {
    		throw new NullPointerException("naziv terapije ne sme biti null");
    	}
    	if(naziv.isEmpty()) {
    		throw new IllegalArgumentException("naziv terapije ne sme biti prazan");
    	}
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
    	if(opis==null) {
    		throw new NullPointerException("opis terapije ne sme biti null");
    	}
    	if(opis.isEmpty()) {
    		throw new IllegalArgumentException("opis terapije ne sme biti prazan");
    	}
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
    	if(cena<0) {
    		throw new IllegalArgumentException("cena terapije ne sme biti manja od 0");
    	}
        this.cena = cena;
    }

    

    @Override
	public int hashCode() {
		return Objects.hash(idTerapija);
	}

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
