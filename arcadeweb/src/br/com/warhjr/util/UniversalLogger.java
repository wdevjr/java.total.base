//*************************************************************************************************
/*
 * Copyright AZ Inform�tica LTDA.
 * Projeto CassemsFinanceiro
 * 
 * Cria��o.........: 01/11/2006
 * Desenvolvido por: Patrick Augusto Gon�alves
 * �ltima altera��o: 
 * Alterado por....: 
 * Descri��o.......: Implementa mecanismo de log utilizando a biblioteca log4j.
 */
//**************************************************************************************************

package br.com.warhjr.util;

import org.apache.log4j.Logger;

/**
 * Fornece acesso ao mecanismo de log da aplica��o. Toda a impress�o dos logs �
 * controlada pela biblioteca log4j, sendo que s�o disponibilizos 4 n�veis de
 * log: DEBUG, INFO, WARN e ERROR.<br/>
 * A configura��o do log � feita atrav�s de um arquivo com o nome
 * log4j.properties localizado no diret�rio raiz do classpath da aplica��o.
 * Geralmente o diret�rio raiz pode ser referenciado a partir do diret�rio
 * principal de arquivos de c�digo-fonte da aplica��o.
 * <ul>
 * <li>Para impress�o de logs a n�vel de DEBUG utilize a seguinte
 * instru��o:<br/>
 * <code>UniversalLogger.log.debug( "Mensagem." );</code> <br/>
 * <br/>
 * </li>
 * <li>Logs a n�vel de informa��o s�o impressas com a seguinte instru��o:<br/>
 * <code>UniversalLogger.log.info( "Mensagem." );</code> <br/>
 * <br/>
 * </li>
 * <li>Para fazer log de mensagens de warning:<br/>
 * <code>UniversalLogger.log.warn( "Mensagem." );</code> <br/>
 * <br/>
 * </li>
 * <li>E finalmente para registrar no log as exce��es, utilize a seguinte
 * instru��o:<br/>
 * <code>UniversalLogger.log.error( "Mensagem.", objetoThrowable );</code></li>
 * </ul>
 * 
 * @author Patrick Augusto Gon�alves
 */
public class UniversalLogger {
	public static Logger log = Logger.getLogger("UniversalLogger");
}
