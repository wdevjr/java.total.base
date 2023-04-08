package com.algaworks.warhjr.financeiro.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.algaworks.warhjr.financeiro.model.Lancamento;
import com.algaworks.warhjr.financeiro.repository.Lancamentos;
import com.algaworks.warhjr.financeiro.util.Transactional;

public class CadastroLancamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Lancamentos lancamentos;

	@Transactional
	public void salvar(Lancamento lancamento) throws NegocioException {
		if (lancamento.getDataPagamento() != null && lancamento.getDataPagamento().after(new Date())) {
			throw new NegocioException("Data de pagamento não pode ser uma data futura.");
		}
		if (lancamento.getId() != null) {
			this.lancamentos.guardar(lancamento);
		} else {
			this.lancamentos.adicionar(lancamento);
		}
	}

	@Transactional
	public void excluir(Lancamento lancamento) throws NegocioException {
		lancamento = this.lancamentos.porId(lancamento.getId());

		if (lancamento.getDataPagamento() != null) {
			throw new NegocioException("Não é possível excluir um lançamento pago!");
		}

		this.lancamentos.remover(lancamento);
	}

}