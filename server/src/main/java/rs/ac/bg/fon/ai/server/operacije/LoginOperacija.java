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
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    Fizijatar fizijatar;

    public Fizijatar getFizijatar() {
        return fizijatar;
    }

    public void setFizijatar(Fizijatar fizijatar) {
        this.fizijatar = fizijatar;
    }
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Fizijatar)) {
            try {
                throw new Exception("PRIJAVLJIVANJE FIZIJATRA NA SISTEM NIJE USPELO");
            } catch (Exception ex) {
                Logger.getLogger(ObrisiTerapiju.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc){
        try {
            List<Fizijatar>sviFizijatri=broker.getAll((Object)param, null);
            
            for(Fizijatar f:sviFizijatri){
                if(f.equals((Fizijatar)param)){
                    fizijatar=f;
                    return;
                }
            }
            fizijatar=null;
            
        } catch (Exception ex) {
            Logger.getLogger(LoginOperacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
