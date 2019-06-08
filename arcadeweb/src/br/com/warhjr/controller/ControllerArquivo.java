package br.com.warhjr.controller;

import java.util.Date;
import java.util.List;

import br.com.warhjr.dao.ArquivoHibernateDAO;
import br.com.warhjr.model.Arquivo;

public class ControllerArquivo {

	ArquivoHibernateDAO auxControllerArquivos = new ArquivoHibernateDAO();

	public void Inserir(Arquivo arquivo) throws Exception {
		ArquivoHibernateDAO auxControllerArquivos = new ArquivoHibernateDAO();
//		if (arquivo.getDescricaoarquivos() == null)
//		{
//			throw new Exception("Descrição é Necessario!");
//		} else {
		auxControllerArquivos.Salvar(arquivo);
		// }
	}

	public void Excluir(Arquivo arquivo) {

		auxControllerArquivos.excluir(arquivo);

	}

	public void Editar(Arquivo arquivo) throws Exception {
		auxControllerArquivos.Editar(arquivo);
	}

	public List<Arquivo> lista() {

		ArquivoHibernateDAO auxControllerList = new ArquivoHibernateDAO();

		return auxControllerList.listar();

	}

	public List<Arquivo> listaAvancada(Date datainicial, Date datafinal, String nome) {

		return auxControllerArquivos.ListaEntreDataseNome(datainicial, datafinal, '%' + nome + '%');

	}
}
