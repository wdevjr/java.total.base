package br.com.warhjr.controller;

import java.sql.SQLException;
import java.util.List;

import br.com.warhjr.dao.CidadeDAO;
import br.com.warhjr.model.Cidade;

public class CidadeController {

	public List<Cidade> buscaCidadePorNome(String nome) throws Exception {
		CidadeDAO dao = new CidadeDAO();

		return dao.findCidades(nome);
	}

}
