/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.StavkaNalaza;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class DodajNalaz extends ApstraktnaGenerickaOperacija {

    private boolean uspeh=true;

    public boolean isUspeh() {
        return uspeh;
    }

    public void setUspeh(boolean uspeh) {
        this.uspeh = uspeh;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Nalaz)) {
            try {
                throw new Exception("DODAVANJE NALAZA NIJE USPELO");
            } catch (Exception ex) {
                Logger.getLogger(ObrisiTerapiju.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            Nalaz nalaz=(Nalaz) objekat;
            broker.add(objekat);
            
           
            String uslov=" join fizijatar on fizijatar_id=idFizijatra join pacijent on pacijent_id=idPacijent ORDER BY nalaz.idNalaz ASC";
            List<Nalaz>lista=broker.getAll(new Nalaz(),uslov);
            nalaz.setIdNalaz(lista.get(lista.size()-1).getIdNalaz());
            
            for(StavkaNalaza s: nalaz.getListaStavki()){
                s.setNalaz(nalaz);
                broker.add(s);
            }
            
        } catch (Exception ex) {
            uspeh=false;
            Logger.getLogger(DodajNalaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
