package br.com.warhjr.model;

public class Pessoa extends Endereco {

	private Integer id_pessoa;
	private Integer cod_endereco;
	private String nomePessoa;
	private Integer idade;
	private String sexo;

	public Integer getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(Integer id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

	public Integer getCod_endereco() {
		return cod_endereco;
	}

	public void setCod_endereco(Integer cod_endereco) {
		this.cod_endereco = cod_endereco;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
