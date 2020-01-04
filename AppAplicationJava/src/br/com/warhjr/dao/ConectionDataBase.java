package br.com.warhjr.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class ConectionDataBase {
	private static final String URL_SQLSERVER = "jdbc:jtds:sqlserver://warhjr:1433/agendaUser;integratedSecurity=true";
	private static final String DRIVER_CLASS = "net.sourceforge.jtds.jdbc.Driver";
	private static final String USER = "sa";
	private static final String PASSWORD = "root";
	
//	private static final String URL_POSTGRESQL ="jdbc:postgresql://127.0.0.1:5432/agendaUser";
//	private static final String DRIVER_CLASS = "org.postgresql.Driver";
//	private static final String USER = "postgres";
//	private static final String PASSWORD = "root";

	public static Connection getConnection() {
		Connection con = null;
		System.out.println("Conectando ao Banco de Dados");
		try {
			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL_SQLSERVER, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Banco de Dados não Conectado ... !");
			throw new RuntimeException(e);
		}

		return con;
	}

//	public static Connection getConnection() {
//		System.out.println("Conectando ao Banco de Dados");
//		Properties confBanco = new Properties();
//		try {
//
//		confBanco.load(new FileInputStream("Banco.ini"));
//
//		Class.forName(confBanco.getProperty("driver"));
//		String url=confBanco.getProperty("url");
//		//+(!confBanco.getProperty("porta").equals("")?
//		//":" + confBanco.getProperty
//		//("porta") : 
//		//confBanco.getProperty("porta");//)
//		//+
//		//confBanco.getProperty("banco");
//		Connection con = DriverManager.getConnection(url,confBanco.getProperty
//		("usuario"),confBanco.getProperty("senha"));
//		return con;
//		} catch (Exception ex){
//		System.err.println(ex);
//		JOptionPane.showMessageDialog(null, ex.getMessage());
//		}
//		return null;
//	}
}
