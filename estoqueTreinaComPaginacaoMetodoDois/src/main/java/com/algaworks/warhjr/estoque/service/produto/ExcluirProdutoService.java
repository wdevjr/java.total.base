package com.algaworks.warhjr.estoque.service.produto;

import com.algaworks.warhjr.estoque.model.Produto;
import com.algaworks.warhjr.estoque.repository.produto.ProdutosRepository;
import com.algaworks.warhjr.estoque.util.ServiceException;

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
