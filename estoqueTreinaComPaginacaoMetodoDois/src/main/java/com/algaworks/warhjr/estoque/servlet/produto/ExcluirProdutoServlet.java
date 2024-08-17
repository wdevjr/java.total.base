package com.algaworks.warhjr.estoque.servlet.produto;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algaworks.warhjr.estoque.model.Produto;
import com.algaworks.warhjr.estoque.repository.produto.ProdutosRepository;
import com.algaworks.warhjr.estoque.service.produto.ExcluirProdutoService;
import com.algaworks.warhjr.estoque.util.JpaUtil;
import com.algaworks.warhjr.estoque.util.ServiceException;

@WebServlet("/produto/public/exclui-produto")
public class ExcluirProdutoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private String id;

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        EntityManager manager = JpaUtil.getEntityManager();
   		ProdutosRepository produtos = new ProdutosRepository(manager);
   		Produto produto = new Produto();
   		this.id = request.getParameter("id");
          try {
   			produto = produtos.consultarPorId(new Long(id));
   			request.setAttribute("produtos", produto);

   			
   		} catch (Exception e) {
   			request.setAttribute("mensagem", "Produtos/problemas para Excluir ....  "+e.getMessage());
   			//doGet(request, response);
   			response.sendRedirect("exclui-produtos");
   		}
          
          if(!response.isCommitted()) {
   		RequestDispatcher dispatcher = request.getRequestDispatcher(
   				"/produto/public/exclui-produto.jsp");
   		dispatcher.forward(request, response);
          }
   	}
           
          

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//this.doGet(request, response);
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		ProdutosRepository produtos = new ProdutosRepository(manager);

		Produto produto = new Produto();

		produto = produtos.consultarPorId(new Long(id));
		String mensagem;
		
		try {
		ExcluirProdutoService servico = new ExcluirProdutoService(new ProdutosRepository(manager));
		trx.begin();
		servico.ExcluiProduto(produto);
		trx.commit();
		mensagem = "Produto Excluido com sucesso!";
		request.setAttribute("mensagem", mensagem);
		
		} catch (ServiceException e) {
			mensagem = e.getMessage();
		} finally {
			if (trx.isActive()) trx.rollback();
			manager.close();
		}
		RequestDispatcher dispatcher2 = request.getRequestDispatcher("/produto/public/exclui-produto.jsp");
		dispatcher2.forward(request, response);
	}
	
	
	
 }


