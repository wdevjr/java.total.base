package br.com.newidea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.cobranca.repository.filter.TituloFilter;

import br.com.newidea.model.StatusTitulo;
import br.com.newidea.model.Titulo;
import br.com.newidea.repository.TituloRepository;

@Service 
public class TItuloService {
	
	@Autowired
	private TituloRepository tituloRepository;
	
	public Titulo salvar(Titulo titulo) {
		try {
			return tituloRepository.save(titulo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data invalido.");
		}
				
	}

	public List<Titulo> listar() {
		return tituloRepository.findAll();
	}

	public void excluir(Long codigo) {
		tituloRepository.delete(codigo);
	}

	public String receber(Long codigo) {
		Titulo titulo = tituloRepository.findOne(codigo);
		titulo.setStatus(StatusTitulo.RECEBIDO);
		tituloRepository.save(titulo);
		
		return StatusTitulo.RECEBIDO.getDescricao();
	}

	public List<Titulo> filtrar(TituloFilter filtro) {
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return tituloRepository.findByDescricaoContaining(descricao);
	}
}
