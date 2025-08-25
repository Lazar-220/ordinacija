/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.controller;

import rs.ac.bg.fon.ai.server.operacije.DodajFizijatarSpecijalizacije;
import rs.ac.bg.fon.ai.server.operacije.DodajFizijatra;
import rs.ac.bg.fon.ai.server.operacije.DodajNalaz;
import rs.ac.bg.fon.ai.server.operacije.DodajPacijenta;
import rs.ac.bg.fon.ai.server.operacije.DodajSpecijalizaciju;
import rs.ac.bg.fon.ai.server.operacije.DodajTerapiju;
import rs.ac.bg.fon.ai.server.operacije.DodajTipPacijenta;
import rs.ac.bg.fon.ai.server.operacije.IzmeniFizijatra;
import rs.ac.bg.fon.ai.server.operacije.IzmeniNalaz;
import rs.ac.bg.fon.ai.server.operacije.IzmeniPacijent;
import rs.ac.bg.fon.ai.server.operacije.IzmeniSpecijalizaciju;
import rs.ac.bg.fon.ai.server.operacije.IzmeniTerapiju;
import rs.ac.bg.fon.ai.server.operacije.IzmeniTipPacijenta;
import rs.ac.bg.fon.ai.server.operacije.LoginOperacija;
import rs.ac.bg.fon.ai.server.operacije.ObrisiFizijatarSpecijalizaciju;
import rs.ac.bg.fon.ai.server.operacije.ObrisiFizijatra;
import rs.ac.bg.fon.ai.server.operacije.ObrisiNalaz;
import rs.ac.bg.fon.ai.server.operacije.ObrisiPacijenta;
import rs.ac.bg.fon.ai.server.operacije.ObrisiSpecijalizaciju;
import rs.ac.bg.fon.ai.server.operacije.ObrisiTerapiju;
import rs.ac.bg.fon.ai.server.operacije.ObrisiTipPacijenta;
import rs.ac.bg.fon.ai.server.operacije.UcitajFizijatre;
import rs.ac.bg.fon.ai.server.operacije.UcitajNalaze;
import rs.ac.bg.fon.ai.server.operacije.UcitajPacijente;
import rs.ac.bg.fon.ai.server.operacije.UcitajSpecijalizacije;
import rs.ac.bg.fon.ai.server.operacije.UcitajStavkeNalaza;
import rs.ac.bg.fon.ai.server.operacije.UcitajTerapije;
import rs.ac.bg.fon.ai.server.operacije.UcitajTipovePacijenata;
import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar_specijalista;
import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import rs.ac.bg.fon.ai.zajednicki.domen.StavkaNalaza;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author milos
 */
public class Controller {
   
    private List<Fizijatar>onlineUseri=new ArrayList<>();
    private static Controller instance;
    
    public static Controller getInstance() {
        if(instance==null){
            instance=new Controller();
        }
        return instance;
    }
    

    private Controller() {
        
    }

    public List<Fizijatar> getOnlineUseri() {
        return onlineUseri;
    }

    public void setOnlineUseri(List<Fizijatar> onlineUseri) {
        this.onlineUseri = onlineUseri;
    }

    
    public Fizijatar login(Fizijatar ulogovani) throws Exception {
        if(onlineUseri.contains(ulogovani)){
            Fizijatar f=new Fizijatar(1, null, null, null, null);
            return f; // Posto je vec ulogovan da na neki nacin kazemo to klijentu.
            
        }
        LoginOperacija operacija=new LoginOperacija();
        operacija.izvrsi(ulogovani, null);
        return operacija.getFizijatar();
    }

    public List<Terapija> vratiTerapije() throws Exception {
        UcitajTerapije operacija=new UcitajTerapije();
        operacija.izvrsi(new Terapija(), null);
        return operacija.getTerapije();
    }

    public void obrisiTerapiju(Terapija terapija) throws Exception {
        ObrisiTerapiju operacija=new ObrisiTerapiju();
        operacija.izvrsi(terapija, null);
    }

    public void dodajTerapiju(Terapija terapija) throws Exception {
        DodajTerapiju operacija=new DodajTerapiju();
        operacija.izvrsi(terapija, null);
    }

    public void izmeniTerapiju(Terapija terapija) throws Exception {
        IzmeniTerapiju operacija=new IzmeniTerapiju();
        operacija.izvrsi(terapija, null);
    }


    public List<TipPacijenta> vratiTipovePacijenata() throws Exception {
        UcitajTipovePacijenata operacija=new UcitajTipovePacijenata();
        operacija.izvrsi(new TipPacijenta(), null);
        List<TipPacijenta>lista=operacija.getLista();
        return lista;
    }

    public void obrisiTipPacijenta(TipPacijenta tipPacijenta) throws Exception {
        ObrisiTipPacijenta operacija=new ObrisiTipPacijenta();
        operacija.izvrsi(tipPacijenta, null);
    }

    public void dodajTipPacijenta(TipPacijenta tipPacijenta) throws Exception {
        DodajTipPacijenta operacija=new DodajTipPacijenta();
        operacija.izvrsi(tipPacijenta, null);
    }

    public void izmeniTipPacijenta(TipPacijenta tipPacijenta) throws Exception {
        IzmeniTipPacijenta operacija=new IzmeniTipPacijenta();
        operacija.izvrsi(tipPacijenta, null);
    }

