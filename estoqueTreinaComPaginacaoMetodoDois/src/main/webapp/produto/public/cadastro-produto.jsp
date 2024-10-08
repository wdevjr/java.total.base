<?xml version="1.0" encoding="utf-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset=”iso-8859-1” />
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
			<strong>Cadastro de Produtos</strong>
		</div>
		<div class="panel-body">

			<div class="panel-body">
				<form action="cadastro-produto" class="form-horizontal"
					method="post" style="margin: 10px">
					<div class="form-group">
						<fieldset>
							<div class="form-group row">
								<c:if test="${not empty mensagem}">
								<img src="../../img/Info.png" />
									<strong>${mensagem}</strong>

								</c:if>
							</div>
							<div class="form-group row">
								<div class="col-md-4">

									<div class="form-group row">
										<label>Nome</label> <input type="text"
											class="form-control input-sm" name="nome" size="32"
											value="${form.nome}" autofocus="autofocus" maxlength="150"
											placeholder="Informe o Nome" />
									</div>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-4">
									<div class="form-group row">
										<label>Preço Custo</label> <input type="text"
											class="form-control input-sm" name="preco" size="17"
											value="${form.preco}" autofocus="autofocus"
											placeholder="Informe o Preço Custo!" />
									</div>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-4">
									<div class="form-group row">
										<label>Quantidade</label> <input type="text"
											class="form-control input-sm" name="quantidade" size="8"
											value="${form.quantidade}"
											placeholder="Informe a quantidade!" />
									</div>
								</div>
							</div>
						</fieldset>
					</div>
					<div class="form-group row">
						<button type="submit" class="btn btn-sm btn-primary">Salvar</button>
						<a class="btn btn-sm btn-default" href="javascript:history.back()" />Cancela</a>
					</div>
				</form>
				<div class="form-group row">
					<div class="col-md-4">
						<div class="form-group row">
							<c:import url="/produto/public/rodape.jsp" />
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>