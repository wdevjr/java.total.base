package br.com.warhjr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idusuario")
	private Integer idusuario;

	@Column(name = "nomeusuario")
	private String nomeusuario;

	@Column(name = "loginusuario")
	private String loginusuario;

	@Column(name = "senhausuario")
	private String senhausuario;

	@Column(name = "datanascimento")
	@Temporal(TemporalType.DATE)
	private Date datanascimento;

	@Column(name = "datacadastro")
	@Temporal(TemporalType.DATE)
	private Date datacadastro;

	@Column(name = "tipo")
	private boolean tipo;

	@JoinColumn(name = "cod_thema", referencedColumnName = "id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	public Thema thema;

	public Usuario() {
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getLoginusuario() {
		return loginusuario;
	}

	public void setLoginusuario(String loginusuario) {
		this.loginusuario = loginusuario;
	}

	public String getSenhausuario() {
		return senhausuario;
	}

	public void setSenhausuario(String senhausuario) {
		this.senhausuario = senhausuario;
	}

	public String getNomeusuario() {
		return nomeusuario;
	}

	public void setNomeusuario(String nomeusuario) {
		this.nomeusuario = nomeusuario;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public Date getDatacadastro() {
		return datacadastro;
	}

	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}

	public boolean isTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}

	public Thema getThema() {
		return thema;
	}

	public void setThema(Thema thema) {
		this.thema = thema;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datacadastro == null) ? 0 : datacadastro.hashCode());
		result = prime * result + ((datanascimento == null) ? 0 : datanascimento.hashCode());
		result = prime * result + ((idusuario == null) ? 0 : idusuario.hashCode());
		result = prime * result + ((loginusuario == null) ? 0 : loginusuario.hashCode());
		result = prime * result + ((nomeusuario == null) ? 0 : nomeusuario.hashCode());
		result = prime * result + ((senhausuario == null) ? 0 : senhausuario.hashCode());
		result = prime * result + ((thema == null) ? 0 : thema.hashCode());
		result = prime * result + (tipo ? 1231 : 1237);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (datacadastro == null) {
			if (other.datacadastro != null)
				return false;
		} else if (!datacadastro.equals(other.datacadastro))
			return false;
		if (datanascimento == null) {
			if (other.datanascimento != null)
				return false;
		} else if (!datanascimento.equals(other.datanascimento))
			return false;
		if (idusuario == null) {
			if (other.idusuario != null)
				return false;
		} else if (!idusuario.equals(other.idusuario))
			return false;
		if (loginusuario == null) {
			if (other.loginusuario != null)
				return false;
		} else if (!loginusuario.equals(other.loginusuario))
			return false;
		if (nomeusuario == null) {
			if (other.nomeusuario != null)
				return false;
		} else if (!nomeusuario.equals(other.nomeusuario))
			return false;
		if (senhausuario == null) {
			if (other.senhausuario != null)
				return false;
		} else if (!senhausuario.equals(other.senhausuario))
			return false;
		if (thema == null) {
			if (other.thema != null)
				return false;
		} else if (!thema.equals(other.thema))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

}
