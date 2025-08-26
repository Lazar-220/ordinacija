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
 * Klasa ObrisiTipPacijenta predstavlja konkretnu sistemsku operaciju koja služi za 
 * brisanje {@link TipPacijenta} iz sistema.
 * <p>
 * Nasleđuje apstraktnu klasu {@link ApstraktnaGenerickaOperacija} i implementira njene 
 * apstraktne metode za definisanje preduslova i izvršavanje same operacije.
 * </p>
 *
 * @author 
 * Lazar Milosavljević
 */
public class ObrisiTipPacijenta extends ApstraktnaGenerickaOperacija {

	
    
	/**
     * Podrazumevani konstruktor.
     * Kreira novu instancu objekta ove klase.
     * <p>
     * Poziva podrazumevani konstruktor nadklase {@link ApstraktnaGenerickaOperacija}.
     * </p>
     */
    public ObrisiTipPacijenta() {
        super();
    }

    /**
     * Parametrizovani konstruktor.
     * Omogućava definisanje da li će operacija koristiti auto-commit režim.
     * <p>
     * Poziva konstruktor nadklase {@link ApstraktnaGenerickaOperacija} sa prosleđenom
     * vrednošću parametra <i>autoCommit</i>.
     * </p>
     *
     * @param autoCommit true ako je potrebno automatsko potvrđivanje transakcije, false inače
     */
    public ObrisiTipPacijenta(boolean autoCommit) {
        super(autoCommit);
    }

    /**
     * Proverava ispravnost prosleđenog parametra pre izvršenja operacije.
     * <p>
     * Parametar mora biti instanca klase {@link TipPacijenta} i ne sme imati null
     * vrednosti za obavezne atribute.
     * </p>
     *
     * @param param objekat koji se validira pre brisanja
     * @throws java.lang.Exception ako je parametar:
     * <ul>
     *   <li>null</li>
     *   <li>nije instanca klase TipPacijenta</li>
     *   <li>ima null vrednosti obaveznih atributa (pol, starosnaDob)</li>
     * </ul>
     */
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
	/**
     * Izvršava operaciju brisanja tipa pacijenta iz baze podataka.
     * <p>
     * Ako dođe do izuzetka tokom brisanja, greška se loguje.
     * </p>
     *
     * @param objekat Objekat koji treba obrisati iz baze (očekuje se {@link TipPacijenta})
     * @param kljuc Dodatni parametar (nije korišćen u ovoj implementaciji)
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            broker.delete(objekat);
        } catch (Exception ex) {
            Logger.getLogger(ObrisiTipPacijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
