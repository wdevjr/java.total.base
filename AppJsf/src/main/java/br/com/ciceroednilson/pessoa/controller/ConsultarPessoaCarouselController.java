package br.com.ciceroednilson.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ciceroednilson.repository.PessoaRepository;
import br.com.ciceroednilson.entity.*;

@Named(value="consultarPessoaCarouselController")
@ViewScoped
public class ConsultarPessoaCarouselController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject transient
	private PessoaRepository pessoaRepository;

	@Produces 
	private List<PessoaEntity> pessoas;
	
	public List<PessoaEntity> getPessoas() {
		return pessoas;
	}
	
	@PostConstruct
	private void init(){
		//CONSULTA AS PESSOAS CADASTRADAS
		this.pessoas = pessoaRepository.GetPessoas();
	}

	
			
	
}
