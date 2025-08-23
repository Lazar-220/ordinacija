/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.forme.modeliTabela;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class ModelTabeleNalaz extends AbstractTableModel {
    private List<Nalaz>lista;
    private static String[]kolone={"datum izdavanja","opis nalaza","ukupna cena","fizijatar","pacijent"};

    public ModelTabeleNalaz(List<Nalaz> lista) {
        this.lista = lista;
    }

    public List<Nalaz> getLista() {
        return lista;
    }

    public void setLista(List<Nalaz> lista) {
        this.lista = lista;
    }
    
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Nalaz n=lista.get(rowIndex);
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy.");
        switch (columnIndex) {
            case 0:
                return sdf.format(n.getDatumIzdavanja());
            case 1:
                return n.getOpisNalaza();
            case 2:
                return n.getUkupnaCena();
            case 3:
                return n.getFizijatar();
            case 4:
                return n.getPacijent();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    public List<Nalaz> vratiFiltriranuListu(Nalaz nalaz){
        List<Nalaz>filtriranaLista=lista.stream().

                filter(n -> (nalaz.getFizijatar().getIme() == null || nalaz.getFizijatar().getIme().isEmpty() || n.getFizijatar().getIme().toLowerCase().contains(nalaz.getFizijatar().getIme().toLowerCase()))).
                filter(n -> (nalaz.getFizijatar().getPrezime()== null || nalaz.getFizijatar().getPrezime().isEmpty() || n.getFizijatar().getPrezime().toLowerCase().contains(nalaz.getFizijatar().getPrezime().toLowerCase()))).
                filter(n -> (nalaz.getUkupnaCena()== -1 || n.getUkupnaCena() == nalaz.getUkupnaCena())).
                filter(n -> (nalaz.getPacijent().getIme() == null || nalaz.getPacijent().getIme().isEmpty() || n.getPacijent().getIme().toLowerCase().contains(nalaz.getPacijent().getIme().toLowerCase()))).
                filter(n -> (nalaz.getPacijent().getPrezime()== null || nalaz.getPacijent().getPrezime().isEmpty() || n.getPacijent().getPrezime().toLowerCase().contains(nalaz.getPacijent().getPrezime().toLowerCase()))).
                collect(Collectors.toList());        //toList(); //jel moglo ovako?
        
        return filtriranaLista;
    }
        
}
