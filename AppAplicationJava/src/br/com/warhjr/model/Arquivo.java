package br.com.warhjr.model;

import java.util.Date;

public class Arquivo extends Pessoa {

	private Integer id;
	private Integer cod_pessoa;
	private String nomearquivo;
	private String extencao;
	private String tamanho;
	private byte[] dados;
	private String data;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCod_pessoa() {
		return cod_pessoa;
	}

	public void setCod_pessoa(Integer cod_pessoa) {
		this.cod_pessoa = cod_pessoa;
	}

	public String getNomearquivo() {
		return nomearquivo;
	}

	public void setNomearquivo(String nomearquivo) {
		this.nomearquivo = nomearquivo;
	}

	public String getExtencao() {
		return extencao;
	}

	public void setExtencao(String extencao) {
		this.extencao = extencao;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

}
