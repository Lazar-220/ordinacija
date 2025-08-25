/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;

import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class IzmeniFizijatra extends ApstraktnaGenerickaOperacija {

	
    public IzmeniFizijatra() {
		super();
	}
    

	public IzmeniFizijatra(boolean autoCommit) {
		super(autoCommit);
	}



	@Override
    protected void preduslovi(Object param) throws Exception {
		if(param == null) {
			
	        throw new NullPointerException("AZURIRANJE FIZIJATRA NIJE USPELO, OBJEKAT UNET ZA PARAMETAR JE NULL");
	          
	  	}
	      if (!(param instanceof Fizijatar)) {
	          
	         throw new ClassCastException("AZURIRANJE FIZIJATRA NIJE USPELO, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
	          
	      }
	      
	      if(((Fizijatar)param).getIme()==null||((Fizijatar)param).getIme().isEmpty()||
	        		((Fizijatar)param).getPrezime()==null||((Fizijatar)param).getPrezime().isEmpty()||
	        		((Fizijatar)param).getKorisnickoIme()==null||((Fizijatar)param).getKorisnickoIme().isEmpty()||
	        		((Fizijatar)param).getSifra()==null||((Fizijatar)param).getSifra().isEmpty()) {
	        	
	           throw new IllegalArgumentException("BRISANJE PACIJENTA NIJE USPELO, OBJEKAT UNET ZA PARAMETAR IMA NULL VREDNOSTI");
	            
	        }
    }
	
	


    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            broker.edit(objekat);
        } catch (Exception ex) {
            Logger.getLogger(IzmeniFizijatra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
