package br.com.warhjr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.warhjr.model.Arquivo;
import br.com.warhjr.model.Usuario;
import br.com.warhjr.util.HibernateUtil;

public class ArquivoHibernateDAO {

	private Session session;

	public void Salvar(Arquivo arquivo) {
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(arquivo);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println("Salvo com sucesso!");

	}

	public void Editar(Arquivo arquivo) {
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.update(arquivo);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println("Editado com sucesso!");

	}

	public void excluir(Arquivo p) {
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(p);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println("Excluido com sucesso!");
	}

	public List<Arquivo> ListaEntreDataseNome(java.util.Date dataInicio, java.util.Date dataFim, String nome) {
		session = HibernateUtil.getSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Arquivo.class);
		List<Arquivo> results = null;
		Criterion nomeAdd = null;
		Criterion orExp = null;

		if ((dataInicio == null) && (dataFim == null) && (nome != null)) {

			nomeAdd = Restrictions.ilike("nomearquivos", nome);

			orExp = Restrictions.and(nomeAdd);

			criteria.add(orExp);
			results = criteria.list();

		} else if ((dataInicio != null) && (dataFim != null) && (nome != null)) {

			Criterion datasInFin = Restrictions.and(Restrictions.ge("datacadastro", dataInicio))
					.add(Restrictions.le("datacadastro", dataFim));

			nomeAdd = Restrictions.ilike("nomearquivos", nome);

			orExp = Restrictions.and(datasInFin, nomeAdd);

			criteria.add(orExp);
			results = criteria.list();
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Arquivo> listar() {

		session = HibernateUtil.getSession();
		session.beginTransaction();
		String sql = "SELECT p FROM Arquivo p order by p.nomearquivos, p.usuarionome desc";
		Query query = session.createQuery(sql);
		// query.setFirstResult(0);
		// query.setMaxResults(5);
		return query.list();
	}

}
