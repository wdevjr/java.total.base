<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aw" tagdir="/WEB-INF/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exclui Produtos</title>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet"></link>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<style type="text/css">
body {
	text-align: center;
}

#tudo {
	width: 500px;
	position: relative;
	height: 250px";
	margin-left: 50%;
	left: 10%;
	text-align: left;
}
</style>
</head>
<body>
	<div id="tudo" style="width: 548px;">

		<strong>Exclusão de produtos</strong>
<br><br>
		<div class="panel-body" style="height: 389px; width: 538px">
			<c:if test="${not empty mensagem}">
				<strong>${mensagem}</strong>

			</c:if>

			<div class="panel-footer"
				style="background: buttonface; width: 509px; height: 235px">
				<form action="exclui-produto" class="form-horizontal" method="post"
					style="margin: 70px; height: 157px">

					<div class="form-group" style="height: 175px; width: 395px">

						<input type="hidden" name="id" size="20" value="${produtos.id}" />
						<br>
						<div class="form-group row">
							Nome:&nbsp; <input type="text" name="nome" size="38"
								disabled="disabled" value="${produtos.nome}" /><br />
						</div>
						<div class="form-group row">
							Preço Custo:&nbsp; <input type="text" name="preco"
								disabled="disabled" size="8" value="${produtos.precoCusto}" /><br />
						</div>
						<div class="form-group row">
							Quantidade Estoque:&nbsp; <input type="text" name="quantidade"
								disabled="disabled" size="8"
								value="${produtos.quantidadeEstoque}" />
						</div>


					</div>

					<input type="submit" class="delete btn btn-sm btn-danger"
						value="Excluir" />

				</form>
			</div>
		</div>
	</div>

	<%-- <c:import url="/paginas/rodape.jsp" /> --%>


</body>
</html>