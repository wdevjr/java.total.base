package com.algaworks.estoque.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algaworks.estoque.model.Produto;
import com.algaworks.estoque.repository.ProdutosRepository;
import com.algaworks.estoque.service.EditarProdutoService;
import com.algaworks.estoque.service.ServiceException;
import com.algaworks.estoque.util.JpaUtil;

@WebServlet("/edita-produto")
public class EditaProdutosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String id;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager manager = JpaUtil.getEntityManager();
		ProdutosRepository produtos = new ProdutosRepository(manager);
		Produto produto = new Produto();
		id = request.getParameter("id");
		try {
			produto = produtos.consultarPorId(new Long(id));
			request.setAttribute("produtos", produto);

		} catch (Exception e) {
			request.setAttribute("mensagem", "Produtos/problemas pra Editar ....  " + e.getMessage());
			doGet(request, response);
			response.sendRedirect("edita-produto");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/paginas/edita-produto.jsp");
		dispatcher.forward(request, response);
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		Produto produtoUx = new Produto();
		produtoUx.setId(new Long(request.getParameter("id")));
		produtoUx.setNome(request.getParameter("nome"));
		
		if ((request.getParameter("precoCusto") == "") || (request.getParameter("precoCusto") == null)) {
			produtoUx.setPrecoCusto(new BigDecimal(0));
		} else {
			produtoUx.setPrecoCusto(new BigDecimal(request.getParameter("precoCusto")));
		}
		if (((request.getParameter("quantidadeEstoque")) == "") || (request.getParameter("quantidadeEstoque") == null)) {
			produtoUx.setQuantidadeEstoque(0);
		} else {
			produtoUx.setQuantidadeEstoque(new Integer(request.getParameter("quantidadeEstoque")));
		}
		
		//Produto produto = produtoUx;

		String mensagem = null;

		try {
			trx.begin();
			EditarProdutoService servico = new EditarProdutoService(new ProdutosRepository(manager));
			servico.EditaProduto(produtoUx);
			trx.commit();
			mensagem = "Produto Editado com sucesso!";

		} catch (ServiceException e) {
			mensagem = e.getMessage();
		} finally {
			if (trx.isActive())
				trx.rollback();
			manager.close();
		}

		request.setAttribute("mensagem", mensagem);
		// request.setAttribute("form", form);

		doGet(request, response);

	}

}