package com.algaworks.warhjr.estoque.service;

import com.algaworks.warhjr.estoque.repository.UsuariosRepository;

public class LoginUsuarioService {
	
	
	private UsuariosRepository usuarios;
	
	public LoginUsuarioService(UsuariosRepository usuarios) {
		this.usuarios =usuarios;
	}
	
	public void loginServ(String login, String senha) throws ServiceException {
		this.usuarios.login(login,senha);
	}

}
