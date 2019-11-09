/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aps.quarto.semestre.table.model;

import br.com.aps.quarto.semestre.modelo.Fase;
import br.com.aps.quarto.semestre.modelo.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tsdev04
 */
public class UsuarioTableModel extends AbstractTableModel{

    private List<Usuario> usuarios;
    private String[] columns = {"id", "Nome", "Nickmane"};

    public UsuarioTableModel() {
        
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        
        fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public int getRowCount() {
        return (usuarios==null)?0:usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch(columnIndex){
             case 0:
                 return usuarios.get(rowIndex).getIdUsuario();
             case 1:
                 return usuarios.get(rowIndex).getNmUsuario();
             case 2:
                 return usuarios.get(rowIndex).getNickname();
             default:
                 throw new IllegalArgumentException("Coluna não existe");
         }
    }
    
    public Usuario getRowByIndex(int index){
        if(index>(usuarios.size()-1))
            throw new IllegalArgumentException("Não existe elemento para a posição informada");
        return usuarios.get(index);
    }
    
    public void addRow(Usuario usuario){
        usuarios.add(usuario);
        
        fireTableDataChanged();
    }
    
    public void removeRow(int index){
        if(index>(usuarios.size()-1))
            throw new IllegalArgumentException("Não existe elemento para a posição informada");
        
        usuarios.remove(index);
        
        fireTableDataChanged();
    }
}
