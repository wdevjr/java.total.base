<?xml version="1.0" encoding="utf-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulta Produtos</title>
<link href="../../Styles/yui-datatable/yui-datatable.css"
	rel="stylesheet" type="text/css" />
<link href="../../Styles/tablecloth.css" rel="stylesheet"
	rel="stylesheet" type="text/css" />
<link href="../../Styles/Yahoo-Ocean/stylesheet.css" rel="stylesheet"
	rel="stylesheet" type="text/css" />

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"/>
<script type="text/javascript"> 
src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
</script>
</head>
<body>

	<div class="panel panel-default">
		<div class="panel-heading">
			<strong>Consulta de produtos</strong>
		</div>
		<div class="panel-body">
			<div class="panel-footer">
				<form action="consulta-produtos" method="get">
					Nome: <input type="text" name="nome" />
					<button type="submit" value="Consultar">
						<img src="../../img/magnifier.png" /> Consultar
					</button>
				</form>
			</div>

			<br /> <br />
			<c:if test="${empty produtos}">
				<strong>Nenhum produto encontrado.</strong>
			</c:if>

			<c:if test="${not empty produtos}">
				<div class="table-responsive">
					<table class="tablecloth-theme">
						<tr>
							<th style="text-align: center;">Código</th>
							<th style="text-align: center;">Nome</th>
							<th style="text-align: center;">Preço</th>
							<th style="text-align: center;">Quantidade</th>
							<th colspan="2" style="text-align: center;">Ação</th>
						</tr>
						<c:forEach items="${produtos}" var="produto" varStatus="p">
							<tr bgcolor="${p.count	% 2	==	0 ? '#BDCEDF':'#E2E2E2'}">
								<td>${produto.id}</td>
								<td>${produto.nome}</td>
								<td><fmt:formatNumber value="${produto.precoCusto}"
										pattern="#,#00.00#" /></td>
								<td>${produto.quantidadeEstoque}</td>
								<div class="btn-group pull-right">
									<td style="text-align: center;"><a
										href="<c:url value="edita-produto?id=${produto.id}" />"><img
											src="../../img/alterar.gif"></a></td>
									<td style="text-align: center;"><a
										href="<c:url value="exclui-produto?id=${produto.id}"/>"><img
											src="../../img/excluir2.gif"></a></td>

								</div>
							</tr>
						</c:forEach>
					</table>
					<br>
					<c:choose>
						<c:when test="${cont == maxPorPagina}">
							<td><strong>4 Itens!</strong></td>
						</c:when>
						<c:when test="${cont == (2)}">
							<td><strong>2 Itens!</strong></td>
						</c:when>
						<c:when test="${cont == (3)}">
							<td><strong>3 Itens!</strong></td>
						</c:when>
						<c:otherwise>
							<c:forEach items="${paginacao}" var="produto" varStatus="q">
								<td><a
									href="<c:url value="/produto/public/consulta-produtos?page=${q.count}"/>">${q.count}</a></td>
							</c:forEach>
							<p>
								Registros Encontrados:
								<c:out value="${cont}"></c:out>
							</p>
						</c:otherwise>
					</c:choose>

				</div>

			</c:if>
			<br> <br>
			<div class="panel-footer">
				<a class="btn btn-sm btn-success"
					href=<c:url value="/produto/public/cadastro-produto.jsp"/>>Novo</a>
			</div>
			<c:import url="/produto/public/rodape.jsp" />
		</div>
	</div>
</body>
</html>