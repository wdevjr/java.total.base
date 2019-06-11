
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
			<title>List of Disciplina</title>
			<link href="../Styles/yui-datatable.css" rel="stylesheet" type="text/css"> 
			<!-- <link href="../Yahoo-Ocean/stylesheet.css" rel="stylesheet" type="text/css"> -->
<style type="text/css">

table {
    border-collapse: collapse;
    
}
.row1 {
	background: #c0ccdb;
}

.row2 {
	background: #ffffff;
}
table, th,td{
    border: 1px solid black;
    
}

</style>
<meta charset="UTF-8">
<meta http-equiv="content-type" content="text/html; charset=UTF-8"
    />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=2">
<meta http-equiv="X-UA-Compatible" content="IE=edge, 9 chrome=1"/>
		</head>
		<body>
			<div class="line-separator"></div>
			<div style="text-align:center">
				<h1 class="title">List of Disciplina</h1>
			</div>
			<div class="line-separator"></div>
			<br>
			
			<h:form>
			
				<h:dataTable value="#{DisciplinaManagedBean.listaDisciplinas}" var="item" styleClass="yui-datatable-theme" headerClass="header" rowClasses="row1,row2" style="width: 1058px; height : 122px;">
					
					<h:column>
						<f:facet  name="header">
							<h:outputText style="width:5%" value="Código"/>
						</f:facet>
						<h:outputText value="#{item.id}"/>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="Nome"/>
						</f:facet>
						<h:outputText value="#{item.nome}"/>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="Quantidade Creditos"/>
						</f:facet>
						<h:outputText value="#{item.quantidadeCreditos}"/>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="Nome Professor"/>
						</f:facet>
						<h:outputText value="#{item.nomeProfessor}"/>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="Curso"/>
						</f:facet>
						<h:outputText value="#{item.curso}">
							<f:converter converterId="CursoConverterList"/>
						</h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="Editar"/>
						</f:facet>
						<div align="center">
						<h:commandLink type="submit" action="editDisciplina" actionListener="#{DisciplinaManagedBean.findDisciplina}" >
							<f:attribute name="codDisciplina" value="#{item.id}"/>
							<h:graphicImage value="/Yahoo-Ocean/edit.png" />
						</h:commandLink>
						</div>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="Remover"/>
						</f:facet>
						<div align="center">
						<h:commandLink type="submit" actionListener="#{DisciplinaManagedBean.removeDisciplina}" >
							<f:attribute name="codDisciplina" value="#{item.id}"/>
							<h:graphicImage value="/Yahoo-Ocean/delete.png"></h:graphicImage>
						</h:commandLink>
						</div>
					</h:column>
					
				</h:dataTable>
				<br>
				<div style="text-align:center">
					<h:commandButton value="Back to Index" immediate="true" action="index" styleClass="button"/>
					<h:commandButton value="New Disciplina" immediate="true" action="newDisciplina" styleClass="button"/>
				</div>
				
			</h:form>
	
		</body>
	</html>
</f:view>
