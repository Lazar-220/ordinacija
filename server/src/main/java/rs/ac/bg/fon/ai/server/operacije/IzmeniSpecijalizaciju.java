/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */


public class IzmeniSpecijalizaciju extends ApstraktnaGenerickaOperacija {

    public IzmeniSpecijalizaciju() {
		super();
	}

	public IzmeniSpecijalizaciju(boolean autoCommit) {
		super(autoCommit);
	}


	@Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param == null) {
    		
            throw new NullPointerException("IZMENA SPECIJALIZACIJE NIJE USPELA, OBJEKAT UNET ZA PARAMETAR JE NULL");
              
      	}
          if (!(param instanceof Specijalizacija)) {
              
             throw new ClassCastException("IZMENA SPECIJALIZACIJE NIJE USPELA, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
              
          }
          
          if(((Specijalizacija)param).getNaziv()==null||((Specijalizacija)param).getNaziv().isEmpty()||
          		((Specijalizacija)param).getInstitucija()==null||((Specijalizacija)param).getInstitucija().isEmpty()) {
          	
             throw new IllegalArgumentException("IZMENA SPECIJALIZACIJE NIJE USPELA, OBJEKAT UNET ZA PARAMETAR IMA NULL VREDNOSTI");
              
          }
          List<Specijalizacija>lista=broker.getAll(new Specijalizacija(), null);
          for(Specijalizacija s:lista) {
        	  if ( ((Specijalizacija)param).getNaziv().equals(s.getNaziv()) 
        			     && ((Specijalizacija)param).getInstitucija().equals(s.getInstitucija()) ){
        		  throw new IllegalArgumentException("IZMENA SPECIJALIZACIJE NIJE USPELA, OBJEKAT VEC POSTOJI U BAZI");
                  
        	  }
          }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            broker.edit(objekat);
        } catch (Exception ex) {
            Logger.getLogger(IzmeniSpecijalizaciju.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
