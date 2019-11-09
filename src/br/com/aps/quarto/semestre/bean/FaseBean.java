package br.com.aps.quarto.semestre.bean;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import br.com.aps.quarto.semestre.modelo.Combate;
import br.com.aps.quarto.semestre.modelo.Dialogo;
import br.com.aps.quarto.semestre.modelo.Fase;
import br.com.aps.quarto.semestre.modelo.Progresso;
import br.com.aps.quarto.semestre.modelo.Resposta;
import br.com.aps.quarto.semestre.modelo.TipoFase;
import br.com.aps.quarto.semestre.modelo.Usuario;
import br.com.aps.quarto.semestre.service.FaseService;
import br.com.aps.quarto.semestre.service.UsuarioService;

public class FaseBean {
	private String usuario;
	private Progresso progresso;
	private UsuarioService usuarioService;
	private FaseService faseService;
	private List<Resposta> respostas;
	private Combate combate;
	private int idResposta;
	private String msg;
	private String respCompletar;
	
	public FaseBean() {
		usuarioService = new UsuarioService();
		faseService = new FaseService();
	}
	
	private void responderFase(int ponto) {
		try {
			Fase fase = faseService.getProximaFase(getFase(), getProgresso().getQtdPontos()+ponto);
			
			Progresso progresso = new Progresso();
			progresso.setDataProgresso(new Date());
			progresso.setQtdPontos(getProgresso().getQtdPontos()+ponto);
			progresso.setFase(fase);
			progresso.setUsuario(getProgresso().getUsuario());
			
			usuarioService.save(progresso);
			
			setProgresso(progresso);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void batalhar() {
		int dado = faseService.valorDadoCombate(getProgresso().getQtdPontos(), getCombate().getDificuldade());
		
		StringBuilder msg= new StringBuilder();
		
		if(getCombate().getDificuldade()<=dado) {
			responderFase(1);
			
			msg.append("Você derrotou o inimigo com um golpe de ");
			msg.append(dado);
			msg.append(" de força");
			
			setMsg(msg.toString());
		}
		else {
			msg.append("Você não derrotou o inimigo, pois o golpe teve ");
			msg.append(dado);
			msg.append(" de força e era necessário ");
			msg.append(getCombate().getDificuldade());
			
			responderFase(0);
			
			setMsg(msg.toString());
		} 
	}

	public void setIdResposta(int idResposta) {
		this.idResposta = idResposta;
		
		Resposta resposta = null;
		for (Resposta r: respostas) {
			if(r.getIdResposta()==idResposta)
				resposta = r;
		}
		
		responderFase((resposta.getPontoResposta()!=null)?resposta.getPontoResposta():0);
		
		setMsg((resposta.getMsgResposta()==null)?"":resposta.getMsgResposta());
	}

	
	
	public String getRespCompletar() {
		return respCompletar;
	}

	public void setRespCompletar(String respCompletar) {
		this.respCompletar = respCompletar;
		
		if(getRespostas().get(0).getTextResposta().equals(respCompletar)) {
			
			setMsg(getRespostas().get(0).getMsgResposta());
			responderFase(1);

		}
		else{
			responderFase(0);
			setMsg("Você não acertou o código");
		}
			
	}

	public String getImg() {
		byte[] content = getFase().getImagem();
		String base64Encoded = "";
		try {
			base64Encoded = new String(Base64.encodeBase64(content), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return base64Encoded;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Fase getFase() {
		return progresso.getFase();
	}
	
	public List<Dialogo> getDialogos() {
		return faseService.getDialogosByFase(progresso.getFase());
	}

	private void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}
	
	public List<Resposta> getRespostas() {
		return respostas;
	}

	private void setCombate(Combate combate) {
		this.combate = combate;
	}
    
	public Combate getCombate() {
		return combate;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
		
		Usuario user = usuarioService.getUsuarioByNickName(usuario);
		
		setProgresso(usuarioService.getProgressoByUsuario(user));
	}

	public Progresso getProgresso() {
		return progresso;
	}

	public void setProgresso(Progresso progresso) {
		this.progresso = progresso;
		
		TipoFase tpFase = progresso.getFase().getTipoFase();
		
		if(tpFase == TipoFase.PERGUNTA || tpFase == TipoFase.COMPLETAR) {
			setRespostas(faseService.getRespostaByFase(getProgresso().getFase()));
		}
		else if(tpFase==TipoFase.COMBATE) {
			setCombate(faseService.getCombateByFase(getProgresso().getFase()));
		}
		else {
			setCombate(null);
			setRespostas(null);
		}
	}
	
}
