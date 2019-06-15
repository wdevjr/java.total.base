package br.com.devmedia.curso.dao;

import java.util.List;

import br.com.devmedia.curso.domain.TipoSexo;
import br.com.devmedia.curso.domain.Usuario;

public interface UsuarioDao {

	void salvar(Usuario usuario);
	
	void editar(Usuario usuario);
	
	void excluir(Long id);
	
	Usuario getId(Long id);
	
	List<Usuario> getTodos(); 
	
	List<Usuario> getBySexo(TipoSexo sexo);
	
	List<Usuario> getByNome(String nome);
}
