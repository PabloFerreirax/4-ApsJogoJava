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
@Table(name="tb_resposta")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Resposta implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 6074050443265152604L;


	@Id
    @Column(name="id_resposta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResposta;
    
    
    @Column(name="text_resposta", columnDefinition="text")
    private String textResposta;
    
    @ManyToOne
    @JoinColumn(name = "id_fase")
    private Fase fase;
    
    @Column(name = "ponto_resposta")
    private Integer pontoResposta;
    
    @Column(name = "msg_pergunta", columnDefinition="text")
    private String msgResposta;

    public Long getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(Long idResposta) {
        this.idResposta = idResposta;
    }

    public String getTextResposta() {
        return textResposta;
    }

    public void setTextResposta(String textResposta) {
        this.textResposta = textResposta;
    }

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public Integer getPontoResposta() {
		return pontoResposta;
	}

	public void setPontoResposta(Integer pontoResposta) {
		this.pontoResposta = pontoResposta;
	}

	public String getMsgResposta() {
		return msgResposta;
	}

	public void setMsgResposta(String msgResposta) {
		this.msgResposta = msgResposta;
	}

	@Override
	public String toString() {
		return "Resposta [idResposta=" + idResposta + ", textResposta=" + textResposta + ", fase=" + fase
				+ ", pontoResposta=" + pontoResposta + ", msgResposta=" + msgResposta + "]";
	}
    
}
