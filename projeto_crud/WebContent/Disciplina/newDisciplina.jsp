<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


<f:view>
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
			<title>New Disciplina</title>
		</head>
		<body>
			<div class="line-separator"></div>
			<div style="text-align:center">
			<h1 class="title">New Disciplina</h1>
			</div>
			<div class="line-separator"></div>
			<br>
			<h:form>
				<h:panelGrid columns="2" styleClass="table">
					<h:outputText value="nome"/>
					<h:inputText required="true" label="nome" value="#{DisciplinaManagedBean.disciplina.nome}"/>
					<h:outputText value="quantidadeCreditos"/>
					<h:inputText label="quantidadeCreditos" value="#{DisciplinaManagedBean.disciplina.quantidadeCreditos}"/>
					<h:outputText value="nomeProfessor"/>
					<h:inputText label="nomeProfessor" value="#{DisciplinaManagedBean.disciplina.nomeProfessor}"/>
					<h:outputText value="curso"/>
					<h:selectOneMenu required="true" label="curso" value="#{DisciplinaManagedBean.disciplina.curso}">
					<f:selectItems value="#{CursoManagedBean.cursos}" />
						<f:converter converterId="CursoConverter"/>
					</h:selectOneMenu>
				</h:panelGrid>
				
				<br>
				<h:messages errorClass="error"></h:messages>
				<div style="text-align:center">
					<h:commandButton value="Save" action="#{DisciplinaManagedBean.newDisciplina}" styleClass="save"/>
					<h:commandButton value="Cancel" immediate="true" action="listDisciplina" styleClass="cancel"/>
				</div>
			</h:form>
		</body>
	</html>
</f:view>
