<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aw" tagdir="/WEB-INF/tags"%>
<html>
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width" />
	<title>Login</title>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet"></link>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<!-- <link href="/css/css.css" rel="stylesheet"></link> -->
<%-- <link href="<c:url value="../css/css.css"/>" rel="stylesheet" type="text/css" media="screen"/> --%>


<style type="text/css">

#tudo {
	width: 500px;
	position: relative;
	height: 250px";
	margin-left: -30%%;
	left: 30%;
	text-align: left;
}

.container {
	position: relative;
}

.child {
	width: 350px;
	height: 90px;
	/* Centralizar na vertical e na horizontal */
	position: absolute;
	top: 50%;
	left: 30%;
	margin: 50px;
	/* aplique margens superior e esquerda negativas para centralizar de verdade o elemento */
}
</style>
</head>
<body>
	<div class="panel panel-default">
		<div class="container">
			<div class="child">
				<h1>Login</h1>

				<c:if test="${not empty mensagem}">
					<img src="<c:out value="com/algaworks/estoque/img/warnning.gif"/>"/>
					<img src="../../img/warning.png" />
					<img src='<c:url value="../../paginas/warning.png" />' />
					<img src="<c:url value="/img/warnning.png"/>" />
					<strong style="color: red; font: normal; font-size: 11;">${mensagem}</strong>
					<br />
				</c:if>

				<form action="login" method="post">
					<div class="form-group">
						<table bgcolor="#DFDFBF" style="width: 199px; height: 148px; ">
							<tr>
								<td>&nbsp; Usuário:</td>
								<td>
									<input type="text" style="color: blue; width: 121px" name="login" size="12" value="${param.usuario}" />
										
									
								</td>

							</tr>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;Senha:</td>
								<td><input type="password" style="color: green; width: 121px"
									name="senha" size="12" /></td>
							</tr>
							<tr>

								<td>&nbsp;
								<input type="submit" class="btn btn-sm btn-success"
									value="Login" /></td>
							</tr>
						</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>