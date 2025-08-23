/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class DodajTerapiju extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object param)  throws Exception{
        if(param==null||!(param instanceof Terapija)){
            try {
                throw new Exception("DODAVANJE TERAPIJE NIJE USPELO");
            } catch (Exception ex) {
                Logger.getLogger(DodajTerapiju.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            broker.add(objekat);
        } catch (Exception ex) {
            Logger.getLogger(DodajTerapiju.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
