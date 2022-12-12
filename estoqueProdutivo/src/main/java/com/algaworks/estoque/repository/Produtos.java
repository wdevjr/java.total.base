package com.algaworks.estoque.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.estoque.model.Produto;

public class Produtos {

	private EntityManager manager;

	public Produtos(EntityManager manager) {
		this.manager = manager;
	}

	public void adicionar(Produto produto) {
		this.manager.persist(produto);
	}

	public void editar(Produto obj) {
		this.manager.merge(obj);
	}

	public void excluir(Long id) {
		this.manager.remove(id);
	}

	public List<Produto> todos() {
		TypedQuery<Produto> query = manager.createQuery("from Produto", Produto.class);
		return query.getResultList();
	}

	public List<Produto> porNomeNaoExato(String nome) {
		TypedQuery<Produto> query = manager.createQuery("from Produto where upper(nome) like upper(:nome)",
				Produto.class);
		query.setParameter("nome", "%" + (nome == null ? "" : nome) + "%");

		return query.getResultList();
	}



//	@SuppressWarnings("unchecked")
//	public Produto BuscaId(Long idCod) {
//		TypedQuery<Produto> query = manager.createQuery(
//				"from c Produto c where c.id=:id" , Produto.class);
//		query.setParameter("id",idCod);
//		
//	   return produto = (query.getSingleResult());
//	}

//	public List<Produto> consultarPorId(Long id) {
//
//		TypedQuery<Produto> query = manager.createQuery(
//				"from Produto c where c.id=:id ", Produto.class);
//		query.setParameter("id",id);
//	
//		return query.getResultList();
//	}

	public Produto consultarPorId(Long id) {

		Produto produto = null;
		try {
			produto = manager.find(Produto.class, id);
		} finally {
			manager.close();
		}
		return produto;
	}

}