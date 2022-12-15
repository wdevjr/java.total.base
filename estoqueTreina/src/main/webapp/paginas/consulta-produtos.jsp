<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="aw" tagdir="/WEB-INF/tags" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h1>Consulta de produtos</h1>

	<aw:pesquisa acao="consulta-produtos" nomeParametro="nome"
		descricaoParametro="Nome" />

		<br />
		<br />
	<c:if test="${empty produtos}">
		<strong>Nenhum produto encontrado.</strong>
	</c:if>
	
	<c:if test="${not empty produtos}"> 
	<table border="1">
		<tr>
		    <th>Código</th>
			<th>Nome</th>
			<th>Preço</th>
			<th>Quantidade</th>
			<th colspan="2">Ação</th>
		</tr>
		
		<c:forEach items="${produtos}" var="produto">
				<tr>
					<td>${produto.id}</td>
					<td>${produto.nome}</td>
					<td><fmt:setLocale value="pt_BR" /> <fmt:formatNumber
							type="currency" value="${produto.precoCusto}" /></td>
					<td>${produto.quantidadeEstoque}</td>

					<td><a href="<c:url value="/edita-produto?id=${produto.id}" />">Editar</a></td>
	                <td><a href="<c:url value="/exclui-produto?id=${produto.id}"/>">Excluir</a></td>
	              		
				</tr>

			</c:forEach>
		    <tr>
				<td colspan="7">Total de Registros:<c:out value="${cont}"/></td>
			</tr>
	</table>
 	</c:if>
	<br><br>
	<a href=<c:url value="/paginas/cadastro-produto.jsp"/>>Novo</a>
	<c:import url="/paginas/rodape.jsp" />
</body>
</html>