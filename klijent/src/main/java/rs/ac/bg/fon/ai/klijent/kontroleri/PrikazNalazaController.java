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
import rs.ac.bg.fon.ai.klijent.forme.modeliTabela.ModelTabeleNalaz;
import rs.ac.bg.fon.ai.klijent.forme.modeliTabela.ModelTabeleStavkaNalaza;
import rs.ac.bg.fon.ai.klijent.komunikacija.Komunikacija;
import rs.ac.bg.fon.ai.klijent.koordinator.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author milos
 */
public class PrikazNalazaController {

    private Nalaz izabraniNalaz;
    private PrikazNalazaForma pnf;

    public PrikazNalazaController(PrikazNalazaForma prikazNalazaForma) {
        this.pnf = prikazNalazaForma;
    }
            
        
            
    public void otvoriFormu() {
        pnf.setVisible(true);
        pripremiFormu();
        addMouseListener();
        addActionListenerFilterNalaza();
        addActionListenerResetFilteraNalaza();
        addActionListenerFilterTerapija();
        addActionListenerResetFilteraTerapija();
        addActionListenerObrisiNalaz();
        addActionListenerIzmeniNalaz();
        addActionListenerDodajNalaz();
       
        
        pnf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
    }

    public void pripremiFormu() {
        List<Nalaz>lista=Komunikacija.getInstance().vratiNalaze();
        ModelTabeleNalaz model=new ModelTabeleNalaz(lista);
        pnf.getjTableNalaz().setModel(model);
        
        ModelTabeleStavkaNalaza mts=new ModelTabeleStavkaNalaza(new ArrayList<>());
        pnf.getjTableStavkaNalaza().setModel(mts);
    }
    public void napuniTabeluStavka(Nalaz n) {
        List<StavkaNalaza>lista1=Komunikacija.getInstance().vratiStavkeNalaza(n);
        ModelTabeleStavkaNalaza model=new ModelTabeleStavkaNalaza(lista1);
        pnf.getjTableStavkaNalaza().setModel(model); 
        List<StavkaNalaza>lista2=Komunikacija.getInstance().vratiStavkeNalaza();
        Koordinator.getInstance().setRbSledeceg(lista2.get(lista2.size()-1).getRb()+1);
    }

    private void addMouseListener() {
        pnf.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                klik(evt);
            }

            private void klik(MouseEvent evt) {
                int red=pnf.getjTableNalaz().getSelectedRow();
                if(red==-1){
                    return;
                }
                
                ModelTabeleNalaz model=(ModelTabeleNalaz) pnf.getjTableNalaz().getModel();
                izabraniNalaz=model.getLista().get(red);
                
                if(izabraniNalaz.getUkupnaCena()==0){
                    JOptionPane.showMessageDialog(pnf, "Sistem ne može da nađe nalaz.", "Uspeh", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(pnf, "Sistem je našao nalaz.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                
                
                
                
                napuniTabeluStavka(izabraniNalaz);
                
                
                
                
                pnf.getjTextFieldImeFizijatra().setText(izabraniNalaz.getFizijatar().getIme());
                pnf.getjTextFieldPrezimeFizijatra().setText(izabraniNalaz.getFizijatar().getPrezime());
                pnf.getjTextFieldImePacijenta().setText(izabraniNalaz.getPacijent().getIme());
                pnf.getjTextFieldPrezimePacijenta().setText(izabraniNalaz.getPacijent().getPrezime());
                pnf.getjTextFieldUkupnaCena().setText(izabraniNalaz.getUkupnaCena()+"");
            }

            
        });
    }

