/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.kontroleri;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.StavkaNalaza;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import rs.ac.bg.fon.ai.klijent.forme.DodajNalazForma;
import rs.ac.bg.fon.ai.klijent.forme.GlavnaKlijentskaForma;
import rs.ac.bg.fon.ai.klijent.forme.PrikazNalazaForma;
import rs.ac.bg.fon.ai.klijent.forme.modeliTabela.ModelTabeleStavkaNalaza;
import rs.ac.bg.fon.ai.klijent.komunikacija.Komunikacija;
import rs.ac.bg.fon.ai.klijent.koordinator.Koordinator;
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
public class DodajNalazController {
    private DodajNalazForma dnf;
    private Nalaz izabraniNalaz;
    private List<StavkaNalaza>stavke=new ArrayList<>();
    private double ukupnaCenaNalaza=0;
    
    private PrikazNalazaForma parent;

    public DodajNalazController(DodajNalazForma dnf) {
        this.dnf = dnf;
    }

    public void otvoriFormu(Nalaz izabraniNalaz) {
        dnf.setVisible(true);
        for(Fizijatar f:Komunikacija.getInstance().vratiFizijatre()){
            dnf.getjComboBoxFizijatar().addItem(f);
        }
        for(Pacijent p:Komunikacija.getInstance().vratiPacijente()){
            dnf.getjComboBoxPacijent().addItem(p);
        }
        popuniCombobox();
        dnf.getjTextFieldCenaTerapije().setEnabled(false);
        dnf.pokreniNit();
        
        this.izabraniNalaz=izabraniNalaz;
        if(izabraniNalaz==null){
            dnf.getjButtonIzmeniNalaz().setEnabled(false);
            dnf.getjButtonIzmeniStavku().setEnabled(false);
            
        }else{
            dnf.getjButtonDodajNalaz().setEnabled(false);
            SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy.");
            dnf.getjTextFieldDatum().setText(sdf.format(izabraniNalaz.getDatumIzdavanja()));
            dnf.getjTextFieldOpis().setText(izabraniNalaz.getOpisNalaza());
            dnf.getjComboBoxFizijatar().setSelectedItem(izabraniNalaz.getFizijatar());
            dnf.getjComboBoxPacijent().setSelectedItem(izabraniNalaz.getPacijent());
            
            //dnf.getjButtonDodajStavku().setEnabled(false);
            
            stavke=Komunikacija.getInstance().vratiStavkeNalaza(izabraniNalaz);
            popuniTabeluStavke();
        }
        addActionListenerDodaj();
        addActionListenerIzmeni();
        
        addActionListenerDodajStavku();
        addActionListenerObrisiStavku();
        addActionListenerIzmeniStavku();
        
        dnf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        ModelTabeleStavkaNalaza mts=new ModelTabeleStavkaNalaza(stavke);
        dnf.getjTableStavkaNalaza().setModel(mts);
        
    }

