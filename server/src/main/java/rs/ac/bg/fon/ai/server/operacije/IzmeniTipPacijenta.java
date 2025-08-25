/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class IzmeniTipPacijenta extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof TipPacijenta)) {
            try {
                throw new Exception("AZURIRANJE TIPA PACIJENTA NIJE USPELO");
            } catch (Exception ex) {
                Logger.getLogger(ObrisiTerapiju.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            broker.edit(objekat);
        } catch (Exception ex) {
            Logger.getLogger(IzmeniTipPacijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
