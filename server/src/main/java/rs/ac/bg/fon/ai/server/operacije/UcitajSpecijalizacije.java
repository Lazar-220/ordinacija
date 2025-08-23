/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class UcitajSpecijalizacije extends ApstraktnaGenerickaOperacija {

    List<Specijalizacija>lista;
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Specijalizacija)) {
            try {
                throw new Exception("UCITAVANJE SPECIJALIZACIJA NIJE USPELO");
            } catch (Exception ex) {
                Logger.getLogger(ObrisiTerapiju.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            lista=broker.getAll(new Specijalizacija(), null);
        } catch (Exception ex) {
            Logger.getLogger(UcitajSpecijalizacije.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Specijalizacija> getLista() {
        return lista;
    }

    public void setLista(List<Specijalizacija> lista) {
        this.lista = lista;
    }
    
}
