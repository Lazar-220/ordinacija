/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class ObrisiPacijenta extends ApstraktnaGenerickaOperacija {
	
    private boolean uspeh=true;

    
    public ObrisiPacijenta() {
		super();
	}
    

	public ObrisiPacijenta(boolean autoCommit) {
		super(autoCommit);
	}


	public boolean isUspeh() {
        return uspeh;
    }

//    public void setUspeh(boolean uspeh) {
//        this.uspeh = uspeh;
//    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    	if(param == null) {
    		
          throw new NullPointerException("BRISANJE PACIJENTA NIJE USPELO, OBJEKAT UNET ZA PARAMETAR JE NULL");
            
    	}
        if (!(param instanceof Pacijent)) {
            
           throw new ClassCastException("BRISANJE PACIJENTA NIJE USPELO, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
            
        }
        
        if(((Pacijent)param).getIme()==null||((Pacijent)param).getIme().isEmpty()||
        		((Pacijent)param).getPrezime()==null||((Pacijent)param).getPrezime().isEmpty()||
        		((Pacijent)param).getEmail()==null||((Pacijent)param).getEmail().isEmpty()||
        		((Pacijent)param).getTipPacijenta()==null) {
        	
           throw new IllegalArgumentException("BRISANJE PACIJENTA NIJE USPELO, OBJEKAT UNET ZA PARAMETAR IMA NULL VREDNOSTI");
            
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            broker.delete(objekat);
        } catch (Exception ex) {
            uspeh=false;
            Logger.getLogger(ObrisiNalaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
