<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aw" tagdir="/WEB-INF/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulta Produtos</title>
<link rel="stylesheet" href="Styles/yui-datatable/yui-database.css">
<link rel="stylesheet" href="Styles/tablecloth.css">
<link rel="stylesheet" href="Styles/Yahoo-Ocean/stylesheet.css">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet"></link>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="panel panel-default">
		<div class="panel-heading">
			<strong>Consulta de produtos</strong>
		</div>
		<div class="panel-body">
			<div class="panel-footer">
				<aw:pesquisa acao="consulta-produtos" nomeParametro="nome"
					descricaoParametro="Nome" />
			</div>

			<br /> <br />
			<c:if test="${empty produtos}">
				<strong>Nenhum produto encontrado.</strong>
			</c:if>



			<div class="table-responsive">
				<c:if test="${not empty produtos}">
					<table border="1" style="width: auto; height: 8px;"
						class="tablecloth-theme">
						<tr>
							<th style="text-align: center;">Código</th>
							<th style="text-align: center;">Nome</th>
							<th style="text-align: center;">Preço</th>
							<th style="text-align: center;">Quantidade</th>
							<th colspan="2" style="text-align: center;">Ação</th>
						</tr>

						<c:forEach items="${produtos}" var="produto" varStatus="p">
							<c:choose>
								<c:when test="${row.count % 2 == 0}">
									<c:set var="estiloLinha" value="odd" />
								</c:when>
								<c:otherwise>
									<c:set var="estiloLinha" value="even" />
								</c:otherwise>
							</c:choose>
							<tr text-align="center"
								<c:if test="${p.count%2 != 0}">bgcolor="#BDCEDF"</c:if>
								<c:if test="${p.count%2 == 0}">bgcolor="#E2E2E2"</c:if>>
								<td>${produto.id}</td>
								<td>${produto.nome}</td>
								<td>${produto.precoCusto}</td>
								<td>${produto.quantidadeEstoque}</td>
								<div class="btn-group pull-right">
									<td style="text-align: center;"><a
										class="btn btn-sm btn-primary"
										href="<c:url value="/edita-produto?id=${produto.id}" />">Editar</a></td>
									<td style="text-align: center;"><a
										class="delete btn btn-sm btn-danger"
										href="<c:url value="/exclui-produto?id=${produto.id}"/>">Excluir</a></td>
								</div>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="7">Total de Registros:<c:out value="${cont}" /></td>
						</tr>
					</table>
			</div>

			</c:if>
			<br> <br>
			<div class="panel-footer">
				<a class="btn btn-sm btn-success"
					href=<c:url value="/paginas/cadastro-produto.jsp"/>>Novo</a>
			</div>
			<c:import url="/paginas/rodape.jsp" />
		</div>
	</div>
</body>
</html>