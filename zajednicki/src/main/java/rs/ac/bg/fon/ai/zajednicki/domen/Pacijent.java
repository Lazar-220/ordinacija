/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import rs.ac.bg.fon.ai.zajednicki.domen.Enum.Pol;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.StarosnaDob;

/**
 * Predstavlja pacijenta u sistemu.
 * 
 * Sadrži informacije o ID-u pacijenta, imenu, prezimenu, email adresi i tipu pacijenta.
 * Tip pacijenta je dodatna klasifikacija koja uključuje pol i starosnu dob.
 * 
 * Ova klasa implementira interfejs ApstraktniDomenskiObjekat, što omogućava komunikaciju sa bazom podataka.
 * 
 * @author Lazar Milosavljević
 */
public class Pacijent implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator pacijenta.
     */
    private int idPacijent;

    /**
     * Ime pacijenta.
     */
    private String ime;

    /**
     * Prezime pacijenta.
     */
    private String prezime;

    /**
     * Email adresa pacijenta.
     */
    private String email;

    /**
     * Tip pacijenta koji uključuje pol i starosnu dob.
     */
    private TipPacijenta tipPacijenta;

    /**
     * Podrazumevani konstruktor.
     */
    public Pacijent() {
    }

    /**
     * Parametrizovani konstruktor. Inicijalizuje pacijenta sa zadatim vrednostima.
     * 
     * @param idPacijent Jedinstveni identifikator pacijenta. Ne sme biti manji od 0.
     * @param ime Ime pacijenta. Ne sme biti null ni prazan string.
     * @param prezime Prezime pacijenta. Ne sme biti null ni prazan string.
     * @param email Email pacijenta. Ne sme biti null ni prazan string.
     * @param tipPacijenta Tip pacijenta. Ne sme biti null.
     */
    public Pacijent(int idPacijent, String ime, String prezime, String email, TipPacijenta tipPacijenta) {
//        this.idPacijent = idPacijent;
//        this.ime = ime;
//        this.prezime = prezime;
//        this.email = email;
//        this.tipPacijenta = tipPacijenta;
        setIdPacijent(idPacijent);
        setIme(ime);
        setPrezime(prezime);
        setEmail(email);
        setTipPacijenta(tipPacijenta);
    }

    /**
     * Vraća ID pacijenta.
     * 
     * @return ID pacijenta kao int.
     */
    public int getIdPacijent() {
        return idPacijent;
    }

    /**
     * Postavlja ID pacijenta.
     * 
     * @param idPacijent ID pacijenta. Ne sme biti manji od 0.
     * 
     * @throws java.lang.IllegalArgumentException ako je ID manji od 0.
     */
    public void setIdPacijent(int idPacijent) {
        if(idPacijent < 0) {
            throw new IllegalArgumentException("id pacijenta ne sme biti manji od 0");
        }
        this.idPacijent = idPacijent;
    }

    /**
     * Vraća ime pacijenta.
     * 
     * @return ime kao String.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime pacijenta.
     * 
     * @param ime Ime pacijenta. Ne sme biti null ni prazan string.
     * 
     * @throws java.lang.NullPointerException ako je ime null.
     * @throws java.lang.IllegalArgumentException ako je ime prazan string.
     */
    public void setIme(String ime) {
        if(ime == null) {
            throw new NullPointerException("ime pacijenta ne sme biti null");
        }
        if(ime.isEmpty()) {
            throw new IllegalArgumentException("ime pacijenta ne sme biti prazno");
        }
        this.ime = ime;
    }

    /**
     * Vraća prezime pacijenta.
     * 
     * @return prezime kao String.
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime pacijenta.
     * 
     * @param prezime Prezime pacijenta. Ne sme biti null ni prazan string.
     * 
     * @throws java.lang.NullPointerException ako je prezime null.
     * @throws java.lang.IllegalArgumentException ako je prezime prazan string.
     */
    public void setPrezime(String prezime) {
        if(prezime == null) {
            throw new NullPointerException("prezime pacijenta ne sme biti null");
        }
        if(prezime.isEmpty()) {
            throw new IllegalArgumentException("prezime pacijenta ne sme biti prazno");
        }
        this.prezime = prezime;
    }

    /**
     * Vraća email adresu pacijenta.
     * 
     * @return email kao String.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Postavlja email adresu pacijenta.
     * 
     * @param email Email pacijenta. Ne sme biti null ni prazan string.
     * 
     * @throws java.lang.NullPointerException ako je email null.
     * @throws java.lang.IllegalArgumentException ako je email prazan string.
     */
    public void setEmail(String email) {
        if(email == null) {
            throw new NullPointerException("email pacijenta ne sme biti null");
        }
        if(email.isEmpty()) {
            throw new IllegalArgumentException("email pacijenta ne sme biti prazan");
        }
        this.email = email;
    }

    /**
     * Vraća tip pacijenta.
     * 
     * @return Tip pacijenta.
     */
    public TipPacijenta getTipPacijenta() {
        return tipPacijenta;
    }

    /**
     * Postavlja tip pacijenta.
     * 
     * @param tipPacijenta Tip pacijenta. Ne sme biti null.
     * 
     * @throws java.lang.NullPointerException ako je tip pacijenta null.
     */
    public void setTipPacijenta(TipPacijenta tipPacijenta) {
        if(tipPacijenta == null) {
            throw new NullPointerException("tip pacijenta ne sme biti null");
        }
        this.tipPacijenta = tipPacijenta;
    }

    /**
     * Računa hashCode na osnovu email adrese.
     * 
     * @return Hash code vrednost.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * Poredi dva pacijenta na osnovu email adrese.
     * 
     * @param obj Objekat sa kojim se poredi.
     * 
     * @return 
     * <ul>
     * <li><b>true</b> - ako su email adrese iste.</li>
     * <li><b>false</b> - u suprotnom.</li>
     * </ul>
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Pacijent other = (Pacijent) obj;
        return Objects.equals(email, other.email);
    }

    /**
     * Vraća pacijenta u formatu "ime prezime".
     * 
     * @return String reprezentacija pacijenta.
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    
    @Override
    public String vratiNazivTabele() {
        return "pacijent";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat>lista=new ArrayList<>();
        while(rs.next()){
            int id=rs.getInt("pacijent.idPacijent");
            String ime=rs.getString("pacijent.ime");
            String prezime=rs.getString("pacijent.prezime");
            String email=rs.getString("pacijent.email");
            TipPacijenta tp=new TipPacijenta(rs.getInt("tip_pacijenta.idTipPacijenta"), StarosnaDob.valueOf(rs.getString("tip_pacijenta.starosnaDob")), Pol.valueOf(rs.getString("tip_pacijenta.pol")));
            Pacijent p=new Pacijent(id, ime, prezime, email, tp);
            lista.add(p);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,email,tipPacijenta_id";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+email+"',"+tipPacijenta.getIdTipPacijenta();
    
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "pacijent.idPacijent="+idPacijent;
    }

//    @Override
//    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime= '"+ime+"',prezime= '"+prezime+"',email= '"+email+"',tipPacijenta_id= "+tipPacijenta.getIdTipPacijenta();
    
    }
    
}
