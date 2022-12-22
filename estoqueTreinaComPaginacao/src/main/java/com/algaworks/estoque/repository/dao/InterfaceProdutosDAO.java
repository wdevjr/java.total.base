package com.algaworks.estoque.repository.dao;

import java.util.List;

import com.algaworks.estoque.model.Produto;

public interface InterfaceProdutosDAO {



	public abstract List todosProdutos();
	
	public abstract List todosProdutos(String nome ,int m, int p);
	
	public abstract int totalDeProdutos();

	List<Produto> todosProdutosComNome(String nome);

}