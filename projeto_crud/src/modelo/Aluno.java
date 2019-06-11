package modelo;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "RA", nullable = false)
	private int ra;
	@Basic(optional = false)
	@Column(name = "EMAIL", nullable = false)
	private String email;
	@Basic(optional = false)
	@Column(name = "ESCOLARIDADE", nullable = true)

	private String escolaridade;

	@JoinColumn(name = "CURSO_ID", referencedColumnName = "ID", nullable = false)
	@ManyToOne
	private Curso curso;

	public Aluno() {
	}

	public int getRa() {
		return ra;
	}

	public void setRa(int ra) {
		this.ra = ra;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Aluno)) {
			return false;
		}
		Aluno other = (Aluno) object;
		if (!(this.getCpf().equals(other.getCpf()))) {
			return false;
		}
		return true;
	}
}

