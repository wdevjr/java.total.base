//*************************************************************************************************
/*
 * Copyright AZ Informática LTDA.
 * Projeto CassemsFinanceiro
 * 
 * Criação.........: 01/11/2006
 * Desenvolvido por: Patrick Augusto Gonçalves
 * Última alteração: 
 * Alterado por....: 
 * Descrição.......: Implementa mecanismo de log utilizando a biblioteca log4j.
 */
//**************************************************************************************************

package br.com.warhjr.util;

import org.apache.log4j.Logger;

/**
 * Fornece acesso ao mecanismo de log da aplicação. Toda a impressão dos logs é
 * controlada pela biblioteca log4j, sendo que são disponibilizos 4 níveis de
 * log: DEBUG, INFO, WARN e ERROR.<br/>
 * A configuração do log é feita através de um arquivo com o nome
 * log4j.properties localizado no diretório raiz do classpath da aplicação.
 * Geralmente o diretório raiz pode ser referenciado a partir do diretório
 * principal de arquivos de código-fonte da aplicação.
 * <ul>
 * <li>Para impressão de logs a nível de DEBUG utilize a seguinte
 * instrução:<br/>
 * <code>UniversalLogger.log.debug( "Mensagem." );</code> <br/>
 * <br/>
 * </li>
 * <li>Logs a nível de informação são impressas com a seguinte instrução:<br/>
 * <code>UniversalLogger.log.info( "Mensagem." );</code> <br/>
 * <br/>
 * </li>
 * <li>Para fazer log de mensagens de warning:<br/>
 * <code>UniversalLogger.log.warn( "Mensagem." );</code> <br/>
 * <br/>
 * </li>
 * <li>E finalmente para registrar no log as exceções, utilize a seguinte
 * instrução:<br/>
 * <code>UniversalLogger.log.error( "Mensagem.", objetoThrowable );</code></li>
 * </ul>
 * 
 * @author Patrick Augusto Gonçalves
 */
public class UniversalLogger {
	public static Logger log = Logger.getLogger("UniversalLogger");
}
