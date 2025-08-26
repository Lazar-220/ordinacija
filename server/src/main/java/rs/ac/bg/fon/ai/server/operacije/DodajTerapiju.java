/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;


import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Predstavlja sistemsku operaciju dodavanja objekta tipa Terapija u bazu.
 * 
 * Nasleđuje apstraktnu klasu {@link ApstraktnaGenerickaOperacija} i implementira metode
 * za validaciju parametara i izvršavanje same operacije dodavanja.
 * 
 * Operacija uspeva samo ako je prosleđeni objekat tipa {@link Terapija} i
 * ako su svi obavezni atributi pravilno popunjeni.
 * 
 * @author Lazar Milosavljević
 */
public class DodajTerapiju extends ApstraktnaGenerickaOperacija{

	
	
	/**
     * Podrazumevani konstruktor.
     * Kreira novu instancu ove klase.
     * <p>
     * Poziva podrazumevani konstruktor nadklase {@link ApstraktnaGenerickaOperacija}.
     * </p>
     */
    public DodajTerapiju() {    
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
	public DodajTerapiju(boolean autoCommit) {   
		super(autoCommit);
	}


	/**
     * Proverava preduslove za izvršenje operacije.
     * 
     * Preduslovi su:
     * <ul>
     *   <li>Parametar ne sme biti null.</li>
     *   <li>Parametar mora biti instanca klase {@link Terapija}.</li>
     *   <li>Naziv terapije ne sme biti null niti prazan.</li>
     *   <li>Cena mora biti veća od 0.</li>
     *   <li>Opis terapije ne sme biti null niti prazan.</li>
     * </ul>
     * 
     * @param param Objekat koji se validira
     * 
     * @throws java.lang.NullPointerException ako je parametar null
     * @throws java.lang.ClassCastException ako parametar nije tipa Terapija
     * @throws java.lang.IllegalArgumentException ako neki od obaveznih atributa ima nedozvoljenu vrednost
     */
	@Override
    protected void preduslovi(Object param)  throws Exception{
		
        if(param == null) {
    		
            throw new NullPointerException("DODAVANJE TERAPIJE NIJE USPELO, OBJEKAT UNET ZA PARAMETAR JE NULL");
              
      	}
          if (!(param instanceof Terapija)) {
              
             throw new ClassCastException("DODAVANJE TERAPIJE NIJE USPELO, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
              
          }
          
          if(((Terapija)param).getNaziv()==null||((Terapija)param).getNaziv().isEmpty()||
          		((Terapija)param).getCena()<=0||
          		((Terapija)param).getOpis()==null||((Terapija)param).getOpis().isEmpty()) {
          	
             throw new IllegalArgumentException("DODAVANJE TERAPIJE NIJE USPELO, OBJEKAT UNET ZA PARAMETAR IMA NULL VREDNOSTI");
              
          }

    }

	/**
     * Izvršava operaciju dodavanja objekta u bazu.
     * 
     * @param objekat Objekat koji se dodaje u bazu (tipa Terapija)
     * @param kljuc Dodatni uslov koji se može koristiti pri izvršavanju (nije obavezan u ovoj operaciji)
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            broker.add(objekat);
        } catch (Exception ex) {
            Logger.getLogger(DodajTerapiju.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
