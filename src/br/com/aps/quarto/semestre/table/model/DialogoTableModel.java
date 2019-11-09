/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aps.quarto.semestre.table.model;

import br.com.aps.quarto.semestre.modelo.Dialogo;
import br.com.aps.quarto.semestre.util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tsdev04
 */
public class DialogoTableModel extends AbstractTableModel{

    private List<Dialogo> dialogos;
    private String[] columns = {"Di�logo"};

    public DialogoTableModel() {
        dialogos = new ArrayList<Dialogo>();
    }
    
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public int getRowCount() {
        return (dialogos==null)?0:dialogos.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch(columnIndex){
             case 0:
                 return dialogos.get(rowIndex).getTextDialogo();
             default:
                 throw new IllegalArgumentException("Coluna não existe");
         }
    }
    
    public Dialogo getRowByIndex(int index){
        if(index>(dialogos.size()-1))
            throw new IllegalArgumentException("Não existe elemento para a posição informada");
        return dialogos.get(index);
    }
    
    public List<Dialogo> getDialogos(){
        return this.dialogos;
    }
    
    public void setDialogos(List<Dialogo> dialogos){
        this.dialogos = dialogos;
        
        fireTableDataChanged();
    }
    
    public void addRow(Dialogo dialogo){
        dialogos.add(dialogo);
        
        fireTableDataChanged();
    }
    
    public void removeRow(int index){
        if(index>(dialogos.size()-1))
            throw new IllegalArgumentException("Não existe elemento para a posição informada");
        
        dialogos.remove(index);
        
        fireTableDataChanged();
    }
    
    public void update(int position, Dialogo dialogo){
        dialogos.get(position).setTextDialogo(dialogo.getTextDialogo());
        
        fireTableDataChanged();
    }
}
