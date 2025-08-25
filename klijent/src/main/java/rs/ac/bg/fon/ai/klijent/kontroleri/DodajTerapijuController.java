/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.kontroleri;

import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import rs.ac.bg.fon.ai.klijent.forme.DodajTerapijuForma;
import rs.ac.bg.fon.ai.klijent.forme.GlavnaKlijentskaForma;
import rs.ac.bg.fon.ai.klijent.forme.PrikazTerapijaForma;
import rs.ac.bg.fon.ai.klijent.komunikacija.Komunikacija;
import rs.ac.bg.fon.ai.klijent.koordinator.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author milos
 */
public class DodajTerapijuController {
    private DodajTerapijuForma dtf;
    private Terapija terapijaZaIzmenu;

    public DodajTerapijuController(DodajTerapijuForma dtf) {
        this.dtf = dtf;
    }

   


    public void otvoriFormu(Terapija terapijaZaIzmenu) {
        dtf.setVisible(true);
        addActionListener1();
        addActionListener2();
        this.terapijaZaIzmenu=terapijaZaIzmenu;
        if(terapijaZaIzmenu!=null){
            
            dtf.getjTextFieldNaziv().setText(terapijaZaIzmenu.getNaziv());
            dtf.getjTextFieldOpis().setText(terapijaZaIzmenu.getOpis());
            dtf.getjTextFieldCenaTerapije().setText(terapijaZaIzmenu.getCena()+"");
            dtf.getjButton1().setEnabled(false);
        }else{
            dtf.getjButtonIzmeni().setEnabled(false);
        }
        
        dtf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public boolean dobriPodaci(){
                if(dtf.getjTextFieldNaziv().getText().isEmpty()||dtf.getjTextFieldOpis().getText().isEmpty()||dtf.getjTextFieldCenaTerapije().getText().isEmpty()){
                    JOptionPane.showMessageDialog(dtf, "Unesite sva 3 podatka!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                char[]niz1=dtf.getjTextFieldCenaTerapije().getText().toCharArray();
                for(int i=0;i<niz1.length;i++){
                    if(niz1[i]=='-'){
                        JOptionPane.showMessageDialog(dtf, "Cena mora biti veca od 0!", "Greska", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                    if(!Character.isDigit(niz1[i])&&niz1[i]!='.'){
                        JOptionPane.showMessageDialog(dtf, "Unesite cenu ciframa!", "Greska", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
                char[]niz2=dtf.getjTextFieldNaziv().getText().toCharArray();
                boolean signal=false;
                for(int i=0;i<niz2.length;i++){
                    if(Character.isLetter(niz2[i])){
                        signal=true;
                    }
                }
                if(signal==false){
                    JOptionPane.showMessageDialog(dtf, "Naziv mora da sadrzi slova!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                signal=false;
                
                char[]niz3=dtf.getjTextFieldOpis().getText().toCharArray();
                for(int i=0;i<niz3.length;i++){
                    if(Character.isLetter(niz3[i])){
                        signal=true;
                    }
                }
                if(signal==false){
                    JOptionPane.showMessageDialog(dtf, "Opis mora da sadrzi slova!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                return true;
    }
    private void addActionListener1() {
        dtf.addActionListener1(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajTerapiju(e);

            }
            private void dodajTerapiju(ActionEvent e) {
                
                if(!dobriPodaci()){
                    return;
                }
                
               
                if(Double.parseDouble(dtf.getjTextFieldCenaTerapije().getText())<=0){
                    JOptionPane.showMessageDialog(dtf, "Cena mora biti veca od nule.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int odg=JOptionPane.showConfirmDialog(dtf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if(odg==JOptionPane.NO_OPTION||odg==JOptionPane.CLOSED_OPTION){
                    return;
                }
                String naziv=dtf.getjTextFieldNaziv().getText();
                String opis=dtf.getjTextFieldOpis().getText();
                double cena=Double.parseDouble(dtf.getjTextFieldCenaTerapije().getText());
                Terapija t=new Terapija(-1,naziv,opis,cena);
                boolean odgovor=Komunikacija.getInstance().dodajTerapiju(t);
                if(odgovor==true){
                    JOptionPane.showMessageDialog(dtf, "Sistem je kreirao terapiju.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().osveziTabeluTerapija();
                    dtf.dispose();
                }else{
                    JOptionPane.showMessageDialog(dtf, "Sistem ne može da kreira terapiju.", "Greska", JOptionPane.ERROR_MESSAGE);
                        
                }
            }

            

            
        });
    }
    
    
    private void addActionListener2() {
        dtf.addActionListener2(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            
            
            

            private void izmeni(ActionEvent e) {
                
                
                    
                if(!dobriPodaci()){
                    return;
                }
                if(Double.parseDouble(dtf.getjTextFieldCenaTerapije().getText())<=0){
                    JOptionPane.showMessageDialog(dtf, "Cena mora biti veca od nule.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Terapija t1=new Terapija();
                t1.setIdTerapija(terapijaZaIzmenu.getIdTerapija());
                t1.setNaziv(dtf.getjTextFieldNaziv().getText());
                t1.setOpis(dtf.getjTextFieldOpis().getText());
                t1.setCena(Double.parseDouble(dtf.getjTextFieldCenaTerapije().getText()));
                
                int provera=JOptionPane.showConfirmDialog(dtf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                boolean odgovor=Komunikacija.getInstance().izmeniTerapiju(t1);
                if(odgovor==true){
                    JOptionPane.showMessageDialog(dtf, "Sistem je zapamtio terapiju.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                       Koordinator.getInstance().osveziTabeluTerapija();
                       dtf.dispose();
                }else{
                    JOptionPane.showMessageDialog(dtf, "Sistem ne može da zapamti terapiju.", "Greska", JOptionPane.ERROR_MESSAGE);
                        
                }
            }

            
        });
    }
    
}