/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.kontroleri;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import rs.ac.bg.fon.ai.klijent.forme.DodajFizijatraForma;
import rs.ac.bg.fon.ai.klijent.forme.DodajPacijentaForma;
import rs.ac.bg.fon.ai.klijent.forme.DodajSpecijalizacijuForma;
import rs.ac.bg.fon.ai.klijent.forme.PrikazFizijataraForma;
import rs.ac.bg.fon.ai.klijent.forme.PrikazPacijenataForma;
import rs.ac.bg.fon.ai.klijent.forme.modeliTabela.ModelTabeleFizijatar;
import rs.ac.bg.fon.ai.klijent.forme.modeliTabela.ModelTabeleNalaz;
import rs.ac.bg.fon.ai.klijent.forme.modeliTabela.ModelTabelePacijent;
import rs.ac.bg.fon.ai.klijent.forme.modeliTabela.ModelTabeleSpecijalizacija;
import rs.ac.bg.fon.ai.klijent.komunikacija.Komunikacija;
import rs.ac.bg.fon.ai.klijent.koordinator.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author milos
 */
public class PrikazPacijenataController {
    private PrikazPacijenataForma ppf;

    public PrikazPacijenataController(PrikazPacijenataForma prikazPacijenataForma) {
        this.ppf = prikazPacijenataForma;
    }

    public void otvoriFormu() {
        ppf.setVisible(true);
        pripremiFormu();
        
        addMouseListener();
        addActionListener1();
        addActionListener2();
        addActionListener3();
        addActionListener4();
        addActionListenerDodaj();
        
        popuniCombobox();
        
        ppf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void pripremiFormu() {
        List<Pacijent>lista=Komunikacija.getInstance().vratiPacijente();
        ModelTabelePacijent model=new ModelTabelePacijent(lista);
        ppf.getjTable1().setModel(model);
    }
    
    private void addMouseListener() {
        ppf.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                klik(evt);
            }

            private void klik(MouseEvent evt) {
                int red=ppf.getjTable1().getSelectedRow();
                if(red==-1){
                    return;
                }
                ModelTabelePacijent model=(ModelTabelePacijent) ppf.getjTable1().getModel();
                Pacijent izabraniPacijent=model.getLista().get(red);
                
                
                if(red>model.getLista().size()){
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da nađe pacijenta.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                    
                
                ppf.getjTextFieldIme().setText(izabraniPacijent.getIme());
                ppf.getjTextFieldPrezime().setText(izabraniPacijent.getPrezime());
                ppf.getjTextFieldEmail().setText(izabraniPacijent.getEmail());
                JOptionPane.showMessageDialog(ppf, "Sistem je našao pacijenta.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                
                
                
            }

            
        });
    }
    private void addActionListener1() {
        ppf.addActionListener1(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                obrisi(e);
            }

            private void obrisi(ActionEvent e) {
                int red=ppf.getjTable1().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(ppf, "Izaberite pacijenta koga zelite da obrisete.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabelePacijent model=(ModelTabelePacijent) ppf.getjTable1().getModel();
                Pacijent p=model.getLista().get(red);
                try{
                    int provera=JOptionPane.showConfirmDialog(ppf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    Komunikacija.getInstance().obrisiPacijenta(p);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da obriše pacijenta.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(ppf, "Sistem je obrisao pacijenta.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                pripremiFormu();
                   
            }
        });
    }
    private void addActionListener2() {
        ppf.addActionListener2(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                int red=ppf.getjTable1().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(ppf, "Izaberite pacijenta koga zelite da izmenite.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabelePacijent model1=(ModelTabelePacijent) ppf.getjTable1().getModel();
                Pacijent p2=model1.getLista().get(red);
                try {
                    DodajPacijentaController dodajPacijentaController=new DodajPacijentaController(new DodajPacijentaForma());
                    dodajPacijentaController.otvoriFormu(p2);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ppf, "Greska pri izmeni.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
            }
        });
    }
    private void addActionListener3() {
        ppf.addActionListener3(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtriraj(e);
            }

            private void filtriraj(ActionEvent e) {
                if(ppf.getjTextFieldIme().getText().isEmpty()&&ppf.getjTextFieldPrezime().getText().isEmpty()&&
                         ppf.getjTextFieldEmail().getText().isEmpty()&&ppf.getjComboBoxTipPacijenta().getSelectedIndex()<=0){
                        System.out.println("to");
                    JOptionPane.showMessageDialog(ppf, "Unesite bar jedan kriterijum za pretrazivanje.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                System.out.println(ppf.getjTextFieldIme().getText());
                System.out.println(ppf.getjTextFieldPrezime().getText());
                System.out.println(ppf.getjTextFieldEmail().getText());
                System.out.println(ppf.getjComboBoxTipPacijenta().getSelectedIndex());
                List<Pacijent>lista=Komunikacija.getInstance().vratiPacijente();
                ModelTabelePacijent model=new ModelTabelePacijent(lista);
                
                String ime=ppf.getjTextFieldIme().getText();
                String prezime=ppf.getjTextFieldPrezime().getText();
                String email=ppf.getjTextFieldEmail().getText();
                TipPacijenta tp=(TipPacijenta) ppf.getjComboBoxTipPacijenta().getSelectedItem();
                Pacijent p=new Pacijent(-1, ime, prezime, email, tp);
                
                
                
                if(tp==null){
                    lista=model.vratiFiltriranuListu(p);
                    model=new ModelTabelePacijent(lista);
                }
                else if((!email.isEmpty()||!prezime.isEmpty()||!ime.isEmpty())){
                    lista=model.vratiFiltriranuListu(p);
                    model=new ModelTabelePacijent(lista);
                }
                
                if(tp!=null){
                    lista=model.vratiFiltriranuListuTP(p);
                    model=new ModelTabelePacijent(lista);
                }
                ppf.getjTable1().setModel(model);
                
                
                if(lista.isEmpty()){
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da nađe pacijente po zadatim kriterijumima.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(ppf, "Sistem je našao pacijente po zadatim kriterijumima.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });
    }

    private void addActionListener4() {
        ppf.addActionListener4(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetujFilter();
            }

            private void resetujFilter() {
                pripremiFormu();
                ppf.getjTextFieldIme().setText("");
                ppf.getjTextFieldPrezime().setText("");
                ppf.getjTextFieldEmail().setText("");
            }
        });
    }

    private void popuniCombobox() {
        List<TipPacijenta>lista=Komunikacija.getInstance().vratiTipovePacijenata();
        ppf.getjComboBoxTipPacijenta().addItem(null);
        for(TipPacijenta tp:lista){
            ppf.getjComboBoxTipPacijenta().addItem(tp);
        }
    }

    private void addActionListenerDodaj() {
        ppf.addActionListenerDodaj(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }

            private void dodaj() {
                Koordinator.getInstance().otvoriDodajPacijentaFormu();
            }
        });
        
        
    }
    
}
