package br.com.ciceroednilson.usuario.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.ciceroednilson.repository.UsuarioRepository;
import br.com.ciceroednilson.entity.*;
import br.com.ciceroednilson.uteis.Uteis;

@Named(value="usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;
		
	@Inject
	private UsuarioEntity usuarioModel;

	@Inject
	private UsuarioRepository usuarioRepository;
	
	@Inject
	private UsuarioEntity usuarioEntity;
	
	public UsuarioEntity getUsuarioModel() {
		return usuarioModel;
	}
	public void setUsuarioModel(UsuarioEntity usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
	
	public UsuarioEntity GetUsuarioSession(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		return (UsuarioEntity)facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}
	
	public String Logout(){
						
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "/index.xhtml?faces-redirect=true";
	}
	public String EfetuarLogin(){
			
		if(StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())){
			
			Uteis.Mensagem("Favor informar o login!");
			return null;
		}
		else if(StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())){
			
			Uteis.Mensagem("Favor informar a senha!");
			return null;
		}
		else{	

			usuarioEntity = usuarioRepository.ValidaUsuario(usuarioModel);
			
			if(usuarioEntity!= null){
							
				usuarioModel.setSenha(null);
				usuarioModel.setCodigo(usuarioEntity.getCodigo());
				
				
				FacesContext facesContext = FacesContext.getCurrentInstance();
				
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);
				
				
				return "sistema/home?faces-redirect=true";
			}
			else{
				
				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}
		
		
	}
	
}