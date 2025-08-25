/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.kontroleri;

import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import rs.ac.bg.fon.ai.klijent.forme.DodajTerapijuForma;
import rs.ac.bg.fon.ai.klijent.forme.PrikazTerapijaForma;
import rs.ac.bg.fon.ai.klijent.forme.modeliTabela.ModelTabeleTerapija;
import rs.ac.bg.fon.ai.klijent.komunikacija.Komunikacija;
import rs.ac.bg.fon.ai.klijent.koordinator.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author milos
 */
public class PrikazTerapijaController {
    private final PrikazTerapijaForma ptf;

    public PrikazTerapijaController(PrikazTerapijaForma ptf) {
        this.ptf = ptf;
    }

    public void otvoriFormu() {
        pripremiFormu();
        ptf.setVisible(true);
        addActionListener1();
        addActionListener2();
        addActionListener3();
        addActionListener4();
        addMouseListeners();
        addActionListenerDodaj();
        
        ptf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void pripremiFormu() {
        List<Terapija>lista=Komunikacija.getInstance().vratiTerapije();
        ModelTabeleTerapija model=new ModelTabeleTerapija(lista);
        ptf.getjTable1().setModel(model);
    }

    private void addActionListener1() {
        ptf.addActionListener1(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                obrisi(e);
            }

            private void obrisi(ActionEvent e) {
                int provera=JOptionPane.showConfirmDialog(ptf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                  
                int red=ptf.getjTable1().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(ptf, "Izaberite terapiju koju zelite da obrisete.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleTerapija model=(ModelTabeleTerapija) ptf.getjTable1().getModel();
                Terapija t=model.getLista().get(red);
                boolean uspeh=Komunikacija.getInstance().obrisiTerapiju(t);
                if(uspeh==true){
                    JOptionPane.showMessageDialog(ptf, "Sistem ne može da obriše terapiju.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    pripremiFormu();
                }else{
                    JOptionPane.showMessageDialog(ptf, "Sistem je obrisao terapiju.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                   
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
                    JOptionPane.showMessageDialog(ptf, "Izaberite terapiju za izmenu iz tabele!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleTerapija model=(ModelTabeleTerapija) ptf.getjTable1().getModel();
                Terapija t1=model.getLista().get(red);
                DodajTerapijuController dodajTerapijuController=new DodajTerapijuController(new DodajTerapijuForma());
                dodajTerapijuController.otvoriFormu(t1);
            }
        });
    }

    private void addMouseListeners() {
        ptf.addListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable tabela=(JTable) e.getSource();
                int red=tabela.getSelectedRow();
                int kolona=tabela.getSelectedColumn();
                if(kolona==2){
                       JOptionPane.showMessageDialog(ptf, tabela.getValueAt(red, kolona));
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

    private void addActionListener3() {
        ptf.addActionListener3(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtriraj(e);
            }

            private void filtriraj(ActionEvent e) {
                
                if(ptf.getjTextFieldCena().getText().isEmpty()&&ptf.getjTextFieldNaziv().getText().isEmpty()&&
                         ptf.getjTextFieldOpis().getText().isEmpty()){
                        
                    JOptionPane.showMessageDialog(ptf, "Unesite bar jedan kriterijum za pretrazivanje.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!ptf.getjTextFieldCena().getText().isEmpty()){
                    char[]niz1=ptf.getjTextFieldCena().getText().toCharArray();
                    for(int i=0;i<niz1.length;i++){
                        
                        if(niz1[i]=='-'){
                            JOptionPane.showMessageDialog(ptf, "Cena mora biti veca od 0!", "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if(!Character.isDigit(niz1[i])&&niz1[i]!='.'){
                            JOptionPane.showMessageDialog(ptf, "Unesi cenu ciframa!", "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                String naziv=ptf.getjTextFieldNaziv().getText();
                String opis=ptf.getjTextFieldOpis().getText();
                double cena;
                if(ptf.getjTextFieldCena().getText().isEmpty()){
                    cena=-1;
                }else{
                    cena=Integer.parseInt(ptf.getjTextFieldCena().getText());
                }
                Terapija t2=new Terapija(-1,naziv,opis,cena);
                
                List<Terapija>lista1=Komunikacija.getInstance().vratiTerapije();
                ModelTabeleTerapija model=new ModelTabeleTerapija(lista1);
                lista1=model.vratiFiltriranuListu(t2);
                model=new ModelTabeleTerapija(lista1);
                ptf.getjTable1().setModel(model);
                
                if(lista1.isEmpty()){
                    JOptionPane.showMessageDialog(ptf, "Sistem ne može da nađe terapije po zadatim kriterijumima.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                JOptionPane.showMessageDialog(ptf, "Sistem je našao terapije po zadatim kriterijumima.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });
    }

    private void addActionListener4() {
        ptf.addActionListener4(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetuj(e);
            }

            private void resetuj(ActionEvent e) {
                pripremiFormu();
                ptf.getjTextFieldCena().setText("");
                ptf.getjTextFieldNaziv().setText("");
                ptf.getjTextFieldOpis().setText("");
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
                Koordinator.getInstance().otvoriDodajTerapijuFormu();
            }
        });
        
        
    }

    
    
    
}
