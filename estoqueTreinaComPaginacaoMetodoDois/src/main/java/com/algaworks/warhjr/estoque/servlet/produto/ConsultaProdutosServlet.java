package com.algaworks.warhjr.estoque.servlet.produto;

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

import com.algaworks.warhjr.estoque.model.Produto;
import com.algaworks.warhjr.estoque.repository.produto.ProdutosRepository;
import com.algaworks.warhjr.estoque.repository.produto.dao.InterfaceProdutosDAO;
import com.algaworks.warhjr.estoque.util.JpaUtil;

@WebServlet("/produto/public/consulta-produtos")
public class ConsultaProdutosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private List ListaNenhum;
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

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		EntityManager manager = JpaUtil.getEntityManager();
		ProdutosRepository produtos = new ProdutosRepository(manager);

		nome = request.getParameter("nome");
		if (request.getParameter("page") != null) {
			this.pageid = request.getParameter("page");
			paginaAtual = Integer.parseInt(pageid);
		} else {
			paginaAtual = 1;
		}

			maxPorPagina = 4;

		if (paginaAtual == 1) {
		} else {
			paginaAtual = paginaAtual - 1;
			paginaAtual = ((((paginaAtual * maxPorPagina) + 1) / maxPorPagina) + 1);
		}

		String mensagem = null;

		if (nome != null) {
			nometemp = this.nome;
		}
		if ((nometemp != null) && (nometemp != "")) {
			request.setAttribute("produtos", getTodos());
			request.setAttribute("agora", new Date());
			request.setAttribute("paginacao", getlistanome());
			cont = String.valueOf(getlistanome().size());
			request.setAttribute("cont", cont);
			RequestDispatcher dispatcher2 = request.getRequestDispatcher("/produto/public/consulta-produtos.jsp");
			try {
				dispatcher2.forward(request, response);
			} catch (ServletException | IOException e) {

				e.printStackTrace();
			}

		} else {
			
			request.setAttribute("produtos", ListaNenhum);
			request.setAttribute("agora", new Date());
			cont = "0";
			RequestDispatcher dispatcher1 = request.getRequestDispatcher("/produto/public/consulta-produtos.jsp");
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
			return listatodos = ldao.todosProdutosNomeComParam(this.nome, this.maxPorPagina, this.paginaAtual);
		} else {
			return listatodos = ldao.todosProdutosNomeComParam(this.nometemp, this.maxPorPagina, this.paginaAtual);
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

	//desnecessario ----
	public int todosProdutosComNomeTotal(List<Produto> list) {
			int total = (list.size());
			return total;

	}
  
}
