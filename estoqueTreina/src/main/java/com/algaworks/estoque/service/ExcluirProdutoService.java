package com.algaworks.estoque.service;

import com.algaworks.estoque.model.Produto;
import com.algaworks.estoque.repository.Produtos;

public class ExcluirProdutoService {

	
private Produtos produtos;
	
	public ExcluirProdutoService(Produtos produtos) {
		this.produtos = produtos;
	}
	
    public void ExcluiProduto(Produto produto) throws ServiceException
    {
    	this.produtos.excluir(produto);
    	
    }
    
    
}
