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
        this.idNalaz = idNalaz;
        this.datumIzdavanja = datumIzdavanja;
        this.opisNalaza = opisNalaza;
        this.ukupnaCena = ukupnaCena;
        this.fizijatar = fizijatar;
        this.pacijent = pacijent;
        this.listaStavki = listaStavki;
    }

    public int getIdNalaz() {
        return idNalaz;
    }

    public void setIdNalaz(int idNalaz) {
        this.idNalaz = idNalaz;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public String getOpisNalaza() {
        return opisNalaza;
    }

    public void setOpisNalaza(String opisNalaza) {
        this.opisNalaza = opisNalaza;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Fizijatar getFizijatar() {
        return fizijatar;
    }

    public void setFizijatar(Fizijatar fizijatar) {
        this.fizijatar = fizijatar;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public List<StavkaNalaza> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<StavkaNalaza> listaStavki) {
        this.listaStavki = listaStavki;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Nalaz other = (Nalaz) obj;
        if (this.idNalaz != other.idNalaz) {
            return false;
        }
        if (Double.doubleToLongBits(this.ukupnaCena) != Double.doubleToLongBits(other.ukupnaCena)) {
            return false;
        }
        if (!Objects.equals(this.opisNalaza, other.opisNalaza)) {
            return false;
        }
        if (!Objects.equals(this.datumIzdavanja, other.datumIzdavanja)) {
            return false;
        }
        if (!Objects.equals(this.fizijatar, other.fizijatar)) {
            return false;
        }
        if (!Objects.equals(this.pacijent, other.pacijent)) {
            return false;
        }
        return Objects.equals(this.listaStavki, other.listaStavki);
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

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        java.sql.Date datumSql=new java.sql.Date(datumIzdavanja.getTime());
        return "datumIzdavanja= '"+datumSql+"',opisNalaza= '"+opisNalaza+"',ukupnaCena= "+ukupnaCena+",fizijatar_id= "+fizijatar.getIdFizijatra()+",pacijent_id="+pacijent.getIdPacijent();
    
    }
    
}
