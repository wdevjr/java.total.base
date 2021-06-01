package br.com.warhjr.model;

import java.util.Arrays;
import java.util.Date;

public class Arquivo extends Pessoa{

	private Integer id;
	//private Pessoa pessoa;
	private String nomearquivo;
	private String extencao;
	private String tamanho;
	private byte[] dados;
	private String data = (new Date(System.currentTimeMillis())).toString();
	public Arquivo arquivo;// = new Arquivo();
	public Pessoa pessoa;// = new Pessoa();
	
	
	public Arquivo getArquivo()
	{
		if (this.arquivo == null)
		{
			this.arquivo = new Arquivo();
			this.arquivo.pessoa = new Pessoa();
		}
		
		return arquivo;
	}
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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



	public Pessoa getPessoa() {
		return pessoa;
	}



	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivo == null) ? 0 : arquivo.hashCode());
		result = prime * result + Arrays.hashCode(dados);
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((extencao == null) ? 0 : extencao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomearquivo == null) ? 0 : nomearquivo.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arquivo other = (Arquivo) obj;
		if (arquivo == null) {
			if (other.arquivo != null)
				return false;
		} else if (!arquivo.equals(other.arquivo))
			return false;
		if (!Arrays.equals(dados, other.dados))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (extencao == null) {
			if (other.extencao != null)
				return false;
		} else if (!extencao.equals(other.extencao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomearquivo == null) {
			if (other.nomearquivo != null)
				return false;
		} else if (!nomearquivo.equals(other.nomearquivo))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (tamanho == null) {
			if (other.tamanho != null)
				return false;
		} else if (!tamanho.equals(other.tamanho))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Arquivo [id=" + id + ", nomearquivo=" + nomearquivo + ", extencao=" + extencao + ", tamanho=" + tamanho
				+ ", dados=" + Arrays.toString(dados) + ", data=" + data + ", arquivo=" + arquivo + ", pessoa=" + pessoa
				+ "]";
	}



	
}
