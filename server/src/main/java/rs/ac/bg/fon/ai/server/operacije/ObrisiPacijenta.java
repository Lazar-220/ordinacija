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
 * Klasa ObrisiPacijenta predstavlja konkretnu sistemsku operaciju koja služi za 
 * brisanje {@link Pacijent} iz sistema.
 * <p>
 * Nasleđuje apstraktnu klasu {@link ApstraktnaGenerickaOperacija} i implementira njene 
 * apstraktne metode za definisanje preduslova i izvršavanje same operacije.
 * </p>
 *
 * @author 
 * Lazar Milosavljević
 */
public class ObrisiPacijenta extends ApstraktnaGenerickaOperacija {

	/**
     * Atribut koji označava da li je operacija brisanja uspešno izvršena kao boolean.
     */
    private boolean uspeh=true;

    /**
     * Podrazumevani konstruktor.
     * Kreira novu instancu objekta ove klase.
     * <p>
     * Poziva podrazumevani konstruktor nadklase {@link ApstraktnaGenerickaOperacija}.
     * </p>
     */
    public ObrisiPacijenta() {
		super();
	}
    
    /**
     * Parametrizovani konstruktor.
     * Omogućava definisanje da li će operacija koristiti auto-commit režim.
     * <p>
     * Poziva konstruktor nadklase {@link ApstraktnaGenerickaOperacija} sa prosleđenom
     * vrednošću parametra <code>autoCommit</code>.
     * </p>
     *
     * @param autoCommit true ako je potrebno automatsko potvrđivanje transakcije, false inače
     */
	public ObrisiPacijenta(boolean autoCommit) {
		super(autoCommit);
	}

	/**
     * Vraća informaciju o tome da li je operacija brisanja pacijenta bila uspešna.
     *
     * @return true ako je operacija uspešno izvršena, false ako nije
     */
	
	public boolean isUspeh() {
        return uspeh;
    }

//    public void setUspeh(boolean uspeh) {
//        this.uspeh = uspeh;
//    }
    
	/**
     * Proverava ispravnost prosleđenog parametra pre izvršenja operacije.
     * <p>
     * Parametar mora biti instanca klase {@link Pacijent} i ne sme imati null
     * ili prazne vrednosti za obavezne atribute.
     * </p>
     *
     * @param param objekat koji se validira pre brisanja
     * @throws java.lang.Exception ako je parametar:
     * <ul>
     *   <li>null</li>
     *   <li>nije instanca klase Pacijent</li>
     *   <li>ima null ili prazne vrednosti obaveznih atributa (ime, prezime, email, tipPacijenta)</li>
     * </ul>
     */
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

    /**
     * Izvršava operaciju brisanja pacijenta iz baze podataka.
     * <p>
     * Ako dođe do izuzetka tokom brisanja, atribut uspeh se postavlja na false, a greška se loguje.
     * </p>
     *
     * @param objekat Objekat koji treba obrisati iz baze (očekuje se {@link Pacijent})
     * @param kljuc Dodatni parametar (nije korišćen u ovoj implementaciji)
     */
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
