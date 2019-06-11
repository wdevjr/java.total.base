package br.com.ciceroednilson.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ciceroednilson.repository.PessoaRepository;
import br.com.ciceroednilson.entity.PessoaEntity;

@Named(value="consultarPessoaController")
@ViewScoped
public class ConsultarPessoaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject transient
	private PessoaEntity PessoaEntity;

	@Produces 
	private List<PessoaEntity> pessoas;
	
	@Inject transient
	private PessoaRepository pessoaRepository;

	public List<PessoaEntity> getPessoas() {
		return pessoas;
	}
	public void setPessoasEntity(List<PessoaEntity> pessoas) {
		this.pessoas = pessoas;
	}		
	public PessoaEntity getPessoaEntity() {
		return PessoaEntity;
	}
	public void setPessoaEntity(PessoaEntity PessoaEntity) {
		this.PessoaEntity = PessoaEntity;
	}

	/***
	 * CARREGA AS PESSOAS NA INICIALIZAÇÃO 
	 */
	@PostConstruct
	public void init(){
		
		//RETORNAR AS PESSOAS CADASTRADAS
		this.pessoas = pessoaRepository.GetPessoas();
	}
	
	/***
	 * CARREGA INFORMAÇÕES DE UMA PESSOA PARA SER EDITADA
	 * @param PessoaEntity
	 */
	public void Editar(PessoaEntity PessoaEntity){
		
		/*PEGA APENAS A PRIMEIRA LETRA DO SEXO PARA SETAR NO CAMPO(M OU F)*/
		PessoaEntity.setSexo(PessoaEntity.getSexo().substring(0, 1));
		
		this.PessoaEntity = PessoaEntity;
				
	}
	
	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro(){
		
		this.pessoaRepository.AlterarRegistro(this.PessoaEntity);	
		
				
		/*RECARREGA OS REGISTROS*/
		this.init();
	}
	
	/***
	 * EXCLUINDO UM REGISTRO
	 * @param PessoaEntity
	 */
	public void ExcluirPessoa(PessoaEntity PessoaEntity){
		
		//EXCLUI A PESSOA DO BANCO DE DADOS
		this.pessoaRepository.ExcluirRegistro(PessoaEntity.getCodigo());
		
		//REMOVENDO A PESSOA DA LISTA
		//ASSIM QUE É A PESSOA É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.pessoas.remove(PessoaEntity);
		
	}
		
}