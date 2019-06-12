package br.com.newidea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.newidea.model.Titulo;

public interface TituloRepository extends JpaRepository<Titulo, Long>{
	public List<Titulo> findByDescricaoContaining(String descricao);
}
