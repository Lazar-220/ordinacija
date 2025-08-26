/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar_specijalista;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;

import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Predstavlja sistemsku operaciju dodavanja objekta tipa Fizijatar_specijalista u bazu.
 * 
 * Nasleđuje apstraktnu klasu {@link ApstraktnaGenerickaOperacija} i implementira metode
 * za validaciju parametara i izvršavanje same operacije dodavanja.
 * 
 * Operacija uspeva samo ako je prosleđeni objekat tipa {@link Fizijatar_specijalista} i
 * ako su svi obavezni atributi pravilno popunjeni.
 * 
 * @author Lazar Milosavljević
 */
public class DodajFizijatarSpecijalizacije extends ApstraktnaGenerickaOperacija{
	/**
     * Informacija o uspešnosti operacije.
     */
    private boolean uspeh = true;

    /**
     * Vraća informaciju da li je operacija uspešno izvršena.
     * 
     * @return true ako je operacija uspešno izvršena, false inače
     */
    public boolean isUspeh() {
        return uspeh;
    }

//    public void setUspeh(boolean uspeh) {
//        this.uspeh = uspeh;
//    }
    
    /**
     * Podrazumevani konstruktor.
     * Kreira novu instancu ove klase.
     * <p>
     * Poziva parametarski konstruktor i prosleđuje true kao parametar.
     * </p>
     */
    public DodajFizijatarSpecijalizacije() {
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
    public DodajFizijatarSpecijalizacije(boolean autoCommit) {
        super(autoCommit);
    }

    /**
     * Proverava preduslove za izvršenje operacije.
     * 
     * Preduslovi su:
     * <ul>
     *   <li>Parametar ne sme biti null.</li>
     *   <li>Parametar mora biti instanca klase {@link Fizijatar_specijalista}.</li>
     *   <li>Fizijatar ne sme biti null.</li>
     *   <li>Datum izdavanja ne sme biti null niti u budućnosti.</li>
     *   <li>Specijalizacija ne sme biti null.</li>
     * </ul>
     * 
     * @param param Objekat koji se validira
     * 
     * @throws java.lang.NullPointerException ako je parametar null
     * @throws java.lang.ClassCastException ako parametar nije tipa Fizijatar_specijalista
     * @throws java.lang.IllegalArgumentException ako neki od obaveznih atributa ima null vrednost ili je datum izdavanja u budućnosti
     */
	@Override
    protected void preduslovi(Object param) throws Exception {
         
         
         if(param == null) {
     		
             throw new NullPointerException("DODAVANJE FIZIJATAR_SPECIJALIZACIJE NIJE USPELO, OBJEKAT UNET ZA PARAMETAR JE NULL");
               
       	}
           if (!(param instanceof Fizijatar_specijalista)) {
               
              throw new ClassCastException("DODAVANJE FIZIJATAR_SPECIJALIZACIJE NIJE USPELO, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
               
           }
           
           if(((Fizijatar_specijalista)param).getFizijatar()==null||
           		((Fizijatar_specijalista)param).getDatumIzdavanja()==null||((Fizijatar_specijalista)param).getDatumIzdavanja().getTime()>(new Date()).getTime()||
           		((Fizijatar_specijalista)param).getSpecijalizacija()==null) {
           	
              throw new IllegalArgumentException("DODAVANJE FIZIJATAR_SPECIJALIZACIJE NIJE USPELO, OBJEKAT UNET ZA PARAMETAR IMA NULL VREDNOSTI");
               
           }
    }

    
	/**
     * Izvršava operaciju dodavanja objekta u bazu.
     * 
     * @param objekat Objekat koji se dodaje u bazu
     * @param kljuc Dodatni uslov koji se može koristiti pri izvršavanju (nije obavezan u ovoj operaciji)
     */
	@Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            broker.add(objekat);
        } catch (Exception ex) {
            uspeh=false;
            Logger.getLogger(DodajSpecijalizaciju.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
