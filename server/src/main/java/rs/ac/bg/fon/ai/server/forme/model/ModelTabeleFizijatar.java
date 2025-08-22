/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.forme.model;

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
    
        
}
