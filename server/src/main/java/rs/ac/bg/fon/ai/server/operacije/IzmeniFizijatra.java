/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;

import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Predstavlja sistemsku operaciju ažuriranja (izmene) objekta tipa Fizijatar u bazi.
 * 
 * Nasleđuje apstraktnu klasu {@link ApstraktnaGenerickaOperacija} i implementira metode
 * za validaciju parametara i izvršavanje same operacije izmene.
 * 
 * Operacija uspeva samo ako je prosleđeni objekat tipa {@link Fizijatar} i
 * ako su svi obavezni atributi pravilno popunjeni.
 * 
 * @author Lazar Milosavljević
 */
public class IzmeniFizijatra extends ApstraktnaGenerickaOperacija {

	
	/**
     * Podrazumevani konstruktor.
     * Kreira novu instancu objekta ove klase.
     * <p>
     * Poziva podrazumevani konstruktor nadklase {@link ApstraktnaGenerickaOperacija}.
     * </p>
     */
    public IzmeniFizijatra() {
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
    public IzmeniFizijatra(boolean autoCommit) {
        super(autoCommit);
    }


    /**
     * Proverava preduslove za izvršenje operacije.
     *
     * Preduslovi su:
     * <ul>
     *   <li>Parametar ne sme biti null.</li>
     *   <li>Parametar mora biti instanca klase {@link Fizijatar}.</li>
     *   <li>Ime, prezime, korisničko ime i šifra ne smeju biti null niti prazni.</li>
     * </ul>
     *
     * @param param Objekat koji se validira
     *
     * @throws java.lang.NullPointerException ako je parametar null
     * @throws java.lang.ClassCastException ako parametar nije tipa Fizijatar
     * @throws java.lang.IllegalArgumentException ako neki od obaveznih atributa ima nedozvoljenu vrednost
     */
	@Override
    protected void preduslovi(Object param) throws Exception {
		if(param == null) {
			
	        throw new NullPointerException("AZURIRANJE FIZIJATRA NIJE USPELO, OBJEKAT UNET ZA PARAMETAR JE NULL");
	          
	  	}
	      if (!(param instanceof Fizijatar)) {
	          
	         throw new ClassCastException("AZURIRANJE FIZIJATRA NIJE USPELO, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
	          
	      }
	      
	      if(((Fizijatar)param).getIme()==null||((Fizijatar)param).getIme().isEmpty()||
	        		((Fizijatar)param).getPrezime()==null||((Fizijatar)param).getPrezime().isEmpty()||
	        		((Fizijatar)param).getKorisnickoIme()==null||((Fizijatar)param).getKorisnickoIme().isEmpty()||
	        		((Fizijatar)param).getSifra()==null||((Fizijatar)param).getSifra().isEmpty()) {
	        	
	           throw new IllegalArgumentException("BRISANJE PACIJENTA NIJE USPELO, OBJEKAT UNET ZA PARAMETAR IMA NULL VREDNOSTI");
	            
	        }
    }
	
	

	/**
     * Izvršava operaciju izmene objekta u bazi.
     * 
     * @param objekat Objekat koji se ažurira u bazi (tipa Fizijatar)
     * @param kljuc Dodatni uslov koji se može koristiti pri izvršavanju (nije obavezan u ovoj operaciji)
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            broker.edit(objekat);
        } catch (Exception ex) {
            Logger.getLogger(IzmeniFizijatra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
