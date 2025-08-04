/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import rs.ac.bg.fon.ai.zajednicki.domen.Enum.Pol;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.StarosnaDob;

/**
 *
 * @author milos
 */
public class Pacijent implements ApstraktniDomenskiObjekat {

    
    private int idPacijent;
    private String ime;
    private String prezime;
    private String email;
    private TipPacijenta tipPacijenta;

    public Pacijent() {
    }

    public Pacijent(int idPacijent, String ime, String prezime, String email, TipPacijenta tipPacijenta) {
//        this.idPacijent = idPacijent;
//        this.ime = ime;
//        this.prezime = prezime;
//        this.email = email;
//        this.tipPacijenta = tipPacijenta;
    	setIdPacijent(idPacijent);
    	setIme(ime);
    	setPrezime(prezime);
    	setEmail(email);
    	setTipPacijenta(tipPacijenta);
    }

    

    public int getIdPacijent() {
        return idPacijent;
    }

    public void setIdPacijent(int idPacijent) {
    	if(idPacijent<0) {
    		throw new IllegalArgumentException("id pacijenta ne sme biti manji od 0");
    	}
        this.idPacijent = idPacijent;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
    	if(ime==null) {
    		throw new NullPointerException("ime pacijenta ne sme biti null");
    	}
    	if(ime.isEmpty()) {
    		throw new IllegalArgumentException("ime pacijenta ne sme biti prazno");
    	}
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
    	if(prezime==null) {
    		throw new NullPointerException("prezime pacijenta ne sme biti null");
    	}
    	if(prezime.isEmpty()) {
    		throw new IllegalArgumentException("prezime pacijenta ne sme biti prazno");
    	}
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
    	if(email==null) {
    		throw new NullPointerException("email pacijenta ne sme biti null");
    	}
    	if(email.isEmpty()) {
    		throw new IllegalArgumentException("email pacijenta ne sme biti prazan");
    	}
        this.email = email;
    }

    public TipPacijenta getTipPacijenta() {
    	
        return tipPacijenta;
    }

    public void setTipPacijenta(TipPacijenta tipPacijenta) {
    	if(tipPacijenta==null) {
    		throw new NullPointerException("tip pacijenta ne sme biti null");
    	}
        this.tipPacijenta = tipPacijenta;
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
        final Pacijent other = (Pacijent) obj;
        if (this.idPacijent != other.idPacijent) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.tipPacijenta, other.tipPacijenta);
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    
    @Override
    public String vratiNazivTabele() {
        return "pacijent";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat>lista=new ArrayList<>();
        while(rs.next()){
            int id=rs.getInt("pacijent.idPacijent");
            String ime=rs.getString("pacijent.ime");
            String prezime=rs.getString("pacijent.prezime");
            String email=rs.getString("pacijent.email");
            TipPacijenta tp=new TipPacijenta(rs.getInt("tip_pacijenta.idTipPacijenta"), StarosnaDob.valueOf(rs.getString("tip_pacijenta.starosnaDob")), Pol.valueOf(rs.getString("tip_pacijenta.pol")));
            Pacijent p=new Pacijent(id, ime, prezime, email, tp);
            lista.add(p);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,email,tipPacijenta_id";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+email+"',"+tipPacijenta.getIdTipPacijenta();
    
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "pacijent.idPacijent="+idPacijent;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime= '"+ime+"',prezime= '"+prezime+"',email= '"+email+"',tipPacijenta_id= "+tipPacijenta.getIdTipPacijenta();
    
    }
    
}
