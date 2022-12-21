<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edita Produtos</title>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet"></link>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<style type="text/css">
body {
	text-align: center;
}

#tudo {
	position: relative;
	height: 60px";
	margin-left: 50%;
	left: 10%;
	text-align: left;
}
</style>
</head>
<body>
	<div id="tudo">

		<strong>Edição de produtos</strong>
       <br><br>
		<c:if test="${not empty mensagem}">
			<strong>${mensagem}</strong>

		</c:if>

		<div class="panel-footer"
			style="background: buttonface; width: 499px; height: 250px">

			<form action="edita-produto" class="form-horizontal" method="post"
				style="margin: 70px; height: 154px; width: 384px">
				<input type="hidden" name="id" size="20" value="${produtos.id}" />
				<br>
				<div class="form-group">
					<div class="form-group row">
						<label>Nome:</label>&nbsp;<input type="text" name="nome" size="35"
							value="${produtos.nome}" /><br />
					</div>
					<div class="form-group row">
						<label>Preço Custo:&nbsp;</label><input type="text"
							name="precoCusto" size="15" value="${produtos.precoCusto}" /><br />
					</div>
					<div class="form-group row">
						<label>Quantidade Estoque:&nbsp;</label> <input type="text"
							name="quantidadeEstoque" size="8"
							value="${produtos.quantidadeEstoque}" /><br />
					</div>

				</div>

				<input type="submit" class="btn btn-sm btn-primary" value="Editar" />

			</form>
		</div>
	</div>


	<%-- <c:import url="/paginas/rodape.jsp" /> --%>


</body>
</html>