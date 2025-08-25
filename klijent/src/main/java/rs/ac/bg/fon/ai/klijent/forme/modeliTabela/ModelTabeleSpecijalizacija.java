/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.forme.modeliTabela;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class ModelTabeleSpecijalizacija extends AbstractTableModel {

    private List<Specijalizacija>lista;
    private static String[]kolone={"naziv","institucija"};

    public ModelTabeleSpecijalizacija(List<Specijalizacija> lista) {
        this.lista = lista;
    }

    

    public List<Specijalizacija> getLista() {
        return lista;
    }

    public void setLista(List<Specijalizacija> lista) {
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
        Specijalizacija s=lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getNaziv();
            case 1:
                return s.getInstitucija();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    public List<Specijalizacija> filtriraj(Specijalizacija specijalizacija){
        List<Specijalizacija>filtriranaLista=new ArrayList<>();
        if(!specijalizacija.getNaziv().isEmpty()&&!specijalizacija.getInstitucija().isEmpty()){
            for(Specijalizacija s:lista){
                if(s.getNaziv().toLowerCase().contains(specijalizacija.getNaziv().toLowerCase())){
                    if(s.getInstitucija().toLowerCase().contains(specijalizacija.getInstitucija().toLowerCase())){
                        filtriranaLista.add(s);
                    }
                }
                
            }
        }else if(!specijalizacija.getNaziv().isEmpty()){
            for(Specijalizacija s:lista){
                if(s.getNaziv().toLowerCase().contains(specijalizacija.getNaziv().toLowerCase())){
                    
                        filtriranaLista.add(s);
                    
                }
                
            }
        }else if(!specijalizacija.getInstitucija().isEmpty()){
            for(Specijalizacija s:lista){
                
                    if(s.getInstitucija().toLowerCase().contains(specijalizacija.getInstitucija().toLowerCase())){
                        filtriranaLista.add(s);
                    }
                
                
            }
        }
        return filtriranaLista;
    }
}
