/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aps.quarto.semestre.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author Leandro
 */


@Entity
@Table(name="tb_progresso")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Progresso implements Serializable {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
    @Column(name="id_progresso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgresso;
    
    
    @ManyToOne
    @JoinColumn(name="id_fase")
    private Fase fase;
    
    @Column(name="qtd_pontos")
    private Integer qtdPontos;
    
    @Column(name="data_progresso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataProgresso;
    
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getIdProgresso() {
        return idProgresso;
    }

    public void setIdProgresso(Long idProgresso) {
        this.idProgresso = idProgresso;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public Integer getQtdPontos() {
		return qtdPontos;
	}

	public void setQtdPontos(Integer qtdPontos) {
		this.qtdPontos = qtdPontos;
	}

	public Date getDataProgresso() {
        return dataProgresso;
    }

    public void setDataProgresso(Date dataProgresso) {
        this.dataProgresso = dataProgresso;
    }
    
    
    
    
    
    
}
