package br.com.warhjr.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import br.com.warhjr.dao.UsuarioHibernateDAO;
import br.com.warhjr.model.Usuario;

public class ControllerUsuario {
	private Usuario usuario;
	private DataModel model;
	public static String nome;
	private Session session;
	UsuarioHibernateDAO userDao = new UsuarioHibernateDAO();

	public ControllerUsuario() {
		usuario = new Usuario();
	}

	/*
	 * criamos uma nova instância para um novo usuário
	 */
	public String novologin() {
		usuario = new Usuario();
		return "cadastrarlogin";
	}
	/*
	 * obtém todos os usuarios e passamos esse método para um DataTable no JSF que
	 * tem como objetivo exibir todos os usuários cadastrados
	 */

	public boolean verificarlogin(String email, String senha) {
		boolean autenticado;
		UsuarioHibernateDAO usuarioDao = new UsuarioHibernateDAO();
		autenticado = usuarioDao.verificarLogin(email, senha);

		return autenticado;
	}

	public void salvar(Usuario usuario) throws Exception {
//    	if (usuario.getLoginusuario() == null)
//    	{
//    		throw new Exception("Login não pode ficar vazio!");
//    	}else{
//    		if (usuario.getSenhausuario() == null)
//    		{
//    			throw new Exception("Senha deve ser informada!");
//    		}else{
//    			   if (usuario.getNomeusaurio() == null)
//    			   {
//    				   throw new Exception("Nome também deve ser informado!");
//    			   }else{
//    				   
		userDao.inserir(usuario);
	}
//    		}
//    	}
//    }

	public void EditarAtual(Usuario usuario) throws Exception {
		userDao.alterar(usuario);
	}

	public void ExcluirUser(Usuario usuario) {
		userDao.excluir(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> listaALL() {
		UsuarioHibernateDAO auxUserDAO = new UsuarioHibernateDAO();

		return auxUserDAO.listar();
	}

	public List<Usuario> listDataAtual(String data) {
		UsuarioHibernateDAO auxUserDAO = new UsuarioHibernateDAO();

		return auxUserDAO.listarDataAtual(data);
	}

	public List<Usuario> listaEntreDuasDatas(Date datainicial, Date datafinal, String nome) {

		return userDao.ListaEntreDatas(datainicial, datafinal, '%' + nome + '%');
	}

	public List<Usuario> listaPorNomeUsuarios(String nome) {
		return userDao.ListaUsuariosPorNome('%' + nome + '%');
	}

	public List<Usuario> listaPorNomeUsuariosVerificar(String nome) {

		return userDao.listarVerifUser(nome);

	}
}
