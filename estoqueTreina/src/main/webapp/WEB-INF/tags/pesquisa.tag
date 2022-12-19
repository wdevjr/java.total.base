<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="nomeParametro" required="true"  %>
<%@ attribute name="descricaoParametro" required="true"  %>
<%@ attribute name="descricaoBotao" required="false"  %>
<%@ attribute name="acao" required="true"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<c:if test="${empty descricaoBotao}">
	<c:set var="descricaoBotao" value="Consultar" />
</c:if>

<form action="${acao}" method="get">
	${descricaoParametro}:
	<input type="text" name="${nomeParametro}" value="${param[nomeParametro]}" />
	<input type="submit" class="btn btn-sm btn-success" value="${descricaoBotao}" />
</form>