<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


<f:view>
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
			<title>Edit Disciplina</title>
		</head>
		<body>
			<div class="line-separator"></div>
			<div style="text-align:center">
				<h1 class="title">Edit Disciplina</h1>
			</div>
			<div class="line-separator"></div>
			<br>
			<h:form>
				<table class="table">
					<h:inputHidden value="#{DisciplinaManagedBean.disciplina.id}"/>
					<tr>
						<td>nome</td>
						<td>
							<h:inputText required="true" label="nome" value="#{DisciplinaManagedBean.disciplina.nome}"/>
						</td>
					</tr>
					<tr>
						<td>quantidadeCreditos</td>
						<td>
							<h:inputText label="quantidadeCreditos" value="#{DisciplinaManagedBean.disciplina.quantidadeCreditos}"/>
						</td>
					</tr>
					<tr>
						<td>nomeProfessor</td>
						<td>
							<h:inputText label="nomeProfessor" value="#{DisciplinaManagedBean.disciplina.nomeProfessor}"/>
						</td>
					</tr>
					<tr>
						<td>curso</td>
						<td>
							<h:selectOneMenu required="true" label="curso" value="#{DisciplinaManagedBean.disciplina.curso}">
								<f:selectItems value="#{CursoManagedBean.cursos}" />
								<f:converter converterId="CursoConverter"/>
							</h:selectOneMenu>
						</td>
					</tr>
				</table>
				<br>
				

				<h:messages errorClass="error"></h:messages>
				<div style="text-align:center">
					<h:commandButton value="Save" action="#{DisciplinaManagedBean.editDisciplina}" styleClass="save"/>
					<h:commandButton value="Cancel" immediate="true" action="listDisciplina" styleClass="cancel"/>
				</div>
			</h:form>
		</body>
	</html>
</f:view>
