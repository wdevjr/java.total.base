package com.algaworks.warhjr.estoque.repository.produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.algaworks.warhjr.estoque.model.Produto;
import com.algaworks.warhjr.estoque.repository.produto.dao.InterfaceProdutosDAO;

public class ProdutosRepository implements InterfaceProdutosDAO {

	private EntityManager manager;

	public ProdutosRepository(EntityManager manager) {
		this.manager = manager;
	}

	public void adicionar(Produto produto) {
		this.manager.persist(produto);
	}

	public void editar(Produto obj) {
		this.manager.merge(obj);
	}

	public void excluir(Produto obj) {
		//this.manager.remove(obj);
		this.manager.remove(this.manager.contains(obj) ? obj :
		this.manager.merge(obj));
	}

	public List<Produto> todos() {
		TypedQuery<Produto> query = manager.createQuery("from Produto", Produto.class);
		return query.getResultList();
	}

	@Override
	public List<Produto> todosProdutosCount() {

		try {
			Query q = manager.createQuery("select object(l) from Produto as l");

			return q.getResultList();

		} finally {
			manager.close();
		}

	}


	public List<Produto> todosProdutosNomeComParam(String nome, int maximo, int atual) {
		try {
			TypedQuery<Produto> query = manager
					.createQuery("from Produto where upper(nome) like upper(:nome)", Produto.class)
					.setMaxResults(maximo).setFirstResult(atual - 1);
			query.setParameter("nome", "%" + (nome == null ? "" : nome) + "%");
			return query.getResultList();
		} finally {
			manager.close();
		}

	}

	@Override
	public List<Produto> todosProdutosComNome(String nome) {
		try {
			TypedQuery<Produto> query = manager.createQuery("from Produto where upper(nome) like upper(:nome)",
					Produto.class);
			query.setParameter("nome","%"+ nome + "%");
			return query.getResultList();
		} finally {
			manager.close();
		}
	}

	@Override
	public int todosProdutosComContagem() {

			int total = ((Long) manager.createQuery("select count(l) from Produto l").getSingleResult()).intValue();
			return total;

	}

	@Override
	public int totalDeProdutos() {
		try {
			int total = ((Long) manager.createQuery("select count(l) from Produto l").getSingleResult()).intValue();
			return total - 3;
		} finally {
			manager.close();
		}
	}

	public Produto consultarPorId(Long id) {

		Produto produto = null;

		produto = manager.find(Produto.class, id);

		return produto;
	}




}