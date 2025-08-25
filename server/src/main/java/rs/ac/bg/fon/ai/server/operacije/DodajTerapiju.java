/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class DodajTerapiju extends ApstraktnaGenerickaOperacija{

	
	
	
    public DodajTerapiju() {    //
		super();
	}

	public DodajTerapiju(boolean autoCommit) {   //
		super(autoCommit);
	}



	@Override
    protected void preduslovi(Object param)  throws Exception{
		
        if(param == null) {
    		
            throw new NullPointerException("DODAVANJE TERAPIJE NIJE USPELO, OBJEKAT UNET ZA PARAMETAR JE NULL");
              
      	}
          if (!(param instanceof Terapija)) {
              
             throw new ClassCastException("DODAVANJE TERAPIJE NIJE USPELO, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
              
          }
          
          if(((Terapija)param).getNaziv()==null||((Terapija)param).getNaziv().isEmpty()||
          		((Terapija)param).getCena()<=0||
          		((Terapija)param).getOpis()==null||((Terapija)param).getOpis().isEmpty()) {
          	
             throw new IllegalArgumentException("DODAVANJE TERAPIJE NIJE USPELO, OBJEKAT UNET ZA PARAMETAR IMA NULL VREDNOSTI");
              
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
