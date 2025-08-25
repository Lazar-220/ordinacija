/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.kontroleri;

import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import rs.ac.bg.fon.ai.klijent.forme.DodajTipPacijentaForma;
import rs.ac.bg.fon.ai.klijent.forme.PrikazTipPacijentaForma;
import rs.ac.bg.fon.ai.klijent.forme.modeliTabela.ModelTabeleTipPacijenta;
import rs.ac.bg.fon.ai.klijent.komunikacija.Komunikacija;
import rs.ac.bg.fon.ai.klijent.koordinator.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author milos
 */
public class PrikazTipPacijentaController{
    private PrikazTipPacijentaForma ptf;

    public PrikazTipPacijentaController(PrikazTipPacijentaForma ptf) {
        this.ptf = ptf;
    }

    public void otvoriFormu() {
        ptf.setVisible(true);
        pripremiFormu();
        addActionListener1();
        addActionListener2();
        addActionListenerDodaj();
        
        ptf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void pripremiFormu() {
        List<TipPacijenta>lista=Komunikacija.getInstance().vratiTipovePacijenata();
        ModelTabeleTipPacijenta model=new ModelTabeleTipPacijenta(lista);
        ptf.getjTable1().setModel(model);
    }

    private void addActionListener1() {
        ptf.addActionListener1(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi(e);
            }

            private void obrisi(ActionEvent e) {
                int red=ptf.getjTable1().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(ptf, "Izaberite tip pacijenta koji zelite da obrisete.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleTipPacijenta model1=(ModelTabeleTipPacijenta) ptf.getjTable1().getModel();
                TipPacijenta t1=model1.getLista().get(red);
                try{
                    int provera=JOptionPane.showConfirmDialog(ptf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    Komunikacija.getInstance().obrisiTipPacijenta(t1);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(ptf, "Greska pri brisanju.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(ptf, "Tip pacijenta je obrisan.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                pripremiFormu();
            }
        });
    }

    private void addActionListener2() {
        ptf.addActionListener2(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                int red=ptf.getjTable1().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(ptf, "Izaberite tip pacijenta koji zelite da izmenite.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleTipPacijenta model1=(ModelTabeleTipPacijenta) ptf.getjTable1().getModel();
                TipPacijenta t2=model1.getLista().get(red);
                try {
                    DodajTipPacijentaController dodajTipPacijentaController=new DodajTipPacijentaController(new DodajTipPacijentaForma());
                    dodajTipPacijentaController.otvoriFormu(t2);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ptf, "Greska pri izmeni.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
            }
        });
    }

    private void addActionListenerDodaj() {
        ptf.addActionListenerDodaj(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }

            private void dodaj() {
                Koordinator.getInstance().otvoriDodajTipPacijentaFormu();
            }
        });
        
    }
    
    
}
