package br.pro.delfino.drogaria.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.pro.delfino.drogaria.domain.Usuario;
import br.pro.delfino.drogaria.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {
	
	
	
//	public boolean autenticarLogin(Usuario usuario) {
//		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
//		
//		try{
//			Criteria consulta = sessao.createCriteria(Usuario.class);
//			//consulta.createAlias("pessoa", "p");
//			//consulta.createAlias("pessoa.usuario", "u");
//			
//			consulta.add(Restrictions.eq("email",usuario.getEmail()));
//			
//			//SimpleHash hash = new SimpleHash("md5", senha);
//			consulta.add(Restrictions.eq("senha", usuario.getSenha()));
//			
//			boolean resultado = consulta.uniqueResult() != null;
//			
//		    return resultado;		
//			
//		} catch (RuntimeException erro) {
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//	}
	
	public Usuario autenticar(String email, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try{
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p");
			
			
			consulta.add(Restrictions.eq("p.email", email));
			
			//SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", senha));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			return resultado;
			
			
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
