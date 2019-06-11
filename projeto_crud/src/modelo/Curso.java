package modelo;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "curso")
public class Curso implements Serializable {

private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "ID", nullable=false)
private int id;
@Basic(optional = false)
@Column(name = "NOME", nullable=false)
private String nome;
@Basic(optional = false)
@Column(name = "NOMEDEPARTAMENTO", nullable=false)
private String nomeDepartamento;
@Basic(optional = false)
@Column(name = "CARGAHORARIA", nullable=true)
private int cargaHoraria;

public Curso() {
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
public String getNomeDepartamento() {
    return nomeDepartamento;
}
public void setNomeDepartamento(String nomeDepartamento) {
    this.nomeDepartamento = nomeDepartamento;
}
public int getCargaHoraria() {
    return cargaHoraria;
}
public void setCargaHoraria(int cargaHoraria) {
    this.cargaHoraria = cargaHoraria;
}

@Override
public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Curso)) {
        return false;
    }
    Curso other = (Curso) object;
    if (!(this.getId() == other.getId())) {
        return false;
    }
    return true;
}
}

