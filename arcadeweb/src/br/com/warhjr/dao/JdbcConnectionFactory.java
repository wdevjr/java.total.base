
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
					"O arquivo de configura��o database-connection.properties n�o foi encontrado. Ele � necess�rio para a cria��o manual de conex�es JDBC.");
		} else {
			driver = bundle.getString("connection.driver_class");
			url = bundle.getString("connection.url");
			username = bundle.getString("connection.username");
			password = bundle.getString("connection.password");

			if (driver == null || url == null || username == null || password == null)
				UniversalLogger.log
						.error("O arquivo database-connection.properties n�o est� configurado corretamente.");
			else {
				try {
					Class.forName(driver);
					UniversalLogger.log.info("Configura��es manuais de conex�o JDBC carregadas com sucesso.");
				} catch (Exception e) {
					UniversalLogger.log.error("N�o foi poss�vel configurar a cria��o manual de conex�es JDBC.", e);
				}
			}
		}
	}

	/**
	 * Retorna uma nova conex�o JDBC obtida do banco de dados.
	 * 
	 * @return Conex�o JDBC.
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection(url, username, password);
	}
}
