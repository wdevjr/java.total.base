
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


<html>

<head>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="../js/jquery.ui.datepicker-pt-BR.js"></script>

<script type="text/javascript" src="../js/jquery.maskedinput.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>List of Pessoa</title>
<link type="text/css" href="../Styles/tablecloth.css" rel="stylesheet" />
<link href="../Styles/yui-datatable.css" rel="stylesheet"
	type="text/css" />
<link href="../Yahoo-Ocean/stylesheet.css" rel="stylesheet"
	type="text/css" />

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/redmond/jquery-ui.css" />
<link rel="stylesheet" href="/resources/demos/style.css" />

<script>
	var win = null;
	function NovaJanela(pagina, nome, w, h, scroll) {
		LeftPosition = (screen.width) ? (screen.width - w) / 2 : 0;
		TopPosition = (screen.height) ? (screen.height - h) / 2.8 : 0;
		settings = 'height=' + h + ',width=' + w + ',top=' + TopPosition
				+ ',left=' + LeftPosition + ',scrollbars=' + scroll
				+ ',resizable'
		win = window.open(pagina, nome, settings);
		document.f1.enviar.value = "";
		win.focus();
	}
</script>


<script>
	$(function() {
		$("#data").datepicker({
			showOn : "button",
			buttonText : "Mostrar calendário",
			buttonImage : "../Img/calendar.gif",
			buttonImageOnly : true,
			autoSize : true,
			dateFormat : "dd/mm/yy",
			changeMonth : true,
			changeYear : true,
			yearRange : "-50:+50",
		});
	});

	jQuery(function($) {
		$("#campoData").mask("99/99/9999");
		$("#campoSenha").mask("***-****");
        $("#campoTelefone").mask("(999) 99999-9999");
	});
</script>

<style type="text/css">
.row1 {
	background: #c0ccdb;
}

.row2 {
	background: #ffffff;
}

.ui-datepicker {
	height: 155px;
	padding: 0.1em 0.1em 0;
	width: 160px;
	font-size: 10px;
}
</style>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
			<title>New Pessoa</title>
			<link href="../Styles/buttonsImputs.css" rel="stylesheet" type="text/css">
		</head>
		<body>
		<f:view>
			<div class="line-separator"></div>
			<div style="text-align:center">
			<h1 class="title">New Pessoa</h1>
			</div>
			<div class="line-separator"></div>
			<br>
					<h:form id="f8" prependId="false">
			<table class="table">
				<tr>
					<td>cpf</td>
					<td><h:inputHidden value="#{PessoaManagedBean.pessoa.cpf}" />
						<h:inputText disabled="false"
							value="#{PessoaManagedBean.pessoa.cpf}" style=" width : 185px;" />
					</td>
				</tr>
				<tr>
					<td>nome</td>
					<td><h:inputText required="true" label="nome"
							value="#{PessoaManagedBean.pessoa.nome}" style=" width : 313px;" />
					</td>
				</tr>
				<tr>
					<td>dataNascimento</td>
					<td><h:inputText required="true" id="data"
							label="dataNascimento"
							value="#{PessoaManagedBean.pessoa.dataNascimento}">
							<f:convertDateTime pattern="dd/MM/yyyy" />
						</h:inputText></td>
				</tr>
				<tr>
					<td>telefone</td>
					<td><h:inputText id="campoTelefone" required="true"
							value="#{PessoaManagedBean.pessoa.telefone}" /></td>
				</tr>
			</table>
			<h:panelGrid columns="6" style=" width : 400px;">
				<h:outputText value="Código Cidade" />
				
				<%-- <h:inputHidden value="#{PessoaManagedBean.cidade.id}"/> --%><h:inputText id="idCidade"
					value="#{PessoaManagedBean.pessoa.cidade.id}"
					style=" width : 95px;">

				</h:inputText>

				<h:outputLink value="#" id="link"
					onclick="NovaJanela('../Pessoa/jan3.jsf','Consultas','800','450','yes');return false">
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

			<div style="text-align: center">
				<h:commandButton value="Save"
					action="#{PessoaManagedBean.newPessoa}"
					/>
				<h:commandButton value="Cancel" immediate="true" action="listPessoa" />
			</div>
			</h:form>
		</body>
		</f:view>
	</html>


