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
public class TipPacijenta implements ApstraktniDomenskiObjekat {

    private int idTipPacijenta;
    private StarosnaDob starosnaDob;
    private Pol pol;

    public TipPacijenta() {
    }

    public TipPacijenta(int idTipPacijenta, StarosnaDob starosnaDob, Pol pol) {
        this.idTipPacijenta = idTipPacijenta;
        this.starosnaDob = starosnaDob;
        this.pol = pol;
    }

    public int getIdTipPacijenta() {
        return idTipPacijenta;
    }

    public void setIdTipPacijenta(int idTipPacijenta) {
        this.idTipPacijenta = idTipPacijenta;
    }

    public StarosnaDob getStarosnaDob() {
        return starosnaDob;
    }

    public void setStarosnaDob(StarosnaDob starosnaDob) {
        this.starosnaDob = starosnaDob;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final TipPacijenta other = (TipPacijenta) obj;
        if (this.starosnaDob != other.starosnaDob) {
            return false;
        }
        return this.pol == other.pol;
    }

    

    

    @Override
    public String toString() {
        return "TipPacijenta{" + "starosnaDob=" + starosnaDob + ", pol=" + pol + '}';
    }
    
    
    
    @Override
    public String vratiNazivTabele() {
        return "tip_pacijenta";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next()){
            int idTipPacijenta=rs.getInt("tip_pacijenta.idTipPacijenta");
            StarosnaDob starosnaDob=StarosnaDob.valueOf(rs.getString("tip_pacijenta.starosnaDob"));
            Pol pol=Pol.valueOf(rs.getString("tip_pacijenta.pol"));
            TipPacijenta tp=new TipPacijenta(idTipPacijenta, starosnaDob, pol);
            lista.add(tp);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "starosnaDob,pol";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+starosnaDob+"','"+pol+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "tip_pacijenta.idTipPacijenta="+idTipPacijenta;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "starosnaDob= '"+starosnaDob+"',pol= '"+pol+"'";
    
    }
    
}