    private void popuniCombobox() {
        List<Terapija>lista=Komunikacija.getInstance().vratiTerapije();
        for(Terapija t:lista){
            dnf.getjComboBoxSveTerapije().addItem(t);
        }
        
    }
    public boolean dobriPodaci(){
                if(dnf.getjTextFieldDatum().getText().isEmpty()||dnf.getjTextFieldOpis().getText().isEmpty()){
                    JOptionPane.showMessageDialog(dnf, "Unesite datum i opis nalaza!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                return true;
    }
    private void podesavanjeRb(){
                        int rb=1;
                    for (StavkaNalaza s:stavke){
                        s.setRb(rb);
                        rb++;
                    }
    
    }
    private void addActionListenerDodaj() {
        dnf.addActionListenerDodajNalaz(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                if(!dobriPodaci()){
                    return;
                }
                if(stavke.isEmpty()){
                    JOptionPane.showMessageDialog(dnf, "Morate prvo da dodate bar jednu stavku nalaza!","Greska",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                
                try {
                    Date datum;
                    try {
                        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy.");
                        datum=sdf.parse(dnf.getjTextFieldDatum().getText());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dnf, "Datum mora biti u formatu dd.MM.yyyy.","Greska",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(datum.getTime()>(new Date()).getTime()){
                        JOptionPane.showMessageDialog(dnf, "Datum ne sme biti u buducnosti.","Greska",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Fizijatar fizijatar=(Fizijatar) dnf.getjComboBoxFizijatar().getSelectedItem();
                    Pacijent pacijent=(Pacijent) dnf.getjComboBoxPacijent().getSelectedItem();
                    
                    
                    Nalaz n=new Nalaz(-1, datum, dnf.getjTextFieldOpis().getText(), ukupnaCena(stavke), fizijatar, pacijent, stavke);


                    int provera=JOptionPane.showConfirmDialog(dnf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    
                    Komunikacija.getInstance().dodajNalaz(n);
                    
                    Koordinator.getInstance().osveziTabeluNalaza();
                    
                    ukupnaCenaNalaza=0;
                    
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dnf, "Sistem ne može da kreira nalaz.","Greska",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(dnf, "Sistem je kreirao nalaz.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                dnf.dispose();
            }
        });
    }

    private void addActionListenerIzmeni() {
        dnf.addActionListenerIzmeni(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                if(!dobriPodaci()){
                    return;
                }
                if(stavke.isEmpty()){
                    JOptionPane.showMessageDialog(dnf, "Morate da dodate bar jednu stavku nalaza!","Greska",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                
                try {
                    Date datum;
                    try {
                        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy.");
                        datum=sdf.parse(dnf.getjTextFieldDatum().getText());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dnf, "Datum mora biti u formatu dd.MM.yyyy.","Greska",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(datum.getTime()>(new Date()).getTime()){
                        JOptionPane.showMessageDialog(dnf, "Datum ne sme biti u buducnosti.","Greska",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Fizijatar fizijatar=(Fizijatar) dnf.getjComboBoxFizijatar().getSelectedItem();
                    Pacijent pacijent=(Pacijent) dnf.getjComboBoxPacijent().getSelectedItem();
                    
                   
                    izabraniNalaz.setUkupnaCena(ukupnaCena(stavke));

                    Nalaz n=new Nalaz(izabraniNalaz.getIdNalaz(), datum, dnf.getjTextFieldOpis().getText(), izabraniNalaz.getUkupnaCena(), fizijatar, pacijent, stavke);


                    int provera=JOptionPane.showConfirmDialog(dnf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    Komunikacija.getInstance().izmeniNalaz(n);
                    
                    Koordinator.getInstance().osveziTabeluNalaza();
                    
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dnf, "Sistem ne može da zapamti nalaz.","Greska",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(dnf, "Sistem je zapamtio nalaz.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                dnf.dispose();
            }
        });
    }

    private void addActionListenerDodajStavku() {
        dnf.addActionListenerDodajStavku(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajStavku();
            }

            private void dodajStavku() {

                Terapija terapija=(Terapija) dnf.getjComboBoxSveTerapije().getSelectedItem();
                double cena=Double.parseDouble(dnf.getjTextFieldCenaTerapije().getText());
                StavkaNalaza stavka=new StavkaNalaza(Koordinator.getInstance().getRbSledeceg(), cena, terapija, null);
                Koordinator.getInstance().setRbSledeceg(Koordinator.getInstance().getRbSledeceg()+1);
                for(StavkaNalaza s:stavke){
                    if(stavka.getTerapija().getNaziv().equals(s.getTerapija().getNaziv())){
                        JOptionPane.showMessageDialog(dnf, "Vec ste dodali datu stavku!", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                stavke.add(stavka);
                podesavanjeRb();
                ukupnaCenaNalaza+=stavka.getCena();
                
                ModelTabeleStavkaNalaza mts=new ModelTabeleStavkaNalaza(stavke);
                dnf.getjTableStavkaNalaza().setModel(mts);
            }
        });
    }

    private void popuniTabeluStavke() {
        ModelTabeleStavkaNalaza model=new ModelTabeleStavkaNalaza(Komunikacija.getInstance().vratiStavkeNalaza(izabraniNalaz));
        dnf.getjTableStavkaNalaza().setModel(model);
        dnf.getjComboBoxSveTerapije().setSelectedItem(Komunikacija.getInstance().vratiStavkeNalaza(izabraniNalaz).get(0).getTerapija());
    }

    private void addActionListenerIzmeniStavku() {
        dnf.addActionListenerIzmeniStavku(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniStavku();
            }

            private void izmeniStavku() {
                
                
                int red=dnf.getjTableStavkaNalaza().getSelectedRow();
                if(red==-1||dnf.getjTableStavkaNalaza().getSelectedRowCount()>1){
                    JOptionPane.showMessageDialog(dnf, "Izaberite jednu stavku iz tabele za izmenu!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Terapija izabranaTerapija=(Terapija) dnf.getjComboBoxSveTerapije().getSelectedItem();
                for(StavkaNalaza s: stavke){
                    if(s.getTerapija().equals(izabranaTerapija)){
                        JOptionPane.showMessageDialog(dnf, "Vec postoji izabrana stavka u nalazu!", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                stavke.get(red).setTerapija(izabranaTerapija);
                stavke.get(red).setCena(izabranaTerapija.getCena());
                
                podesavanjeRb();
                
                ModelTabeleStavkaNalaza mts=new ModelTabeleStavkaNalaza(stavke);
                dnf.getjTableStavkaNalaza().setModel(mts);
            }
        });
    }
    private double ukupnaCena(List<StavkaNalaza>lista){
        double ukupno=0;
        for(StavkaNalaza s:stavke){
               ukupno+=s.getCena();
        }
        return ukupno;
    }

    private void addWindowClosingListener(PrikazNalazaForma pnf) {
        dnf.addWindowListener1(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent evt) {
                    try {
                        pnf.setEnabled(true);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dnf, "Greska pri gasenju forme.", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            });

        }

    private void addActionListenerObrisiStavku() {
        dnf.addActionListenerObrisiStavku(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiStavku();
            }

            private void obrisiStavku() {
                
                
                int red=dnf.getjTableStavkaNalaza().getSelectedRow();
                if(red==-1||dnf.getjTableStavkaNalaza().getSelectedRowCount()>1){
                    JOptionPane.showMessageDialog(dnf, "Izaberite jednu stavku iz tabele za brisanje!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                stavke.remove(stavke.get(red));
                
                podesavanjeRb();
                
                ModelTabeleStavkaNalaza mts=new ModelTabeleStavkaNalaza(stavke);
                dnf.getjTableStavkaNalaza().setModel(mts);
            }
        });
    }
    
}
