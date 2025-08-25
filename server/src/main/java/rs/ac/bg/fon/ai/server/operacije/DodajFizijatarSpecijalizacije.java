/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar_specijalista;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;

import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;

import java.util.Date;
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

//    public void setUspeh(boolean uspeh) {
//        this.uspeh = uspeh;
//    }
    
    public DodajFizijatarSpecijalizacije() {
		super();
	}
    
    public DodajFizijatarSpecijalizacije(boolean autoCommit) {
		super(autoCommit);
	}

	@Override
    protected void preduslovi(Object param) throws Exception {
         
         
         if(param == null) {
     		
             throw new NullPointerException("DODAVANJE FIZIJATAR_SPECIJALIZACIJE NIJE USPELO, OBJEKAT UNET ZA PARAMETAR JE NULL");
               
       	}
           if (!(param instanceof Fizijatar_specijalista)) {
               
              throw new ClassCastException("DODAVANJE FIZIJATAR_SPECIJALIZACIJE NIJE USPELO, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
               
           }
           
           if(((Fizijatar_specijalista)param).getFizijatar()==null||
           		((Fizijatar_specijalista)param).getDatumIzdavanja()==null||((Fizijatar_specijalista)param).getDatumIzdavanja().getTime()>(new Date()).getTime()||
           		((Fizijatar_specijalista)param).getSpecijalizacija()==null) {
           	
              throw new IllegalArgumentException("DODAVANJE FIZIJATAR_SPECIJALIZACIJE NIJE USPELO, OBJEKAT UNET ZA PARAMETAR IMA NULL VREDNOSTI");
               
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
