/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class UcitajTerapije extends ApstraktnaGenerickaOperacija {

    List<Terapija>terapije;
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Terapija)) {
            try {
                throw new Exception("UCITAVANJE TERAPIJA NIJE USPELO");
            } catch (Exception ex) {
                Logger.getLogger(ObrisiTerapiju.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) {
        try {
            List<Terapija>lista=broker.getAll(new Terapija(), null);
            terapije=lista;
        } catch (Exception ex) {
            Logger.getLogger(UcitajTerapije.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Terapija> getTerapije() {
        return terapije;
    }

    public void setTerapije(List<Terapija> terapije) {
        this.terapije = terapije;
    }
    
}
