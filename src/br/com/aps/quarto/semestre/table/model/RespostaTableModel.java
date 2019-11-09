/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aps.quarto.semestre.table.model;

import br.com.aps.quarto.semestre.modelo.Dialogo;
import br.com.aps.quarto.semestre.modelo.Resposta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tsdev04
 */
public class RespostaTableModel extends AbstractTableModel{

    private List<Resposta> respostas;
    private String[] columns = {"Resposta", "Ponto", "Mensagem"};

    public RespostaTableModel(List<Resposta> respostas) {
        setRespostas(respostas);
    }
    
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public int getRowCount() {
        return (respostas==null)?0:respostas.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch(columnIndex){
             case 0:
                 return respostas.get(rowIndex).getTextResposta();
             case 1:
                 return respostas.get(rowIndex).getPontoResposta();
             case 2:
                 return respostas.get(rowIndex).getMsgResposta();
             default:
                 throw new IllegalArgumentException("Coluna não existe");
         }
    }
    
    public Resposta getRowByIndex(int index){
        if(index>(respostas.size()-1))
            throw new IllegalArgumentException("Não existe elemento para a posição informada");
        return respostas.get(index);
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    private void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
        
        fireTableDataChanged();
    }
    
    public void addRow(Resposta resposta){
        respostas.add(resposta);
        
        fireTableDataChanged();
    }
    
    public void removeRow(int index){
        if(index>(respostas.size()-1))
            throw new IllegalArgumentException("Não existe elemento para a posição informada");
        
        respostas.remove(index);
        
        fireTableDataChanged();
    }
    
    public void update(int position, Resposta resposta){
        respostas.get(position).setMsgResposta(resposta.getMsgResposta());
        respostas.get(position).setPontoResposta(resposta.getPontoResposta());
        respostas.get(position).setTextResposta(resposta.getTextResposta());
        
        fireTableDataChanged();
    }
}
