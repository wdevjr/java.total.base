package com.algaworks.warhjr.estoque.servlet;

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

import com.algaworks.warhjr.estoque.model.Usuario;
import com.algaworks.warhjr.estoque.repository.UsuariosRepository;
import com.algaworks.warhjr.estoque.util.JpaUtil;

@WebServlet("/paginas/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	private List<Usuario> loginUser = new ArrayList<Usuario>();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/paginas/login.jsp");
		dispatcher.forward(request, response);
	//	response.sendRedirect(request.getContextPath() + "/paginas/login.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager manager = JpaUtil.getEntityManager();
		UsuariosRepository usuarios = new UsuariosRepository(manager);
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		try {
			loginUser = usuarios.login(login, senha);
			if (loginUser.size() == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("usuarioLogado", login);
				session.setAttribute("dataLogin", new Date());
				
				//response.sendRedirect("/paginas/consulta-produtos.jsp");
				response.sendRedirect(request.getContextPath() + "/paginas/public/consulta-produtos.jsp");
//				RequestDispatcher dispatcher = request.getRequestDispatcher(
//						"/paginas/consulta-produtos.jsp");
//				dispatcher.forward(request, response);
			} else {
				request.setAttribute("mensagem", "Usuário/senha inválidos.");
				doGet(request, response);
			}
			
		} finally {
			manager.close();
		}
		
		
		
	}
	
}