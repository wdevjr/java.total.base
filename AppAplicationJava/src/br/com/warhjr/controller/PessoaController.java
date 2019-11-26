package br.com.warhjr.controller;

import java.sql.SQLException;
import java.util.List;

import br.com.warhjr.dao.PessoaDAO;
import br.com.warhjr.model.Pessoa;

public class PessoaController {

	public List<Pessoa> BuscaPessoas(String nomePessoa) throws Exception {
		PessoaDAO auxPessoaFind = new PessoaDAO();

		return auxPessoaFind.findPessoas(nomePessoa);
	}

}
