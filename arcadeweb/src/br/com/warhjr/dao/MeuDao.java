/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.warhjr.dao;

import java.util.List;

import br.com.warhjr.model.Thema;
import br.com.warhjr.model.Usuario;

public interface MeuDao {

	public List<Usuario> consultaTodosUsuarios();

	public List<Thema> consultaTodosThemas();

	public Usuario getByIdUsuario(int id);

	public Thema getByIdThema(int id);

}
