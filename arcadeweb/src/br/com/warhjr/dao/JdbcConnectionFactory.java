
package br.com.warhjr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import br.com.warhjr.util.UniversalLogger;

public class JdbcConnectionFactory {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	static {
		ResourceBundle bundle = ResourceBundle.getBundle("database-connection");

		if (bundle == null) {
			UniversalLogger.log.error(
					"O arquivo de configuração database-connection.properties não foi encontrado. Ele é necessário para a criação manual de conexões JDBC.");
		} else {
			driver = bundle.getString("connection.driver_class");
			url = bundle.getString("connection.url");
			username = bundle.getString("connection.username");
			password = bundle.getString("connection.password");

			if (driver == null || url == null || username == null || password == null)
				UniversalLogger.log
						.error("O arquivo database-connection.properties não está configurado corretamente.");
			else {
				try {
					Class.forName(driver);
					UniversalLogger.log.info("Configurações manuais de conexão JDBC carregadas com sucesso.");
				} catch (Exception e) {
					UniversalLogger.log.error("Não foi possível configurar a criação manual de conexões JDBC.", e);
				}
			}
		}
	}

	/**
	 * Retorna uma nova conexão JDBC obtida do banco de dados.
	 * 
	 * @return Conexão JDBC.
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection(url, username, password);
	}
}
