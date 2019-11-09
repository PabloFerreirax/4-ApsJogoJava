/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aps.quarto.semestre.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Gabriel
 */
public enum TipoFase implements Serializable {    
    PERGUNTA(0,"PERGUNTA"), COMBATE(1,"COMBATE"), COMPLETAR(2,"COMPLETAR"), FINAL(3, "FINAL");

    private int cod;
    private String value;
    
    TipoFase(int cod, String value) {
        this.cod = cod;
        this.value = value;
    }

    public static List<String> getValues(){
        List<String> values = new ArrayList<>();
        
        for (TipoFase e : TipoFase.values()){
               values.add(e.value);
        }
        
        return values;
    }
    
    public static TipoFase getTipoFaseByCod(int cod){
        List<String> values = new ArrayList<>();
        
        for (TipoFase e : TipoFase.values()){
               if(e.cod == cod)
                   return e;
        }
        
        throw new IllegalArgumentException("Não existe um tipo de fase para o código informado");
    }

    @Override
    public String toString() {
        return this.value;
    }
    
    public int toInt(){
    	return this.cod;
    }
    
    
    
}
