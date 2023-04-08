package com.algaworks.warhjr.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.warhjr.financeiro.model.Pessoa;



public class Pessoas implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject
	public Pessoas(EntityManager manager) {
		this.manager = manager;
	}

	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}
	
	public List<Pessoa> todas() {
		TypedQuery<Pessoa> query = manager.createQuery(
				"from Pessoa", Pessoa.class);
		return query.getResultList();
	}
	
	public List<Pessoa> ProcPesquisa(Object desc)
	{
	 String hql="select p from Pessoa p where p.nome like :nome";
	 TypedQuery<Pessoa> query = manager.createQuery(hql,Pessoa.class);
	 query.setParameter("nome", "%"+desc+"%");
	 List<Pessoa> resultlList = query.getResultList();
	 
	 return resultlList;
	}
}