    private void addActionListenerFilterNalaza() {
        pnf.addActionListenerFilterNalaza(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               filtrirajNalaze(e);
            }

            private void filtrirajNalaze(ActionEvent e) {
                
                if(pnf.getjTextFieldUkupnaCena().getText().isEmpty()&&
                        pnf.getjTextFieldImeFizijatra().getText().isEmpty()&&pnf.getjTextFieldPrezimeFizijatra().getText().isEmpty()&&
                         pnf.getjTextFieldImePacijenta().getText().isEmpty()&&pnf.getjTextFieldPrezimePacijenta().getText().isEmpty()){
                        
                    JOptionPane.showMessageDialog(pnf, "Unesite bar jedan kriterijum za pretrazivanje nalaza.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                 if(!pnf.getjTextFieldUkupnaCena().getText().isEmpty()){
                    char[]niz1=pnf.getjTextFieldUkupnaCena().getText().toCharArray();
                    for(int i=0;i<niz1.length;i++){
                        if(niz1[i]=='-'){
                            JOptionPane.showMessageDialog(pnf, "Cena mora biti veca od 0!", "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if(!Character.isDigit(niz1[i])&&niz1[i]!='.'){
                            JOptionPane.showMessageDialog(pnf, "Unesi cenu ciframa!", "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                List<Nalaz>lista=Komunikacija.getInstance().vratiNalaze();
                ModelTabeleNalaz model=new ModelTabeleNalaz(lista);
                Fizijatar f=new Fizijatar(-1, pnf.getjTextFieldImeFizijatra().getText(), pnf.getjTextFieldPrezimeFizijatra().getText(), null, null);
                Pacijent p=new Pacijent(-1,pnf.getjTextFieldImePacijenta().getText(), pnf.getjTextFieldPrezimePacijenta().getText(), null, null);
                double ukupnaCena=-1;
                if(!pnf.getjTextFieldUkupnaCena().getText().isEmpty()){
                    ukupnaCena=Double.parseDouble(pnf.getjTextFieldUkupnaCena().getText());
                }
                Nalaz n=new Nalaz(-1,null,null,ukupnaCena,f,p, null);
                lista= (List<Nalaz>)model.vratiFiltriranuListu(n);
                model=new ModelTabeleNalaz((List<Nalaz>) lista);
                pnf.getjTableNalaz().setModel(model);
                
                if(lista.isEmpty()){
                    JOptionPane.showMessageDialog(pnf, "Sistem ne može da nađe nalaze po zadatim kriterijumima.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                JOptionPane.showMessageDialog(pnf, "Sistem je našao nalaze po zadatim kriterijumima.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });
    }

    private void addActionListenerResetFilteraNalaza() {
        pnf.addActionListenerResetFilteraNalaza(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
                pnf.getjTextFieldImeFizijatra().setText("");
                pnf.getjTextFieldPrezimeFizijatra().setText("");
                pnf.getjTextFieldImePacijenta().setText("");
                pnf.getjTextFieldPrezimePacijenta().setText("");
                pnf.getjTextFieldUkupnaCena().setText("");
            }
        });
    }

    private void addActionListenerFilterTerapija() {
        pnf.addActionListenerFilterTerapija(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrirajTerapije(e);
            }

            private void filtrirajTerapije(ActionEvent e) {
                
                if(izabraniNalaz==null){
                    JOptionPane.showMessageDialog(pnf, "Morate prvo izabrati nalaz iz leve tabele, zatim unesite bar jedan kriterijum za pretrazivanje stavke izabranog nalaza.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(pnf.getjTextFieldNazivTerapije().getText().isEmpty()&&
                        pnf.getjTextFieldCenaTerapije().getText().isEmpty()){
                        
                    JOptionPane.showMessageDialog(pnf, "Unesite bar jedan kriterijum za pretrazivanje stavke izabranog nalaza.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!pnf.getjTextFieldCenaTerapije().getText().isEmpty()){
                    char[]niz1=pnf.getjTextFieldCenaTerapije().getText().toCharArray();
                    for(int i=0;i<niz1.length;i++){
                        if(niz1[i]=='-'){
                            JOptionPane.showMessageDialog(pnf, "Cena mora biti veca od 0!", "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if(!Character.isDigit(niz1[i])&&niz1[i]!='.'){
                            JOptionPane.showMessageDialog(pnf, "Unesite cenu ciframa!", "Greska", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                
                List<StavkaNalaza>lista=Komunikacija.getInstance().vratiStavkeNalaza(izabraniNalaz);
                ModelTabeleStavkaNalaza model=new ModelTabeleStavkaNalaza(lista);
                
                Terapija t=new Terapija(-1, pnf.getjTextFieldNazivTerapije().getText(), null, -1);
                double cena=-1;
                if(!pnf.getjTextFieldCenaTerapije().getText().isEmpty()){
                    cena=Double.parseDouble(pnf.getjTextFieldCenaTerapije().getText());
                }
                StavkaNalaza sn=new StavkaNalaza(-1, cena, t, izabraniNalaz);
                
                lista= (List<StavkaNalaza>)model.vratiFiltriranuListu(sn);
                model=new ModelTabeleStavkaNalaza((List<StavkaNalaza>) lista);
                pnf.getjTableStavkaNalaza().setModel(model);
                
                if(lista.isEmpty()){
                    JOptionPane.showMessageDialog(pnf, "Sistem ne može da nađe stavke nalaza po zadatim kriterijumima.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                JOptionPane.showMessageDialog(pnf, "Sistem je našao stavke nalaza po zadatim kriterijumima.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });
    }

    private void addActionListenerResetFilteraTerapija() {
        pnf.addActionListenerResetFilteraTerapija(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(izabraniNalaz==null){
                    return;
                }
                napuniTabeluStavka(izabraniNalaz);
                pnf.getjTextFieldNazivTerapije().setText("");
                pnf.getjTextFieldCenaTerapije().setText("");
                
            }
        });
    }

    private void addActionListenerObrisiNalaz() {
        pnf.addActionListenerObrisiNalaz(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiNalaz(e);
            }

            private void obrisiNalaz(ActionEvent e) {
                int red=pnf.getjTableNalaz().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pnf, "Izaberite nalaz koji zelite da obrisete.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleNalaz model=(ModelTabeleNalaz) pnf.getjTableNalaz().getModel();
                Nalaz n=model.getLista().get(red);
                try{
                    ModelTabeleStavkaNalaza mts=(ModelTabeleStavkaNalaza) pnf.getjTableStavkaNalaza().getModel();
                    n.setListaStavki(mts.getLista());
                    
                    int provera=JOptionPane.showConfirmDialog(pnf, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
                    if(provera==JOptionPane.NO_OPTION||provera==JOptionPane.CLOSED_OPTION){
                        return;
                    }
                    Komunikacija.getInstance().obrisiNalaz(n);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(pnf, "Sistem ne može da obriše nalaz.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(pnf, "Sistem je obrisao nalaz.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                pripremiFormu();
            }
        });
    }

    

    

   

    private void addActionListenerIzmeniNalaz() {
        pnf.addActionListenerIzmeniNalaz(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniNalaz(e);
            }

            private void izmeniNalaz(ActionEvent e) {
                if(izabraniNalaz==null){
                    JOptionPane.showMessageDialog(pnf, "Izaberite nalaz za izmenu.", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                DodajNalazController dodajNalazController=new DodajNalazController(new DodajNalazForma());
                dodajNalazController.otvoriFormu(izabraniNalaz);
            }
        });
    }

    private void addActionListenerDodajNalaz() {
        pnf.addActionListenerDodajNalaz(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }

            private void dodaj() {
                Koordinator.getInstance().otvoriDodajNalazFormu();
            }
        });
        
                
                
    }

    
               
    
    
}
