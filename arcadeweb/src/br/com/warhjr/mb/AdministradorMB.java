package br.com.warhjr.mb;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.warhjr.controller.ControllerAdministrador;
import br.com.warhjr.controller.ControllerUsuario;
import br.com.warhjr.model.Administrador;
import br.com.warhjr.model.Usuario;
import br.com.warhjr.util.HibernateUtil;

@ManagedBean(name = "AdmMB")
@SessionScoped
public class AdministradorMB extends HibernateUtil {

	Administrador auxadm = new Administrador();
	public static String nomeadm;
	HttpSession session;

	public String loginAdmin() throws Exception {
		boolean logado = false;
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		ControllerAdministrador auxAdm = new ControllerAdministrador();

		logado = auxAdm.verificaLogin(this.auxadm.getLoginadm(), this.auxadm.getSenhaadm());

		if (logado) {
			Administrador dados = (Administrador) session.getAttribute("administrador");
			nomeadm = dados.getNomeadm();
			this.auxadm.setLoginadm(null);
			this.auxadm.setSenhaadm(null);
			rp.sendRedirect(rq.getContextPath() + "/protected/admin/index.xhtml");
			return "successo";
		} else {

			rp.sendRedirect(rq.getContextPath() + "/protected/login.xhtml");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro na inicialização!"));

			return "failurer";

		}

	}

	public String logout() {
//		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//		HttpServletResponse rpsair = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
//				.getResponse();
//		HttpServletRequest rqsair = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
//				.getRequest();
		session.invalidate();

		return "logoutOk";
	}

	public Administrador getAuxadm() {
		return auxadm;
	}

	public void setAuxadm(Administrador auxadm) {
		this.auxadm = auxadm;
	}

	public static String getNomeadm() {
		return nomeadm;
	}

	public static void setNomeadm(String nomeadm) {
		AdministradorMB.nomeadm = nomeadm;
	}

	public String getNomeAdm() {
		return nomeadm;
	}

}
