package com.algaworks.estoque.service;

import java.math.BigDecimal;
import java.util.List;

import com.algaworks.estoque.model.Produto;
import com.algaworks.estoque.repository.ProdutosRepository;

public class EditarProdutoService {
	
	
	private ProdutosRepository produtos;
	
	public EditarProdutoService(ProdutosRepository produtos) {
		this.produtos = produtos;
	}
	
    public void EditaProduto(Produto produto)
    {
    	try {
			validarProduto(produto);
			this.produtos.editar(produto);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
    	
    }
	
	public Produto listarProd(Long id) throws ServiceException {

		return this.produtos.consultarPorId(id);
		
		}

	private void validarProduto(Produto produto) throws ServiceException {
		if (produto.getNome() == null || produto.getNome().equals("")) {
			throw new ServiceException("Nome deve ser informado.");
		} else if (produto.getPrecoCusto() == null) {
			throw new ServiceException("Preço de custo deve ser informado.");
		} else if (produto.getQuantidadeEstoque() == null) {
			throw new ServiceException("Quantidade em estoque deve ser informada.");
		} else if (produto.getPrecoCusto().compareTo(BigDecimal.ZERO) < 0) {
			throw new ServiceException("Preço de custo deve ser maior que zero.");
		} else if (produto.getQuantidadeEstoque() < 0) {
			throw new ServiceException("Quantidade em estoque deve ser maior que zero.");
		}
	}

}
