<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aw" tagdir="/WEB-INF/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="Styles/yui-datatable/yui-database.css">
<link rel="stylesheet" href="Styles/tablecloth.css">
<link rel="stylesheet" href="Styles/Yahoo-Ocean/stylesheet.css">

	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"></link>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">
body {

	text-align: center;
}

#tudo {

    position: relative;
    height: 400px";
	margin-left: 50%;
	left: 10%;
	text-align: left;
	
}
</style>
</head>
<body>
	<div id="tudo">

				<strong>Cadastro de produtos</strong>

			<div class="panel-body">
				<c:if test="${not empty mensagem}">
					<strong>${mensagem}</strong>

				</c:if>

				<div class="panel-footer"
					style="background: buttonface; width: 391px; height: 227px">

					<form action="cadastro-produto" class="form-horizontal"
						method="post">
						<br>
						<div class="form-group">
							<fieldset>
								<div class="form-group row">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nome:&nbsp;<input type="text" name="nome" size="32"
										value="${form.nome}" /><br />
								</div>
								<div class="form-group row">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label>Preço Custo:&nbsp;</label><input type="text"
										name="preco" size="8" value="${form.preco}" /><br />
								</div>
								<div class="form-group row">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label>Quantidade Estoque:&nbsp;</label> <input type="text"
										name="quantidade" size="8" value="${form.quantidade}" /><br />
								</div>

							</fieldset>
						</div>
						<div class="form-group row">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="btn btn-sm btn-success"
								value="Cadastrar" />
						</div>
					</form>
				</div>
			</div>
		</div>
	
	<c:import url="/paginas/rodape.jsp" /> 


</body>
</html>