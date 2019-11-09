/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aps.quarto.semestre.modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="tb_fase")
@DynamicUpdate(true)
@DynamicInsert(true)
@Inheritance(strategy = InheritanceType.JOINED)
public class Fase implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6545363114231301445L;


	@Id
    @Column(name="id_fase")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFase;
    
    @Column(name="ds_fase" , length = 60)
    private String dsFase;
    
    @Column(name="seq_fase", unique = true, columnDefinition="serial")
    private Integer seqFase;

    @Column(columnDefinition = "bool default true")
    private Boolean ativo;

    @Enumerated(value = EnumType.ORDINAL)
	private TipoFase tipoFase;
    
    @Lob
    private byte[] imagem;
    
    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    

    public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Long getIdFase() {
        return idFase;
    }

    public void setIdFase(Long idFase) {
        this.idFase = idFase;
    }

    public TipoFase getTipoFase() {
        return this.tipoFase;
    }

    public void setTipoFase(TipoFase tipoFase) {
        this.tipoFase = tipoFase;
    }

    public String getDsFase() {
        return dsFase;
    }

    public void setDsFase(String dsFase) {
        this.dsFase = dsFase;
    }

    public Integer getSeqFase() {
        return seqFase;
    }

    public void setSeqFase(Integer seqFase) {
        this.seqFase = seqFase;
    }

    @Override
    public String toString() {
            return "Fase [idFase=" + idFase + ", dsFase=" + dsFase + ", seqFase=" + seqFase + ", tipoFase=" + tipoFase
                            + "]";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    public boolean isNullOrEmpty() {
        
        if (getIdFase()==null && getDsFase()== null && getSeqFase()==null && getTipoFase() == null) {
            return true;
        }
       
        return false;
    }

    public Fase() {
    }
    
    
}
