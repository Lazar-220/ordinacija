/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.komunikacija;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar_specijalista;
import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import rs.ac.bg.fon.ai.zajednicki.domen.StavkaNalaza;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import rs.ac.bg.fon.ai.zajednicki.komunikacija.Odgovor;
import rs.ac.bg.fon.ai.zajednicki.komunikacija.Operacija;
import rs.ac.bg.fon.ai.zajednicki.komunikacija.Posiljalac;
import rs.ac.bg.fon.ai.zajednicki.komunikacija.Primalac;
import rs.ac.bg.fon.ai.zajednicki.komunikacija.Zahtev;
import rs.ac.bg.fon.ai.klijent.forme.PrikazFizijataraForma;
import rs.ac.bg.fon.ai.klijent.forme.PrikazNalazaForma;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author milos
 */
public class Komunikacija {
    private static Komunikacija instance;
    private Socket s;
    private Posiljalac posiljalac;
    private Primalac primalac;
    
    
    public static Komunikacija getInstance() {
        if(instance==null){
            instance=new Komunikacija();
        }
        return instance;
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    
    public Posiljalac getPosiljalac() {
        return posiljalac;
    }

    public void setPosiljalac(Posiljalac posiljalac) {
        this.posiljalac = posiljalac;
    }

    public Primalac getPrimalac() {
        return primalac;
    }

    public void setPrimalac(Primalac primalac) {
        this.primalac = primalac;
    }
    

    
    private Komunikacija() {
    }
    
    public void Konekcija(){
        try {
            s=new Socket("localhost", 9000);
            posiljalac=new Posiljalac(s);
            primalac=new Primalac(s);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Server nije povezan");
        }
    }

    public Fizijatar login(String username, String password) throws Exception {
        Fizijatar ulogovani=new Fizijatar(-1, null, null, username, password);
        Zahtev zahtev=new Zahtev(Operacija.LOGIN, (Fizijatar)ulogovani);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor=(Odgovor) primalac.primi();
        ulogovani= (Fizijatar) odgovor.getOdgovor();
        if(ulogovani!=null){   
            if(ulogovani.getIme()==null){
                throw new IllegalArgumentException();
            }
            return ulogovani;
        }else{
            
            throw new NullPointerException();
        }
    }

    public List<Terapija> vratiTerapije(){
        List<Terapija>lista=new ArrayList<>();
        Zahtev z=new Zahtev(Operacija.UCITAJ_TERAPIJE, null);
        posiljalac.posalji(z);
        Odgovor odgovor=(Odgovor) primalac.primi();
        lista=(List<Terapija>) odgovor.getOdgovor();
        return lista;
    }

    public boolean obrisiTerapiju(Terapija t){
        Zahtev z=new Zahtev(Operacija.OBRISI_TERAPIJU, (Terapija)t);
        posiljalac.posalji(z);
        
        Odgovor o= (Odgovor) primalac.primi();
        if((boolean)o.getOdgovor()==true){
            return true;
        }
        return false;
    }

    public boolean dodajTerapiju(Terapija t){
        Zahtev z=new Zahtev(Operacija.DODAJ_TERAPIJU, t);
        posiljalac.posalji(z);
        
        Odgovor o= (Odgovor) primalac.primi();
        if((boolean)o.getOdgovor()==true){
            return true;
        }
        return false;
    }

    public boolean izmeniTerapiju(Terapija t1) {
        Zahtev z=new Zahtev(Operacija.IZMENI_TERAPIJU, t1);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        return (boolean) o.getOdgovor();
    }


    public List<TipPacijenta> vratiTipovePacijenata() {
        Zahtev z=new Zahtev(Operacija.UCITAJ_TIPOVE_PACIJENATA, null);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        List<TipPacijenta>lista=(List<TipPacijenta>) o.getOdgovor();
        return lista;
    }

    public void obrisiTipPacijenta(TipPacijenta t1) throws Exception {
        Zahtev z=new Zahtev(Operacija.OBRISI_TIP_PACIJENTA, t1);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){
            throw new Exception();
        }
    }

