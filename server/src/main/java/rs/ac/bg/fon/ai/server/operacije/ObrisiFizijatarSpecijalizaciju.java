/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar_specijalista;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class ObrisiFizijatarSpecijalizaciju extends ApstraktnaGenerickaOperacija {

    private boolean uspeh=true;

    public boolean isUspeh() {
        return uspeh;
    }

    public void setUspeh(boolean uspeh) {
        this.uspeh = uspeh;
    }
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Fizijatar)) {
            try {
                throw new Exception("BRISANJE SERTIFIKATA NIJE USPELO");
            } catch (Exception ex) {
                Logger.getLogger(ObrisiTerapiju.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            Fizijatar fizijatar=(Fizijatar)objekat;
            String uslov=" JOIN fizijatar ON fizijatar_id=fizijatar.idFizijatra WHERE fizijatar_id="+fizijatar.getIdFizijatra();
            List<Fizijatar_specijalista>lista=broker.getAll(new Fizijatar_specijalista(), uslov);
            for(Fizijatar_specijalista fS:lista){
                broker.delete(fS);
            }
            
        } catch (Exception ex) {
            uspeh=false;
            Logger.getLogger(ObrisiFizijatarSpecijalizaciju.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
