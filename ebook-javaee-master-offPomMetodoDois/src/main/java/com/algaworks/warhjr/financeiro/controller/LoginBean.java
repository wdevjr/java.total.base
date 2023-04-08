package com.algaworks.warhjr.financeiro.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import com.algaworks.warhjr.financeiro.model.Usuario;
import com.algaworks.warhjr.financeiro.repository.Usuarios;

@Named
@RequestScoped
public class LoginBean extends HttpServlet{
	
	
	private List<Usuario> loginUser = new ArrayList<Usuario>();
	
	private String nomeUsuario;
	private String senha;


	@Inject
	private Usuario usuario;
	
	@Inject
	private Usuarios UsuariosRepository;



	public String login() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);


		loginUser = UsuariosRepository.login(nomeUsuario, senha);
		
		if (loginUser.size() == 1) {
			this.usuario.setNome(this.nomeUsuario);
		    this.usuario.setDataLogin(new Date());
			session.setAttribute("nomeUsuario",this.usuario);
			return "/financeiro/inicio?faces-redirect=true";
			
		} else {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			FacesMessage mensagem = new FacesMessage("Usuário/senha inválidos!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null, mensagem);
		}
		
		return null;
	}
	
	public String logout() {
		//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}
	


	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}