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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name = "tb_dialogo")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Dialogo implements Serializable {
   
    /**
	 * 
	 */
	private static final long serialVersionUID = -947180909053517952L;

	@Id
    @Column(name="id_dialogo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDialogo;
    
    @ManyToOne
    @JoinColumn(name="idFase")
    private Fase fase;
    
    @Column(name = "seq_dialogo")
    private Integer seqDialogo;
    
    @Column(name="text_dialogo", columnDefinition = "text")
    private String textDialogo;

    public Long getIdDialogo() {
        return idDialogo;
    }

    public void setIdDialogo(Long idDialogo) {
        this.idDialogo = idDialogo;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public Integer getSeqDialogo() {
        return seqDialogo;
    }

    public void setSeqDialogo(Integer seqDialogo) {
        this.seqDialogo = seqDialogo;
    }

    public String getTextDialogo() {
        return textDialogo;
    }

    public void setTextDialogo(String textDialogo) {
        this.textDialogo = textDialogo;
    }

	@Override
	public String toString() {
		return "Dialogo [idDialogo=" + idDialogo + ", fase=" + fase + ", seqDialogo=" + seqDialogo + ", textDialogo="
				+ textDialogo + "]";
	}
    
    
    
    
}
