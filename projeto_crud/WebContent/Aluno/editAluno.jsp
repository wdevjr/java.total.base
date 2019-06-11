<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


<f:view>
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
			<title>Edit Aluno</title>
		</head>
		<body>
			<div class="line-separator"></div>
			<div style="text-align:center">
				<h1 class="title">Edit Aluno</h1>
			</div>
			<div class="line-separator"></div>
			<br>
			<h:form>
				<table class="table">
					<tr>
						<td>cpf</td>
						<td>
							<h:inputHidden value="#{AlunoManagedBean.aluno.cpf}"/>
							<h:inputText disabled="true" value="#{AlunoManagedBean.aluno.cpf}"/>
						</td>
					</tr>
					<tr>
						<td>nome</td>
						<td>
							<h:inputText required="true" label="nome" value="#{AlunoManagedBean.aluno.nome}"/>
						</td>
					</tr>
					<tr>
						<td>dataNascimento</td>
						<td>
							<h:inputText required="true" label="dataNascimento" value="#{AlunoManagedBean.aluno.dataNascimento}">
								<f:convertDateTime pattern="dd/MM/yyyy"/>
							</h:inputText>
						</td>
					</tr>
					<tr>
						<td>telefone</td>
						<td>
							<h:inputText label="telefone" value="#{AlunoManagedBean.aluno.telefone}"/>
						</td>
					</tr>
					<tr>
						<td>ra</td>
						<td>
							<h:inputText required="true" label="ra" value="#{AlunoManagedBean.aluno.ra}"/>
						</td>
					</tr>
					<tr>
						<td>email</td>
						<td>
							<h:inputText required="true" label="email" value="#{AlunoManagedBean.aluno.email}"/>
						</td>
					</tr>
					<tr>
						<td>escolaridade</td>
						<td>
							<h:inputText label="escolaridade" value="#{AlunoManagedBean.aluno.escolaridade}"/>
						</td>
					</tr>
					<tr>
						<td>curso</td>
						<td>
							<h:selectOneMenu required="true" label="curso" value="#{AlunoManagedBean.aluno.curso}">
								<f:selectItems value="#{CursoManagedBean.cursos}" />
								<f:converter converterId="CursoConverter"/>
							</h:selectOneMenu>
						</td>
					</tr>
				</table>
				<br>
				<h:messages errorClass="error"></h:messages>
				<div style="text-align:center">
					<h:commandButton value="Save" action="#{AlunoManagedBean.editAluno}" styleClass="save"/>
					<h:commandButton value="Cancel" immediate="true" action="listAluno" styleClass="cancel"/>
				</div>
			</h:form>
		</body>
	</html>
</f:view>
