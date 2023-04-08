package com.algaworks.warhjr.financeiro.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.warhjr.financeiro.model.Usuario;

public class Usuarios {

	private static final long serialVersionUID = 1L;

	private Boolean loggerIn;

	private Usuario usuario;
	private String nome;
	private String senha;
	private EntityManager manager;
	private int count;
	
    @Inject
	public Usuarios(EntityManager manager) {
		this.manager = manager;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> login(String login, String senha) {
		TypedQuery<Usuario> query = manager.createQuery(
				"from Usuario c where c.nome = :Paramlogin and c.senha = :Paramsenha)", Usuario.class);
		query.setParameter("Paramlogin", limparScriptAtaque(login));
		query.setParameter("Paramsenha", limparScriptAtaque(senha));
	
		return query.getResultList();
	}
	
	public final String limparScriptAtaque(String str) {
		str = str.trim();
		str = str.replace(" ", "");
		str = str.toLowerCase();
		str = str.replace("=", "");
		str = str.replace("'", "");
		str = str.replace(" or ", "");
		str = str.replace(" and ", "");
		str = str.replace("(", "");
		str = str.replace(")", "");
		str = str.replace("<", "[");
		str = str.replace(">", "]");
		str = str.replace("update", "");
		str = str.replace("-shutdown", "");
		str = str.replace("--", "");
		str = str.replace("'", "");
		str = str.replace("#", "");
		str = str.replace("$", "");
		str = str.replace("%", "");
		str = str.replace("�", "");
		str = str.replace("&", "");
		str = str.replace("'1'='1'", "");
		str = str.replace("'1'-'1'", "");
		str = str.replace("'or'1'='1'", "");
		str = str.replace("--", "");
		str = str.replace("insert", "");
		str = str.replace("drop", "");
		str = str.replace("delete", "");
		str = str.replace("xp_", "");
		str = str.replace("select", "");
		str = str.replace("applet", "");
		str = str.replace("drop table", "");
		str = str.replace("show table", "");
		str = str.replace("object", "");
		str = str.replace("*", "");
		return str;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Boolean getLoggerIn() {
		return loggerIn;
	}

	public void setLoggerIn(Boolean loggerIn) {
		this.loggerIn = loggerIn;
	}

}
