package modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "cidade")
public class Cidade implements Serializable {

private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "IDCidade", nullable=false)
private int id;


@Basic(optional = false)
@Column(name = "NomeCidade", nullable=false)
private String nomeCidade;


@Basic(optional = false)
@Column(name = "uf", nullable=true)
private String uf;


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getNomeCidade() {
	return nomeCidade;
}


public void setNomeCidade(String nomeCidade) {
	this.nomeCidade = nomeCidade;
}


public String getUf() {
	return uf;
}


public void setUf(String uf) {
	this.uf = uf;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	result = prime * result + ((nomeCidade == null) ? 0 : nomeCidade.hashCode());
	result = prime * result + ((uf == null) ? 0 : uf.hashCode());
	return result;
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (!(obj instanceof Cidade))
		return false;
	Cidade other = (Cidade) obj;
	if (id != other.id)
		return false;
	if (nomeCidade == null) {
		if (other.nomeCidade != null)
			return false;
	} else if (!nomeCidade.equals(other.nomeCidade))
		return false;
	if (uf == null) {
		if (other.uf != null)
			return false;
	} else if (!uf.equals(other.uf))
		return false;
	return true;
}


}
