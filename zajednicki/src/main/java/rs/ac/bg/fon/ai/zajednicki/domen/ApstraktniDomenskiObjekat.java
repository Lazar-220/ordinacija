/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domen;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;
/**
 * Predstavlja apstraktan domenski objekat koji sve domenske klase treba da implementiraju
 * kako bi obezbedile osnovne metode za komunikaciju sa bazom podataka.
 * 
 * Ovaj interfejs definiše osnovne metode potrebne za rad sa CRUD operacijama
 * i konverziju rezultata iz baze u listu objekata.
 * 
 * Implementiraju ga klase: 
 * <ul>
 * <li>Fizijatar_specijalista</li>
 * <li>Fizijatar</li>
 * <li>Nalaz</li>
 * <li>Pacijent</li>
 * <li>Specijalizacija</li>
 * <li>StavkaNalaza</li>
 * <li>Terapija</li>
 * <li>TipPacijenta</li>
 * </ul>
 *
 * @author Lazar Milosavljević
 */
public interface ApstraktniDomenskiObjekat extends Serializable {
    
	/**
	 * Vraća naziv tabele iz bazi podataka koja odgovara ovoj klasi.
	 * 
	 * @return Naziv tabele kao String.
	 */
    public String vratiNazivTabele();
    
    /**
     * Pravi i vraća listu domenskih objekata na osnovu podataka iz ResultSet-a.
     * 
     * @param rs ResultSet koji sadrži rezultate izvršenog sql upita.
     * @return Lista domenskih objekata (instanci klase koja implementira interfejs)
     * @throws java.lang.Exception ako dođe do greške prilikom obrade ResultSet-a.
     */
    public List<ApstraktniDomenskiObjekat>vratiListu(ResultSet rs) throws Exception;
    /**
     * Vraća nazive kolone tabele iz baze podataka koja odgovara ovoj klasi.
     * 
     * @return Nazivi kolona razdvojeni zarezima kao String.
     */
    public String vratiKoloneZaUbacivanje();
    
    /**
     * Vraća vrednosti koje će se koristiti za ubacivanje u bazu podataka.
     * 
     * @return Vrednosti razdvojene zarezima kao String.
     */
    public String vratiVrednostiZaUbacivanje();
    
    /**
     * Vraća primarni ključ tabele iz baze podataka koja odgovara ovoj klasi u formatu:
     * "imeTabele.primarniKljuc=####" npr. nalaz.idNalaz=3
     * 
     * Primarni ključ predstavlja sql uslov za where klauzulu.
     * 
     * @return Primarni ključ kao String.
     */
    public String vratiPrimarniKljuc();
    //public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception;
    
    /**
     * Vraća parove kolona i njihovih vrednosti, koje će se koristiti za izmenu u bazi podataka, u formatu:
     * "imeTabele.nazivKolone1=####,imeTabele.nazivKolone2=####" npr. terapija.naziv='Kineziterapija',terapija.cena=3000.00
     * 
     * @return Parovi kolona i njihovih vrednosti razdvojeni zarezima kao String.
     */
    public String vratiVrednostiZaIzmenu();
}