    public void dodajTipPacijenta(TipPacijenta tp) throws Exception {
        Zahtev z=new Zahtev(Operacija.DODAJ_TIP_PACIJENTA, tp);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){
            throw new Exception();
        }
    }

    public void izmeniTipPacijenta(TipPacijenta tipPacijenta) throws Exception {
        Zahtev z=new Zahtev(Operacija.IZMENI_TIP_PACIJENTA, (TipPacijenta) tipPacijenta);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){
            throw new Exception();
        }
    }

    public List<Specijalizacija> vratiSpecijalizacije() {
        Zahtev z=new Zahtev(Operacija.UCITAJ_SPECIJALIZACIJE, null);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        List<Specijalizacija>lista=(List<Specijalizacija>) o.getOdgovor();
        return lista;
    }

    public void obrisiSpecijalizaciju(Specijalizacija s) throws Exception {
        Zahtev z=new Zahtev(Operacija.OBRISI_SPECIJALIZACIJU, (Specijalizacija)s);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){
            throw new Exception();
        }
    }

    public void dodajSpecijalizaciju(Specijalizacija s) throws Exception {
        Zahtev z=new Zahtev(Operacija.DODAJ_SPECIJALIZACIJU, (Specijalizacija)s);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){                                        
            throw new Exception();
        }
    }

    public void izmeniSpecijalizaciju(Specijalizacija specijalizacija) throws Exception {
        Zahtev z=new Zahtev(Operacija.IZMENI_SPECIJALIZACIJU, (Specijalizacija) specijalizacija);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){
            throw new Exception();
        }
    }

    public List<Fizijatar> vratiFizijatre() {
        Zahtev z=new Zahtev(Operacija.UCITAJ_FIZIJATRE, null);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        List<Fizijatar>lista=(List<Fizijatar>) o.getOdgovor();
        return lista;
    }

    public void obrisiFizijatra(Fizijatar f) throws Exception {
        Zahtev z=new Zahtev(Operacija.OBRISI_FIZIJATRA, (Fizijatar)f);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){
            throw new Exception();
        }
    }

    public Fizijatar dodajFizijatra(Fizijatar f) throws Exception {
        Zahtev z=new Zahtev(Operacija.DODAJ_FIZIJATRA, (Fizijatar)f);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        
        if(o.getOdgovor()==null){
            throw new Exception();
        }
        Fizijatar dodatiFizijatar=(Fizijatar) o.getOdgovor();
        return dodatiFizijatar;
    }

    public void izmeniFizijatra(Fizijatar fizijatar) throws Exception {
        Zahtev z=new Zahtev(Operacija.IZMENI_FIZIJATRA, (Fizijatar) fizijatar);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){
            throw new Exception();
        }
    }

    public List<Nalaz> vratiNalaze(){
        Zahtev z=new Zahtev(Operacija.UCITAJ_NALAZE, null);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        List<Nalaz>lista=(List<Nalaz>) o.getOdgovor();
        return lista;
    }

    public List<StavkaNalaza> vratiStavkeNalaza(Nalaz n){
        Zahtev z=new Zahtev(Operacija.UCITAJ_STAVKE_NALAZA,(Nalaz) n);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        List<StavkaNalaza>lista=(List<StavkaNalaza>) o.getOdgovor();
        return lista;
    }

    public void obrisiNalaz(Nalaz n) throws Exception {
        Zahtev z=new Zahtev(Operacija.OBRISI_NALAZ, (Nalaz)n);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){
            throw new Exception();
        }
    }

   

   

    


    public void azurirajCenuNalaza(Nalaz izabraniNalaz, double razlikaUCeni) {
        
        Nalaz n=izabraniNalaz;
        n.setUkupnaCena(izabraniNalaz.getUkupnaCena()+razlikaUCeni);
        
        Zahtev z=new Zahtev(Operacija.AZURIRAJ_CENU_NALAZA, (Nalaz) n);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){
            try {
                throw new Exception();
            } catch (Exception ex) {
                ex.printStackTrace();
                Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void povecajCenuNalaza(Nalaz izabraniNalaz, double cena){
        Nalaz n=izabraniNalaz;
        n.setUkupnaCena(izabraniNalaz.getUkupnaCena()+cena);
        
        Zahtev z=new Zahtev(Operacija.AZURIRAJ_CENU_NALAZA, (Nalaz) n);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){
            try {
                throw new Exception();
            } catch (Exception ex) {
                ex.printStackTrace();
                Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void smanjiCenuNalaza(Nalaz izabraniNalaz, double cena){
        Nalaz n=izabraniNalaz;
        n.setUkupnaCena(izabraniNalaz.getUkupnaCena()-cena);
        
        Zahtev z=new Zahtev(Operacija.AZURIRAJ_CENU_NALAZA, (Nalaz) n);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){
            try {
                throw new Exception();
            } catch (Exception ex) {
                ex.printStackTrace();
                Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Pacijent> vratiPacijente() {
        Zahtev z=new Zahtev(Operacija.UCITAJ_PACIJENTE,null);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        List<Pacijent>lista=(List<Pacijent>) o.getOdgovor();
        return lista;
    }

    public void dodajNalaz(Nalaz n) throws Exception {
        Zahtev z=new Zahtev(Operacija.DODAJ_NALAZ, (Nalaz)n);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){
            throw new Exception();
        }
        rs.ac.bg.fon.ai.klijent.koordinator.Koordinator.getInstance().setRbSledeceg(1);
    }

    public List<StavkaNalaza> vratiStavkeNalaza(){
        Zahtev z=new Zahtev(Operacija.UCITAJ_SVE_STAVKE_NALAZA,null);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        List<StavkaNalaza>lista=(List<StavkaNalaza>) o.getOdgovor();
        return lista;
    }

    public void izmeniNalaz(Nalaz n) throws Exception {
        Zahtev z=new Zahtev(Operacija.IZMENI_NALAZ, (Nalaz) n);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){                                                       
            throw new Exception();
        }
    }

    public void obrisiPacijenta(Pacijent p) throws Exception {
        Zahtev z=new Zahtev(Operacija.OBRISI_PACIJENTA, (Pacijent)p);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){                                        
            throw new Exception();
        }
    }

    public void dodajPacijenta(Pacijent p) throws Exception {
        Zahtev z=new Zahtev(Operacija.DODAJ_PACIJENTA, (Pacijent)p);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){                                                     
            throw new Exception();
        }
    }

    public void izmeniPacijenta(Pacijent pacijent) throws Exception {
        Zahtev z=new Zahtev(Operacija.IZMENI_PACIJENTA, (Pacijent) pacijent);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){                                                   
            throw new Exception();
        }
    }

    public Fizijatar odjaviSe(Fizijatar ulogovani) throws Exception {
        Zahtev z=new Zahtev(Operacija.ODJAVI_FIZIJATRA, (Fizijatar) ulogovani);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        
        Fizijatar odjavljen=(Fizijatar) o.getOdgovor();
        if(odjavljen==null){
            throw new Exception();
        }else{
            try {
                if(s != null) {
                   s.close(); 
                }
                
            } catch (IOException ex) {
                Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return odjavljen;
             

    }

    public void dodajFizijatarSpecijalizacija(List<Fizijatar_specijalista> listaFizSpec) throws Exception {
        Zahtev z=new Zahtev(Operacija.DODAJ_FIZIJATRA_SPECIJALIZACIJU, listaFizSpec);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){
            throw new Exception();
        }
    }

    public void obrisiFizijatarSpecijalizacija(Specijalizacija specijalizacija) throws Exception {
        Zahtev z=new Zahtev(Operacija.OBRISI_FIZIJATRA_SPECIJALIZACIJU, specijalizacija);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){
            throw new Exception();
        }
    }

	public void obrisiFizijatarSpecijalizacija1(Fizijatar f) throws Exception {
		Zahtev z=new Zahtev(Operacija.OBRISI_FIZIJATRA_SPECIJALIZACIJU, f);
        posiljalac.posalji(z);
        
        Odgovor o=(Odgovor) primalac.primi();
        if(o.getOdgovor()!=null){
            throw new Exception();
        }
	}

    

   

    

}
