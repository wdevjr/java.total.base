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

import com.algaworks.warhjr.estoque.form.ProdutoForm;
import com.algaworks.warhjr.estoque.model.Produto;
import com.algaworks.warhjr.estoque.repository.ProdutosRepository;
import com.algaworks.warhjr.estoque.service.EditarProdutoService;
import com.algaworks.warhjr.estoque.service.ServiceException;
import com.algaworks.warhjr.estoque.util.JpaUtil;

@WebServlet("/edita-produto")
public class EditaProdutosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String id = null;
	private Produto produtoUx = new Produto();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager manager = JpaUtil.getEntityManager();
		ProdutosRepository produtos = new ProdutosRepository(manager);
		Produto produto = new Produto();
		
        
		id = request.getParameter("id");

		
		try {
			produto = produtos.consultarPorId(new Long(id));
			request.setAttribute("produtos", produto);
            id = null;
		} catch (Exception e) {
			request.setAttribute("mensagem", "Produtos/problemas pra Editar ....  " + e.getMessage());
			//doGet(request, response);
			response.sendRedirect("edita-produto");
		} finally {
			//id = null;
			manager.close();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/paginas/edita-produto.jsp");
		dispatcher.forward(request, response);
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		
		produtoUx.setId(new Long(request.getParameter("idproduto")));
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
		//ProdutoForm form = null;
		String mensagem = null;
		id = null;
		try {
			trx.begin();
			//form = ProdutoForm.fromRequest(request);
			//produtoUx = form.toProduto();
			EditarProdutoService servico = new EditarProdutoService(new ProdutosRepository(manager));
			servico.EditaProduto(produtoUx);
			id = null;
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
		//request.setAttribute("form", form);

		doGet(request, response);

	}

}