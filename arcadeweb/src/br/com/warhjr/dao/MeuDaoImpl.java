/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.warhjr.dao;

import br.com.warhjr.model.Thema;
import br.com.warhjr.model.Usuario;
import br.com.warhjr.util.HibernateUtil;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author altitdb
 */
public class MeuDaoImpl implements MeuDao {

	@Override
	public Usuario getByIdUsuario(int id) {
		return (Usuario) HibernateUtil.getSession().get(Usuario.class, id);
	}

	@Override
	public List<Usuario> consultaTodosUsuarios() {
		Criteria crit = HibernateUtil.getSession().createCriteria(Usuario.class);
		return crit.list();
	}

	@Override
	public Thema getByIdThema(int id) {

		return (Thema) HibernateUtil.getSession().get(Thema.class, id);
	}

	@Override
	public List<Thema> consultaTodosThemas() {
		Criteria crit = HibernateUtil.getSession().createCriteria(Thema.class);
		return crit.list();
	}

}
