/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.kontroleri;

import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import rs.ac.bg.fon.ai.klijent.forme.DodajSpecijalizacijuForma;
import rs.ac.bg.fon.ai.klijent.forme.DodajTipPacijentaForma;
import rs.ac.bg.fon.ai.klijent.forme.PrikazSpecijalizacijaForma;
import rs.ac.bg.fon.ai.klijent.forme.modeliTabela.ModelTabeleSpecijalizacija;
import rs.ac.bg.fon.ai.klijent.forme.modeliTabela.ModelTabeleTerapija;
import rs.ac.bg.fon.ai.klijent.forme.modeliTabela.ModelTabeleTipPacijenta;
import rs.ac.bg.fon.ai.klijent.komunikacija.Komunikacija;
import rs.ac.bg.fon.ai.klijent.koordinator.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author milos
 */
public class PrikazSpecijalizacijaController {
    private PrikazSpecijalizacijaForma psf;

    public PrikazSpecijalizacijaController(PrikazSpecijalizacijaForma psf) {
        this.psf = psf;
    }

    public void otvoriFormu() {
        psf.setVisible(true);
        pripremiFormu();
        addMouseListeners();
        addActionListener1();
        addActionListener2();
        addActionListener3();
        addActionListener4();
        addActionListenerDodaj();
        
        psf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void pripremiFormu() {
        List<Specijalizacija>lista=Komunikacija.getInstance().vratiSpecijalizacije();
        ModelTabeleSpecijalizacija model=new ModelTabeleSpecijalizacija(lista);
        psf.getjTable1().setModel(model);
    }
    private void addMouseListeners() {
        psf.addListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable tabela=(JTable) e.getSource();
                int red=tabela.getSelectedRow();
                int kolona=tabela.getSelectedColumn();
                if(kolona==1){
                       JOptionPane.showMessageDialog(psf, tabela.getValueAt(red, kolona));
                 }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                }

            @Override
            public void mouseReleased(MouseEvent e) {
                }

            @Override
            public void mouseEntered(MouseEvent e) {
                }

            @Override
            public void mouseExited(MouseEvent e) {
                }

        });
    }
    private void addActionListener1() {
        psf.addActionListener1(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                obrisi(e);
            }

            private void obrisi(ActionEvent e) {
                int red=psf.getjTable1().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(psf, "Izaberite specijalizaciju koju zelite da obrisete.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleSpecijalizacija model=(ModelTabeleSpecijalizacija) psf.getjTable1().getModel();
                Specijalizacija s=model.getLista().get(red);
                try{
                    int provera=JOptionPane.showConfirmDialog(psf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    Komunikacija.getInstance().obrisiFizijatarSpecijalizacija(s);
                    Komunikacija.getInstance().obrisiSpecijalizaciju(s);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(psf, "Greska pri brisanju.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(psf, "Specijalizacija je obrisana.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                pripremiFormu();
                   
            }
        });
    }
    private void addActionListener2() {
        psf.addActionListener2(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                int red=psf.getjTable1().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(psf, "Izaberite specijalizaciju koju zelite da izmenite.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleSpecijalizacija model1=(ModelTabeleSpecijalizacija) psf.getjTable1().getModel();
                Specijalizacija s2=model1.getLista().get(red);
                try {
                    DodajSpecijalizacijuController dodajSpecijalizacijuController=new DodajSpecijalizacijuController(new DodajSpecijalizacijuForma());
                    dodajSpecijalizacijuController.otvoriFormu(s2);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(psf, "Greska pri izmeni.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
            }
        });
    }

    private void addActionListener3() {
        psf.addActionListener3(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtriraj(e);
            }

            private void filtriraj(ActionEvent e) {
                if(psf.getjTextFieldNaziv().getText().isEmpty()&&psf.getjTextFieldInstitucija().getText().isEmpty()){
                        
                    JOptionPane.showMessageDialog(psf, "Unesite bar jedan kriterijum za pretrazivanje.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                List<Specijalizacija>lista=Komunikacija.getInstance().vratiSpecijalizacije();
                ModelTabeleSpecijalizacija model=new ModelTabeleSpecijalizacija(lista);
                
                String naziv=psf.getjTextFieldNaziv().getText();
                String institucija=psf.getjTextFieldInstitucija().getText();
                Specijalizacija s=new Specijalizacija(-1,naziv,institucija);
                
                lista=model.filtriraj(s);
                model=new ModelTabeleSpecijalizacija(lista);
                psf.getjTable1().setModel(model);
                
                if(lista.isEmpty()){
                    JOptionPane.showMessageDialog(psf, "Sistem ne može da nađe specijalizacije po zadatim kriterijumima.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                JOptionPane.showMessageDialog(psf, "Sistem je našao specijalizacije po zadatim kriterijumima.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });
    }

    private void addActionListener4() {
        psf.addActionListener4(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetujFilter();
            }

            private void resetujFilter() {
                pripremiFormu();
                psf.getjTextFieldNaziv().setText("");
                psf.getjTextFieldInstitucija().setText("");
            }
        });
    }

    private void addActionListenerDodaj() {
        psf.addActionListenerDodaj(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }

            private void dodaj() {
                Koordinator.getInstance().otvoriDodajSpecijalizacijuForma();
            }
        });
        
    }
}
