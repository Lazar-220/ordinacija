/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.forme.modeliTabela;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class ModelTabelePacijent extends AbstractTableModel {
    private List<Pacijent>lista;
    private static String[]kolone={"ime","prezime","email","pol","starosna dob"};

    public ModelTabelePacijent(List<Pacijent> lista) {
        this.lista = lista;
    }

    public List<Pacijent> getLista() {
        return lista;
    }

    public void setLista(List<Pacijent> lista) {
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
        Pacijent f=lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return f.getIme();
            case 1:
                return f.getPrezime();
            case 2:
                return f.getEmail();
            case 3:
                return f.getTipPacijenta().getPol();
            case 4:
                return f.getTipPacijenta().getStarosnaDob();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    public List<Pacijent> vratiFiltriranuListu(Pacijent pacijent){
        List<Pacijent>filtriranaLista=new ArrayList<>();
        for(Pacijent p:lista){
            if(!pacijent.getIme().isEmpty()&&!pacijent.getPrezime().isEmpty()&&!pacijent.getEmail().isEmpty()){
                if(p.getIme().toLowerCase().contains(pacijent.getIme().toLowerCase())){
                    if(p.getPrezime().toLowerCase().contains(pacijent.getPrezime().toLowerCase())){
                        if(p.getEmail().toLowerCase().contains(pacijent.getEmail().toLowerCase())){
                            filtriranaLista.add(p);
                        }
                    }
                }
            }else if(!pacijent.getIme().isEmpty()&&!pacijent.getPrezime().isEmpty()){
                  if(p.getIme().toLowerCase().contains(pacijent.getIme().toLowerCase())){
                        if(p.getPrezime().toLowerCase().contains(pacijent.getPrezime().toLowerCase())){
                            
                            filtriranaLista.add(p);
                            
                        }
                  }
            }else if(!pacijent.getPrezime().isEmpty()&&!pacijent.getEmail().isEmpty()){
                if(p.getPrezime().toLowerCase().contains(pacijent.getPrezime().toLowerCase())){
                        if(p.getEmail().toLowerCase().contains(pacijent.getEmail().toLowerCase())){
                            filtriranaLista.add(p);
                        }
                    }
            }else if(!pacijent.getIme().isEmpty()&&!pacijent.getEmail().isEmpty()){
                if(p.getIme().toLowerCase().contains(pacijent.getIme().toLowerCase())){
                      if(p.getEmail().toLowerCase().contains(pacijent.getEmail().toLowerCase())){
                            filtriranaLista.add(p);
                        }
                    
                }
            }else if(!pacijent.getIme().isEmpty()){
                if(p.getIme().toLowerCase().contains(pacijent.getIme().toLowerCase())){
                    
                            filtriranaLista.add(p);
                     
                }
            }else if(!pacijent.getPrezime().isEmpty()){
                if(p.getPrezime().toLowerCase().contains(pacijent.getPrezime().toLowerCase())){
                        
                            filtriranaLista.add(p);
                       
                    }
            }else if(!pacijent.getEmail().isEmpty()){
                if(p.getEmail().toLowerCase().contains(pacijent.getEmail().toLowerCase())){
                            filtriranaLista.add(p);
                        }
            }
            
        }
        return filtriranaLista;
    }

    public List<Pacijent> vratiFiltriranuListuTP(Pacijent p) {
        List<Pacijent>filtriranaLista=new ArrayList<>();
        for(Pacijent pac:lista){
            if(pac.getTipPacijenta().equals(p.getTipPacijenta())){
                filtriranaLista.add(pac);
            }
            
        }
        
        return filtriranaLista;
    }
        
}
