package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.model.TipoLancamento;
import com.algaworks.financeiro.repository.Lancamentos;
import com.algaworks.financeiro.repository.Pessoas;
import com.algaworks.financeiro.service.CadastroLancamentos;
import com.algaworks.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastroLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Object desc;
	
	@Inject
	private CadastroLancamentos cadastro;
	
	@Inject
	private Pessoas pessoas;
	
	@Inject
	private Lancamentos lancamentos;
	
	private List<Pessoa> lista;
	
	private Lancamento lancamento;
	
	private Pessoa pessoa = new Pessoa();
	
	
	private List<Pessoa> todasPessoas;
	

	public void pessoaSelecionado(SelectEvent event) {
		Pessoa pessoaAux = (Pessoa) event.getObject();
	    lancamento.setPessoa(pessoaAux);
	}
	
	public void selecionar(Pessoa pessoa) {
		//RequestContext.getCurrentInstance().closeDialog(pessoa); // PrimeFaces 5.1
		PrimeFaces.current().dialog().closeDynamic(pessoa); // PrimeFaces 6.2 novo
	}
	
	public void prepararCadastro() {
		this.todasPessoas = this.pessoas.todas();
		
		if (this.lancamento == null) {
			this.lancamento = new Lancamento();
			this.lancamento.pessoa = new Pessoa();
		}
		
	}
	
	
	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 400);
		opcoes.put("contentWidth", 560);
		
		//RequestContext.getCurrentInstance().openDialog("SelecaoPessoa", opcoes, null); // PrimeFaces 5.1
		PrimeFaces.current().dialog().openDynamic("SelecaoPessoa", opcoes, null); // PrimeFaces 6.2 novo
	}
	
	
	public List<Pessoa> findsql(Object desc) {

		List<Pessoa> lista = this.pessoas.ProcPesquisa(desc);

		return lista;

	}
	public String pesquisaPessoas() {

		// this.listaFieldSet=true;
		if (desc.equals("")) {
			lista = this.pessoas.todas();
			// listaFieldSet=true;
			return null;

		} else {

			lista = findsql(desc);
			// listaFieldSet=true;
			return null;
		}

	}
	
	public List<String> pesquisarDescricoes(String descricao) {
		return this.lancamentos.descricoesQueContem(descricao);
	}
	
	public void dataVencimentoAlterada(AjaxBehaviorEvent event) {
		if (this.lancamento.getDataPagamento() == null) {
			this.lancamento.setDataPagamento(this.lancamento.getDataVencimento());
		}
	}
	
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			this.cadastro.salvar(this.lancamento);
			
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		} catch (NegocioException e) {
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}
	
	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}
	
	public Lancamento getLancamento() {


		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public Pessoa getPessoa() {
		if (this.pessoa == null) {
			this.pessoa = new Pessoa();
			this.lancamento.pessoa = new Pessoa();
	//	this.lancamento.pessoa.setId(this.pessoa.getId());
		//this.lancamento.pessoa.setNome(this.pessoa.getNome());
		}
		
		
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pessoas getPessoas() {
		return pessoas;
	}

	public void setPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}

	public Object getDesc() {
		return desc;
	}

	public void setDesc(Object desc) {
		this.desc = desc;
	}

	public List<Pessoa> getLista() {
		if (this.desc != null) {
//			if (FacesContext.getCurrentInstance().isPostback()) {
//				// listaFieldSet=true;
//				lista = this.pessoas.todas();
//
//			}
//		} else {
			// listaFieldSet=true;
			lista = findsql(desc);
		}
			return lista;
		}


	

	public void setLista(List<Pessoa> lista) {
		this.lista = lista;
	}
	
	
	
}
