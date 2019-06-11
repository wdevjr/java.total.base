
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
	<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/redmond/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
<style type="text/css">
.ui-datepicker {
    height: 155px ;
    padding: 0.1em 0.1em 0;
    width: 160px;
    font-size:10px;
}
</style>
<script>
	
	var 
	win = null;
	function NovaJanela(pagina,nome,w,h,scroll){
	LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
	TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
	settings = 'height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable'
	win = window.open(pagina,nome,settings);
	}
</script>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="../js/jquery.ui.datepicker-pt-BR.js"></script>
  <script>
//<![CDATA[
$(function() {
	$("#data").datepicker({
		showOn: "button",
		buttonText: "Mostrar calendário",
		buttonImage: "../Img/calendar.gif",
		buttonImageOnly: true,
		autoSize: true,
		dateFormat: "dd/mm/yy",
		changeMonth: true,
		changeYear: true,
		yearRange: "-50:+50",
		}); 
});
// ]]>
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Edit Pessoa</title>
</head>
<body>
	<div class="line-separator"></div>
	<div style="text-align: center">
		<h1 class="title">Edit Pessoa</h1>
	</div>
	<div class="line-separator"></div>
	<br>
	<h:form id="f8" prependId="false">
		<table class="table">
			<tr>
				<td>cpf</td>
				<td><h:inputHidden value="#{PessoaManagedBean.pessoa.cpf}" /> 
				    <h:inputText disabled="true" value="#{PessoaManagedBean.pessoa.cpf}" /></td>
			</tr>
			<tr>
				<td>nome</td>
				<td><h:inputText required="true" label="nome"
						value="#{PessoaManagedBean.pessoa.nome}" /></td>
			</tr>
			<tr>
				<td>dataNascimento</td>
				<td><h:inputText required="true" id="data" label="dataNascimento"
						value="#{PessoaManagedBean.pessoa.dataNascimento}">
						<f:convertDateTime pattern="dd/MM/yyyy" />
					</h:inputText></td>
			</tr>
			<tr>
				<td>telefone</td>
				<td><h:inputText label="telefone"
						value="#{PessoaManagedBean.pessoa.telefone}" /></td>
			</tr>
		</table>
		    <h:panelGrid columns="6" style=" width : 400px;">
			<h:outputText value="Código Cidade" />
				<h:inputText id="idCidade"
					value="#{PessoaManagedBean.pessoa.cidade.id}"
					style=" width : 95px;">

				</h:inputText>

			<h:outputLink value="#" id="link"
				onclick="NovaJanela('../Pessoa/jan3.jsf','Consultas','800','500','yes');return false">
				<h:graphicImage value="/images/loupe_petit.bmp" />
			</h:outputLink>
			<h:inputText id="nomeCidade"
				value="#{PessoaManagedBean.pessoa.cidade.nomeCidade}"
				style=" width : 267px;">

			</h:inputText>
			<h:inputText id="uf" value="#{PessoaManagedBean.pessoa.cidade.uf}"
				style=" width : 28px;">

			</h:inputText>
		</h:panelGrid>
		<h:messages errorClass="error"></h:messages>
		<div style="text-align: center">
			<h:commandButton value="Save"
				action="#{PessoaManagedBean.editPessoa}" styleClass="save" />
			<h:commandButton value="Cancel" immediate="true" action="listPessoa"
				styleClass="cancel" />
		</div>
	</h:form>
</body>
	</html>
</f:view>
