
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
			<link type="text/css" href="../Styles/tablecloth.css" rel="stylesheet" />
			<link href="../Styles/yui-datatable.css" rel="stylesheet" type="text/css"> 
			<link href="../Yahoo-Ocean/stylesheet.css" rel="stylesheet" type="text/css">
			 <!-- <link href="../Styles/buttonsImputs.css" rel="stylesheet" type="text/css"> --> 
<script>
function envia() {
	opener.document.f1.idCidade.value = document.f4.CodCidadeList.value;
	//opener.document.f1.nomeCidade.value = document.f2.nomeCidad.value;
	//opener.document.f1.uf.value = document.f2.nomeUFd.value;
	//close();
	}
	




</script>	
<style>
.row1 {
   background: #F2F5F9;
}
.row2 {
   background: #ffffff;
}
</style>	
</head>

<body>
<f:view>
	
	<h:form id="f4" prependId="false">
		
		<h:messages/>
	
		 <fieldset>
	        <legend>Pesquisa de Cidades</legend>
			<h:panelGrid columns="3" style=" width : 722px;">
			
			    <h:outputLabel id="nomeLabel" value="Digite o nome da Cidade: " />
			                                                  
				<h:inputText id="nomeInput" value="#{CidadeManagedBean.desc}" style=" width : 365px;"/>
 
				<h:commandButton value="Pesquisar" action="#{CidadeManagedBean.pesquisaCidades}" style="width: 95px; height: 29px; "/>
				
    		</h:panelGrid>
			</fieldset>
			<br />
			<h:dataTable value="#{CidadeManagedBean.lista}" var="item" styleClass="tablecloth-theme" headerClass="header" rowClasses="row1,row2" width="55%"  style=" width : 736px;">	
				<h:column>
					<f:facet name="header">
						<h:outputText value="Código Cidade" />
					</f:facet>
					<h:outputText id="CodCidadeList" value="#{item.id}" />
				</h:column>
	
				<h:column>
					<f:facet name="header">
						<h:outputText value="Nome Cidade" />
					</f:facet>
					<h:outputText value="#{item.nomeCidade}" />
				</h:column>
	
				<h:column>
					<f:facet name="header">
						<h:outputText value="UF" />
					</f:facet>
					<h:outputText value="#{item.uf}" />
				</h:column>
			
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="Selecionar"/>
					</f:facet>
	
			
						<h:commandLink value="Selecionar" type="submit" onclick="envia()" action="apendCidade" actionListener="#{CidadeManagedBean.findCidade}" >
							<f:attribute name="codCidade" value="#{item.id}"/>
						</h:commandLink>
                     </h:column>  				
	 			

			</h:dataTable>
			
	    </h:form>
	    <h:panelGrid id="mostra" columns="2" rendered="#{CidadeManagedBean.mostratudo}" style=" width : 722px;">
	    <h:form  id="f2" prependId="false">
	    <br>
        <fieldset> <legend>Dados de Cidades</legend>
	    	<h:panelGrid columns="2" id="panelGridInputs" style=" width : 385px;">
				<h:outputLabel  value="Código da Cidade: "/>
				<h:inputText id="CodCidade" value="#{CidadeManagedBean.cidade.id}" style=" width : 254px;"/>
				<h:outputLabel  value="Nome da Cidade: "  />
				<h:inputText id="nomeCidad" value="#{CidadeManagedBean.cidade.nomeCidade}" style=" width : 254px;"/>
				<h:outputLabel  value="UF : "  />
				<h:inputText id="nomeUFd" value="#{CidadeManagedBean.cidade.uf}" style=" width : 254px;"/>
			</h:panelGrid>
			
			<br>
			<INPUT TYPE=button VALUE="enviar Formulário ..." onclick="envia()" style="width: 127px; height: 30px; ">
						<div align="right">
			<input type="button" value="Fechar" onclick="javascript:window.close()">
			</div>
			</fieldset>


	</h:form>
	</h:panelGrid>
</f:view>
</body>
</html>
