/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.koordinator;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import rs.ac.bg.fon.ai.klijent.forme.DodajFizijatraForma;
import rs.ac.bg.fon.ai.klijent.forme.DodajNalazForma;
import rs.ac.bg.fon.ai.klijent.forme.DodajPacijentaForma;
import rs.ac.bg.fon.ai.klijent.forme.DodajSpecijalizacijuForma;
import rs.ac.bg.fon.ai.klijent.forme.DodajTerapijuForma;
import rs.ac.bg.fon.ai.klijent.forme.DodajTipPacijentaForma;
import rs.ac.bg.fon.ai.klijent.forme.GlavnaKlijentskaForma;
import rs.ac.bg.fon.ai.klijent.forme.LoginForma;
import rs.ac.bg.fon.ai.klijent.forme.PrikazFizijataraForma;
import rs.ac.bg.fon.ai.klijent.forme.PrikazNalazaForma;
import rs.ac.bg.fon.ai.klijent.forme.PrikazPacijenataForma;
import rs.ac.bg.fon.ai.klijent.forme.PrikazSpecijalizacijaForma;
import rs.ac.bg.fon.ai.klijent.forme.PrikazTerapijaForma;
import rs.ac.bg.fon.ai.klijent.forme.PrikazTipPacijentaForma;
import rs.ac.bg.fon.ai.klijent.kontroleri.DodajFizijatraController;
import rs.ac.bg.fon.ai.klijent.kontroleri.DodajNalazController;
import rs.ac.bg.fon.ai.klijent.kontroleri.DodajPacijentaController;
import rs.ac.bg.fon.ai.klijent.kontroleri.DodajSpecijalizacijuController;
import rs.ac.bg.fon.ai.klijent.kontroleri.DodajTerapijuController;
import rs.ac.bg.fon.ai.klijent.kontroleri.DodajTipPacijentaController;
import rs.ac.bg.fon.ai.klijent.kontroleri.GlavnaFormaController;
import rs.ac.bg.fon.ai.klijent.kontroleri.LoginController;
import rs.ac.bg.fon.ai.klijent.kontroleri.PrikazFizijataraController;
import rs.ac.bg.fon.ai.klijent.kontroleri.PrikazNalazaController;
import rs.ac.bg.fon.ai.klijent.kontroleri.PrikazPacijenataController;
import rs.ac.bg.fon.ai.klijent.kontroleri.PrikazSpecijalizacijaController;
import rs.ac.bg.fon.ai.klijent.kontroleri.PrikazTerapijaController;
import rs.ac.bg.fon.ai.klijent.kontroleri.PrikazTipPacijentaController;

/**
 *
 * @author milos
 */
public class Koordinator {
    private int rbSledeceg=1;
    private Fizijatar ulogovani;
    private static Koordinator instance;
    private LoginController loginController;
    
    private GlavnaFormaController glavnaFormaController; 
    
    private PrikazTerapijaController prikazTerapijaController;
    private DodajTerapijuController dodajTerapijuController;
    
    private PrikazTipPacijentaController prikazTipPacijentaController;
    private DodajTipPacijentaController dodajTipPacijentaController;
    
    private PrikazSpecijalizacijaController prikazSpecijalizacijaController;
    private DodajSpecijalizacijuController dodajSpecijalizacijuController;
    
    private PrikazFizijataraController prikazFizijataraController;
    private DodajFizijatraController dodajFizijatraController;
    
    private PrikazNalazaController prikazNalazaController;
    private DodajNalazController dodajNalazController;
    
    private PrikazPacijenataController prikazPacijenataController;
    private DodajPacijentaController dodajPacijentaController;
    
    
    private GlavnaKlijentskaForma gkf=new GlavnaKlijentskaForma();
    
    public static Koordinator getInstance() {
        if(instance==null){
            instance=new Koordinator();
        }
        return instance;
    }
    

    private Koordinator() {
        
    }

    public int getRbSledeceg() {
        return rbSledeceg;
    }

