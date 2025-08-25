/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;


import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class ObrisiTipPacijenta extends ApstraktnaGenerickaOperacija {

	
    
    public ObrisiTipPacijenta() {
		super();
	}
    

	public ObrisiTipPacijenta(boolean autoCommit) {
		super(autoCommit);
	}


	@Override
    protected void preduslovi(Object param) throws Exception {
        
        
        if(param == null) {
    		
            throw new NullPointerException("BRISANJE TIPA PACIJENTA NIJE USPELO, OBJEKAT UNET ZA PARAMETAR JE NULL");
              
      	}
          if (!(param instanceof TipPacijenta)) {
              
             throw new ClassCastException("BRISANJE TIPA PACIJENTA NIJE USPELO, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
              
          }
          
          if(((TipPacijenta)param).getPol()==null||((TipPacijenta)param).getStarosnaDob()==null) {
          	
             throw new IllegalArgumentException("BRISANJE TIPA PACIJENTA NIJE USPELO, OBJEKAT UNET ZA PARAMETAR IMA NULL VREDNOSTI");
              
          }

    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            broker.delete(objekat);
        } catch (Exception ex) {
            Logger.getLogger(ObrisiTipPacijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
