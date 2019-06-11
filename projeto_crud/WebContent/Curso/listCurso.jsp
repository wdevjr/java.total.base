<?xml version='1.0' encoding='ISO-8859-1' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
			<title>List of Curso</title>
		</head>
		<body>
			<div class="line-separator"></div>
			<div style="text-align:center">
				<h1 class="title">List of Curso</h1>
			</div>
			<div class="line-separator"></div>
			<br>
			<h:form>
				<h:dataTable value="#{CursoManagedBean.listaCursos}" var="item" styleClass="list_table" headerClass="header" rowClasses="row1,row2">
					<h:column>
						<f:facet name="header" >
							<h:outputText value="id"/>
						</f:facet>
						<h:outputText value="#{item.id}"/>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="nome"/>
						</f:facet>
						<h:outputText value="#{item.nome}"/>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="nomeDepartamento"/>
						</f:facet>
						<h:outputText value="#{item.nomeDepartamento}"/>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="cargaHoraria"/>
						</f:facet>
						<h:outputText value="#{item.cargaHoraria}"/>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="Editar"/>
						</f:facet>
						<h:commandLink value="editar" type="submit" action="editCurso" actionListener="#{CursoManagedBean.findCurso}" >
							<f:attribute name="codCurso" value="#{item.id}"/>
						</h:commandLink>
					</h:column>
					<h:column>
						<f:facet name="header" >
							<h:outputText value="Remover"/>
						</f:facet>
						<h:commandLink value="remover" type="submit" actionListener="#{CursoManagedBean.removeCurso}" >
							<f:attribute name="codCurso" value="#{item.id}"/>
						</h:commandLink>
					</h:column>
				</h:dataTable>
				<br>
				<div style="text-align:center">
					<h:commandButton value="Back to Index" immediate="true" action="index" styleClass="button"/>
					<h:commandButton value="New Curso" immediate="true" action="newCurso" styleClass="button"/>
				</div>
			</h:form>
		</body>
	</html>
</f:view>

