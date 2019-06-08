package br.com.warhjr.mb;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.warhjr.controller.ControllerUsuario;
import br.com.warhjr.dao.ThemaHibernateDAO;
import br.com.warhjr.dao.UsuarioHibernateDAO;
import br.com.warhjr.model.Thema;
import br.com.warhjr.model.Usuario;
import br.com.warhjr.util.HibernateUtil;

@ManagedBean(name = "UsuarioMBean")
@SessionScoped
public class UsuarioMB extends HibernateUtil {

	ThemaHibernateDAO auxthemaDao = new ThemaHibernateDAO();
	ControllerUsuario auxUser = new ControllerUsuario();
	private Usuario usuario = new Usuario();
	private Thema thema = new Thema();
	public static String nome;
	public static String themaAtual = "afterwork";
	private boolean habilitarAdm;
	private boolean habilitarBotao;
	private boolean habilitarFormulario;
	private boolean visivelCampo;
	private List<Usuario> listarUsuarios;
	HttpSession session;
	private Date dInicial;
	private Date dFinal;
	private String nomeUsuario;
	private String nomeDoUser = null;
	private String nomeUserVerificar;

	public Usuario getUsuario() {
		if (this.usuario == null) {
			this.usuario = new Usuario();
			this.usuario.thema = new Thema();

		}
		return usuario;
	}

	public String novo() {

		this.usuario = null;
		this.habilitarBotao = true;
		return null;
	}

	public String editarCancelar() {

		this.usuario = new Usuario();

		return null;
	}

	public void removeUsuario() {

		this.auxUser.ExcluirUser(this.usuario);
		this.usuario = new Usuario();
	}

	public String login() {
		boolean logado = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		ControllerUsuario auxController = new ControllerUsuario();

		logado = auxController.verificarlogin(this.usuario.getLoginusuario(), this.usuario.getSenhausuario());

		if (logado) {
			Usuario dados = (Usuario) session.getAttribute("usuario");
			themaAtual = dados.getThema().getNomethema();
			nome = dados.getNomeusuario();
			this.usuario.setLoginusuario(null);
			this.usuario.setSenhausuario(null);

			if (dados.isTipo() == true) {
				habilitarAdm = true;

			} else {

				habilitarAdm = false;
			}
			try {
				rp.sendRedirect(rq.getContextPath() + "/public/user/index.xhtml");
			} catch (IOException e) {
				FacesMessage facesMessageError = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Erro ao Enviar Dados.",
						"Erro ao Enviar Dados.");
				facesContext.addMessage(null, facesMessageError);
				e.printStackTrace();
			}
			return "sucesso";
		} else {

			try {
				rp.sendRedirect(rq.getContextPath() + "/public/login.xhtml");
			} catch (IOException e) {

				FacesMessage facesMessageError = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Erro ao Enviar Dados.",
						"Erro ao Enviar Dados.");
				facesContext.addMessage(null, facesMessageError);
			}
			// FacesContext.getCurrentInstance().addMessage(null,
			// new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro na
			// inicializaï¿½ï¿½o!"));

			return "failurer";

		}

	}

	public String logoutUser() {

		session.invalidate();

		return "logoutPrincOk";
	}

	public void CheckVisible() {
		this.visivelCampo = true;
	}

	public void Salvando() throws Exception {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {

			auxUser.salvar(this.usuario);
			themaAtual = this.usuario.getThema().getNomethema();
			this.habilitarBotao = false;

			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, " Salvo o Usuário com sucesso.",
					"Salvo o UsuÃ¡rio com sucesso.");
			facesContext.addMessage(null, facesMessage);
		} catch (IOException e) {
			FacesMessage facesMessageError = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Erro ao Salvar o Usuário.",
					" Erro ao Salvar o UsuÃ¡rio.");
			facesContext.addMessage(null, facesMessageError);
		}
	}

	public void Editar() throws Exception {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try {
			if (this.usuario != null) {
				auxUser.EditarAtual(this.usuario);
				themaAtual = this.usuario.getThema().getNomethema();
				
				// this.habilitarFormulario = false;
			}
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, " Salvo o Usuário com sucesso.",
					"Salvo o UsuÃ¡rio com sucesso.");
			facesContext.addMessage(null, facesMessage);
		} catch (IOException e) {
			FacesMessage facesMessageError = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Erro ao Salvar o Usuário.",
					" Erro ao Salvar o UsuÃ¡rio.");
			facesContext.addMessage(null, facesMessageError);
		}
		
	}

	public String pesquisaData() {

		listarUsuarios = auxUser.listaEntreDuasDatas(dInicial, dFinal, nomeUsuario);
		if (listarUsuarios.size() > 0) {
			this.habilitarFormulario = true;
		}

		return null;
	}

	public String pesquisaPorNome() {

		listarUsuarios = auxUser.listaPorNomeUsuarios(nomeDoUser);
		if (listarUsuarios.size() > 0) {
			this.habilitarFormulario = true;
		}

		return null;
	}

	public List<String> listarVerificar(String s) {
		List<String> results = new ArrayList<String>();

		for (Usuario user : auxUser.listaPorNomeUsuariosVerificar(s)) {
			results.add(user.getNomeusuario());
		}

		return results;
	}
	


	public Date getdInicial() {
		return dInicial;
	}

	public String getNomeUserVerificar() {
		return nomeUserVerificar;
	}

	public void setNomeUserVerificar(String nomeUserVerificar) {
		this.nomeUserVerificar = nomeUserVerificar;
	}

	public void setdInicial(Date dInicial) {
		this.dInicial = dInicial;
	}

	public Date getdFinal() {
		return dFinal;
	}

	public void setdFinal(Date dFinal) {
		this.dFinal = dFinal;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNomeUser() {
		return UsuarioMB.nome;
	}

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		UsuarioMB.nome = nome;
	}

	public boolean isHabilitarAdm() {
		return habilitarAdm;
	}

	public void setHabilitarAdm(boolean habilitarAdm) {
		this.habilitarAdm = habilitarAdm;
	}

	public static String getThemaAtual() {
		return themaAtual;
	}

	public static void setThemaAtual(String themaAtual) {
		UsuarioMB.themaAtual = themaAtual;
	}

	public void setListarUsuarios(List<Usuario> listarUsuarios) {
		this.listarUsuarios = listarUsuarios;
	}

	public List<Usuario> getListarUsuarios() {
		return listarUsuarios;
	}

	public boolean isHabilitarBotao() {
		return habilitarBotao;
	}

	public void setHabilitarBotao(boolean habilitarBotao) {
		this.habilitarBotao = habilitarBotao;
	}

	public boolean isHabilitarFormulario() {
		return habilitarFormulario;
	}

	public void setHabilitarFormulario(boolean habilitarFormulario) {
		this.habilitarFormulario = habilitarFormulario;
	}

	public boolean isVisivelCampo() {
		return visivelCampo;
	}

	public void setVisivelCampo(boolean visivelCampo) {
		this.visivelCampo = visivelCampo;
	}

	public String getNomeDoUser() {
		return nomeDoUser;
	}

	public void setNomeDoUser(String nomeDoUser) {
		this.nomeDoUser = nomeDoUser;
	}

}
