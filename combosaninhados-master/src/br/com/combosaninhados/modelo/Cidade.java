package br.com.combosaninhados.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cidades")
public class Cidade implements Serializable{
    
	private static final long serialVersionUID = -2094704997130038211L;

	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cidade_id")
    private Long cidade_id;
    
	@Column(name="nome")
	private String nome;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="id_cid_est")
	private Estado estado;
    


	public Long getCidade_id() {
		return cidade_id;
	}

	public void setCidade_id(Long cidade_id) {
		this.cidade_id = cidade_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String toString() {
        return this.nome;
    }
    
    @Override
	public boolean equals(Object obj){
		if( (obj instanceof Cidade) && ( ((Cidade)obj).getNome().equals(this.nome))){
			return true; 
		}else {
			return false; 
		}
	}

	public int hashCode(){
		return this.nome.length() * 23;
	}
}