package com.algaworks.estoque.servlet;

import java.io.IOException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algaworks.estoque.model.Produto;
import com.algaworks.estoque.repository.Produtos;
import com.algaworks.estoque.service.EditarProdutoService;
import com.algaworks.estoque.service.ExcluirProdutoService;
import com.algaworks.estoque.service.ServiceException;
import com.algaworks.estoque.util.JpaUtil;

@WebServlet("/exclui-produto")
public class ExcluirProdutoServlet extends HttpServlet{
	
	private String id;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		 this.id = request.getParameter("id");

	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   
	    EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		Produtos produtos = new Produtos(manager);
        Produto produtoAux = new Produto();
        produtoAux.setId(new Long(this.id));
		String mensagem = null;
	    produtoAux = produtos.consultarPorId(new Long(this.id));
		ExcluirProdutoService servico = new ExcluirProdutoService(new Produtos(manager));
		try {
			trx.begin();
			servico.ExcluiProduto(produtoAux.getId());
			trx.commit();
			RequestDispatcher dispatcher = request.getRequestDispatcher(
			"/WEB-INF/paginas/consulta-produtos.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {

			request.setAttribute("mensagem", mensagem);
		}
		
		doGet(request, response);
		request.setAttribute("mensagem", mensagem);
	}


	
	
 }


