/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class UcitajPacijente extends ApstraktnaGenerickaOperacija {

    List<Pacijent>lista=new ArrayList<>();

    public List<Pacijent> getLista() {
        return lista;
    }

    public void setLista(List<Pacijent> lista) {
        this.lista = lista;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Pacijent)) {
            try {
                throw new Exception("UCITAVANJE PACIJENTA NIJE USPELO");
            } catch (Exception ex) {
                Logger.getLogger(ObrisiTerapiju.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            String uslov=" join tip_pacijenta on tipPacijenta_id=idTipPacijenta";
            lista=broker.getAll(new Pacijent(), uslov);
        } catch (Exception ex) {
            Logger.getLogger(UcitajPacijente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
