/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class ObrisiSpecijalizaciju extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Specijalizacija)) {
            try {
                throw new Exception("BRISANJE SPECIJALIZACIJe NIJE USPELO");
            } catch (Exception ex) {
                Logger.getLogger(ObrisiTerapiju.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            broker.delete(objekat);
        } catch (Exception ex) {
            Logger.getLogger(ObrisiSpecijalizaciju.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
