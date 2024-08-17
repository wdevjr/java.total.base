<?xml version="1.0" encoding="utf-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width" />
<title>Login</title>
<script type="text/javascript">


</script>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"></link>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js">

</script>
<link href="/css/css.css" rel="stylesheet"></link>



<style type="text/css">
#divCenter {
	width: 400px;
	height: 150px;
	left: 80%;
	margin: -130px 0 0 -210px;
	padding: 10px;
	position: relative;
	top: 200%;
}

body {
	/* background: url(../css/ph-10032.jpg) no-repeat center center fixed; 
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	padding-top: 100px; */
	
}

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

#official-info {
	background: #FFFFB9;
	width: 70%;
	height: 70px;
	overflow-y: auto;
	overflow-x: hidden;
}

#cute-cat {
	width: 70px;
}
</style>
</head>
<body>
	<div class="panel panel-default">
		<div class="container">
			<div class="child">
				<div id="divCenter">
					<h1>Login</h1>
					<c:if test="${not empty mensagem}">
						<div id="official-info" style="width: 199px; height: 30px">
							<img src="../img/block.png" /> <strong
								style="color: red; font: normal; font-size: 11;">${mensagem}</strong>
							<br />
						</div>
					</c:if>
					<c:if test="${not empty mensagemvalida}">
						<div id="official-info" style="width: 199px; height: 30px">
							<img src="../img/Info.png" /> <strong
								style="color: green; font: normal; font-size: 11;">${mensagemvalida}</strong>
							<br />
						</div>

					</c:if>


					<form action="login" method="post">
						<div class="form-group">
							<table bgcolor="#DFDFBF" style="width: 199px; height: 148px;">
								<tr>
									<td>&nbsp; Usuário:</td>
									<td><input type="text" style="color: blue; width: 121px"
										name="login" size="12" value="${param.usuario}" /></td>

								</tr>
								<tr>
									<td>&nbsp;&nbsp;&nbsp;Senha:</td>
									<td><input type="password"
										style="color: green; width: 121px" name="senha" size="12" /></td>
								</tr>
								<tr>

									<td>&nbsp; <input type="submit"
										class="btn btn-sm btn-success" value="Login" /></td>
								</tr>
							</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>