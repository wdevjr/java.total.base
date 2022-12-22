package com.algaworks.estoque.service;

import com.algaworks.estoque.repository.UsuariosRepository;

public class LoginUsuarioService {
	
	
	private UsuariosRepository usuarios;
	
	public LoginUsuarioService(UsuariosRepository usuarios) {
		this.usuarios =usuarios;
	}
	
	public void loginServ(String login, String senha) throws ServiceException {
		this.usuarios.login(login,senha);
	}

}
