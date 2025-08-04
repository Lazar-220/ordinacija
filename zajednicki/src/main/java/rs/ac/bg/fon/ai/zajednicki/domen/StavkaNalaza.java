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

import rs.ac.bg.fon.ai.zajednicki.domen.Enum.Pol;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.StarosnaDob;

/**
 *
 * @author milos
 */
public class StavkaNalaza implements ApstraktniDomenskiObjekat {

    
    private int rb;
    private double cena;
    private Terapija terapija;
    private Nalaz nalaz;

    public StavkaNalaza() {
    }

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

    public StavkaNalaza(int rb, double cena, Terapija terapija) {
//        this.rb = rb;
//        this.cena = cena;
//        this.terapija = terapija;
    	setRb(rb);
    	setCena(cena);
    	setTerapija(terapija);
    }
    

    public Nalaz getNalaz() {
        return nalaz;
    }

    public void setNalaz(Nalaz nalaz) {
    	if(nalaz==null) {
    		throw new NullPointerException("nalaz ne sme biti null");
    	}
        this.nalaz = nalaz;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
    	if(rb<1) {
    		throw new IllegalArgumentException("rb stavke nalaza ne sme biti manji od 1");
    	}
        this.rb = rb;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
    	if(cena<0) {
    		throw new IllegalArgumentException("cena ne sme biti manja od 0");
    	}
        this.cena = cena;
    }

    public Terapija getTerapija() {
        return terapija;
    }

    public void setTerapija(Terapija terapija) {
    	if(terapija==null) {
    		throw new NullPointerException("terapija ne sme biti null");
    	}
        this.terapija = terapija;
    }

    @Override
    public int hashCode() {
    	return Objects.hash(rb, cena, terapija);
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
        final StavkaNalaza other = (StavkaNalaza) obj;
        if (this.rb != other.rb) {
            return false;
        }
        if (Double.doubleToLongBits(this.cena) != Double.doubleToLongBits(other.cena)) {
            return false;
        }
        return Objects.equals(this.terapija, other.terapija);
    }

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
        List<ApstraktniDomenskiObjekat>lista=new ArrayList<>();
        while(rs.next()){
            int rb=rs.getInt("stavka_nalaza.rb");
            double cena=rs.getDouble("stavka_nalaza.cena");
            
            int id=rs.getInt("nalaz.idNalaz");
            Date datum=new Date(rs.getDate("nalaz.datumIzdavanja").getTime());
            String opis=rs.getString("nalaz.opisNalaza");
            double ukupnaCena=rs.getDouble("nalaz.ukupnaCena");
            
            Nalaz n=new Nalaz(id, datum, opis, ukupnaCena, null, null, null);
            
            int idTerapija=rs.getInt("terapija.idTerapija");
            String naziv=rs.getString("terapija.naziv");
            String opisTerapije=rs.getString("terapija.opis");
            double cenaTerapije=rs.getDouble("terapija.cena");
            Terapija t=new Terapija(idTerapija, naziv, opisTerapije, cenaTerapije);
            
            StavkaNalaza stavka=new StavkaNalaza(rb, cena, t, n);
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
