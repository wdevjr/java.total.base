<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
			<title>index</title>
		</head>
		<body>
			<div class="line-separator"></div>
			<div style="text-align:center">
				<h1 class="title">CRUD</h1>
			</div>
			<div class="line-separator"></div>
			<br>
			<h:form>
				<h:panelGrid styleClass="list_table" rowClasses="row1,row2">
					<h:commandLink value="Disciplina" action="listDisciplina"/>
					<h:commandLink value="Pessoa" action="listPessoa"/>
					<h:commandLink value="Aluno" action="listAluno"/>
					<h:commandLink value="Curso" action="listCurso"/>
				</h:panelGrid>
			</h:form>
		</body>
	</html>
</f:view>
