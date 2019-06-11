<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
			<title>New Aluno</title>
		</head>
		<body>
			<div class="line-separator"></div>
			<div style="text-align:center">
			<h1 class="title">New Aluno</h1>
			</div>
			<div class="line-separator"></div>
			<br>
			<h:form>
				<h:panelGrid columns="2" styleClass="table">
					<h:outputText value="cpf"/>
					<h:inputText required="true" label="cpf" value="#{AlunoManagedBean.aluno.cpf}"/>
					<h:outputText value="nome"/>
					<h:inputText required="true" label="nome" value="#{AlunoManagedBean.aluno.nome}"/>
					<h:outputText value="dataNascimento"/>
					<h:inputText required="true" label="dataNascimento" value="#{AlunoManagedBean.aluno.dataNascimento}">
						<f:convertDateTime pattern="dd/MM/yyyy"/>
					</h:inputText>
					<h:outputText value="telefone"/>
					<h:inputText label="telefone" value="#{AlunoManagedBean.aluno.telefone}"/>
					<h:outputText value="ra"/>
					<h:inputText required="true" label="ra" value="#{AlunoManagedBean.aluno.ra}"/>
					<h:outputText value="email"/>
					<h:inputText required="true" label="email" value="#{AlunoManagedBean.aluno.email}"/>
					<h:outputText value="escolaridade"/>
					<h:inputText label="escolaridade" value="#{AlunoManagedBean.aluno.escolaridade}"/>
					<h:outputText value="curso"/>
					
					<h:selectOneMenu required="true" label="curso" value="#{AlunoManagedBean.aluno.curso}">
					<f:selectItems value="#{CursoManagedBean.cursos}" />
						<f:converter converterId="CursoConverter"/>
					</h:selectOneMenu>
				</h:panelGrid>
				<br>
				<h:messages errorClass="error"></h:messages>
				<div style="text-align:center">
					<h:commandButton value="Save" action="#{AlunoManagedBean.newAluno}" styleClass="save"/>
					<h:commandButton value="Cancel" immediate="true" action="listAluno" styleClass="cancel"/>
				</div>
			</h:form>
		</body>
	</html>
</f:view>
