/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.kontroleri;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.klijent.forme.GlavnaKlijentskaForma;
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
public class GlavnaFormaController {

    private final GlavnaKlijentskaForma gf;
    private Fizijatar ulogovani;

    public GlavnaFormaController(GlavnaKlijentskaForma gf) {
        this.gf = gf;
        addActionListenerOdjaviSe();
        addWindowClosingListener();
        
        
    }
    
    
    public void otvoriFormu(Fizijatar ulogovani) {
        gf.setVisible(true);
        gf.getjLabelImePrezime().setText(ulogovani.toString());
        this.ulogovani=ulogovani;
        
        gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    

    private void addActionListenerOdjaviSe() {
        gf.addActionListenerOdjaviSe(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odjaviSe(e);
            }

            private void odjaviSe(ActionEvent e) {
                try {
                    int provera=JOptionPane.showConfirmDialog(gf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    Komunikacija.getInstance().odjaviSe(ulogovani);
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(gf, "Greska pri odjavljivanju.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //koordinator.Koordinator.getInstance().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(gf, "Uspesno odjavljivanje.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    //gf.dispose();
                    System.exit(0);
            }

        });
    }
    private void addWindowClosingListener(){
        gf.addWindowListener1(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                try {
                    Komunikacija.getInstance().odjaviSe(ulogovani);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(gf, "Greska pri odjavljivanju.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                System.exit(0);
            }
        });
               
    }

    public void ugasiForme() {
        System.exit(0);
    }
    
}
