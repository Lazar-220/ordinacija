/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.StavkaNalaza;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa UcitajStavkeNalaza predstavlja konkretnu sistemsku operaciju koja služi za 
 * učitavanje svih {@link StavkaNalaza} objekata povezanih sa datim {@link Nalaz}.
 * <p>
 * Nasleđuje apstraktnu klasu {@link ApstraktnaGenerickaOperacija} i implementira njene 
 * apstraktne metode za definisanje preduslova i izvršavanje same operacije.
 * </p>
 *
 * @author 
 * Lazar Milosavljević
 */
public class UcitajStavkeNalaza extends ApstraktnaGenerickaOperacija {

	/**
     * Lista svih stavki nalaza učitanih iz baze podataka.
     */
    private List<StavkaNalaza>lista=new ArrayList<>();
    
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
        	
        	throw new NullPointerException("UCITAVANJE STAVKI NALAZA NIJE USPELO, OBJEKAT UNET ZA PARAMETAR JE NULL");
            
        }
    	if (!(param instanceof Nalaz)) {
            
    		throw new ClassCastException("UCITAVANJE STAVKI NALAZA NIJE USPELO, POGRESAN TIP OBJEKTA JE UNET ZA PARAMETAR");
            
        }
    }
    

    /**
     * Podrazumevani konstruktor.
     * Kreira novu instancu objekta ove klase.
     * <p>
     * Poziva podrazumevani konstruktor nadklase {@link ApstraktnaGenerickaOperacija}.
     * </p>
     */
    public UcitajStavkeNalaza() {
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
	public UcitajStavkeNalaza(boolean autoCommit) {
		super(autoCommit);
	}


	/**
     * Izvršava operaciju učitavanja stavki nalaza iz baze podataka.
     * <p>
     * Ako {@link Nalaz} ima ID vrednost 0, učitavaju se sve stavke nalaza.
     * U suprotnom, učitavaju se samo stavke koje pripadaju tom nalazu.
     * Ako dođe do izuzetka tokom učitavanja, greška se loguje, a lista ostaje prazna.
     * </p>
     *
     * @param objekat Objekat koji definiše uslove učitavanja (očekuje se {@link Nalaz})
     * @param kljuc Dodatni parametar (nije korišćen u ovoj implementaciji)
     */
	@Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            if(((Nalaz)objekat).getIdNalaz()==0){

                String uslov=" join terapija on terapija_id=idTerapija join nalaz on nalaz_id=idNalaz order by rb asc";
                lista=broker.getAll(new StavkaNalaza(), uslov);
                return;
            }
            String uslov=" join terapija on terapija_id=idTerapija join nalaz on nalaz_id=idNalaz where nalaz_id="+ ((Nalaz)objekat).getIdNalaz()+" order by rb asc";

            lista=broker.getAll(new StavkaNalaza(), uslov);
        } catch (Exception ex) {
            Logger.getLogger(UcitajStavkeNalaza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	/**
     * Vraća listu svih učitanih stavki nalaza.
     *
     * @return lista objekata tipa {@link StavkaNalaza}
     */
    public List<StavkaNalaza> getLista() {
        return lista;
    }

//    public void setLista(List<StavkaNalaza> lista) {
//        this.lista = lista;
//    }

    
}
