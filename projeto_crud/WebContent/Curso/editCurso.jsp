<?xml version='1.0' encoding='ISO-8859-1' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
			<title>Edit Curso</title>
		</head>
		<body>
			<div class="line-separator"></div>
			<div style="text-align:center">
				<h1 class="title">Edit Curso</h1>
			</div>
			<div class="line-separator"></div>
			<br>
			<h:form>
				<table class="table">
					<h:inputHidden value="#{CursoManagedBean.curso.id}"/>
					<tr>
						<td>nome</td>
						<td>
							<h:inputText required="true" label="nome" value="#{CursoManagedBean.curso.nome}"/>
						</td>
					</tr>
					<tr>
						<td>nomeDepartamento</td>
						<td>
							<h:inputText required="true" label="nomeDepartamento" value="#{CursoManagedBean.curso.nomeDepartamento}"/>
						</td>
					</tr>
					<tr>
						<td>cargaHoraria</td>
						<td>
							<h:inputText label="cargaHoraria" value="#{CursoManagedBean.curso.cargaHoraria}"/>
						</td>
					</tr>
				</table>
				<br>
				<h:messages errorClass="error"></h:messages>
				<div style="text-align:center">
					<h:commandButton value="Save" action="#{CursoManagedBean.editCurso}" styleClass="save"/>
					<h:commandButton value="Cancel" immediate="true" action="listCurso" styleClass="cancel"/>
				</div>
			</h:form>
		</body>
	</html>
</f:view>
