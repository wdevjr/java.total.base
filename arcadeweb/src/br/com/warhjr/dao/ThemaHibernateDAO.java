package br.com.warhjr.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;

import br.com.warhjr.model.Thema;
import br.com.warhjr.model.Usuario;
import br.com.warhjr.util.HibernateUtil;

public class ThemaHibernateDAO extends HibernateUtil {

	private Session session;

	private java.util.List List;

	@SuppressWarnings("unchecked")
	public List<Thema> listar() {
		List<Thema> lista = new ArrayList<Thema>();

		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			lista = (List<Thema>) session.createCriteria(Thema.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lista;
	}

	public Thema buscaThema(Integer id) {
		session = HibernateUtil.getSession();
		session.beginTransaction();
		Query q = session.createQuery("select p from Thema as p where p.id=:id");

		q.setParameter("id", id);
		session.getTransaction().commit();
		return (Thema) q.list();
	}

	public List<Thema> consultar(int idthema) {
		String query = "SELECT p FROM Thema as p WHERE p.id=:id";
		session = HibernateUtil.getSession();
		session.beginTransaction();
		Query hql = session.createQuery(query);
		hql.setParameter("id", idthema);
		session.getTransaction().commit();
		return hql.list();
	}

}
