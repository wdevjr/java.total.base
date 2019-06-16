package br.com.warhjr.controller;

import javax.faces.model.DataModel;

import org.hibernate.Session;

import br.com.warhjr.dao.AdministradorHibernateDAO;
import br.com.warhjr.model.Administrador;

public class ControllerAdministrador {

	private Administrador administrador;
	private DataModel model;
	public static String nome;
	private Session session;

	public boolean verificaLogin(String login, String senha) {
		boolean autenticado;

		AdministradorHibernateDAO admaux = new AdministradorHibernateDAO();
		autenticado = admaux.verificaLoginAdm(login, senha);

		return autenticado;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public DataModel getModel() {
		return model;
	}

	public void setModel(DataModel model) {
		this.model = model;
	}

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		ControllerAdministrador.nome = nome;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
