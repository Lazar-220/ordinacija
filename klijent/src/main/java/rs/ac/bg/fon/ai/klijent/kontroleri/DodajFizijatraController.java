/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.kontroleri;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar_specijalista;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import rs.ac.bg.fon.ai.klijent.forme.DodajFizijatraForma;
import rs.ac.bg.fon.ai.klijent.forme.modeliTabela.ModelTabeleSpecijalizacija;
import rs.ac.bg.fon.ai.klijent.komunikacija.Komunikacija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author milos
 */
public class DodajFizijatraController {
    private List<Fizijatar_specijalista>listaFizSpec=new ArrayList<>();
    private DodajFizijatraForma dff;
    private Fizijatar fizijatarZaIzmenu;
    private String staroKorIme;

    public DodajFizijatraController(DodajFizijatraForma dodajFizijatraForma) {
        this.dff = dodajFizijatraForma;
    }

    public void otvoriFormu(Fizijatar fizijatar) {
        dff.setVisible(true);
        this.fizijatarZaIzmenu=fizijatar;
        addActionListener1();
        addActionListener2();
        addActionListener3();
        addActionListenerCheckBox();
        
        dff.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        if(fizijatar!=null){
            staroKorIme=fizijatarZaIzmenu.getKorisnickoIme();
            dff.getjTextFieldIme().setText(fizijatar.getIme());
            dff.getjTextFieldPrezime().setText(fizijatar.getPrezime());
            dff.getjTextFieldKorisnickoIme().setText(fizijatar.getKorisnickoIme());
            dff.getjPasswordField1().setText(fizijatar.getSifra());
            dff.getjButtonDodajFizijatra().setEnabled(false);
            
            dff.getjComboBoxSveSpecijalizacije().setEnabled(false);
            dff.getjComboBoxStringoviDodateSpecIDatumi().setEnabled(false);
            dff.getjTextFieldDatumSpecijalizacije().setEnabled(false);
            dff.getjButtonDodajSpecijalizaciju().setEnabled(false);
        }else{
            dff.getjButtonIzmeni().setEnabled(false);
        }
        popuniCombobox();
    }
    
    
    
