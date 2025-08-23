/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class UcitajFizijatre extends ApstraktnaGenerickaOperacija {

    List<Fizijatar>lista;
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Fizijatar)) {
            try {
                throw new Exception("UCITAVANJE FIZIJATARA NIJE USPELO");
            } catch (Exception ex) {
                Logger.getLogger(ObrisiTerapiju.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            lista=broker.getAll(new Fizijatar(), null);
        } catch (Exception ex) {
            Logger.getLogger(UcitajFizijatre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Fizijatar> getLista() {
        return lista;
    }

    public void setLista(List<Fizijatar> lista) {
        this.lista = lista;
    }
    
}
