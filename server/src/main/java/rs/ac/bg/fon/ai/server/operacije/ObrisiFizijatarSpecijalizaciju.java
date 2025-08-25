/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar_specijalista;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;

import java.util.ArrayList;
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
        if (param == null || (!(param instanceof Fizijatar)&&!(param instanceof Specijalizacija))) {
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
        	List<Fizijatar_specijalista>lista=new ArrayList<Fizijatar_specijalista>();
        	if(objekat instanceof Fizijatar) {
        		Fizijatar fizijatar=(Fizijatar)objekat;
                String uslov=" JOIN specijalizacija ON specijalizacija_id=specijalizacija.idSpecijalizacija JOIN fizijatar ON fizijatar_id=fizijatar.idFizijatra WHERE fizijatar_id="+fizijatar.getIdFizijatra();
                lista=broker.getAll(new Fizijatar_specijalista(), uslov);
        	}else if(objekat instanceof Specijalizacija) {
        		Specijalizacija specijalizacija=(Specijalizacija) objekat;
        		String uslov=" JOIN specijalizacija ON specijalizacija_id=specijalizacija.idSpecijalizacija JOIN fizijatar ON fizijatar_id=fizijatar.idFizijatra WHERE specijalizacija_id="+specijalizacija.getIdSpecijalizacija();
                lista=broker.getAll(new Fizijatar_specijalista(), uslov);
        	}
            
            for(Fizijatar_specijalista fS:lista){
                broker.delete(fS);
            }
            
        } catch (Exception ex) {
            uspeh=false;
            Logger.getLogger(ObrisiFizijatarSpecijalizaciju.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
