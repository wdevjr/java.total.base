package br.com.warhjr.dao;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.warhjr.model.Administrador;
import br.com.warhjr.model.Usuario;
import br.com.warhjr.util.HibernateUtil;

public class AdministradorHibernateDAO extends HibernateUtil {

	private Session session;

	public boolean verificaLoginAdm(String login, String senha) {
		HttpSession sessionUser = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false);
		session = HibernateUtil.getSession();
		Query query = session.createQuery("from Administrador where loginadm=? and senhaadm=?");
		query.setString(0, limparTexto(login));
		query.setString(1, limparTexto(senha));
		Administrador adm = (Administrador) query.uniqueResult();
		sessionUser.setAttribute("administrador", adm);

		if (adm != null) {
			return true;
		}
		return false;
	}

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
}
