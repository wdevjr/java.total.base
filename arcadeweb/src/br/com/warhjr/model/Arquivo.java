package br.com.warhjr.model;

import java.util.Arrays;
import java.util.Date;

import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "publicararquivos")
public class Arquivo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "idarquivos")
	private Integer idarquivos;

	@Column(name = "nomearquivos")
	private String nomearquivos;

	@Column(name = "descricaoarquivos")
	private String descricaoarquivos;

	@Column(name = "usuarionome")
	private String usuarionome;

	@Column(name = "datacadastro")
	@Temporal(TemporalType.DATE)
	private Date datacadastro = new Date(System.currentTimeMillis());

	public Integer getIdarquivos() {
		return idarquivos;
	}

	public void setIdarquivos(Integer idarquivos) {
		this.idarquivos = idarquivos;
	}

	public String getNomearquivos() {
		return nomearquivos;
	}

	public void setNomearquivos(String nomearquivos) {
		this.nomearquivos = nomearquivos;
	}

	public String getDescricaoarquivos() {
		return descricaoarquivos;
	}

	public void setDescricaoarquivos(String descricaoarquivos) {
		this.descricaoarquivos = descricaoarquivos;
	}

	public String getUsuarionome() {
		return usuarionome;
	}

	public void setUsuarionome(String usuarionome) {
		this.usuarionome = usuarionome;
	}

	public Date getDatacadastro() {
		return datacadastro;
	}

	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}

}
