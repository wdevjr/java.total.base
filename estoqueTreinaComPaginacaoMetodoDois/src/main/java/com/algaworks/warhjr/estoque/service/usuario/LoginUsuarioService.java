package com.algaworks.warhjr.estoque.service.usuario;

import com.algaworks.warhjr.estoque.repository.usuario.UsuariosRepository;
import com.algaworks.warhjr.estoque.util.ServiceException;

public class LoginUsuarioService {
	
	
	private UsuariosRepository usuarios;
	
	public LoginUsuarioService(UsuariosRepository usuarios) {
		this.usuarios =usuarios;
	}
	
	public void loginServ(String login, String senha) throws ServiceException {
		this.usuarios.login(login,senha);
	}

}
