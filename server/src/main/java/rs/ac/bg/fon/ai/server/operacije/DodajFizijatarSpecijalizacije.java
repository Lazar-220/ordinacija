/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar_specijalista;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class DodajFizijatarSpecijalizacije extends ApstraktnaGenerickaOperacija{
    private boolean uspeh=true;

    public boolean isUspeh() {
        return uspeh;
    }

    public void setUspeh(boolean uspeh) {
        this.uspeh = uspeh;
    }
    @Override
    protected void preduslovi(Object param) throws Exception {
         if (param == null || !(param instanceof Fizijatar_specijalista)){
            try {
                throw new Exception("DODAVANJE FIZIJATAR_SPECIJALIZACIJE NIJE USPELO");
            } catch (Exception ex) {
                Logger.getLogger(ObrisiTerapiju.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            broker.add(objekat);
        } catch (Exception ex) {
            uspeh=false;
            Logger.getLogger(DodajSpecijalizaciju.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
