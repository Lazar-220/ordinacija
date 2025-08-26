/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import java.util.logging.Level;
import java.util.logging.Logger;

import rs.ac.bg.fon.ai.server.repository.Repository;
import rs.ac.bg.fon.ai.server.repository.db.DbRepository;
import rs.ac.bg.fon.ai.server.repository.db.impl.DbRepositoryGeneric;

/**
 * ApstraktnaGenerickaOperacija predstavlja apstraktnu sistemsku operaciju
 * koja definiše osnovni okvir za izvršavanje različitih poslovnih operacija
 * u sistemu.
 * <p>
 * Klasa koristi šablon metoda (Template Method pattern) kojim obezbeđuje
 * tok izvršavanja operacije: provera preduslova, pokretanje transakcije,
 * izvršavanje konkretne operacije i potvrđivanje/poništavanje transakcije.
 * </p>
 * 
 * Konkretne sistemske operacije nasleđuju ovu klasu i implementiraju
 * apstraktne metode preduslovi(Object) i izvrsiOperaciju(Object, String).
 * 
 * @author 
 * Lazar Milosavljević
 */
public abstract class ApstraktnaGenerickaOperacija {
	/**
     * Objekat klase Repository koji omogućava pristup bazi podataka.
     */
    protected final Repository broker;

    /**
     * Oznaka za proveru da li se koristi automatsko potvrđivanje transakcija kao boolean.
     */
    protected final boolean autoCommit;

    /**
     * Podrazumevani konstruktor.
     * <p>
     * Kreira novu instancu i automatski uključuje auto-commit režim.
     * </p>
     */
    protected ApstraktnaGenerickaOperacija() {       //poziva parametarski konstruktor i prosledjuje true kao parametar
        this(true);
    }

    /**
     * Parametrizovani konstruktor.
     * <p>
     * Kreira novu instancu sa zadatom vrednošću za auto-commit režim.
     * </p>
     * 
     * @param autoCommit true ako transakcije treba automatski potvrđivati, false inače
     */
    protected ApstraktnaGenerickaOperacija(boolean autoCommit) {
        this.broker = new DbRepositoryGeneric();
        this.autoCommit = autoCommit;
    }
    
    /**
     * Šablon metoda koja definiše tok izvršavanja operacije.
     * <p>
     * Redosled je sledeći:
     * <ol>
     *   <li>provera preduslova</li>
     *   <li>pokretanje transakcije</li>
     *   <li>izvršavanje konkretne operacije</li>
     *   <li>potvrda transakcije (ako je autoCommit uključen)</li>
     *   <li>poništavanje transakcije u slučaju greške</li>
     * </ol>
     * 
     * 
     * @param objekat Objekat nad kojim se operacija izvršava
     * @param kljuc Dodatni parametar koji definiše uslove operacije
     * @throws Exception ako dođe do greške u bilo kojoj fazi izvršavanja
     */
    public final void izvrsi(Object objekat,String kljuc) throws Exception {
        try {
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat,kljuc);
            if (autoCommit==true) {
                potvrdiTransakciju();
            }
        } catch (Exception e) {
            ponistiTransakciju();
            throw e;
        } finally {
            //ugasiKonekciju();
        }
    }

    /**
     * Proverava da li su ispunjeni preduslovi za izvršavanje operacije.
     * <p>
     * Implementaciju definišu konkretne klase naslednice.
     * </p>
     * 
     * @param param Objekat koji se proverava
     * @throws Exception ako preduslovi nisu ispunjeni
     */
    protected abstract void preduslovi(Object param) throws Exception;
    
    /**
     * Pokreće novu transakciju nad bazom podataka.
     */
    private void zapocniTransakciju() {             //
        try {
            ((DbRepository)broker).connect();
        } catch (Exception ex) {
            Logger.getLogger(ApstraktnaGenerickaOperacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Izvršava konkretnu operaciju.
     * <p>
     * Implementaciju definišu konkretne klase naslednice.
     * </p>
     * 
     * @param objekat Objekat nad kojim se operacija izvršava
     * @param kljuc Dodatni parametar koji može odrediti uslove izvršavanja
     * @throws Exception ako dođe do greške tokom izvršavanja operacije
     */
    protected abstract void izvrsiOperaciju(Object objekat,String kljuc) throws Exception;
    
    /**
     * Potvrđuje transakciju u bazi podataka.
     */
    private void potvrdiTransakciju() {           //
        try {
            ((DbRepository)broker).commit();
        } catch (Exception ex) {
            Logger.getLogger(ApstraktnaGenerickaOperacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Poništava transakciju u slučaju greške.
     */
    private void ponistiTransakciju() {           //
        try {
            ((DbRepository)broker).rollback();
        } catch (Exception ex) {
            Logger.getLogger(ApstraktnaGenerickaOperacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Zatvara konekciju sa bazom podataka.
     */
    private void ugasiKonekciju() {
        try {
            ((DbRepository)broker).disconnect();
        } catch (Exception ex) {
            Logger.getLogger(ApstraktnaGenerickaOperacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
