/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;

import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;

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
    
    

    public LoginOperacija() {
		super();
	}
    
    public LoginOperacija(boolean autoCommit) {
		super(autoCommit);
	}



	@Override
    protected void preduslovi(Object param) throws Exception {
        
		if(param == null) {
        	
        	throw new NullPointerException("PRIJAVLJIVANJE FIZIJATRA NA SISTEM NIJE USPELO, OBJEKAT UNET ZA PARAMETAR JE NULL");
            
        }
			
          if (!(param instanceof Fizijatar)) {
              
             throw new ClassCastException("PRIJAVLJIVANJE FIZIJATRA NA SISTEM NIJE USPELO, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
              
          }
          
          if(((Fizijatar)param).getKorisnickoIme()==null||((Fizijatar)param).getKorisnickoIme().isEmpty()||
          		((Fizijatar)param).getSifra()==null||((Fizijatar)param).getSifra().isEmpty()) {
          	
             throw new IllegalArgumentException("PRIJAVLJIVANJE FIZIJATRA NA SISTEM NIJE USPELO, OBJEKAT UNET ZA PARAMETAR IMA NULL VREDNOSTI");
              
          }
    }

    

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception{
       
            List<Fizijatar>sviFizijatri=broker.getAll((Object)param, null);
            
            for(Fizijatar f:sviFizijatri){
                if(f.equals((Fizijatar)param)){
                    fizijatar=f;
                    return;
                }
            }
            fizijatar=null;
            
            throw new IllegalArgumentException();
            


    }

    
    
}
