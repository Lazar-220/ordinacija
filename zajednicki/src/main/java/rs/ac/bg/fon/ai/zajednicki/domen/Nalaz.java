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
public class Nalaz implements ApstraktniDomenskiObjekat {

    private int idNalaz;
    private Date datumIzdavanja;
    private String opisNalaza;
    private double ukupnaCena;
    private Fizijatar fizijatar;
    private Pacijent pacijent;
    private List<StavkaNalaza>listaStavki;
    
    

    public Nalaz() {
    }

    public Nalaz(int idNalaz, Date datumIzdavanja, String opisNalaza, double ukupnaCena, Fizijatar fizijatar, Pacijent pacijent, List<StavkaNalaza> listaStavki) {
//        this.idNalaz = idNalaz;
//        this.datumIzdavanja = datumIzdavanja;
//        this.opisNalaza = opisNalaza;
//        this.ukupnaCena = ukupnaCena;
//        this.fizijatar = fizijatar;
//        this.pacijent = pacijent;
//        this.listaStavki = listaStavki;
    	setIdNalaz(idNalaz);
    	setDatumIzdavanja(datumIzdavanja);
    	setOpisNalaza(opisNalaza);
    	setUkupnaCena(ukupnaCena);
    	setFizijatar(fizijatar);
    	setPacijent(pacijent);
    	setListaStavki(listaStavki);
    }

    public int getIdNalaz() {
        return idNalaz;
    }

    public void setIdNalaz(int idNalaz) {
    	if(idNalaz<0) {
    		throw new IllegalArgumentException("id nalaza ne sme biti manji od 0");
    	}
        this.idNalaz = idNalaz;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
    	if(datumIzdavanja==null) {
    		throw new NullPointerException("datum izdavanja nalaza ne sme biti null");
    	}
    	if(datumIzdavanja.getTime()>new Date().getTime()) {
    		throw new IllegalArgumentException("datum izdavanja nalaza ne sme biti u buducnosti");
    	}
        this.datumIzdavanja = datumIzdavanja;
    }

    public String getOpisNalaza() {
        return opisNalaza;
    }

    public void setOpisNalaza(String opisNalaza) {
    	if(opisNalaza==null) {
    		throw new NullPointerException("opis nalaza ne sme biti null");
    	}
    	if(opisNalaza.isEmpty()) {
    		throw new IllegalArgumentException("opis nalaza ne sme biti prazan");
    	}
        this.opisNalaza = opisNalaza;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
    	if(ukupnaCena<0) {
    		throw new IllegalArgumentException("ukupna cena nalaza ne sme biti manja od 0");
    	}
        this.ukupnaCena = ukupnaCena;
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

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
    	if(pacijent==null) {
    		throw new NullPointerException("pacijent ne sme biti null");
    	}
        this.pacijent = pacijent;
    }

    public List<StavkaNalaza> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<StavkaNalaza> listaStavki) {
    	if(listaStavki==null) {
    		throw new NullPointerException("lista stavki ne sme biti null");
    	}
    	if(listaStavki.isEmpty()) {
    		throw new IllegalArgumentException("lista stavki nalaza ne sme biti prazna");
    	}
        this.listaStavki = listaStavki;
    }

    

    @Override
	public int hashCode() {
		return Objects.hash(datumIzdavanja, fizijatar, idNalaz, listaStavki, opisNalaza, pacijent, ukupnaCena);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nalaz other = (Nalaz) obj;
		return Objects.equals(datumIzdavanja, other.datumIzdavanja) && Objects.equals(fizijatar, other.fizijatar)
				&& idNalaz == other.idNalaz && Objects.equals(listaStavki, other.listaStavki)
				&& Objects.equals(opisNalaza, other.opisNalaza) && Objects.equals(pacijent, other.pacijent)
				&& Double.doubleToLongBits(ukupnaCena) == Double.doubleToLongBits(other.ukupnaCena);
	}

    @Override
    public String toString() {
        return "Nalaz{" + "opisNalaza=" + opisNalaza + ", ukupnaCena=" + ukupnaCena + '}';
    }
    
    
    
    @Override
    public String vratiNazivTabele() {
        return "nalaz";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat>lista=new ArrayList<>();
        while(rs.next()){
            int id=rs.getInt("nalaz.idNalaz");
            Date datum=new Date(rs.getDate("nalaz.datumIzdavanja").getTime());
            String opis=rs.getString("nalaz.opisNalaza");
            double ukupnaCena=rs.getDouble("nalaz.ukupnaCena");
            Fizijatar f=new Fizijatar(rs.getInt("fizijatar.idFizijatra"), rs.getString("fizijatar.ime"), rs.getString("fizijatar.prezime"), rs.getString("fizijatar.korisnickoIme"), rs.getString("fizijatar.sifra"));
            //TipPacijenta tipPacijenta=new TipPacijenta(rs.getInt("tip_pacijenta.idTipPacijenta"), StarosnaDob.valueOf(rs.getString("tip_pacijenta.starosnaDob")), Pol.valueOf(rs.getString("tip_pacijenta.pol")));
            Pacijent p=new Pacijent(rs.getInt("pacijent.idPacijent"), rs.getString("pacijent.ime"), rs.getString("pacijent.prezime"), rs.getString("pacijent.email"), null);
            Nalaz n=new Nalaz(id, datum, opis, ukupnaCena, f, p, null);
            lista.add(n);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumIzdavanja,opisNalaza,ukupnaCena,fizijatar_id,pacijent_id";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        java.sql.Date datumSql=new java.sql.Date(datumIzdavanja.getTime());
        return "'"+datumSql+"','"+opisNalaza+"',"+ukupnaCena+","+fizijatar.getIdFizijatra()+","+pacijent.getIdPacijent();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "nalaz.idNalaz="+idNalaz;
    }

//    @Override
//    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        java.sql.Date datumSql=new java.sql.Date(datumIzdavanja.getTime());
        return "datumIzdavanja= '"+datumSql+"',opisNalaza= '"+opisNalaza+"',ukupnaCena= "+ukupnaCena+",fizijatar_id= "+fizijatar.getIdFizijatra()+",pacijent_id="+pacijent.getIdPacijent();
    
    }
    
}
