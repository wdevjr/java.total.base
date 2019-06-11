<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
			<title>List of Aluno</title>
		</head>
		<body>
			<div class="line-separator"></div>
			<div style="text-align:center">
				<h1 class="title">List of Aluno</h1>
			</div>
			<div class="line-separator"></div>
			<br>
			<h:form>
				<h:dataTable value="#{AlunoManagedBean.listaAlunos}" var="item" styleClass="list_table" headerClass="header" rowClasses="row1,row2">
					<h:column>
						<f:facet name="header" >
							<h:outputText value="cpf"/>
						</f:facet>
						<h:outputText value="#{item.cpf}"/>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="nome"/>
						</f:facet>
						<h:outputText value="#{item.nome}"/>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="dataNascimento"/>
						</f:facet>
						<h:outputText value="#{item.dataNascimento}">
							<f:convertDateTime pattern="dd/MM/yyyy"/>
						</h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="telefone"/>
						</f:facet>
						<h:outputText value="#{item.telefone}"/>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="ra"/>
						</f:facet>
						<h:outputText value="#{item.ra}"/>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="email"/>
						</f:facet>
						<h:outputText value="#{item.email}"/>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="escolaridade"/>
						</f:facet>
						<h:outputText value="#{item.escolaridade}"/>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="curso"/>
						</f:facet>
						<h:outputText value="#{item.curso}">
							<f:converter converterId="CursoConverter"/>
						</h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="Editar"/>
						</f:facet>
						<h:commandLink value="editar" type="submit" action="editAluno" actionListener="#{AlunoManagedBean.findAluno}" >
							<f:attribute name="codAluno" value="#{item.cpf}"/>
						</h:commandLink>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="Remover"/>
						</f:facet>
						<h:commandLink value="remover" type="submit" actionListener="#{AlunoManagedBean.removeAluno}" >
							<f:attribute name="codAluno" value="#{item.cpf}"/>
						</h:commandLink>
					</h:column>
				</h:dataTable>
				<br>
				<div style="text-align:center">
					<h:commandButton value="Back to Index" immediate="true" action="index" styleClass="button"/>
					<h:commandButton value="New Aluno" immediate="true" action="newAluno" styleClass="button"/>
				</div>
			</h:form>
		</body>
	</html>
</f:view>
