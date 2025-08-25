/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.forme.modeliTabela;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import rs.ac.bg.fon.ai.zajednicki.domen.StavkaNalaza;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class ModelTabeleStavkaNalaza extends AbstractTableModel {
    private List<StavkaNalaza>lista;
    private static String[]kolone={"rb","terapija","cena"};

    public ModelTabeleStavkaNalaza(List<StavkaNalaza> lista) {
        this.lista = lista;
    }

    public List<StavkaNalaza> getLista() {
        return lista;
    }

    public void setLista(List<StavkaNalaza> lista) {
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
        StavkaNalaza sn=lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sn.getRb();
            case 1:
                return sn.getTerapija().getNaziv();
            case 2:
                return sn.getCena();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    public List<StavkaNalaza> vratiFiltriranuListu(StavkaNalaza stavka){
        List<StavkaNalaza>filtriranaLista=new ArrayList<>();

        if(!stavka.getTerapija().getNaziv().isEmpty()&&stavka.getCena()!=-1){
            for(StavkaNalaza s:lista){
                if(s.getTerapija().getNaziv().toLowerCase().contains(stavka.getTerapija().getNaziv().toLowerCase())){
                    if(s.getCena()==stavka.getCena()){
                        filtriranaLista.add(s);
                    }
                }
                
            }
        }else if(!stavka.getTerapija().getNaziv().isEmpty()){
            for(StavkaNalaza s:lista){
                if(s.getTerapija().getNaziv().toLowerCase().contains(stavka.getTerapija().getNaziv().toLowerCase())){
                    
                        filtriranaLista.add(s);
                    
                }
                
            }
        }else if(stavka.getCena()!=-1){
            for(StavkaNalaza s:lista){
                
                    if(s.getCena()==stavka.getCena()){
                        filtriranaLista.add(s);
                    }
                
                
            }
        }
                
        
        return filtriranaLista;
    }
        
}
