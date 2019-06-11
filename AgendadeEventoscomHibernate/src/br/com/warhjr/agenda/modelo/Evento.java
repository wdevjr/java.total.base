package br.com.warhjr.agenda.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Evento")
public class Evento{
     @Id
     @GeneratedValue(strategy=GenerationType.SEQUENCE)
     @Column(name="id_evento")
     private Long id;
     
     @Column(name="titulo_evento")
     private String titulo;
     
     @Column(name="incio_evento")
     private Date incio;
     
     @Column(name="fim_evento")
     private Date fim;
     
     @Column(name="desc_evento")
     private String descricao;
     
     @Column(name="status_evento")
     private boolean status;
     
    public Evento() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getIncio() {
		return incio;
	}

	public void setIncio(Date incio) {
		this.incio = incio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	

	
}
