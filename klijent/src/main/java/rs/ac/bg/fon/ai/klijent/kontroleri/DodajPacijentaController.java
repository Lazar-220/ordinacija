/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.kontroleri;

import rs.ac.bg.fon.ai.zajednicki.domen.Enum.Pol;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.StarosnaDob;
import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import rs.ac.bg.fon.ai.klijent.forme.DodajFizijatraForma;
import rs.ac.bg.fon.ai.klijent.forme.DodajPacijentaForma;
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
public class DodajPacijentaController {
    private DodajPacijentaForma dpf;
    private Pacijent pacijentZaIzmenu;
    private String stariEmail;

    public DodajPacijentaController(DodajPacijentaForma dodajPacijentaForma) {
        this.dpf = dodajPacijentaForma;
    }

    public void otvoriFormu(Pacijent pacijent) {
        dpf.setVisible(true);
        this.pacijentZaIzmenu=pacijent;
        popuniComboboxeve();
        addActionListener1();
        addActionListener2();
        if(pacijent!=null){
            stariEmail=pacijentZaIzmenu.getEmail();
            dpf.getjButtonDodaj().setEnabled(false);
            dpf.getjTextFieldIme().setText(pacijent.getIme());
            dpf.getjTextFieldPrezime().setText(pacijent.getPrezime());
            dpf.getjTextFieldEmail().setText(pacijent.getEmail());
            dpf.getjComboBoxPol().setSelectedItem(pacijent.getTipPacijenta().getPol());
            dpf.getjComboBoxStarosnaDob().setSelectedItem(pacijent.getTipPacijenta().getStarosnaDob());
        }else{
            dpf.getjButtonIzmeni().setEnabled(false);
        }
        
        dpf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public static boolean validirajEmail(String email) {
        // mora da se završi na "mail.com"
        if (!email.endsWith("mail.com")) {
            return false;
        }

        // mora da sadrži "@"
        int atIndex = email.indexOf('@');
        if (atIndex == -1) {
            return false;
        }

        // deo pre @ i deo posle @ ne smeju biti prazni
        String preAt = email.substring(0, atIndex);
        String posleAt = email.substring(atIndex + 1, email.length() - "mail.com".length());

        if (preAt.isEmpty() || posleAt.isEmpty()) {
            return false;
        }

        // provera karaktera u oba dela (dozvoljeno: slova i cifre)
        for (char c : preAt.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }

        for (char c : posleAt.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
    public boolean dobriPodaci(){
                if(dpf.getjTextFieldIme().getText().isEmpty()||dpf.getjTextFieldPrezime().getText().isEmpty()||dpf.getjTextFieldEmail().getText().isEmpty()){
                    JOptionPane.showMessageDialog(dpf, "Unesite sve podatke!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }if(!validirajEmail(dpf.getjTextFieldEmail().getText())){
                    JOptionPane.showMessageDialog(dpf, "Email mora biti u formatu ####@####mail.com, na primer pera@gmail.com!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                return true;
    }
    
    private void addActionListener1() {
        dpf.addActionListener1(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                
                if(!dobriPodaci()){
                    return;
                }
                String ime=dpf.getjTextFieldIme().getText();
                String prezime=dpf.getjTextFieldPrezime().getText();
                String email=dpf.getjTextFieldEmail().getText();
                Pol pol=(Pol) dpf.getjComboBoxPol().getSelectedItem();
                StarosnaDob starosnaDob=(StarosnaDob) dpf.getjComboBoxStarosnaDob().getSelectedItem();
                TipPacijenta tp=new TipPacijenta(-1,starosnaDob,pol);
                
                for(TipPacijenta tip:Komunikacija.getInstance().vratiTipovePacijenata()){
                    if(tp.equals(tip)){
                        tp.setIdTipPacijenta(tip.getIdTipPacijenta());
                    }
                }
                
                Pacijent p=new Pacijent(-1,ime,prezime, email, tp);
                List<Pacijent>lista=Komunikacija.getInstance().vratiPacijente();
                for(Pacijent pac:lista){
                        if(p.getEmail().equals(pac.getEmail())){
                            JOptionPane.showMessageDialog(dpf, "Vec postoji pacijent sa tim mejlom u bazi!", "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                try {
                    int provera=JOptionPane.showConfirmDialog(dpf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    Komunikacija.getInstance().dodajPacijenta(p);
                    Koordinator.getInstance().osveziTabeluPacijenta();
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dpf, "Sistem ne može da zapamti pacijenta.","Greska",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio pacijenta.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                dpf.dispose();
                
            }
        });
    }

    private void addActionListener2() {
        dpf.addActionListener2(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                try {
                    if(!dobriPodaci()){
                        return;
                    }
                   String ime=dpf.getjTextFieldIme().getText();
                    String prezime=dpf.getjTextFieldPrezime().getText();
                    String email=dpf.getjTextFieldEmail().getText();
                    Pol pol=(Pol) dpf.getjComboBoxPol().getSelectedItem();
                    StarosnaDob sd=(StarosnaDob) dpf.getjComboBoxStarosnaDob().getSelectedItem();
                    TipPacijenta tipPacijenta=new TipPacijenta(-1,sd,pol);
                    for(TipPacijenta tip:Komunikacija.getInstance().vratiTipovePacijenata()){
                        if(tipPacijenta.equals(tip)){
                            tipPacijenta.setIdTipPacijenta(tip.getIdTipPacijenta());
                        }
                    }
                    pacijentZaIzmenu.setIme(ime);
                    pacijentZaIzmenu.setPrezime(prezime);
                    pacijentZaIzmenu.setEmail(email);
                    pacijentZaIzmenu.setTipPacijenta(tipPacijenta);
                    
                    List<Pacijent>lista=Komunikacija.getInstance().vratiPacijente();
                    for(Pacijent pac:lista){
                        if(pacijentZaIzmenu.getEmail().equals(pac.getEmail())&&!stariEmail.equals(pacijentZaIzmenu.getEmail())){//
                            JOptionPane.showMessageDialog(dpf, "Vec postoji pacijent sa tim mejlom u bazi!", "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    
                    int provera=JOptionPane.showConfirmDialog(dpf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    Komunikacija.getInstance().izmeniPacijenta((Pacijent)pacijentZaIzmenu);
                    Koordinator.getInstance().osveziTabeluPacijenta();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dpf, "Sistem ne može da zapamti pacijenta.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio pacijenta.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                dpf.dispose();
            }
        });
    }

    private void popuniComboboxeve() {
        for(Pol p:Pol.values()){
            dpf.getjComboBoxPol().addItem(p);
        }
        for(StarosnaDob sd:StarosnaDob.values()){
            dpf.getjComboBoxStarosnaDob().addItem(sd);
        }
    }
}
