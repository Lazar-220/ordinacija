/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;


import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Predstavlja sistemsku operaciju ažuriranja (izmene) objekta tipa {@link Specijalizacija} u bazi.
 * 
 * Nasleđuje apstraktnu klasu {@link ApstraktnaGenerickaOperacija} i implementira metode
 * za validaciju parametara i izvršavanje same operacije izmene.
 * 
 * Operacija uspeva samo ako je prosleđeni objekat tipa {@link Specijalizacija} i
 * ako su svi obavezni atributi pravilno popunjeni.
 * 
 * Takođe, validira se da objekat sa istim nazivom i institucijom već ne postoji u bazi.
 * 
 * @author Lazar Milosavljević
 */
public class IzmeniSpecijalizaciju extends ApstraktnaGenerickaOperacija {

	/**
     * Podrazumevani konstruktor.
     * Kreira novu instancu objekta ove klase.
     * <p>
     * Poziva podrazumevani konstruktor nadklase {@link ApstraktnaGenerickaOperacija}.
     * </p>
     */
    public IzmeniSpecijalizaciju() {
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
    public IzmeniSpecijalizaciju(boolean autoCommit) {
        super(autoCommit);
    }


    /**
     * Proverava preduslove za izvršenje operacije.
     *
     * Preduslovi su:
     * <ul>
     *   <li>Parametar ne sme biti null.</li>
     *   <li>Parametar mora biti instanca klase {@link Specijalizacija}.</li>
     *   <li>Naziv i institucija specijalizacije ne smeju biti null niti prazni.</li>
     *   <li>Specijalizacija sa istim nazivom i institucijom ne sme već postojati u bazi.</li>
     * </ul>
     *
     * @param param Objekat koji se validira
     *
     * @throws java.lang.NullPointerException ako je parametar null
     * @throws java.lang.ClassCastException ako parametar nije tipa {@link Specijalizacija}
     * @throws java.lang.IllegalArgumentException ako neki od obaveznih atributa ima nedozvoljenu vrednost 
     *                                            ili ako specijalizacija već postoji u bazi
     */
	@Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param == null) {
    		
            throw new NullPointerException("IZMENA SPECIJALIZACIJE NIJE USPELA, OBJEKAT UNET ZA PARAMETAR JE NULL");
              
      	}
          if (!(param instanceof Specijalizacija)) {
              
             throw new ClassCastException("IZMENA SPECIJALIZACIJE NIJE USPELA, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
              
          }
          
          if(((Specijalizacija)param).getNaziv()==null||((Specijalizacija)param).getNaziv().isEmpty()||
          		((Specijalizacija)param).getInstitucija()==null||((Specijalizacija)param).getInstitucija().isEmpty()) {
          	
             throw new IllegalArgumentException("IZMENA SPECIJALIZACIJE NIJE USPELA, OBJEKAT UNET ZA PARAMETAR IMA NULL VREDNOSTI");
              
          }
          List<Specijalizacija>lista=broker.getAll(new Specijalizacija(), null);
          for(Specijalizacija s:lista) {
        	  if ( ((Specijalizacija)param).getNaziv().equals(s.getNaziv()) 
        			     && ((Specijalizacija)param).getInstitucija().equals(s.getInstitucija()) ){
        		  throw new IllegalArgumentException("IZMENA SPECIJALIZACIJE NIJE USPELA, OBJEKAT VEC POSTOJI U BAZI");
                  
        	  }
          }

    }

	/**
     * Izvršava operaciju izmene objekta u bazi.
     * 
     * @param objekat Objekat koji se ažurira u bazi (tipa {@link Specijalizacija})
     * @param kljuc Dodatni uslov koji se može koristiti pri izvršavanju (nije obavezan u ovoj operaciji)
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            broker.edit(objekat);
        } catch (Exception ex) {
            Logger.getLogger(IzmeniSpecijalizaciju.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
