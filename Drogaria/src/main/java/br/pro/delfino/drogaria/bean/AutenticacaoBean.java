package br.pro.delfino.drogaria.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.model.menu.MenuModel;

import br.pro.delfino.drogaria.dao.UsuarioDAO;
import br.pro.delfino.drogaria.domain.Pessoa;
import br.pro.delfino.drogaria.domain.Usuario;
import br.pro.delfino.drogaria.enumeracao.TipoUsuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());	
	}

	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getEmail(), usuario.getSenha());
			
			if(usuarioLogado == null){
				Messages.addGlobalError("email e/ou senha incorretos");
				return;
			}
			
			Faces.redirect("./pages/pessoas.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
	
//	public void login()
//	{
//		
//		UsuarioDAO usuarioDAO = new UsuarioDAO();
//		
//		if (usuarioDAO.autenticarLogin(usuario))
//		{
//			
//			try {
//				Faces.redirect("./pages/principal.xhtml");
//			} catch (IOException e) {
//				
//				e.printStackTrace();
//			}
//			return;
//		}else
//		{
//			Messages.addGlobalError("eamil e/ou senha incorretos");
//			return;
//		}
//		
//	}
	
	
//	public boolean temPermissoes(List<String> permissoes){	
//		for(String permissao : permissoes){
//			if(usuarioLogado.getTipo() == permissao.charAt(0)){
//				return true;
//			}
//		}
//		
//		return false;
//	}
	
}
