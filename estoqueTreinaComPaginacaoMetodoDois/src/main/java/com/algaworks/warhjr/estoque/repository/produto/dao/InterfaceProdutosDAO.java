package com.algaworks.warhjr.estoque.repository.produto.dao;

import java.util.List;

import com.algaworks.warhjr.estoque.model.Produto;

public interface InterfaceProdutosDAO {



//	public abstract List todosProdutos();
//	
//	public abstract List todosProdutos(String nome ,int m, int p);
	
	public abstract int totalDeProdutos();

	public abstract List<Produto> todosProdutosComNome(String nome);

	public abstract List<Produto> todosProdutosNomeComParam(String nome, int maximo, int atual);

	public abstract int todosProdutosComContagem();

	public abstract List<Produto> todosProdutosCount();



}