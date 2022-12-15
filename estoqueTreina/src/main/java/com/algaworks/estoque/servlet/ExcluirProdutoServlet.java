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
import com.algaworks.estoque.repository.Produtos;
import com.algaworks.estoque.service.ExcluirProdutoService;
import com.algaworks.estoque.service.ServiceException;
import com.algaworks.estoque.util.JpaUtil;

@WebServlet("/exclui-produto")
public class ExcluirProdutoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private String id;

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        EntityManager manager = JpaUtil.getEntityManager();
   		Produtos produtos = new Produtos(manager);
   		Produto produto = new Produto();
   		this.id = request.getParameter("id");
          try {
   			produto = produtos.consultarPorId(new Long(id));
   			request.setAttribute("produtos", produto);

   			
   		} catch (Exception e) {
   			request.setAttribute("mensagem", "Produtos/problemas ....  "+e.getMessage());
   			doGet(request, response);
   			response.sendRedirect("exclui-produtos");
   		}
           
   		RequestDispatcher dispatcher = request.getRequestDispatcher(
   				"/paginas/exclui-produto.jsp");
   		dispatcher.forward(request, response);
   	}
           
          

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//this.doGet(request, response);
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		Produtos produtos = new Produtos(manager);

		Produto produto = new Produto();

		produto = produtos.consultarPorId(new Long(id));
		String mensagem = null;
		
		try {
		ExcluirProdutoService servico = new ExcluirProdutoService(new Produtos(manager));
		trx.begin();
		servico.ExcluiProduto(produto);
		trx.commit();
		mensagem = "Produto Excluido com sucesso!";
		} catch (ServiceException e) {
			e.printStackTrace();
		} finally {
			if (trx.isActive()) trx.rollback();
			manager.close();
		}
		RequestDispatcher dispatcher2 = request.getRequestDispatcher("/paginas/exclui-produto.jsp");
		dispatcher2.forward(request, response);

		request.setAttribute("mensagem", mensagem);
		//doGet(request, response);
	}
	
	
	
 }


