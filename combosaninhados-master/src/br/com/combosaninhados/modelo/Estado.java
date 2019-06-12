package br.com.combosaninhados.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="estados")
public class Estado implements Serializable {
    
	private static final long serialVersionUID = -5582648910303813488L;

	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="estado_id")
    private Long estado_id;
    
	@Column(name="sigla")
	private String sigla; 

	@Column(name="nome")
	private String nome; 

	@OneToMany(mappedBy="estado", fetch=FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Cidade> cidades = new ArrayList<Cidade>();
	


	public Long getEstado_id() {
		return estado_id;
	}

	public void setEstado_id(Long estado_id) {
		this.estado_id = estado_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public String toString() {
        return this.nome;
    }
	
	@Override
	public boolean equals(Object obj){
		if( (obj instanceof Estado) && ( ((Estado)obj).getNome().equals(this.nome))){
			return true; 
		}else {
			return false; 
		}
	}

	public int hashCode(){
		return this.nome.length() * 23;
	}

}