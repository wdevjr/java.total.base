<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="com.algaworks.estoque.util.JpaUtil"%>
<%@page import="com.algaworks.estoque.model.Produto"%>
<%@page import="com.algaworks.estoque.repository.Produtos"%>
<%@page import="com.algaworks.estoque.service.ExcluirProdutoService"%>
<%@page import="com.algaworks.estoque.servlet.ExcluirProdutoServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Excluindo Produto</title>
</head>
<body>
<%
EntityManager manager = JpaUtil.getEntityManager();
EntityTransaction trx = manager.getTransaction();
Produto produto = new Produto();

produto.setId(new Long(request.getParameter("id")));
ExcluirProdutoService servico = new ExcluirProdutoService(new Produtos(manager));

	trx.begin();
	servico.ExcluiProduto(produto.getId());
	trx.commit();
	RequestDispatcher dispatcher = request.getRequestDispatcher(
	"/WEB-INF/paginas/consulta-produtos.jsp");
	dispatcher.forward(request, response); 
    response.sendRedirect("/WEB-INF/paginas/consulta-produtos.jsp");
%>
</body>
</html>