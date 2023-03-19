package com.algaworks.warhjr.estoque.repository.usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.warhjr.estoque.model.Usuario;

public class UsuariosRepository {
	
	private EntityManager manager;
	
	public UsuariosRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void RepositLogin(Usuario usuario) {
		this.manager.persist(usuario);
	}
	
	public List<Usuario> login(String login, String senha) {
		TypedQuery<Usuario> query = manager.createQuery(
				"from Usuario c where c.login = :Paramlogin and c.senha = :Paramsenha)", Usuario.class);
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
}
