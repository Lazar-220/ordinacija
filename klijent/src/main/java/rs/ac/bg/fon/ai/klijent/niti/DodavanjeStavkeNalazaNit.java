/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.niti;

import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import rs.ac.bg.fon.ai.klijent.forme.DodajNalazForma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class DodavanjeStavkeNalazaNit extends Thread {
    private DodajNalazForma dnf;
    
    public DodavanjeStavkeNalazaNit(DodajNalazForma dnf) {
        this.dnf = dnf;
    }

    @Override
    public void run() {
        while(true){
            try {
                Terapija t=(Terapija)dnf.getjComboBoxSveTerapije().getSelectedItem();
                if(t==null){
                    dnf.getjComboBoxSveTerapije().setSelectedIndex(0);
                    t=(Terapija)dnf.getjComboBoxSveTerapije().getSelectedItem();
                }
                dnf.getjTextFieldCenaTerapije().setText(t.getCena()+"");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(DodavanjeStavkeNalazaNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
