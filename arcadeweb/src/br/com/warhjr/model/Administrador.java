package br.com.warhjr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "administrador")
public class Administrador {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "idadm")
	private Integer idadm;

	@Column(name = "loginadm")
	private String loginadm;

	@Column(name = "senhaadm")
	private String senhaadm;

	@Column(name = "nomeadm")
	private String nomeadm;

	public Administrador() {
	}

	public Integer getIdadm() {
		return idadm;
	}

	public void setIdadm(Integer idadm) {
		this.idadm = idadm;
	}

	public String getLoginadm() {
		return loginadm;
	}

	public void setLoginadm(String loginadm) {
		this.loginadm = loginadm;
	}

	public String getSenhaadm() {
		return senhaadm;
	}

	public void setSenhaadm(String senhaadm) {
		this.senhaadm = senhaadm;
	}

	public String getNomeadm() {
		return nomeadm;
	}

	public void setNomeadm(String nomeadm) {
		this.nomeadm = nomeadm;
	}

}