    public void setRbSledeceg(int rbSledeceg) {
        this.rbSledeceg = rbSledeceg;
    }

    
    public void otvoriLoginFormu() {
        loginController=new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu(Fizijatar ulogovani) {
        glavnaFormaController=new GlavnaFormaController(gkf);
        glavnaFormaController.otvoriFormu(ulogovani);
    }

    public Fizijatar getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Fizijatar ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void otvoriPrikazTerapijaFormu() {
        prikazTerapijaController=new PrikazTerapijaController(new PrikazTerapijaForma());
        prikazTerapijaController.otvoriFormu();
    }

    public void otvoriDodajTerapijuFormu() {
        dodajTerapijuController=new DodajTerapijuController(new DodajTerapijuForma());
        dodajTerapijuController.otvoriFormu(null);
    }

//    public void otvoriIzmeniTerapijuFormu() {
//      //  if(prikazTerapijaController==null){
//            prikazTerapijaController=new PrikazTerapijaController(new PrikazTerapijaForma());
//      //  }
//        prikazTerapijaController.otvoriFormu();
//    }

    public void osveziTabeluTerapija() {
        if(prikazTerapijaController==null){
            prikazTerapijaController=new PrikazTerapijaController(new PrikazTerapijaForma());
            prikazTerapijaController.otvoriFormu();
        }
        
        prikazTerapijaController.pripremiFormu();
    }

    public void otvoriPrikazTipPacijentaForma() {
        prikazTipPacijentaController=new PrikazTipPacijentaController(new PrikazTipPacijentaForma());
        prikazTipPacijentaController.otvoriFormu();
    }

    public void otvoriDodajTipPacijentaFormu() {
        dodajTipPacijentaController=new DodajTipPacijentaController(new DodajTipPacijentaForma());
        dodajTipPacijentaController.otvoriFormu(null);
    }

//    public void otvoriIzmeniTipPacijentaFormu() {
//      //  if(prikazTipPacijentaController==null){
//            prikazTipPacijentaController=new PrikazTipPacijentaController(new PrikazTipPacijentaForma());
//        
//      //  }
//        prikazTipPacijentaController.otvoriFormu();
//    }

    public void osveziTabeluTipPacijenta() {
        if(prikazTipPacijentaController==null){
            prikazTipPacijentaController=new PrikazTipPacijentaController(new PrikazTipPacijentaForma());
            prikazTipPacijentaController.otvoriFormu();
        }
        prikazTipPacijentaController.pripremiFormu();
    }

    public void otvoriPrikazSpecijalizacijaForma() {
        prikazSpecijalizacijaController=new PrikazSpecijalizacijaController(new PrikazSpecijalizacijaForma());
        prikazSpecijalizacijaController.otvoriFormu();
    }

    public void otvoriDodajSpecijalizacijuForma() {
        dodajSpecijalizacijuController=new DodajSpecijalizacijuController(new DodajSpecijalizacijuForma());
        dodajSpecijalizacijuController.otvoriFormu(null);
    }

    public void osveziTabeluSpecijalizacija() {
        if(prikazSpecijalizacijaController==null){
            prikazSpecijalizacijaController=new PrikazSpecijalizacijaController(new PrikazSpecijalizacijaForma());
            prikazSpecijalizacijaController.otvoriFormu();
        }
        prikazSpecijalizacijaController.pripremiFormu();
    }

//    public void otvoriIzmeniSpecijalizacijuForma() {
//      //  if(prikazSpecijalizacijaController==null){
//            prikazSpecijalizacijaController=new PrikazSpecijalizacijaController(new PrikazSpecijalizacijaForma());
//      //  }
//        prikazSpecijalizacijaController.otvoriFormu();
//    }

    public void otvoriPrikazFizijataraFormu() {
      //  if(prikazFizijataraController==null){
            prikazFizijataraController=new PrikazFizijataraController(new PrikazFizijataraForma());
      //  }
        prikazFizijataraController.otvoriFormu(ulogovani);
    }

    public void otvoriDodajFizijatraForma() {
        dodajFizijatraController=new DodajFizijatraController(new DodajFizijatraForma());
        dodajFizijatraController.otvoriFormu((Fizijatar)null);
    }

    public void osveziTabeluFizijatara() {
        if(prikazFizijataraController==null){
            prikazFizijataraController=new PrikazFizijataraController(new PrikazFizijataraForma());
            prikazFizijataraController.otvoriFormu(ulogovani);
        }
        prikazFizijataraController.pripremiFormu();
    }

//    public void otvoriIzmeniFizijatraFormu() {
//      //  if(prikazFizijataraController==null){
//            prikazFizijataraController=new PrikazFizijataraController(new PrikazFizijataraForma());
//      //  }
//        prikazFizijataraController.otvoriFormu(ulogovani);
//    }

    public void otvoriPrikazNalazaFormu() {
       // if(prikazNalazaController==null){
            prikazNalazaController=new PrikazNalazaController(new PrikazNalazaForma());
       // }
        prikazNalazaController.otvoriFormu();
    }

    public void osveziTabeluStavkaNalaza(Nalaz n) {    //
//        if(prikazNalazaController==null){
//            prikazNalazaController=new PrikazNalazaController(new PrikazNalazaForma());
//            prikazNalazaController.otvoriFormu();
//        }
        prikazNalazaController.napuniTabeluStavka(n);
    }

    public void osveziTabeluNalaza() {                       //
        if(prikazNalazaController==null){
            prikazNalazaController=new PrikazNalazaController(new PrikazNalazaForma());
            prikazNalazaController.otvoriFormu();
        }
        prikazNalazaController.pripremiFormu();
    }

    public void otvoriDodajNalazFormu() {
        //if(dodajNalazController==null){
            dodajNalazController=new DodajNalazController(new DodajNalazForma());
        //}
        dodajNalazController.otvoriFormu(null);
    }

//    public void otvoriIzmeniNalazFormu() {
//        //if(prikazNalazaController==null){
//            prikazNalazaController=new PrikazNalazaController(new PrikazNalazaForma());
//        //}
//        prikazNalazaController.otvoriFormu();
//    }

    public void otvoriPrikazPacijentaFormu() {
       // if(prikazPacijenataController==null){
            prikazPacijenataController=new PrikazPacijenataController(new PrikazPacijenataForma());
       // }
        prikazPacijenataController.otvoriFormu();
    }

    public void osveziTabeluPacijenta() {
        if(prikazPacijenataController==null){
            prikazPacijenataController=new PrikazPacijenataController(new PrikazPacijenataForma());
            prikazPacijenataController.otvoriFormu();
        }
        prikazPacijenataController.pripremiFormu();
    }

    public void otvoriDodajPacijentaFormu() {
        //if(dodajPacijentaController==null){
            dodajPacijentaController=new DodajPacijentaController(new DodajPacijentaForma());
        //}
        dodajPacijentaController.otvoriFormu(null);
    }

//    public void otvoriIzmeniPacijentaFormu() {
//       // if(prikazPacijenataController==null){
//            prikazPacijenataController=new PrikazPacijenataController(new PrikazPacijenataForma());
//       // }
//        prikazPacijenataController.otvoriFormu();
//    }

    

    
    
}
