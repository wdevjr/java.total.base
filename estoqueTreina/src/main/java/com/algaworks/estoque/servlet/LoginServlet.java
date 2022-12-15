package com.algaworks.estoque.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.algaworks.estoque.model.Usuario;
import com.algaworks.estoque.repository.Usuarios;
import com.algaworks.estoque.util.JpaUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	private List<Usuario> loginUser = new ArrayList<Usuario>();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/login.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager manager = JpaUtil.getEntityManager();
		Usuarios usuarios = new Usuarios(manager);
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		try {
			loginUser = usuarios.login(login, senha);
			if (loginUser.size() == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("usuarioLogado", login);
				session.setAttribute("dataLogin", new Date());
				
				response.sendRedirect("consulta-produtos");
			} else {
				request.setAttribute("mensagem", "Usuário/senha inválidos.");
				doGet(request, response);
			}
			
		} finally {
			manager.close();
		}
		
		
		
	}
	
}