    public boolean dobriPodaci(){
                if(dff.getjTextFieldIme().getText().isEmpty()||dff.getjTextFieldPrezime().getText().isEmpty()||dff.getjTextFieldKorisnickoIme().getText().isEmpty()||dff.getjPasswordField1().getPassword().length==0){
                    JOptionPane.showMessageDialog(dff, "Unesite sve podatke!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }else if(dff.getjPasswordField1().getPassword().length<2){
                    JOptionPane.showMessageDialog(dff, "Sifra mora da sadrzi bar 2 karaktera!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                
                char[]niz=String.valueOf(dff.getjPasswordField1().getPassword()).toCharArray();
                boolean signal1=false;
                boolean signal2=false;
                for(int i=0;i<niz.length;i++){
                    
                    if(Character.isDigit(niz[i])){
                        signal1=true;
                    }else if(Character.isLowerCase(niz[i])){
                        signal2=true;
                    }
                }
                if(signal1==false||signal2==false){
                    JOptionPane.showMessageDialog(dff, "Sifra mora da sadrzi barem jedno malo slovo i barem jednu cifru!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                return true;
    }
    
    private void popuniCombobox() {
        List<Specijalizacija>listaSpec=Komunikacija.getInstance().vratiSpecijalizacije();
        for(Specijalizacija sp:listaSpec){
            dff.getjComboBoxSveSpecijalizacije().addItem(sp);
        }
    }
    private void addActionListener1() {
        dff.addActionListener1(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }
            
            private void dodaj(ActionEvent e) {
                
                if(!dobriPodaci()){
                    return;
                }if(listaFizSpec.size()<1){
                    JOptionPane.showMessageDialog(dff, "Dodajte bar jednu specijalizaciju!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String ime=dff.getjTextFieldIme().getText();
                String prezime=dff.getjTextFieldPrezime().getText();
                String korIme=dff.getjTextFieldKorisnickoIme().getText();
                String sifra=String.valueOf(dff.getjPasswordField1().getPassword());
                Fizijatar f=new Fizijatar(-1,ime,prezime,korIme,sifra);
                
                List<Fizijatar>lista=Komunikacija.getInstance().vratiFizijatre();
                for(Fizijatar fiz:lista){
                        if(f.getKorisnickoIme().equals(fiz.getKorisnickoIme())){
                            JOptionPane.showMessageDialog(dff, "Vec postoji fizijatar sa tim korisnickim imenom u bazi!", "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                 }
                
                try {
                    int provera=JOptionPane.showConfirmDialog(dff, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    Fizijatar dodatiFizijatar=Komunikacija.getInstance().dodajFizijatra(f);
                    rs.ac.bg.fon.ai.klijent.koordinator.Koordinator.getInstance().osveziTabeluFizijatara();
                    for(Fizijatar_specijalista fS:listaFizSpec){
                        fS.setFizijatar(dodatiFizijatar);
                    }
                    Komunikacija.getInstance().dodajFizijatarSpecijalizacija(listaFizSpec);
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dff, "Sistem ne može da kreira fizijatra.","Greska",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(dff, "Sistem je kreirao fizijatra.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                dff.dispose();
                
            }
        });
    }

    private void addActionListener2() {
        dff.addActionListener2(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                try {
                    if(!dobriPodaci()){
                        return;
                    }
                   String ime=dff.getjTextFieldIme().getText();
                    String prezime=dff.getjTextFieldPrezime().getText();
                    String korIme=dff.getjTextFieldKorisnickoIme().getText();
                    String sifra=String.valueOf(dff.getjPasswordField1().getPassword());
                    fizijatarZaIzmenu.setIme(ime);
                    fizijatarZaIzmenu.setPrezime(prezime);
                    
                    fizijatarZaIzmenu.setKorisnickoIme(korIme);
                    fizijatarZaIzmenu.setSifra(sifra);
                    
                    List<Fizijatar>lista=Komunikacija.getInstance().vratiFizijatre();
                    for(Fizijatar fiz:lista){
                        if(fiz.getKorisnickoIme().equals(fizijatarZaIzmenu.getKorisnickoIme())&&!staroKorIme.equals(fizijatarZaIzmenu.getKorisnickoIme())){
                            JOptionPane.showMessageDialog(dff, "Vec postoji fizijatar sa tim korisnickim imenom u bazi!", "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    
                    int provera=JOptionPane.showConfirmDialog(dff, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    Komunikacija.getInstance().izmeniFizijatra((Fizijatar)fizijatarZaIzmenu);
                    JOptionPane.showMessageDialog(dff, "Sistem je zapamtio fizijatra.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    rs.ac.bg.fon.ai.klijent.koordinator.Koordinator.getInstance().osveziTabeluFizijatara();
                    dff.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dff, "Sistem ne može da zapamti fizijatra.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
            }
        });
    }

    

    private void addActionListener3() {
        dff.addActionListener3(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajSpecijalizaciju();
            }

            private void dodajSpecijalizaciju() {
                if(dff.getjTextFieldDatumSpecijalizacije().getText().isEmpty()){
                    JOptionPane.showMessageDialog(dff, "Unesite datum specijalizacije.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Specijalizacija izabranaSpecijalizacija=(Specijalizacija) dff.getjComboBoxSveSpecijalizacije().getSelectedItem();
                    
                    Date datumSpecijalizacije;
                    SimpleDateFormat sdf;
                    try {
                        sdf=new SimpleDateFormat("dd.MM.yyyy.");
                        datumSpecijalizacije=sdf.parse(dff.getjTextFieldDatumSpecijalizacije().getText());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dff, "Datum mora biti u formatu dd.MM.yyyy.","Greska",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(datumSpecijalizacije.getTime()>(new Date()).getTime()){
                        JOptionPane.showMessageDialog(dff, "Datum ne sme biti u buducnosti.","Greska",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Fizijatar_specijalista fS=new Fizijatar_specijalista(-1, null, izabranaSpecijalizacija, datumSpecijalizacije);
                    if(listaFizSpec.contains(fS)){
                        JOptionPane.showMessageDialog(dff, "Vec ste dodali datu specijalizaciju.","Greska",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    listaFizSpec.add(fS);
                    dff.getjComboBoxStringoviDodateSpecIDatumi().addItem(sdf.format(fS.getDatumIzdavanja())+" "+fS.getSpecijalizacija().getNaziv());

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(dff, "Greska pri dodavanju specijalizacije.","Greska",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                
                dff.getjTextFieldDatumSpecijalizacije().setText("");
            }
        });
    }

    private void addActionListenerCheckBox() {
        dff.addActionListenerCheckBox(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check();
            }

            private void check() {
                if(dff.getjCheckBox1().isSelected()){
                    dff.getjPasswordField1().setEchoChar((char)0);
                }
                else{
                    dff.getjPasswordField1().setEchoChar('•');
                }
            }
        });
    }
}
