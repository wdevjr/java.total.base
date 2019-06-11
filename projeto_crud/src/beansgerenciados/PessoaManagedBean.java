package beansgerenciados;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import fachada.PessoaFacade;
import modelo.Cidade;
import modelo.Pessoa;
import net.sf.jasperreports.engine.JRException;

@ManagedBean(name = "PessoaManagedBean")
@RequestScoped
public class PessoaManagedBean extends AbstractPessoa implements Serializable  {

	private static final long serialVersionUID = -333995781063775201L;
	private Pessoa pessoa = new Pessoa();
	private Cidade cidade = new Cidade();
	private PessoaFacade pessoaFacade = new PessoaFacade();
	private List<Pessoa> lista;
	private static String descPessoa;
	private boolean listSimouNao;
	private String tempVarDesc;
	private Object session;

	public Pessoa getPessoa() {
		if (pessoa.cidade == null) {
			// pessoa = new Pessoa();
			pessoa.cidade = new Cidade();
		}

		return pessoa;
	}

	public PessoaManagedBean() {
	}

	public Cidade getCidade() {

		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	// public Pessoa getPessoa() {
	// return pessoa;
	// }

	public String getDescPessoa() {
		return descPessoa;
	}

	public void setDescPessoa(String descPessoa) {
		PessoaManagedBean.descPessoa = descPessoa;
	}

	/**
	 * @return the listSimouNao
	 */
	public boolean isListSimouNao() {
		return listSimouNao;
	}

	/**
	 * @param listSimouNao
	 *            the listSimouNao to set
	 */
	public void setListSimouNao(boolean listSimouNao) {
		this.listSimouNao = listSimouNao;
	}

	/**
	 * @return the lista
	 */
	public List<Pessoa> getLista() {

		this.lista = pessoaFacade.FindAllComNovoProcPesquisa(descPessoa);
		if (lista.size() > 0) {
			listSimouNao = true;
		} else if (lista.size() == 0) {
			listSimouNao = false;
		}

		return this.lista;
	}

	public void setLista(List<Pessoa> lista) {
		this.lista = lista;

	}
	// public List<Pessoa> getLista() {
	// return lista;
	// }

	public String newPessoa() {
		this.pessoaFacade.create(this.pessoa);
		return "newSuccess";
	}

	public String editPessoa() {
		this.pessoaFacade.edit(this.pessoa);
		// this.pessoa = new Pessoa();
		return "editSuccess";
	}

	public void removePessoa(ActionEvent e) {
		String id = (String) e.getComponent().getAttributes().get("codPessoa");
		this.pessoaFacade.remove(this.pessoaFacade.find(id));

	}

	@SuppressWarnings("unchecked")
	public String findPessoa(ActionEvent e) {
		String id = (String) e.getComponent().getAttributes().get("codPessoa");
		pessoa = this.pessoaFacade.find(id);
		
		return "sucesso";
	}

	public List<Pessoa> getListaPessoas() {
		return this.pessoaFacade.findAll();
	}

	public String pesquisaPessoas() {
		lista = pessoaFacade.FindAllComNovoProcPesquisa(descPessoa);

		if (lista.size() > 0) {
			listSimouNao = true;
		} else if (lista.size() == 0) {
			listSimouNao = false;
		}
		return null;
	}

	// public List<SelectItem> getPessoas() {
	// List<SelectItem> list = new ArrayList<SelectItem>();
	// List<Pessoa> it = getListaPessoas();
	// for (int i = 0; i < it.size(); i++) {
	// Pessoa pessoa = it.get(i);
	// list.add(new SelectItem(pessoa, pessoa.getCpf() + ""));
	// }
	// return list;
	// }

	public static String getNomeDesc() {

		return PessoaManagedBean.descPessoa;

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void findPessoaRelatorio() throws ClassNotFoundException, FileNotFoundException, JRException, SQLException
	{
		HashMap paramRel = new HashMap();
		paramRel.put("paramCPF",pessoa.getCpf());
		String nomeRelatorio = "reportPessoa";
		gerarRelatorio(nomeRelatorio, paramRel);

	}

}
