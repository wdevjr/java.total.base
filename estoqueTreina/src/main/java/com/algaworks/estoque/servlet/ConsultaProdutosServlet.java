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
import com.algaworks.estoque.repository.Produtos;
import com.algaworks.estoque.util.JpaUtil;

@WebServlet("/consulta-produtos")
public class ConsultaProdutosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private List<Produto> ListaNenhum = new ArrayList<Produto>();
	private List<Produto> ListaTodos = new ArrayList<Produto>();
	private List<Produto> todosProdutos = new ArrayList<Produto>();

	private String nome;

	public String CodigoIdProduto;
	private String cont = null;
	private String img = null;

	private int maxPorPagina = 3;
	private int paginaAtual = 1;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		EntityManager manager = JpaUtil.getEntityManager();
		Produtos produtos = new Produtos(manager);
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;

		HttpSession session = req.getSession();

		nome = request.getParameter("nome");
		// String CodigoIdProduto = request.getParameter("id");
		String mensagem = null;

		if (nome == null) {
			// ListaTodos = produtos.todos();
			ListaNenhum.clear();
			request.setAttribute("produtos", ListaNenhum);
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
			// response.sendRedirect("/consulta-produtos");

		} else {
			//todosProdutos = produtos.porNomeNaoExato(this.nome, maxPorPagina, paginaAtual);
			request.setAttribute("produtos", getlist());
			request.setAttribute("agora", new Date());
			//cont = String.valueOf(todosProdutos.size());
			//request.setAttribute("cont", cont);
			RequestDispatcher dispatcher2 = request.getRequestDispatcher("/paginas/consulta-produtos.jsp");
			try {
				dispatcher2.forward(request, response);
			} catch (ServletException | IOException e) {

				e.printStackTrace();
			}

			// response.sendRedirect("/consulta-produtos");

		}

	}

	public List<Produto> getlist() {
		EntityManager manager = JpaUtil.getEntityManager();
		Produtos produtos = new Produtos(manager);
		return ListaTodos = produtos.porNomeNaoExato(this.nome, maxPorPagina, paginaAtual);
	}

	public String primeiraPagina() {

		paginaAtual = 0;

		return "mostrar";
	}

	public String ultimaPagina() {

		int rest = getTotal() % maxPorPagina;

		if (rest != 0)
			paginaAtual = getTotal() - rest;
		else
			paginaAtual = getTotal() - maxPorPagina;

		return "mostrar";
	}

	public int getpaginaAtual() {

		return paginaAtual;
	}

	public int getproximaPagina() {
		int total = getTotal();
		int soma = paginaAtual + maxPorPagina;

		int proxima = (soma > total) ? total : soma;

		return proxima;
	}

	public int getMaxPorPagina() {

		return maxPorPagina;
	}

	public int getTotal() {

		EntityManager manager = JpaUtil.getEntityManager();
		Produtos produtos = new Produtos(manager);

		return produtos.totaldeProdutos();
	}

	public String proxima() {

		int soma = paginaAtual + maxPorPagina;

		if (soma < getTotal())
			paginaAtual += maxPorPagina;

		return "mostrar";
	}

	public String anterior() {
		paginaAtual -= maxPorPagina;
		if (paginaAtual < 0)
			paginaAtual = 0;

		return "mostrar";

	}

	public List<Produto> getTodosProdutos() {
		return todosProdutos;
	}

	public void setTodosProdutos(List<Produto> todosProdutos) {
		this.todosProdutos = todosProdutos;
	}

	public void setMaxPorPagina(int maxPorPagina) {
		this.maxPorPagina = maxPorPagina;
	}

	public void setPaginaAtual(int paginaAtual) {
		this.paginaAtual = paginaAtual;
	}
	
	
}
