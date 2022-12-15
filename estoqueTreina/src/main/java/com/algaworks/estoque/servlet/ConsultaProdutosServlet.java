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
	private List<Produto> todosProdutos1  = new ArrayList<Produto>();
	private List<Produto> todosProdutos2  = new ArrayList<Produto>();
	public String CodigoIdProduto;
	private String cont = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EntityManager manager = JpaUtil.getEntityManager();
		Produtos produtos = new Produtos(manager);
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		HttpSession session = req.getSession();
		
		String nome = request.getParameter("nome");
		String CodigoIdProduto = request.getParameter("id");
		String mensagem = null;
		
			    if (nome == null)
			    {
			    	//mensagem = "Informe um Produto!";
					//ListaTodos = produtos.todos();
			    	ListaNenhum.clear();
					request.setAttribute("produtos", ListaNenhum);
					request.setAttribute("agora", new Date());
					cont = String.valueOf(ListaNenhum.size());
					request.setAttribute("cont", cont);
    				//response.sendRedirect(request.getContextPath() + "/paginas/consulta-produtos.jsp");
					RequestDispatcher dispatcher1 = request.getRequestDispatcher(
							"/paginas/consulta-produtos.jsp");
					dispatcher1.forward(request, response);
					//response.sendRedirect("/consulta-produtos");

			    } else {
			    	todosProdutos1  = produtos.porNomeNaoExato(nome);
			    	//todosProdutos2  = produtos.porNomeNaoExato(nome);
					request.setAttribute("produtos", todosProdutos1);
					//request.setAttribute("produtos", todosProdutos2);
					request.setAttribute("agora", new Date());
					cont = String.valueOf(todosProdutos1.size());
					//cont = String.valueOf(todosProdutos2.size());
	                request.setAttribute("cont", cont);
//	                if (todosProdutos1.size() == 0)
//	                {
//	                todosProdutos2  = produtos.porNomeNaoExato(nome);
//	                request.setAttribute("produtos", todosProdutos2);
//	                }
					RequestDispatcher dispatcher2 = request.getRequestDispatcher(
							"/paginas/consulta-produtos.jsp");
					dispatcher2.forward(request, response);
			    	
					
	             //  response.sendRedirect("/consulta-produtos"); 

				}


  }
}