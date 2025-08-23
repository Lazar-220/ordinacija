/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.klijent.forme.modeliTabela;

import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class ModelTabeleTipPacijenta extends AbstractTableModel{
    private List<TipPacijenta>lista;
    private static String[]kolone={"pol","starosna dob"};

    public ModelTabeleTipPacijenta(List<TipPacijenta> lista) {
        this.lista = lista;
    }

    public List<TipPacijenta> getLista() {
        return lista;
    }

    public void setLista(List<TipPacijenta> lista) {
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
        TipPacijenta t=lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getPol().toString();
            case 1:
                return t.getStarosnaDob().toString();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
}
