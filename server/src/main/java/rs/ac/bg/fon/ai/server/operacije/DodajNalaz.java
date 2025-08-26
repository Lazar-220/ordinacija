/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar_specijalista;
import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.StavkaNalaza;

import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Predstavlja sistemsku operaciju dodavanja objekta tipa Nalaz u bazu.
 * 
 * Nasleđuje apstraktnu klasu {@link ApstraktnaGenerickaOperacija} i implementira metode
 * za validaciju parametara i izvršavanje same operacije dodavanja.
 * 
 * Operacija uspeva samo ako je prosleđeni objekat tipa {@link Nalaz} i
 * ako su svi obavezni atributi pravilno popunjeni.
 * 
 * @author Lazar Milosavljević
 */
public class DodajNalaz extends ApstraktnaGenerickaOperacija {

	/**
     * Informacija o uspešnosti operacije kao boolean.
     */
    private boolean uspeh=true;

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
     * Poziva podrazumevani konstruktor nadklase {@link ApstraktnaGenerickaOperacija}.
     * </p>
     */
    public DodajNalaz() {
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
    public DodajNalaz(boolean autoCommit) {
		super(autoCommit);
	}
    
    /**
     * Proverava preduslove za izvršenje operacije.
     * 
     * Preduslovi su:
     * <ul>
     *   <li>Parametar ne sme biti null.</li>
     *   <li>Parametar mora biti instanca klase {@link Nalaz}.</li>
     *   <li>Opis nalaza ne sme biti null niti prazan.</li>
     *   <li>Ukupna cena mora biti veća od 0.</li>
     *   <li>Fizijatar i pacijent ne smeju biti null.</li>
     *   <li>Lista stavki ne sme biti null niti prazna.</li>
     *   <li>Datum izdavanja ne sme biti null niti u budućnosti.</li>
     * </ul>
     * 
     * @param param Objekat koji se validira
     * 
     * @throws java.lang.NullPointerException ako je parametar null
     * @throws java.lang.ClassCastException ako parametar nije tipa Nalaz
     * @throws java.lang.IllegalArgumentException ako neki od obaveznih atributa ima nedozvoljenu vrednost
     */

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
          		((Nalaz)param).getDatumIzdavanja()==null||((Nalaz)param).getDatumIzdavanja().getTime()>(new Date()).getTime()) {
          	
             throw new IllegalArgumentException("DODAVANJE NALAZA NIJE USPELO, OBJEKAT UNET ZA PARAMETAR IMA NULL VREDNOSTI");
              
          }
    }

	/**
     * Izvršava operaciju dodavanja objekta u bazu.
     * 
     * Nakon dodavanja nalaza, operacija učitava poslednji dodat nalaz iz baze
     * kako bi dobila generisani ID i dodelila ga objektu. 
     * Zatim dodaje sve stavke povezane sa tim nalazom u bazu.
     * 
     * @param objekat Objekat koji se dodaje u bazu (tipa Nalaz)
     * @param kljuc Dodatni uslov koji se može koristiti pri izvršavanju (nije obavezan u ovoj operaciji)
     */
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
