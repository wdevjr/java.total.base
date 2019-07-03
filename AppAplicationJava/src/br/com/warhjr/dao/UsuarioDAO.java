package br.com.warhjr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.com.warhjr.model.Usuario;

public class UsuarioDAO {

	private ConectionDataBase connectionAux = new ConectionDataBase();

	static private String userUsuario;
	static private String userLogin;
	static private String userTipo;
	private JTable jTable;
	private int i = 0;

	public boolean atuentica(Object object, Object senha) throws SQLException {

		PreparedStatement pstmt;
		pstmt = connectionAux.getConnection().prepareStatement("select * from USUARIO where login =? and senha=?");

		pstmt.setString(1, limparTexto(object.toString()));
		pstmt.setString(2, limparTexto(senha.toString()));
		ResultSet res = pstmt.executeQuery();

		Usuario usuario = null;

		if (res.next()) {
			usuario = new Usuario();
			usuario.setId(res.getInt("IDUsuario"));
			usuario.setNome(res.getString("nomeUsuario"));
			usuario.setLogin(res.getString("login"));
			usuario.setSenha(res.getString("senha"));
			usuario.setTipo(res.getString("tipo"));
			userUsuario = usuario.getNome();
			userLogin = usuario.getLogin();
			userTipo = usuario.getTipo();
			// System.setProperty("nomeuser", userUsuario);
			// System.setProperty("userlogin", userLogin);
		}

		if ((usuario == null) || (!usuario.getSenha().equals(senha) || (!usuario.getLogin().equals(object)))) {
			return false;
		} else
			return true;

	}

	public boolean verificaUserCodigo(Integer num) throws SQLException {

		PreparedStatement stmt = connectionAux.getConnection()
				.prepareStatement("select count(*)as num from usuario where id=" + num);

		ResultSet res = stmt.executeQuery();
		boolean result = false;

		while (res.next()) {
			if (res.getInt(1) > 0) {
				result = true;

			} else
				result = false;
		}

		return result;

	}

	public Integer incrementa() throws SQLException {
		PreparedStatement stmt = connectionAux.getConnection()
				.prepareStatement("select max(idusuario)as num from usuario");

		ResultSet res = stmt.executeQuery();
		int maior = 0;

		if (res.next()) {
			if ((res.getInt(1) == 0)) {
				maior = 102030;
			} else {
				maior = res.getInt(1) + 1;
			}
			res.close();
		}

		return maior;
	}

	public final String limparTexto(String str) {
		str = str.trim();
		str = str.replace(" ", "");
		str = str.toLowerCase();
		str = str.replace("=", "");
		str = str.replace("'", "");
		str = str.replace(" or ", "");
		str = str.replace(" and ", "");
		str = str.replace("(", "");
		str = str.replace(")", "");
		str = str.replace("<", "[");
		str = str.replace(">", "]");
		str = str.replace("update", "");
		str = str.replace("-shutdown", "");
		str = str.replace("--", "");
		str = str.replace("'", "");
		str = str.replace("#", "");
		str = str.replace("$", "");
		str = str.replace("%", "");
		str = str.replace("¨", "");
		str = str.replace("&", "");
		str = str.replace("'1'='1'", "");
		str = str.replace("'1'-'1'", "");
		str = str.replace("'or'1'='1'", "");
		str = str.replace("--", "");
		str = str.replace("insert", "");
		str = str.replace("drop", "");
		str = str.replace("delet", "");
		str = str.replace("xp_", "");
		str = str.replace("select", "");
		str = str.replace("*", "");
		return str;
	}

	public String[] getloginCombo(String login, int tam, JComboBox combo) throws SQLException {
		String[] retorno = null;
		retorno = new String[tam];
		PreparedStatement stmt = null;
		try {
			stmt = connectionAux.getConnection().prepareStatement("select login from usuario");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		ResultSet res = stmt.executeQuery();
		combo.addItem("----------------");

		while (res.next()) {

			retorno[i] = res.getString(login);
			i++;
		}
		res.close();
		return retorno;
	}

	public ComboBoxModel carregaComboBox(JComboBox combo) {
		try {
			combo.addItem("Selecione Abaixo !");
			PreparedStatement stmt = connectionAux.getConnection().prepareStatement("select login from USUARIO");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				combo.addItem(rs.getString(1)); // ta carregando o combo aqui ... !
			}
			rs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu erro ao carregar a Combo Box", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		return null; // como é uma função mas que não precisa de return carregado pois o while ja faz
						// isso ...

	}

	public List<Usuario> getCombo() throws SQLException {

		List<Usuario> usuarios = new ArrayList<Usuario>();

		String select = "select login from usuario";

		PreparedStatement stmt = connectionAux.getConnection().prepareStatement(select);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Usuario usuario = new Usuario();

			usuario.setLogin(rs.getString(1));

			usuarios.add(usuario);

		}
		rs.close();
		stmt.close();
		return usuarios;
	}

	public List<Usuario> findUsuarios(String nomeUsuario) throws SQLException {

		List<Usuario> usuarios = new ArrayList<Usuario>();

		String select = "select * from USUARIO where nomeusuario LIKE '%" + nomeUsuario
				+ "%' ORDER BY nomeusuario DESC";
		;

		PreparedStatement stmt = connectionAux.getConnection().prepareStatement(select);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Usuario usuario = new Usuario();

			usuario.setId(rs.getInt("idusuario"));
			usuario.setNome(rs.getString("nomeusuario"));
			usuario.setLogin(rs.getString("login"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setData(rs.getString("data"));
			usuario.setTipo(rs.getString("tipo"));

			usuarios.add(usuario);

		}
		rs.close();
		stmt.close();
		return usuarios;
	}

	public void inserir(Usuario usuarios) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;

		try {

			conn = connectionAux.getConnection();

			String SQL = "insert into usuario(idusuario,nomeusuario,login,senha,tipo,data) values(?,?,?,?,?,?)";

			pst = conn.prepareStatement(SQL);

			pst.setInt(1, incrementa());
			pst.setString(2, usuarios.getNome());
			pst.setString(3, usuarios.getLogin());
			pst.setString(4, usuarios.getSenha());
			pst.setString(5, usuarios.getTipo());
			pst.setString(6, usuarios.getData());

			pst.executeUpdate();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			pst.close();
			conn.close();
		}

	}

	public void updateUsuarios(Usuario usuario) {
		PreparedStatement stmt = null;

		String sql = "update usuario set nomeusuario = ?,login = ?, senha = ?,tipo = ?,data = ? "
				+ "where idusuario = ?";
		try {
			stmt = connectionAux.getConnection().prepareStatement(sql);

			stmt.setInt(6, usuario.getId());
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getLogin());
			stmt.setString(3, usuario.getSenha());
			stmt.setString(4, usuario.getTipo());
			stmt.setString(5, usuario.getData());

			stmt.executeUpdate();

		} catch (SQLException ex) {

			JOptionPane.showMessageDialog(null, ex.getMessage().toString(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void remover(String id) {

		String delete = "delete from USUARIO where idusuario = ?";
		try {
			PreparedStatement pstmt = connectionAux.getConnection().prepareStatement(delete);

			pstmt.setInt(1, new Integer(id));

			pstmt.execute();

			pstmt.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir Usuário do banco de " + "dados " + e.getMessage());

		}
	}

	public static String getNomeUser() {
		return UsuarioDAO.userUsuario;
	}

	public static String getLoginUser() {
		return UsuarioDAO.userLogin;
	}

	public static String getTipo() {
		return UsuarioDAO.userTipo;
	}

}
