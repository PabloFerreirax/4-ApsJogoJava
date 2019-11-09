/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aps.quarto.semestre.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name = "tb_final")
@DynamicUpdate(true)
@DynamicInsert(true)
@PrimaryKeyJoinColumn(name = "id_fase")
public class Final  extends Fase implements Serializable {
   
	
   
    /**
	 * 
	 */
	private static final long serialVersionUID = -3465081158008612697L;

	@Column(name = "intervalo_inf")
    private Integer intervaloInf;
    
    @Column(name = "intevalo_sup")
    private Integer intervaloSup;

	public Integer getIntervaloInf() {
		return intervaloInf;
	}

	public void setIntervaloInf(Integer intervaloInf) {
		this.intervaloInf = intervaloInf;
	}

	public Integer getIntervaloSup() {
		return intervaloSup;
	}

	public void setIntervaloSup(Integer intervaloSup) {
		this.intervaloSup = intervaloSup;
	}
        
        public static Final valueOf(Fase fase){
            Final f = new Final();
            
            f.setDsFase(fase.getDsFase());
            f.setSeqFase(fase.getSeqFase());
            f.setTipoFase(fase.getTipoFase());
            f.setIdFase(fase.getIdFase());
            f.setAtivo(fase.getAtivo());
            f.setImagem(fase.getImagem());
            
            return f;
        }
}
