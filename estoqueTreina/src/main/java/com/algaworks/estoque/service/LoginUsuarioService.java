package com.algaworks.estoque.service;

import com.algaworks.estoque.repository.Usuarios;

public class LoginUsuarioService {
	
	
	private Usuarios usuarios;
	
	public LoginUsuarioService(Usuarios usuarios) {
		this.usuarios =usuarios;
	}
	
	public void loginServ(String login, String senha) throws ServiceException {
		this.usuarios.login(login,senha);
	}

}
