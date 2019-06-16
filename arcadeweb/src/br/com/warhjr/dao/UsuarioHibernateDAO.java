package br.com.warhjr.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.warhjr.model.Arquivo;
import br.com.warhjr.model.Usuario;
import br.com.warhjr.util.HibernateUtil;

public class UsuarioHibernateDAO extends HibernateUtil {

	private Session session;
	private String Date;
	private String data;

	public boolean verificarLogin(String login, String senha) {
		HttpSession sessionUser = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false);
		session = HibernateUtil.getSession();
		Query query = session.createQuery("from Usuario  where loginusuario =? and senhausuario=?");
		query.setString(0, limparTexto(login));
		query.setString(1, limparTexto(senha));
		Usuario user = (Usuario) query.uniqueResult();
		sessionUser.setAttribute("usuario", user);

		if (user != null) {
			return true;
		}
		return false;
	}

	public void alterar(Usuario usuario) {
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.update(usuario);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println("Editado com sucesso!");

	}

	public Usuario consultar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(Usuario p) {
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

	public boolean existe(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	public void inserir(Usuario usuario) {
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(usuario);
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
	/*
	 * public List<Usuario> listar() { List<Usuario> lista = new
	 * ArrayList<Usuario>(); try { session = HibernateUtil.getSession();
	 * session.beginTransaction(); lista = (List<Usuario>)
	 * session.createCriteria(Usuario.class).list();
	 * session.getTransaction().commit(); } catch (Exception e) {
	 * session.getTransaction().rollback(); e.printStackTrace(); } finally {
	 * session.close(); } return lista; }
	 */

	public final String limparTexto(String str) {
		str = str.trim();
		str = str.replace(" ", "");
		str = str.toLowerCase();
		str = str.replace("=", "");
		str = str.replace("'", "");
		str = str.replace(" or ", "");
		str = str.replace(" and ", "");
		str = str.replace("(", "");
		str = str.replace(")", "");
		str = str.replace("<", "[");
		str = str.replace(">", "]");
		str = str.replace("update", "");
		str = str.replace("-shutdown", "");
		str = str.replace("--", "");
		str = str.replace("'", "");
		str = str.replace("#", "");
		str = str.replace("$", "");
		str = str.replace("%", "");
		str = str.replace("¨", "");
		str = str.replace("&", "");
		str = str.replace("'1'='1'", "");
		str = str.replace("'1'-'1'", "");
		str = str.replace("'or'1'='1'", "");
		str = str.replace("--", "");
		str = str.replace("insert", "");
		str = str.replace("drop", "");
		str = str.replace("delet", "");
		str = str.replace("xp_", "");
		str = str.replace("select", "");
		str = str.replace("*", "");
		return str;
	}

	public List<Usuario> listar() {

		session = HibernateUtil.getSession();
		session.beginTransaction();
		String sql = "SELECT p FROM Usuario p";
		Query query = session.createQuery(sql);
		// query.setFirstResult(0);
		// query.setMaxResults(5);
		return query.list();
	}

	@SuppressWarnings("unused")
	private Date formatarData(String data) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
		return new Date(formatter.parse(data).getTime());
	}

	public List<Usuario> listarDataAtual(String data) {
		session = HibernateUtil.getSession();
		session.beginTransaction();
		Query query = session.createQuery("from Usuario where datacadastro =?");
		query.setString(0, data);

		return query.list();
	}

	public List<Usuario> ListaEntreDatas(java.util.Date dataInicio, java.util.Date dataFim, String nome) {
		session = HibernateUtil.getSession();
		session.beginTransaction();
		Criteria critetira = session.createCriteria(Usuario.class);
		critetira.add(Restrictions.ge("datacadastro", dataInicio)).add(Restrictions.le("datacadastro", dataFim));
		critetira.add((Restrictions.like("nomeusuario", nome)));
		List ListaEntreDatas;
		return ListaEntreDatas = critetira.list();
	}

	public List<Usuario> ListaUsuariosPorNome(String pornome) {
		session = HibernateUtil.getSession();
		session.beginTransaction();
		Criteria criteira = session.createCriteria(Usuario.class);
		criteira.add(Restrictions.ilike("nomeusuario", pornome));

		List ListaUsuarios;

		return ListaUsuarios = criteira.list();

	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listarVerifUser(String s) {
		session = HibernateUtil.getSession();
		session.beginTransaction();

		try {
			Criteria cri = session.createCriteria(Usuario.class);
			cri.addOrder(Order.asc("nomeusuario"));
			cri.add(Restrictions.like("nomeusuario", "'%" + s + "%'"));

			return cri.list();
		} finally {
			session.close();
		}

	}
}
