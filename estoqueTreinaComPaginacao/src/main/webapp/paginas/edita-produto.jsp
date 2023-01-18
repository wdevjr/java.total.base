<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aw" tagdir="/WEB-INF/tags"%>

<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width" />
<title>Cadastro de Posts</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"></link>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<strong>Edição de Produtos</strong>
		</div>
		<div class="panel-body">

			<div class="panel-body">
				<form id="formEdita" action="edita-produto" class="form-horizontal"
					method="POST" style="margin: 10px">
					<input type="hidden" name="id" size="20" value="${produtos.id}" />
					<div class="form-group">
						<fieldset>
							<div class="form-group row">
								<c:if test="${not empty mensagem}">
									<strong>${mensagem}</strong>

								</c:if>
							</div>
							<div class="form-group row">
								<div class="col-md-4">

									<div class="form-group row">
										<label>Nome</label> <input type="text"
											class="form-control input-sm" name="nome" size="32"
											value="${produtos.nome}" autofocus="autofocus" maxlength="150"
											placeholder="Informe o Nome" />
									</div>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-4">
									<div class="form-group row">
										<label>Preço Custo</label> <input type="text"
											class="form-control input-sm" name="precoCusto" size="17"
											value="${produtos.precoCusto}" autofocus="autofocus"
											placeholder="Informe o Preço Custo!" />
									</div>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-4">
									<div class="form-group row">
										<label>Quantidade</label> <input type="text"
											class="form-control input-sm" name="quantidadeEstoque" size="8"
											value="${produtos.quantidadeEstoque}"
											placeholder="Informe a quantidade!" />
									</div>
								</div>
							</div>
						</fieldset>
					</div>
					<div class="form-group row">
						<button type="submit" class="btn btn-sm btn-primary">Editar</button>
						<a class="btn btn-sm btn-default" href="javascript:history.back()"  />Cancela</a>
					</div>
				</form>
				<div class="form-group row">
					<div class="col-md-4">
						<div class="form-group row">
							<c:import url="/paginas/rodape.jsp" />
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>