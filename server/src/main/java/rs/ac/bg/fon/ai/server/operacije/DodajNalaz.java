/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.StavkaNalaza;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class DodajNalaz extends ApstraktnaGenerickaOperacija {

    private boolean uspeh=true;

    public boolean isUspeh() {
        return uspeh;
    }

//    public void setUspeh(boolean uspeh) {
//        this.uspeh = uspeh;
//    }
    public DodajNalaz() {
		super();
	}
    
    public DodajNalaz(boolean autoCommit) {
		super(autoCommit);
	}

	@Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param == null) {
        	
        	throw new NullPointerException("DODAVANJE NALAZA NIJE USPELO, OBJEKAT UNET ZA PARAMETAR JE NULL");
            
        }
    	if (!(param instanceof Nalaz)) {
            
    		throw new ClassCastException("DODAVANJE NALAZA NIJE USPELO, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
            
        }
    	if(((Nalaz)param).getOpisNalaza()==null||((Nalaz)param).getOpisNalaza().isEmpty()||
          		((Nalaz)param).getUkupnaCena()<=0||
          		((Nalaz)param).getFizijatar()==null||((Nalaz)param).getPacijent()==null||
          		((Nalaz)param).getListaStavki()==null||((Nalaz)param).getListaStavki().isEmpty()||
          		((Nalaz)param).getDatumIzdavanja()==null) {
          	
             throw new IllegalArgumentException("DODAVANJE NALAZA NIJE USPELO, OBJEKAT UNET ZA PARAMETAR IMA NULL VREDNOSTI");
              
          }
    }


	@Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            Nalaz nalaz=(Nalaz) objekat;
            broker.add(objekat);
            
           
            String uslov=" join fizijatar on fizijatar_id=idFizijatra join pacijent on pacijent_id=idPacijent ORDER BY nalaz.idNalaz ASC";
            List<Nalaz>lista=broker.getAll(new Nalaz(),uslov);
            nalaz.setIdNalaz(lista.get(lista.size()-1).getIdNalaz());
            
            for(StavkaNalaza s: nalaz.getListaStavki()){
                s.setNalaz(nalaz);
                broker.add(s);
            }
            
        } catch (Exception ex) {
            uspeh=false;
            Logger.getLogger(DodajNalaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
