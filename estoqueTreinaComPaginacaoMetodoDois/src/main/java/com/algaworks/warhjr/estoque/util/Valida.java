package com.algaworks.warhjr.estoque.util;

import java.math.BigDecimal;

import com.algaworks.warhjr.estoque.model.Produto;
import com.algaworks.warhjr.estoque.service.ServiceException;

public class Valida {
	
	public Valida(){
		super();
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
