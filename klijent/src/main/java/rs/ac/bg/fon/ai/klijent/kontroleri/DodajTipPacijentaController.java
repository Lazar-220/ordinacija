/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.kontroleri;

import rs.ac.bg.fon.ai.zajednicki.domen.Enum.Pol;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.StarosnaDob;
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import rs.ac.bg.fon.ai.klijent.forme.DodajTipPacijentaForma;
import rs.ac.bg.fon.ai.klijent.komunikacija.Komunikacija;
import rs.ac.bg.fon.ai.klijent.koordinator.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author milos
 */
public class DodajTipPacijentaController {
    private TipPacijenta tipPacijentaZaIzmenu;
    private DodajTipPacijentaForma dtf;

    public DodajTipPacijentaController(DodajTipPacijentaForma dtf) {
        this.dtf = dtf;
    }

    public void otvoriFormu(TipPacijenta t2) {
        dtf.setVisible(true);
        
        for(Pol p:Pol.values()){
            dtf.getjComboBoxPol().addItem(p);
        }
        for(StarosnaDob s:StarosnaDob.values()){
            dtf.getjComboBoxStarosnaDob().addItem(s);
        }
        this.tipPacijentaZaIzmenu=t2;
        if(t2!=null){
            dtf.getjComboBoxPol().setSelectedItem(t2.getPol());
            dtf.getjComboBoxStarosnaDob().setSelectedItem(t2.getStarosnaDob());
            dtf.getjButtonDodaj().setEnabled(false);
        }else{
            dtf.getjButtonIzmeni().setEnabled(false);
        }
        addActionListener1();
        addActionListener2();
        
        dtf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addActionListener1() {
        dtf.addActionListener1(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                
                Pol pol=(Pol) dtf.getjComboBoxPol().getSelectedItem();
                StarosnaDob starosnaDob=(StarosnaDob) dtf.getjComboBoxStarosnaDob().getSelectedItem();
                TipPacijenta tp=new TipPacijenta(-1,starosnaDob,pol);
                if(Komunikacija.getInstance().vratiTipovePacijenata().contains(tp)){
                    JOptionPane.showMessageDialog(dtf, "Vec postoji trazeni tip pacijenta.","Greska",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    int provera=JOptionPane.showConfirmDialog(dtf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    Komunikacija.getInstance().dodajTipPacijenta(tp);
                    Koordinator.getInstance().osveziTabeluTipPacijenta();
                    dtf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dtf, "Greska pri dodavanju tipa pacijenta.","Greska",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(dtf, "Uspesno ste dodali tip pacijenta.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                dtf.dispose();
                
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
                try {
                    
                    Pol pol=(Pol) dtf.getjComboBoxPol().getSelectedItem();
                    StarosnaDob starosnaDob=(StarosnaDob) dtf.getjComboBoxStarosnaDob().getSelectedItem();
                    tipPacijentaZaIzmenu.setPol(pol);
                    tipPacijentaZaIzmenu.setStarosnaDob(starosnaDob);
                    
                    if(Komunikacija.getInstance().vratiTipovePacijenata().contains(tipPacijentaZaIzmenu)){
                        JOptionPane.showMessageDialog(dtf, "Vec postoji trazeni tip pacijenta.","Greska",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int provera=JOptionPane.showConfirmDialog(dtf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    Komunikacija.getInstance().izmeniTipPacijenta((TipPacijenta)tipPacijentaZaIzmenu);
                    JOptionPane.showMessageDialog(dtf, "Tip pacijenta je azuriran.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().osveziTabeluTipPacijenta();
                    dtf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dtf, "Greska pri izmeni.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
            }
        });
    }

    
    
        
}
