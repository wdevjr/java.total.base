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

import com.algaworks.estoque.model.Produto;
import com.algaworks.estoque.repository.ProdutosRepository;
import com.algaworks.estoque.repository.dao.InterfaceProdutosDAO;
import com.algaworks.estoque.util.JpaUtil;

@WebServlet("/consulta-produtos")
public class ConsultaProdutosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private List<Produto> ListaNenhum = new ArrayList<Produto>();
	private List<Produto> listatodos = new ArrayList<Produto>();
	private List<Produto> todosProdutosComNome = new ArrayList<Produto>();

	private String nome;
	private String nometemp;
	private String pageid;
	public String CodigoIdProduto;
	private String cont = null;
	private String img = null;

	private int maxPorPagina;
	private int paginaAtual;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		RequestDispatcher dispatcher = request.getRequestDispatcher(
//				"/paginas/consulta-produtos.jsp");
//		dispatcher.forward(request, response);
		// this.doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		EntityManager manager = JpaUtil.getEntityManager();
		ProdutosRepository produtos = new ProdutosRepository(manager);
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;

		HttpSession session = req.getSession();

		nome = request.getParameter("nome");
		if (request.getParameter("page") != null) {
			this.pageid = request.getParameter("page");
			paginaAtual = Integer.parseInt(pageid);
		} else {
			paginaAtual = 1;
		}
		if (cont != null) {
			maxPorPagina = new Integer(cont);
		}
		if (paginaAtual == 1) {
		} else {
			paginaAtual = paginaAtual - 1;
			paginaAtual = (((paginaAtual * maxPorPagina + 1) / new Integer(cont)) + 1);
		}

		String mensagem = null;

		if (nome != null) {
			nometemp = this.nome;
		}
		if (nometemp != null) {
			request.setAttribute("produtos", getTodos());
			request.setAttribute("agora", new Date());
			request.setAttribute("paginacao", getlistanome());
			cont = String.valueOf(getlistanome().size());
			request.setAttribute("cont", cont);

			RequestDispatcher dispatcher2 = request.getRequestDispatcher("/paginas/consulta-produtos.jsp");
			try {
				dispatcher2.forward(request, response);
			} catch (ServletException | IOException e) {

				e.printStackTrace();
			}

		} else {
			ListaNenhum.clear();
			request.setAttribute("produtos", "");
			request.setAttribute("agora", new Date());
			cont = String.valueOf(ListaNenhum.size());
			// response.sendRedirect(request.getContextPath() +
			// "/paginas/consulta-produtos.jsp");
			RequestDispatcher dispatcher1 = request.getRequestDispatcher("/paginas/consulta-produtos.jsp");
			try {
				dispatcher1.forward(request, response);
			} catch (ServletException | IOException e) {

				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> getTodos() {
		EntityManager manager = JpaUtil.getEntityManager();
		InterfaceProdutosDAO ldao = new ProdutosRepository(manager);
		if (this.nome != null) {
			return listatodos = ldao.todosProdutos(this.nome, this.maxPorPagina, this.paginaAtual);
		} else {
			return listatodos = ldao.todosProdutos(this.nometemp, this.maxPorPagina, this.paginaAtual);
		}
	}

	public List<Produto> getlistanome() {
		EntityManager manager = JpaUtil.getEntityManager();
		InterfaceProdutosDAO ldao = new ProdutosRepository(manager);
		if (this.nome != null) {
			return todosProdutosComNome = ldao.todosProdutosComNome(this.nome);
		} else {
			return todosProdutosComNome = ldao.todosProdutosComNome(this.nometemp);
		}

	}

	public int getTotal() {

		EntityManager manager = JpaUtil.getEntityManager();
		InterfaceProdutosDAO ldao = new ProdutosRepository(manager);

		return ldao.totalDeProdutos();
	}

}
