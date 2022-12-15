<?xml version="1.0" encoding="utf-8"?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
</head>
<body>
	<h1>Exclusão de produto</h1>

	<c:if test="${not empty mensagem}">
		<strong>${mensagem}</strong><br/><br/>
	</c:if>

	<form  action="exclui-produto" method="post">
	<input type="hidden" name="id" value="${produtos.id}" />
	    Código:
	    <input type="text" name="id" value="${produtos.id}" disabled="disabled" />
		Nome:
		<input type="text" name="nome" size="20" value="${produtos.nome}" disabled="disabled"/><br/>
		Preço de custo:
		<input type="text" name="precoCusto" size="8" value="${produtos.precoCusto}" disabled="disabled"/><br/>
		Quantidade em estoque:
		<input type="text" name="quantidadeEstoque" size="8" value="${produtos.quantidadeEstoque}" disabled="disabled"/><br/>
		
		<input type="submit" value="Excluir">
	</form>
	
	<c:import url="/paginas/rodape.jsp" />
</body>
</html>

