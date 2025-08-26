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
 * Klasa UcitajNalaze predstavlja konkretnu sistemsku operaciju koja služi za 
 * učitavanje svih {@link Nalaz} objekata iz sistema.
 * <p>
 * Nasleđuje apstraktnu klasu {@link ApstraktnaGenerickaOperacija} i implementira njene 
 * apstraktne metode za definisanje preduslova i izvršavanje same operacije.
 * </p>
 *
 * @author 
 * Lazar Milosavljević
 */
public class UcitajNalaze extends ApstraktnaGenerickaOperacija {

	/**
     * Lista svih nalaza učitanih iz baze podataka.
     */
    List<Nalaz>lista=new ArrayList<>();

    
    /**
     * Podrazumevani konstruktor.
     * Kreira novu instancu objekta ove klase.
     * <p>
     * Poziva podrazumevani konstruktor nadklase {@link ApstraktnaGenerickaOperacija}.
     * </p>
     */
    public UcitajNalaze() {
		super();
	}

    /**
     * Parametrizovani konstruktor.
     * Omogućava definisanje da li će operacija koristiti auto-commit režim.
     * <p>
     * Poziva konstruktor nadklase {@link ApstraktnaGenerickaOperacija} sa prosleđenom
     * vrednošću parametra autoCommit.
     * </p>
     *
     * @param autoCommit true ako je potrebno automatsko potvrđivanje transakcije, false inače
     */
	public UcitajNalaze(boolean autoCommit) {
		super(autoCommit);
	}


	/**
     * Proverava ispravnost prosleđenog parametra pre izvršenja operacije.
     * <p>
     * Parametar mora biti instanca klase {@link Nalaz}.
     * </p>
     *
     * @param param Objekat koji se validira pre učitavanja
     * @throws java.lang.Exception ako je parametar:
     * <ul>
     *   <li>null</li>
     *   <li>nije instanca klase Nalaz</li>
     * </ul>
     */
	@Override
    protected void preduslovi(Object param) throws Exception {
    	
        if(param == null) {
        	
        	throw new NullPointerException("UCITAVANJE NALAZA NIJE USPELO, OBJEKAT UNET ZA PARAMETAR JE NULL");
            
        }
    	if (!(param instanceof Nalaz)) {
            
    		throw new ClassCastException("UCITAVANJE NALAZA NIJE USPELO, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
            
        }
    }

	/**
     * Izvršava operaciju učitavanja svih nalaza iz baze podataka.
     * <p>
     * Ako dođe do izuzetka tokom učitavanja, greška se loguje, a lista ostaje prazna.
     * </p>
     *
     * @param objekat Objekat koji definiše uslove učitavanja (očekuje se {@link Nalaz})
     * @param kljuc Dodatni parametar (nije korišćen u ovoj implementaciji)
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            String uslov=" join fizijatar on fizijatar_id=idFizijatra join pacijent on pacijent_id=idPacijent";
            lista=broker.getAll(new Nalaz(), uslov);
        } catch (Exception ex) {
            Logger.getLogger(UcitajNalaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Vraća listu svih učitanih nalaza.
     *
     * @return lista objekata tipa {@link Nalaz}
     */
    public List<Nalaz> getLista() {
        return lista;
    }


}
