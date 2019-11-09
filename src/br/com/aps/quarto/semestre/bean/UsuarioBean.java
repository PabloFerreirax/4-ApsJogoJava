package br.com.aps.quarto.semestre.bean;

import br.com.aps.quarto.semestre.modelo.Usuario;
import br.com.aps.quarto.semestre.service.UsuarioService;

public class UsuarioBean {
	private String usuario;
	private String senha;
	private String nome;
	
	private UsuarioService service;
	
	public UsuarioBean() {
		service = new UsuarioService();
	}
	
	public void salvar() {
		Usuario usuario = new Usuario();
		
		usuario.setNickname(getUsuario());
		usuario.setNmUsuario(getNome());
		usuario.setSenha(getSenha());
		
		service.save(usuario);
	}
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean logar() {		
		return service.login(getUsuario(), getSenha());
	}
}
