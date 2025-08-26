/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;

import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Predstavlja konkretnu operaciju logovanja fizijatra na sistem.
 * 
 * Nasleđuje apstraktnu klasu {@link ApstraktnaGenerickaOperacija} i implementira
 * metode za proveru preduslova i izvršavanje operacije logovanja.
 * 
 * Operacija podrazumeva:
 * <ul>
 *   <li>Proveru da li je prosleđeni objekat validan i odgovarajućeg tipa</li>
 *   <li>Proveru da li prosleđeni fizijatar postoji u bazi podataka</li>
 *   <li>Postavljanje prijavljenog fizijatra u atribut klase ukoliko je logovanje uspešno</li>
 * </ul>
 *
 * @author 
 * Lazar Milosavljević
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

	/**
     * Objekat klase {@link Fizijatar} koji se koristi za čuvanje rezultata operacije logovanja.
     */
    Fizijatar fizijatar;

    /**
     * Vraća fizijatra koji je prijavljen na sistem.
     * 
     * @return fizijatar koji je uspešno prijavljen, ili null ukoliko logovanje nije bilo uspešno
     */
    public Fizijatar getFizijatar() {
        return fizijatar;
    }
    
    

    /**
     * Konstruktor koji poziva konstruktor nadklase bez parametara.
     */
    public LoginOperacija() {
        super();
    }

    /**
     * Konstruktor koji omogućava definisanje da li će se transakcije automatski potvrđivati.
     * 
     * @param autoCommit true ako transakcije treba automatski potvrđivati, false inače
     */
    public LoginOperacija(boolean autoCommit) {
        super(autoCommit);
    }


    /**
     * Proverava preduslove za izvršavanje operacije logovanja.
     * 
     * Preduslovi su:
     * <ul>
     *   <li>Parametar ne sme biti null</li>
     *   <li>Parametar mora biti instanca klase {link Fizijatar}</li>
     *   <li>Polja korisnickoIme i sifra ne smeju biti null ili prazna</li>
     * </ul>
     *
     * @param param objekat koji se proverava
     * @throws java.lang.NullPointerException ako je parametar null
     * @throws java.lang.ClassCastException ako prosleđeni parametar nije instanca klase {@link Fizijatar}
     * @throws java.lang.IllegalArgumentException ako korisnickoIme ili sifra imaju nedozvoljene vrednosti
     */
	@Override
    protected void preduslovi(Object param) throws Exception {
        
		if(param == null) {
        	
        	throw new NullPointerException("PRIJAVLJIVANJE FIZIJATRA NA SISTEM NIJE USPELO, OBJEKAT UNET ZA PARAMETAR JE NULL");
            
        }
			
          if (!(param instanceof Fizijatar)) {
              
             throw new ClassCastException("PRIJAVLJIVANJE FIZIJATRA NA SISTEM NIJE USPELO, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
              
          }
          
          if(((Fizijatar)param).getKorisnickoIme()==null||((Fizijatar)param).getKorisnickoIme().isEmpty()||
          		((Fizijatar)param).getSifra()==null||((Fizijatar)param).getSifra().isEmpty()) {
          	
             throw new IllegalArgumentException("PRIJAVLJIVANJE FIZIJATRA NA SISTEM NIJE USPELO, OBJEKAT UNET ZA PARAMETAR IMA NULL VREDNOSTI");
              
          }
    }

    
	/**
     * Izvršava operaciju logovanja fizijatra na sistem.
     * 
     * Poredi prosleđenog fizijatra sa svim fizijatrima iz baze i ako postoji odgovarajući
     * fizijatar, postavlja ga kao prijavljenog.
     * 
     * @param param objekat tipa {@link Fizijatar} koji se pokušava prijaviti
     * @param kljuc dodatni parametar (nije korišćen u ovoj implementaciji)
     * @throws java.lang.IllegalArgumentException ako fizijatar ne postoji
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception{
       
            List<Fizijatar>sviFizijatri=broker.getAll((Object)param, null);
            
            for(Fizijatar f:sviFizijatri){
                if(f.equals((Fizijatar)param)){
                    fizijatar=f;
                    return;
                }
            }
            fizijatar=null;
            
            throw new IllegalArgumentException();
            


    }

    
    
}
