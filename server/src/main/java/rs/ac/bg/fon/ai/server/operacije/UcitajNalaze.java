/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class UcitajNalaze extends ApstraktnaGenerickaOperacija {

    List<Nalaz>lista=new ArrayList<>();

    

    public UcitajNalaze() {
		super();
	}

	public UcitajNalaze(boolean autoCommit) {
		super(autoCommit);
	}


	@Override
    protected void preduslovi(Object param) throws Exception {
    	
        if(param == null) {
        	
        	throw new NullPointerException("UCITAVANJE NALAZA NIJE USPELO, OBJEKAT UNET ZA PARAMETAR JE NULL");
            
        }
    	if (!(param instanceof Nalaz)) {
            
    		throw new ClassCastException("UCITAVANJE NALAZA NIJE USPELO, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
            
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            String uslov=" join fizijatar on fizijatar_id=idFizijatra join pacijent on pacijent_id=idPacijent";
            lista=broker.getAll(new Nalaz(), uslov);
        } catch (Exception ex) {
            Logger.getLogger(UcitajNalaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Nalaz> getLista() {
        return lista;
    }


}
