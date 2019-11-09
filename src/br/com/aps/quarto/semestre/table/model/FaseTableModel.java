/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aps.quarto.semestre.table.model;

import br.com.aps.quarto.semestre.modelo.Dialogo;
import br.com.aps.quarto.semestre.modelo.Fase;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tsdev04
 */
public class FaseTableModel extends AbstractTableModel{

    private List<Fase> fases;
    private String[] columns = {"Id", "Sequ�ncia", "Descri��o", "Tipo"};

    public FaseTableModel() {
        
    }

    public List<Fase> getFases() {
        return fases;
    }

    public void setFases(List<Fase> fases) {
        this.fases = fases;
        
        fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public int getRowCount() {
        return (fases==null)?0:fases.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch(columnIndex){
             case 0:
                 return fases.get(rowIndex).getIdFase();
             case 1:
                 return fases.get(rowIndex).getSeqFase();
             case 2:
                 return fases.get(rowIndex).getDsFase();
             case 3:
                 return fases.get(rowIndex).getTipoFase().toString();
             default:
                 throw new IllegalArgumentException("Coluna não existe");
         }
    }
    
    public Fase getRowByIndex(int index){
        if(index>(fases.size()-1))
            throw new IllegalArgumentException("Não existe elemento para a posição informada");
        return fases.get(index);
    }
    
    public void addRow(Fase dialogo){
        fases.add(dialogo);
        
        fireTableDataChanged();
    }
    
    public void removeRow(int index){
        if(index>(fases.size()-1))
            throw new IllegalArgumentException("Não existe elemento para a posição informada");
        
        fases.remove(index);
        
        fireTableDataChanged();
    }
}
