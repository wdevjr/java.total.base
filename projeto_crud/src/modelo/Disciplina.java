package modelo;

import java.io.Serializable;
import javax.persistence.*;
import modelo.Curso;

@Entity
@Table(name = "disciplina")
public class Disciplina implements Serializable {

private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "ID", nullable=false)
private int id;
@Basic(optional = false)
@Column(name = "NOME", nullable=false)
private String nome;
@Basic(optional = false)
@Column(name = "QUANTIDADECREDITOS", nullable=true)
private int quantidadeCreditos;
@Basic(optional = false)
@Column(name = "NOMEPROFESSOR", nullable=true)
private String nomeProfessor;
@JoinColumn(name = "CURSO_ID", referencedColumnName = "ID", nullable=false)
@ManyToOne
private Curso curso;

public Disciplina() {
}

public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public String getNome() {
    return nome;
}
public void setNome(String nome) {
    this.nome = nome;
}
public int getQuantidadeCreditos() {
    return quantidadeCreditos;
}
public void setQuantidadeCreditos(int quantidadeCreditos) {
    this.quantidadeCreditos = quantidadeCreditos;
}
public String getNomeProfessor() {
    return nomeProfessor;
}
public void setNomeProfessor(String nomeProfessor) {
    this.nomeProfessor = nomeProfessor;
}
public Curso getCurso() {
    return curso;
}
public void setCurso(Curso curso) {
    this.curso = curso;
}

@Override
public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Disciplina)) {
        return false;
    }
    Disciplina other = (Disciplina) object;
    if (!(this.getId() == other.getId())) {
        return false;
    }
    return true;
}
}

