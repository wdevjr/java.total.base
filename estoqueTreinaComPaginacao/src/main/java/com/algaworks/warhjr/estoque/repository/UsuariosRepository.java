package com.algaworks.warhjr.estoque.repository;

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
		query.setParameter("Paramlogin", login);
		query.setParameter("Paramsenha", senha);
	
		return query.getResultList();
	}
}
