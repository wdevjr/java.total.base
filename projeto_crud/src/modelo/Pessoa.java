package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
public class Pessoa implements Serializable {

private static final long serialVersionUID = 1L;
@Id
@Column(name = "CPF", nullable=false)
private String cpf;
@Basic(optional = false)
@Column(name = "NOME", nullable=false)
private String nome;
@Basic(optional = false)
@Column(name = "DATANASCIMENTO", nullable=false)
private Date dataNascimento;
@Basic(optional = false)
@Column(name = "TELEFONE", nullable=true)
private String telefone;

@JoinColumn(name = "cod_cidade", referencedColumnName = "IDCidade", nullable=false)
@ManyToOne(fetch = FetchType.LAZY)
public Cidade cidade;

public Cidade getCidade() {
	return cidade;
}

public void setCidade(Cidade cidade) {
	this.cidade = cidade;
}

public Pessoa() {
}

public String getCpf() {
    return cpf;
}
public void setCpf(String cpf) {
    this.cpf = cpf;
}
public String getNome() {
    return nome;
}
public void setNome(String nome) {
    this.nome = nome;
}
public Date getDataNascimento() {
    return dataNascimento;
}
public void setDataNascimento(Date dataNascimento) {
    this.dataNascimento = dataNascimento;
}
public String getTelefone() {
    return telefone;
}
public void setTelefone(String telefone) {
    this.telefone = telefone;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
	result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
	result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
	Pessoa other = (Pessoa) obj;
	if (cidade == null) {
		if (other.cidade != null)
			return false;
	} else if (!cidade.equals(other.cidade))
		return false;
	if (cpf == null) {
		if (other.cpf != null)
			return false;
	} else if (!cpf.equals(other.cpf))
		return false;
	if (dataNascimento == null) {
		if (other.dataNascimento != null)
			return false;
	} else if (!dataNascimento.equals(other.dataNascimento))
		return false;
	if (nome == null) {
		if (other.nome != null)
			return false;
	} else if (!nome.equals(other.nome))
		return false;
	if (telefone == null) {
		if (other.telefone != null)
			return false;
	} else if (!telefone.equals(other.telefone))
		return false;
	return true;
}



}
