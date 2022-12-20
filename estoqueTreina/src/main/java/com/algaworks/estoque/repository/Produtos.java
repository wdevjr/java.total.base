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

	public void excluir(Produto obj) {
		this.manager.remove(obj);
		//this.manager.remove(this.manager.contains(obj) ? obj : this.manager.merge(obj));
	}

	public List<Produto> todos() {
		TypedQuery<Produto> query = manager.createQuery("from Produto", Produto.class);
		return query.getResultList();
	}

	public List<Produto> porNomeNaoExato(String nome, int maximo, int atual) {
		TypedQuery<Produto> query = manager.createQuery("from Produto where upper(nome) like upper(:nome)",
				Produto.class).setMaxResults(maximo).setFirstResult(atual);
		query.setParameter("nome", "%" + (nome == null ? "" : nome) + "%");

		return query.getResultList();
	}

    public int totaldeProdutos() {
    	
    		int total = ((Long) manager.createQuery("select count(l) from Produto l").getSingleResult()).intValue();
    		return total;
		
    } 

	public Produto consultarPorId(Long id) {

		Produto produto = null;

		produto = manager.find(Produto.class, id);

		return produto;
	}

}