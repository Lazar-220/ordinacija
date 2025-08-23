/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.forme.modeliTabela;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class ModelTabeleFizijatar extends AbstractTableModel {
    private List<Fizijatar>lista;
    private static String[]kolone={"ime","prezime","korisnicko ime"};

    public ModelTabeleFizijatar(List<Fizijatar> lista) {
        this.lista = lista;
    }

    public List<Fizijatar> getLista() {
        return lista;
    }

    public void setLista(List<Fizijatar> lista) {
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
        Fizijatar f=lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return f.getIme();
            case 1:
                return f.getPrezime();
            case 2:
                return f.getKorisnickoIme();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    public List<Fizijatar> vratiFiltriranuListu(Fizijatar fizijatar){
        List<Fizijatar>filtriranaLista=new ArrayList<>();
        for(Fizijatar f:lista){
            if(!fizijatar.getIme().isEmpty()&&!fizijatar.getPrezime().isEmpty()&&!fizijatar.getKorisnickoIme().isEmpty()){
                if(f.getIme().toLowerCase().contains(fizijatar.getIme().toLowerCase())){
                    if(f.getPrezime().toLowerCase().contains(fizijatar.getPrezime().toLowerCase())){
                        if(f.getKorisnickoIme().toLowerCase().contains(fizijatar.getKorisnickoIme().toLowerCase())){
                            filtriranaLista.add(f);
                        }
                    }
                }
            }else if(!fizijatar.getIme().isEmpty()&&!fizijatar.getPrezime().isEmpty()){
                  if(f.getIme().toLowerCase().contains(fizijatar.getIme().toLowerCase())){
                        if(f.getPrezime().toLowerCase().contains(fizijatar.getPrezime().toLowerCase())){
                            
                            filtriranaLista.add(f);
                            
                        }
                  }
            }else if(!fizijatar.getPrezime().isEmpty()&&!fizijatar.getKorisnickoIme().isEmpty()){
                if(f.getPrezime().toLowerCase().contains(fizijatar.getPrezime().toLowerCase())){
                        if(f.getKorisnickoIme().toLowerCase().contains(fizijatar.getKorisnickoIme().toLowerCase())){
                            filtriranaLista.add(f);
                        }
                    }
            }else if(!fizijatar.getIme().isEmpty()&&!fizijatar.getKorisnickoIme().isEmpty()){
                if(f.getIme().toLowerCase().contains(fizijatar.getIme().toLowerCase())){
                      if(f.getKorisnickoIme().toLowerCase().contains(fizijatar.getKorisnickoIme().toLowerCase())){
                            filtriranaLista.add(f);
                        }
                    
                }
            }else if(!fizijatar.getIme().isEmpty()){
                if(f.getIme().toLowerCase().contains(fizijatar.getIme().toLowerCase())){
                    
                            filtriranaLista.add(f);
                     
                }
            }else if(!fizijatar.getPrezime().isEmpty()){
                if(f.getPrezime().toLowerCase().contains(fizijatar.getPrezime().toLowerCase())){
                        
                            filtriranaLista.add(f);
                       
                    }
            }else if(!fizijatar.getKorisnickoIme().isEmpty()){
                if(f.getKorisnickoIme().toLowerCase().contains(fizijatar.getKorisnickoIme().toLowerCase())){
                            filtriranaLista.add(f);
                        }
            }
            
        }
        return filtriranaLista;
    }
        
}
