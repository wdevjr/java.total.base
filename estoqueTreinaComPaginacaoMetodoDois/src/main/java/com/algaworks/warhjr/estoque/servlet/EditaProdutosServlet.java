package com.algaworks.warhjr.estoque.servlet;

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

import com.algaworks.warhjr.estoque.model.Produto;
import com.algaworks.warhjr.estoque.repository.ProdutosRepository;
import com.algaworks.warhjr.estoque.service.EditarProdutoService;
import com.algaworks.warhjr.estoque.service.ServiceException;
import com.algaworks.warhjr.estoque.util.JpaUtil;

@WebServlet("/paginas/public/edita-produto")
public class EditaProdutosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String id;
	private String idTemp;
	private Produto produtoUx;
	private Produto produto;
	private Produto produtoAx;



	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager manager = JpaUtil.getEntityManager();
		ProdutosRepository produtos = new ProdutosRepository(manager);

		this.id = request.getParameter("id");

		try {
			produto = produtos.consultarPorId(new Long(this.id));
			//produtoAx = produto;
			request.setAttribute("produtos", produto);

		} catch (Exception e) {
			request.setAttribute("mensagem", "Produtos/problemas pra Editar ....  " + e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/paginas/public/edita-produto.jsp");
			dispatcher.forward(request, response);
			// doGet(request, response);
			// response.sendRedirect("edita-produto");
		}

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/paginas/public/edita-produto.jsp");
		dispatcher.forward(request, response);
		// response.sendRedirect("edita-produto");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		Long idTemp = null;
		Produto produtoUx = new Produto();
		Produto produtoAx = new Produto();
		produtoUx.setId(new Long(request.getParameter("idproduto")));
		produtoUx.setNome(request.getParameter("nome"));
		if (request.getParameter("precoCusto") == "") {
			produtoUx.setPrecoCusto(new BigDecimal(0));
		}else {
			produtoUx.setPrecoCusto(new BigDecimal(request.getParameter("precoCusto")));
		}
		if (request.getParameter("quantidadeEstoque") == "") {
			produtoUx.setQuantidadeEstoque(new Integer(0));
		}else {
			produtoUx.setQuantidadeEstoque(new Integer(request.getParameter("quantidadeEstoque")));			
		}
        produtoAx = produtoUx;
		String mensagem = null;

		try {

			EditarProdutoService servico = new EditarProdutoService(new ProdutosRepository(manager));
//			try {
			trx.begin();
			servico.EditaProduto(produtoUx);
			trx.commit();
			mensagem = "Produto Editado com sucesso!";
		} catch (ServiceException e) {
			mensagem = e.getMessage();
			// e.printStackTrace();
		}

//		} finally {
//			if (trx.isActive()) trx.rollback();
//			manager.close();
//		}

		request.setAttribute("mensagem", mensagem);
		request.setAttribute("produtos", produtoAx);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/paginas/public/edita-produto.jsp");
		dispatcher.forward(request, response);
		// response.sendRedirect("edita-produto");

	}
}