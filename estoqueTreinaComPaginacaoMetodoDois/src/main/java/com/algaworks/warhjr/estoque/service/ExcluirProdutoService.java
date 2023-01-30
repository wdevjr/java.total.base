package com.algaworks.warhjr.estoque.service;

import com.algaworks.warhjr.estoque.model.Produto;
import com.algaworks.warhjr.estoque.repository.ProdutosRepository;

public class ExcluirProdutoService {

	
private ProdutosRepository produtos;
	
	public ExcluirProdutoService(ProdutosRepository produtos) {
		this.produtos = produtos;
	}
	
    public void ExcluiProduto(Produto produto) throws ServiceException
    {
    	this.produtos.excluir(produto);
    	
    }
    
    
}
