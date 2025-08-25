/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.kontroleri;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import rs.ac.bg.fon.ai.klijent.forme.DodajFizijatraForma;
import rs.ac.bg.fon.ai.klijent.forme.DodajSpecijalizacijuForma;
import rs.ac.bg.fon.ai.klijent.forme.PrikazFizijataraForma;
import rs.ac.bg.fon.ai.klijent.forme.modeliTabela.ModelTabeleFizijatar;
import rs.ac.bg.fon.ai.klijent.forme.modeliTabela.ModelTabeleSpecijalizacija;
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
public class PrikazFizijataraController {
    private PrikazFizijataraForma pff;
    private Fizijatar ulogovani;

    public PrikazFizijataraController(PrikazFizijataraForma prikazFizijataraForma) {
        this.pff = prikazFizijataraForma;
    }

    public void otvoriFormu(Fizijatar ulogovani) {
        pff.setVisible(true);
        pripremiFormu();
        this.ulogovani=ulogovani;
        
        addActionListener1();
        addActionListener2();
        addActionListener3();
        addActionListener4();
        addActionListenerDodaj();
        
        pff.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void pripremiFormu() {
        List<Fizijatar>lista=Komunikacija.getInstance().vratiFizijatre();
        ModelTabeleFizijatar model=new ModelTabeleFizijatar(lista);
        pff.getjTable1().setModel(model);
    }
    
    
    private void addActionListener1() {
        pff.addActionListener1(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                obrisi(e);
            }

            private void obrisi(ActionEvent e) {
                int red=pff.getjTable1().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pff, "Izaberite fizijatra koga zelite da obrisete.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleFizijatar model=(ModelTabeleFizijatar) pff.getjTable1().getModel();
                Fizijatar f=model.getLista().get(red);
                if(!ulogovani.getSifra().toLowerCase().contains("admin")&&!f.equals(ulogovani)){
                    JOptionPane.showMessageDialog(pff, "Fizijatre moze brisati samo admin.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try{
                    int provera=JOptionPane.showConfirmDialog(pff, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    Komunikacija.getInstance().obrisiFizijatarSpecijalizacija1(f);
                    Komunikacija.getInstance().obrisiFizijatra(f);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(pff, "Sistem ne može da obriše fizijatra.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(pff, "Sistem je obrisao fizijatra.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                pripremiFormu();
                   
            }
        });
    }
    private void addActionListener2() {
        pff.addActionListener2(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                int red=pff.getjTable1().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pff, "Odaberite fizijatra iz tabele kog zelite da promenite.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleFizijatar model1=(ModelTabeleFizijatar) pff.getjTable1().getModel();
                Fizijatar f2=model1.getLista().get(red);
                if(!ulogovani.getSifra().toLowerCase().contains("admin")&&!f2.equals(ulogovani)){
                    JOptionPane.showMessageDialog(pff, "Podatke drugih fizijatara moze menjati samo admin.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    DodajFizijatraController dodajFizijatraController=new DodajFizijatraController(new DodajFizijatraForma());
                    dodajFizijatraController.otvoriFormu(f2);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pff, "Greska pri izmeni.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
            }
        });
    }
    private void addActionListener3() {
        pff.addActionListener3(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtriraj(e);
            }

            private void filtriraj(ActionEvent e) {
                if(pff.getjTextFieldIme().getText().isEmpty()&&pff.getjTextFieldPrezime().getText().isEmpty()&&
                         pff.getjTextFieldKorisnickoIme().getText().isEmpty()){
                        
                    JOptionPane.showMessageDialog(pff, "Unesite bar jedan kriterijum za pretrazivanje.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                List<Fizijatar>lista=Komunikacija.getInstance().vratiFizijatre();
                ModelTabeleFizijatar model=new ModelTabeleFizijatar(lista);
                
                String ime=pff.getjTextFieldIme().getText();
                String prezime=pff.getjTextFieldPrezime().getText();
                String korisnickoIme=pff.getjTextFieldKorisnickoIme().getText();
                Fizijatar f=new Fizijatar(-1,ime,prezime,korisnickoIme,"");
                
                lista=model.vratiFiltriranuListu(f);
                model=new ModelTabeleFizijatar(lista);
                pff.getjTable1().setModel(model);
                
                if(lista.isEmpty()){
                    JOptionPane.showMessageDialog(pff, "Sistem ne može da nađe fizijatre po zadatim kriterijumima.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                JOptionPane.showMessageDialog(pff, "Sistem je našao fizijatre po zadatim kriterijumima.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });
    }

    private void addActionListener4() {
        pff.addActionListener4(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetujFilter();
            }

            private void resetujFilter() {
                pripremiFormu();
                pff.getjTextFieldIme().setText("");
                pff.getjTextFieldPrezime().setText("");
                pff.getjTextFieldKorisnickoIme().setText("");
            }
        });
    }
    private void addActionListenerDodaj() {
        
        pff.addActionListenerDodaj(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }

            private void dodaj() {
                Koordinator.getInstance().otvoriDodajFizijatraForma();
            }
        });
       }
}
