/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class UcitajNalaze extends ApstraktnaGenerickaOperacija {

    List<Nalaz>lista=new ArrayList<>();

    
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Nalaz)) {
            try {
                throw new Exception("UCITAVANJE NALAZA NIJE USPELO");
            } catch (Exception ex) {
                Logger.getLogger(ObrisiTerapiju.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            String uslov=" join fizijatar on fizijatar_id=idFizijatra join pacijent on pacijent_id=idPacijent";
            lista=broker.getAll(new Nalaz(), uslov);
        } catch (Exception ex) {
            Logger.getLogger(UcitajNalaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Nalaz> getLista() {
        return lista;
    }

    public void setLista(List<Nalaz> lista) {
        this.lista = lista;
    }
}
