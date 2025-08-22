/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.niti;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar_specijalista;
import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import rs.ac.bg.fon.ai.zajednicki.domen.StavkaNalaza;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ai.zajednicki.komunikacija.Odgovor;
import rs.ac.bg.fon.ai.zajednicki.komunikacija.Operacija;
import rs.ac.bg.fon.ai.zajednicki.komunikacija.Posiljalac;
import rs.ac.bg.fon.ai.zajednicki.komunikacija.Primalac;
import rs.ac.bg.fon.ai.zajednicki.komunikacija.Zahtev;
import rs.ac.bg.fon.ai.server.controller.Controller;

/**
 *
 * @author milos
 */
public class ObradaKlijentskihZahteva extends Thread {

    boolean kraj=false;
    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac=new Posiljalac(socket);
        primalac=new Primalac(socket);
    }

    
    
    @Override
    public void run() {
        
        while(!kraj&&socket!=null&&!socket.isClosed() ){
           try{ 
            Zahtev zahtev=(Zahtev) primalac.primi();
            if(zahtev==null){
                
                return;
            }
            Odgovor odgovor=new Odgovor();
            switch (zahtev.getOperacija()) {
                case LOGIN:
                    Fizijatar ulogovani=(Fizijatar) zahtev.getParametar();
                    ulogovani=Controller.getInstance().login(ulogovani);
                    odgovor.setOdgovor(ulogovani);
                    if(ulogovani!=null&&ulogovani.getIme()!=null){
                        Controller.getInstance().getOnlineUseri().add(ulogovani);
                    }
                    
                    break;
                case UCITAJ_TERAPIJE:
                    List<Terapija>terapije=Controller.getInstance().vratiTerapije();
                    odgovor.setOdgovor(terapije);
                    break;
                case OBRISI_TERAPIJU:
                    try{
                        Controller.getInstance().obrisiTerapiju((Terapija)zahtev.getParametar());
                        odgovor.setOdgovor(true);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    break;
                case DODAJ_TERAPIJU:
                    Controller.getInstance().dodajTerapiju((Terapija)zahtev.getParametar());
                    odgovor.setOdgovor(true);
                    break;
                case IZMENI_TERAPIJU:
                    Controller.getInstance().izmeniTerapiju((Terapija)zahtev.getParametar());
                    odgovor.setOdgovor(true);
                    break;
                case UCITAJ_TIPOVE_PACIJENATA:
                    List<TipPacijenta>listaTipova=Controller.getInstance().vratiTipovePacijenata();
                    odgovor.setOdgovor(listaTipova);
                    break;
                case OBRISI_TIP_PACIJENTA:
                    try {
                        Controller.getInstance().obrisiTipPacijenta((TipPacijenta)zahtev.getParametar());
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case DODAJ_TIP_PACIJENTA:
                    try {
                    	Controller.getInstance().dodajTipPacijenta((TipPacijenta)zahtev.getParametar());
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case IZMENI_TIP_PACIJENTA:
                    try {
                        Controller.getInstance().izmeniTipPacijenta((TipPacijenta)zahtev.getParametar());
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case UCITAJ_SPECIJALIZACIJE:
                    List<Specijalizacija>specijalizacije=Controller.getInstance().vratiSpecijalizacije();
                    odgovor.setOdgovor(specijalizacije);
                    break;
                case OBRISI_SPECIJALIZACIJU:
                    try {
                        Controller.getInstance().obrisiSpecijalizaciju((Specijalizacija)zahtev.getParametar());
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case DODAJ_SPECIJALIZACIJU:
                    try {
                        Controller.getInstance().dodajSpecijalizaciju((Specijalizacija)zahtev.getParametar());
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case IZMENI_SPECIJALIZACIJU:
                    try {
                        Controller.getInstance().izmeniSpecijalizaciju((Specijalizacija)zahtev.getParametar());
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case UCITAJ_FIZIJATRE:
                    List<Fizijatar>fizijari=Controller.getInstance().vratiFizijatre();
                    odgovor.setOdgovor(fizijari);
                    break;
                case OBRISI_FIZIJATRA:
                    try {
                        Controller.getInstance().obrisiFizijatra((Fizijatar)zahtev.getParametar());
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case DODAJ_FIZIJATRA:
                    try {
                        Fizijatar dodatiFizijatar=Controller.getInstance().dodajFizijatra((Fizijatar)zahtev.getParametar());
                        odgovor.setOdgovor(dodatiFizijatar);
                    } catch (Exception e) {
                        odgovor.setOdgovor(null);
                    }
                    break;
                case IZMENI_FIZIJATRA:
                    try {
                        Controller.getInstance().izmeniFizijatra((Fizijatar)zahtev.getParametar());
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case UCITAJ_NALAZE:
                    List<Nalaz>nalazi=Controller.getInstance().vratiNalaze();
                    odgovor.setOdgovor(nalazi);
                    break;
                case UCITAJ_STAVKE_NALAZA:
                    List<StavkaNalaza>stavke=Controller.getInstance().vratiStavkeNalaza((Nalaz)zahtev.getParametar());
                    odgovor.setOdgovor(stavke);
                    break;
                case UCITAJ_SVE_STAVKE_NALAZA:
                    List<StavkaNalaza>sveStavke=Controller.getInstance().vratiSveStavkeNalaza();
                    odgovor.setOdgovor(sveStavke);
                    break;
                case OBRISI_NALAZ:
                    try {
                        boolean uspehObrisiNalaz=Controller.getInstance().obrisiNalaz((Nalaz)zahtev.getParametar());
                        if(uspehObrisiNalaz==true){
                            odgovor.setOdgovor(null);
                        }else{
                            odgovor.setOdgovor(false);
                        }
                        
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                
                case DODAJ_NALAZ:
                    try {
                        boolean uspehDodajNalaz=Controller.getInstance().dodajNalaz((Nalaz)zahtev.getParametar());
                        if(uspehDodajNalaz==true){
                            odgovor.setOdgovor(null);
                        }else{
                            odgovor.setOdgovor(false);
                        }
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                
                
                case IZMENI_NALAZ:
                    try {
                        boolean uspehIzmeniNalaz=Controller.getInstance().izmeniNalaz((Nalaz)zahtev.getParametar());
                        if(uspehIzmeniNalaz==true){
                            odgovor.setOdgovor(null);
                        }else{
                            odgovor.setOdgovor(false);
                        }
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case AZURIRAJ_CENU_NALAZA:
                    try {
                        boolean uspehAzurirajCenu=Controller.getInstance().izmeniNalaz((Nalaz)zahtev.getParametar());
                        if(uspehAzurirajCenu==true){
                            odgovor.setOdgovor(null);
                        }else{
                            odgovor.setOdgovor(false);
                        }
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case UCITAJ_PACIJENTE:
                    List<Pacijent>pacijenti=Controller.getInstance().vratiPacijente();
                    odgovor.setOdgovor(pacijenti);
                    break;
                case OBRISI_PACIJENTA:
                    try {
                        boolean uspehObrisiPacijenta=Controller.getInstance().obrisiPacijenta((Pacijent)zahtev.getParametar());
                        if(uspehObrisiPacijenta==true){
                            odgovor.setOdgovor(null);
                        }else{
                            odgovor.setOdgovor(false);
                        }
                        
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case DODAJ_PACIJENTA:
                    try {
                        boolean uspehDodajPacijenta=Controller.getInstance().dodajPacijenta((Pacijent)zahtev.getParametar());
                        if(uspehDodajPacijenta==true){
                            odgovor.setOdgovor(null);
                        }else{
                            odgovor.setOdgovor(false);
                        }
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case IZMENI_PACIJENTA:
                    try {
                        boolean uspehIzmeniPacijent=Controller.getInstance().izmeniPacijent((Pacijent)zahtev.getParametar());
                        if(uspehIzmeniPacijent==true){
                            odgovor.setOdgovor(null);
                        }else{
                            odgovor.setOdgovor(false);
                        }
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case ODJAVI_FIZIJATRA:
                    try {
                        Fizijatar odjavljen=Controller.getInstance().odjaviFizijatra((Fizijatar)zahtev.getParametar());

                        odgovor.setOdgovor(odjavljen);
                        //zaustaviNit();
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case DODAJ_FIZIJATRA_SPECIJALIZACIJU:
                    try {
                        boolean uspehDodajFS=Controller.getInstance().dodajFizijatarSpecijalizacije((List<Fizijatar_specijalista>)zahtev.getParametar());
                        if(uspehDodajFS==true){
                            odgovor.setOdgovor(null);
                        }else{
                            odgovor.setOdgovor(false);
                        }
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case OBRISI_FIZIJATRA_SPECIJALIZACIJU:
                    try {
                        boolean uspehObrisiFS=Controller.getInstance().obrisiFizijatarSpecijalizacije((Fizijatar)zahtev.getParametar());
                        if(uspehObrisiFS==true){
                            odgovor.setOdgovor(null);
                        }else{
                            odgovor.setOdgovor(false);
                        }
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                
                default:
                    System.out.println("greska, ne postoji operacija");
            }
            
             
            posiljalac.posalji(odgovor);
            
        } catch (Exception ex){
            ex.printStackTrace();
        }  
           
        }
    }

    public void zaustaviNit() {
        
        try {
            kraj=true;
            interrupt();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    
    
    
    
}