    public List<Specijalizacija> vratiSpecijalizacije() throws Exception {
        UcitajSpecijalizacije operacija=new UcitajSpecijalizacije();
        operacija.izvrsi(new Specijalizacija(), null);
        return operacija.getLista();
    }

    public void obrisiSpecijalizaciju(Specijalizacija specijalizacija) throws Exception {
        ObrisiSpecijalizaciju operacija=new ObrisiSpecijalizaciju();
        operacija.izvrsi(specijalizacija, null);
    }

    public void dodajSpecijalizaciju(Specijalizacija specijalizacija) throws Exception {
        DodajSpecijalizaciju operacija=new DodajSpecijalizaciju();
        operacija.izvrsi(specijalizacija, null);
    }

    public void izmeniSpecijalizaciju(Specijalizacija specijalizacija) throws Exception {
        IzmeniSpecijalizaciju operacija=new IzmeniSpecijalizaciju();
        operacija.izvrsi(specijalizacija, null);
    }

    public List<Fizijatar> vratiFizijatre() throws Exception {
        UcitajFizijatre operacija=new UcitajFizijatre();
        operacija.izvrsi(new Fizijatar(), null);
        return operacija.getLista();
    }

    public void obrisiFizijatra(Fizijatar fizijatar) throws Exception {
        ObrisiFizijatra operacija=new ObrisiFizijatra();
        operacija.izvrsi(fizijatar, null);
    }

    public Fizijatar dodajFizijatra(Fizijatar fizijatar) throws Exception {
        DodajFizijatra operacija=new DodajFizijatra();
        operacija.izvrsi(fizijatar, null);
        return operacija.getDodatiFizijatar();
    }

    public void izmeniFizijatra(Fizijatar fizijatar) throws Exception {
        IzmeniFizijatra operacija=new IzmeniFizijatra();
        operacija.izvrsi(fizijatar, null);
    }

    public List<Nalaz> vratiNalaze() throws Exception {
        UcitajNalaze operacija=new UcitajNalaze();
        operacija.izvrsi(new Nalaz(), null);
        return operacija.getLista();
    }

    public List<StavkaNalaza> vratiStavkeNalaza(Nalaz nalaz) throws Exception {
        UcitajStavkeNalaza operacija=new UcitajStavkeNalaza();
        operacija.izvrsi(nalaz, null);
        return operacija.getLista();
    }

    public boolean obrisiNalaz(Nalaz nalaz) throws Exception {
        ObrisiNalaz obrisiNalaz=new ObrisiNalaz();
        obrisiNalaz.izvrsi(nalaz, null);
        return obrisiNalaz.isUspeh();
    }

    

    public boolean izmeniNalaz(Nalaz nalaz) throws Exception {
        IzmeniNalaz izmeniNalaz=new IzmeniNalaz();
        izmeniNalaz.izvrsi(nalaz, null);
        return izmeniNalaz.isUspeh();
    }

    public List<Pacijent> vratiPacijente() throws Exception {
        UcitajPacijente operacija=new UcitajPacijente();
        operacija.izvrsi(new Pacijent(), null);
        return operacija.getLista();
    }

    public boolean dodajNalaz(Nalaz nalaz) throws Exception {
        DodajNalaz dodajNalaz=new DodajNalaz();
        dodajNalaz.izvrsi(nalaz, null);
        return dodajNalaz.isUspeh();
    }

    public List<StavkaNalaza> vratiSveStavkeNalaza() throws Exception {
        UcitajStavkeNalaza operacija=new UcitajStavkeNalaza();
        operacija.izvrsi(new Nalaz(), null);
        return operacija.getLista();
    }

    public boolean obrisiPacijenta(Pacijent pacijent) throws Exception {
        ObrisiPacijenta operacija=new ObrisiPacijenta();
        operacija.izvrsi(pacijent, null);
        return operacija.isUspeh();
    }

    public boolean dodajPacijenta(Pacijent pacijent) throws Exception {
        DodajPacijenta operacija=new DodajPacijenta();
        operacija.izvrsi(pacijent, null);
        return operacija.isUspeh();
    }

    public boolean izmeniPacijent(Pacijent pacijent) throws Exception {
        IzmeniPacijent operacija=new IzmeniPacijent();
        operacija.izvrsi(pacijent, null);
        return operacija.isUspeh();
    }

    public Fizijatar odjaviFizijatra(Fizijatar fizijatar) {
        for(Fizijatar f:onlineUseri){
            if(f.equals(fizijatar)){
                onlineUseri.remove(f);
                return f;
            }
        }
        return null;
    }

    public boolean dodajFizijatarSpecijalizacije(List<Fizijatar_specijalista>lista) throws Exception {
        DodajFizijatarSpecijalizacije operacija=new DodajFizijatarSpecijalizacije();
        for(Fizijatar_specijalista fS:lista){
            operacija.izvrsi(fS, null);
        }
        return operacija.isUspeh();
    }

    public boolean obrisiFizijatarSpecijalizacije(Object object) throws Exception {
        ObrisiFizijatarSpecijalizaciju operacija=new ObrisiFizijatarSpecijalizaciju();
        
            operacija.izvrsi(object, null);
        return operacija.isUspeh();
    }

 

    
}
