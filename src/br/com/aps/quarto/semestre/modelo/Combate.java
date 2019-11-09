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
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="tb_combate")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Combate implements Serializable {
    
    @Id
    @Column(name="id_combate")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCombate;

    @ManyToOne
    @JoinColumn(name="id_Fase", unique = true)
    private Fase fase;
    
 
    @Column(name="dificuldade")
    private Integer dificuldade;
    
    public Long getIdCombate() {
        return idCombate;
    }

    public void setIdCombate(Long idCombate) {
        this.idCombate = idCombate;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public Integer getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Integer dificuldade) {
        this.dificuldade = dificuldade;
    }

    @Override
    public String toString() {
        return "Combate{" + "idCombate=" + idCombate + ", fase=" + fase + ", dificuldade=" + dificuldade + '}';
    }
}
