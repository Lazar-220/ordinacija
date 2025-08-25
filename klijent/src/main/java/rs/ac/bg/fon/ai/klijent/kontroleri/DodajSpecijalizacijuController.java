/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.kontroleri;

import rs.ac.bg.fon.ai.zajednicki.domen.Enum.Pol;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.StarosnaDob;
import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import rs.ac.bg.fon.ai.klijent.forme.DodajSpecijalizacijuForma;
import rs.ac.bg.fon.ai.klijent.komunikacija.Komunikacija;
import rs.ac.bg.fon.ai.klijent.koordinator.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author milos
 */
public class DodajSpecijalizacijuController {
    private DodajSpecijalizacijuForma dsf;
    private Specijalizacija specijalizacijaZaIzmenu;

    public DodajSpecijalizacijuController(DodajSpecijalizacijuForma dodajSpecijalizacijuForma) {
        this.dsf = dodajSpecijalizacijuForma;
    }

    public void otvoriFormu(Specijalizacija s2) {
        this.specijalizacijaZaIzmenu=s2;
        dsf.setVisible(true);
        addActionListener1();
        addActionListener2();
        if(s2!=null){
            dsf.getjTextFieldNaziv().setText(s2.getNaziv());
            dsf.getjTextFieldInstitucija().setText(s2.getInstitucija());
            dsf.getjButtonDodaj().setEnabled(false);
        }else{
            dsf.getjButton1().setEnabled(false);
        }
        
        dsf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public boolean dobriPodaci(){
                if(dsf.getjTextFieldNaziv().getText().isEmpty()||dsf.getjTextFieldInstitucija().getText().isEmpty()){
                    JOptionPane.showMessageDialog(dsf, "Unesite oba podatka!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                return true;
    }
    
    private void addActionListener1() {
        dsf.addActionListener1(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                
                if(!dobriPodaci()){
                    return;
                }
                String naziv=dsf.getjTextFieldNaziv().getText();
                String institucija=dsf.getjTextFieldInstitucija().getText();
                Specijalizacija s=new Specijalizacija(-1,naziv,institucija);
                
                List<Specijalizacija>lista=Komunikacija.getInstance().vratiSpecijalizacije();
                for(Specijalizacija sp:lista){
                        if(s.getInstitucija().equals(sp.getInstitucija())&&s.getNaziv().equals(sp.getNaziv())){
                            JOptionPane.showMessageDialog(dsf, "Vec postoji trazena specijalizacija u bazi!", "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                 }
                try {
                    int provera=JOptionPane.showConfirmDialog(dsf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    Komunikacija.getInstance().dodajSpecijalizaciju(s);
                    Koordinator.getInstance().osveziTabeluSpecijalizacija();
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dsf, "Sistem ne može da zapamti specijalizaciju.","Greska",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio specijalizaciju.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                dsf.dispose();
                
            }
        });
    }

    private void addActionListener2() {
        dsf.addActionListener2(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }
            
            private void izmeni(ActionEvent e) {
                
                
                    if(!dobriPodaci()){
                        return;
                    }
                    String naziv=dsf.getjTextFieldNaziv().getText();
                    String institucija=dsf.getjTextFieldInstitucija().getText();
                    specijalizacijaZaIzmenu.setNaziv(naziv);
                    specijalizacijaZaIzmenu.setInstitucija(institucija);
                    
                    List<Specijalizacija>lista=Komunikacija.getInstance().vratiSpecijalizacije();
                    for(Specijalizacija sp:lista){
                            if(specijalizacijaZaIzmenu.getInstitucija().equals(sp.getInstitucija())&&specijalizacijaZaIzmenu.getNaziv().equals(sp.getNaziv())){
                                JOptionPane.showMessageDialog(dsf, "Vec postoji trazena specijalizacija u bazi!", "Greska", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                     }
                  try {
                    int provera=JOptionPane.showConfirmDialog(dsf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    Komunikacija.getInstance().izmeniSpecijalizaciju((Specijalizacija)specijalizacijaZaIzmenu);
                    JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio specijalizaciju.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().osveziTabeluSpecijalizacija();
                    dsf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dsf, "Sistem ne može da zapamti specijalizaciju.","Greska",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
            }
        });
    }
    
}
