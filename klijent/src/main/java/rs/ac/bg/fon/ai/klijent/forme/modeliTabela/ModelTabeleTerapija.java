/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.forme.modeliTabela;

import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class ModelTabeleTerapija extends AbstractTableModel {

    private List<Terapija>lista;
    private static String[]kolone={"naziv","cena","opis"};

    public ModelTabeleTerapija(List<Terapija> lista) {
        this.lista = lista;
    }

    public List<Terapija> getLista() {
        return lista;
    }

    public void setLista(List<Terapija> lista) {
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
        Terapija t=lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getNaziv();
            case 1:
                return t.getCena();
            case 2:
                return t.getOpis();  //TODO //dodaj ono da se ceo opis prikazuje preko joptpane kad se klikne na celiju
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public List<Terapija> vratiFiltriranuListu(Terapija terapija){
        List<Terapija>filtriranaLista=new ArrayList<>();
        for(Terapija t:lista){
            if(!terapija.getNaziv().isEmpty()&&!terapija.getOpis().isEmpty()&&terapija.getCena()!=-1){
                if(t.getNaziv().toLowerCase().contains(terapija.getNaziv().toLowerCase())){
                    if(t.getOpis().toLowerCase().contains(terapija.getOpis().toLowerCase())){
                        if(t.getCena()==terapija.getCena()){
                            filtriranaLista.add(t);
                        }
                    }
                }
            }else if(!terapija.getNaziv().isEmpty()&&!terapija.getOpis().isEmpty()){
                if(t.getNaziv().toLowerCase().contains(terapija.getNaziv().toLowerCase())){
                    if(t.getOpis().toLowerCase().contains(terapija.getOpis().toLowerCase())){
                        
                            filtriranaLista.add(t);
                        
                    }
                }
            }else if(!terapija.getOpis().isEmpty()&&terapija.getCena()!=-1){
                if(t.getOpis().toLowerCase().contains(terapija.getOpis().toLowerCase())){
                        if(t.getCena()==terapija.getCena()){
                            filtriranaLista.add(t);
                        }
                    }
            }else if(!terapija.getNaziv().isEmpty()&&terapija.getCena()!=-1){
                if(t.getNaziv().toLowerCase().contains(terapija.getNaziv().toLowerCase())){
                    
                        if(t.getCena()==terapija.getCena()){
                            filtriranaLista.add(t);
                        }
                    
                }
            }else if(!terapija.getNaziv().isEmpty()){
                if(t.getNaziv().toLowerCase().contains(terapija.getNaziv().toLowerCase())){
                    
                            filtriranaLista.add(t);
                     
                }
            }else if(!terapija.getOpis().isEmpty()){
                if(t.getOpis().toLowerCase().contains(terapija.getOpis().toLowerCase())){
                        
                            filtriranaLista.add(t);
                       
                    }
            }else if(terapija.getCena()!=-1){
                if(t.getCena()==terapija.getCena()){
                            filtriranaLista.add(t);
                        }
            }
            
        }
        return filtriranaLista;
    }
}
