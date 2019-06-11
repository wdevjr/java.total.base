package br.com.warhjr.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.warhjr.agenda.modelo.Evento;

public class EventoHibernateDAO{
	
	private Session session;

	
	public void alterar(Evento evento) {
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.update(evento);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
       System.out.println("Editado com sucesso!");
		
	}

	
	public Evento consultar(Evento evento) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void excluir(Evento p) {
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

	
	public boolean existe(Evento evento) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void inserir(Evento evento) {
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(evento);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
       System.out.println("Salvo com sucesso!");
		
	}

	@SuppressWarnings("unchecked")
	public List<Evento> listar() {
		List<Evento> lista = new ArrayList<Evento>();
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			lista = (List<Evento>) session.createCriteria(Evento.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lista;
   }

	
   

}
