<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h1>Edição de produto</h1>

	<c:if test="${not empty mensagem}">
		<strong>${mensagem}</strong><br/><br/>
	</c:if>

	<form  action="edita-produto"  method="post">
	    <input type="hidden" name="id" value="${produtos.id}"/>
		Nome:
		<input type="text" name="nome" size="20" value="${produtos.nome}"/><br/>
		Preço de custo:
		<input type="text" name="precoCusto" size="8" value="${produtos.precoCusto}" /><br/>
		Quantidade em estoque:
		<input type="text" name="quantidadeEstoque" size="8" value="${produtos.quantidadeEstoque}" /><br/>
		
		<input type="submit" value="Editar">
	</form>
	
	<c:import url="/WEB-INF/paginas/rodape.jsp" />
</body>
</html>