package com.algaworks.estoque.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.estoque.model.Usuario;

public class Usuarios {
	
	private EntityManager manager;
	
	public Usuarios(EntityManager manager) {
		this.manager = manager;
	}
	
	public void RepositLogin(Usuario usuario) {
		this.manager.persist(usuario);
	}
	
	public List<Usuario> login(String login, String senha) {
		TypedQuery<Usuario> query = manager.createQuery(
				"from Usuario c where c.login = :Paramlogin and c.senha = :Paramsenha)", Usuario.class);
		query.setParameter("Paramlogin", login);
		query.setParameter("Paramsenha", senha);
	
		return query.getResultList();
	}
}
