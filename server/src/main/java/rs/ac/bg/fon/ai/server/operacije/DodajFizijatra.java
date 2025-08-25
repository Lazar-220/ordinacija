/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class DodajFizijatra extends ApstraktnaGenerickaOperacija {

    private Fizijatar dodatiFizijatar;

    public Fizijatar getDodatiFizijatar() {
        return dodatiFizijatar;
    }

    public void setDodatiFizijatar(Fizijatar dodatiFizijatar) {
        this.dodatiFizijatar = dodatiFizijatar;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Fizijatar)) {
            try {
                throw new Exception("DODAVANJE FIZIJATRA NIJE USPELO");
            } catch (Exception ex) {
                Logger.getLogger(ObrisiTerapiju.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            broker.add(objekat);
            
            String uslov=" ORDER BY fizijatar.idFizijatra ASC";
            List<Fizijatar>listaFizijatara=broker.getAll(new Fizijatar(), uslov);
            
            dodatiFizijatar=(Fizijatar) listaFizijatara.get(listaFizijatara.size()-1);
            
        } catch (Exception ex) {
            Logger.getLogger(DodajFizijatra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
