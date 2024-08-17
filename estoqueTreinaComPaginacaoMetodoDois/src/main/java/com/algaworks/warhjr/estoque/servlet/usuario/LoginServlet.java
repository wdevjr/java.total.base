package com.algaworks.warhjr.estoque.servlet.usuario;

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
import com.algaworks.warhjr.estoque.repository.usuario.UsuariosRepository;
import com.algaworks.warhjr.estoque.util.JpaUtil;

@WebServlet("/produto/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	HttpServletRequest req;
	HttpServletResponse resp;
	private List<Usuario> loginUser = new ArrayList<Usuario>();
	private List<Usuario> loginUserAux = new ArrayList<Usuario>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/produto/login.jsp");
		dispatcher.forward(request, response);
		// response.sendRedirect(request.getContextPath() + "/paginas/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager manager = JpaUtil.getEntityManager();
		UsuariosRepository usuarios = new UsuariosRepository(manager);
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		try {
			loginUser = usuarios.login(login, senha);

			if (loginUser.size() == 0) {
				request.setAttribute("mensagem", "Usuário/senha inválidos.");
				doGet(request, response);
			} else {
				if (loginUser.size() == 1) {
					HttpSession session = request.getSession();
					session.setAttribute("usuarioLogado", login);
					session.setAttribute("dataLogin", new Date());
					request.setAttribute("mensagemvalida", "Usuário/senha válidos.");

					if (!response.isCommitted()) {
					response.sendRedirect(request.getContextPath() + "/produto/public/consulta-produtos.jsp");

					}


				}

			}

		} catch (NumberFormatException e) {

			e.printStackTrace();

		} finally {
			manager.close();
		}

	}

	public List<Usuario> getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(List<Usuario> loginUser) {
		this.loginUser = loginUser;
	}

}
