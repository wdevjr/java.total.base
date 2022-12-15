<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
</head>
<body>
	<h1>Cadastro de produto</h1>

	<c:if test="${not empty mensagem}">
		<strong>${mensagem}</strong><br/><br/>
	</c:if>

	<form action="cadastro-produto" method="post" >
		Nome:
		<input type="text" name="nome" size="20" value="${form.nome}" /><br/>
		Preço de custo:
		<input type="text" name="preco" size="8" value="${form.preco}" /><br/>
		
		Quantidade em estoque:
		<input type="text" name="quantidade" size="8" value="${form.quantidade}" /><br/>
		
		<input type="submit" value="Cadastrar">
	</form>
	
	<c:import url="/paginas/rodape.jsp" />
</body>
</html>