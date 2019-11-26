package br.com.warhjr.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import br.com.warhjr.model.Arquivo;
import br.com.warhjr.model.Usuario;

public class ArquivoDAO {

	private static ConectionDataBase conectionAux = new ConectionDataBase();
	private JFileChooser fileChooser;
	private File file;
	private InputStream stream;
	private JButton auxButton;
	private int len;
	private FileInputStream output;
	private static int cont;
	private Arquivo arquivo;
	public Timer t;

	public void insertFile(Arquivo arquivo, String filearq) throws Exception {
		File f = new File(filearq);

		try {
			PreparedStatement ps = ConectionDataBase.getConnection()
					.prepareStatement("insert into arquivo(id,cod_pessoa, nomearquivo,extencao,tamanho,"
							+ "data,dados) values(?,?,?,?,?,?,?)");
			InputStream is = new FileInputStream(f);
			int len = (int) f.length();
			byte[] bytes = new byte[(int) f.length()];
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
			ps.setInt(1, incrementa());
			ps.setInt(2, arquivo.getCod_pessoa());
			ps.setString(3, arquivo.getNomearquivo());
			ps.setString(4, arquivo.getExtencao());
			ps.setString(5, arquivo.getTamanho());
			// ps.setString(6, arquivo.getEndereco());
			ps.setString(6, arquivo.getData());
			ps.setBytes(7, bytes);
			ps.execute();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public void insertArquivo(Arquivo arquivo, String fileArquivo) throws SQLException, FileNotFoundException {

		File file = new File(fileArquivo);
		InputStream fis = new FileInputStream(file);

		len = (int) file.length();
		PreparedStatement statement = null;
		try {
			statement = conectionAux.getConnection().prepareStatement(
					"insert into arquivo(id,cod_pessoa, nomearquivo,extencao,tamanho,data,dados) values(?,?,?,?,?,?,?)");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		statement.setInt(1, incrementa());
		statement.setInt(2, arquivo.getCod_pessoa());

		statement.setString(3, arquivo.getNomearquivo());
		statement.setString(4, arquivo.getExtencao());
		statement.setString(5, (arquivo.getTamanho()));
		statement.setString(6, (arquivo.getData()));

		statement.setBinaryStream(7, fis, len);

		statement.executeUpdate();

	}

	public Integer incrementa() throws SQLException {
		PreparedStatement stmt = ConectionDataBase.getConnection().prepareStatement("select max(id) from arquivo");

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

	public void UpdateArquivo(Arquivo arquivo, String fileArquivo) throws Exception {
		File file = new File(fileArquivo);
		FileInputStream fis = null;

		fis = new FileInputStream(file);

		len = (int) file.length();

		String update = "update arquivo set cod_pessoa = ? ,nomearquivo = ? ,extencao = ? ,tamanho = ? , dados = ?, data = ?"
				+ " where id = ?";

		PreparedStatement stmt = null;

		stmt = ConectionDataBase.getConnection().prepareStatement(update);

		stmt.setInt(1, arquivo.getId_pessoa());
		stmt.setString(2, arquivo.getNomearquivo());
		stmt.setString(3, arquivo.getExtencao());
		stmt.setString(4, arquivo.getTamanho());
		stmt.setBinaryStream(5, fis, len);
		stmt.setString(6, arquivo.getData());
		stmt.setInt(7, arquivo.getId());

		stmt.executeUpdate();

	}

	public void updateSemFile(Arquivo arqui) throws Exception {

		String updates = "update arquivo set cod_pessoa = ? ,nomearquivo = ? ,extencao = ? ,tamanho = ? ,data= ?"
				+ " where id = ?";

		PreparedStatement stmts = null;

		stmts = conectionAux.getConnection().prepareStatement(updates);

		stmts.setInt(6, arqui.getId());
		stmts.setInt(1, arqui.getId_pessoa());
		stmts.setString(2, arqui.getNomearquivo());
		stmts.setString(3, arqui.getExtencao());
		stmts.setString(4, arqui.getTamanho());
		stmts.setString(5, arqui.getData());

		stmts.executeUpdate();
	}

	public void excluirArquivo(String cod) {
		String delete = "delete from Arquivo where id=" + cod;
		try {
			PreparedStatement stmtdelete = conectionAux.getConnection().prepareStatement(delete);

			stmtdelete.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public List<Usuario> findUsuarios(String nomeUsuario) throws SQLException {

		List<Usuario> usuarios = new ArrayList<Usuario>();
		String select = "select * from USUARIO where nome ilike '%" + nomeUsuario + "%' ORDER BY nome DESC";

		PreparedStatement stmt = conectionAux.getConnection().prepareStatement(select);

		ResultSet rs = stmt.executeQuery();
		try {
			while (rs.next()) {
				Usuario usuario = new Usuario();

				usuario.setId(rs.getInt("ID"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setTipo(rs.getString("tipo"));
				usuarios.add(usuario);

			}
		} catch (SQLException eq) {
			JOptionPane.showMessageDialog(null, "Problemas com Banco de Dados Messagem Nativa: !" + eq.getMessage());
		} finally {
			rs.close();
			stmt.close();
		}
		return usuarios;
	}

	public List<Arquivo> findArquivos(String nomepessoa, String nomearquivo) throws SQLException, ParseException {

		List<Arquivo> arquivos = new ArrayList<Arquivo>();
		SimpleDateFormat dnst = new SimpleDateFormat("dd/MM/yyyy");
		String select = "select AQ.id,AQ.COD_PESSOA,PE.nomePessoa,AQ.NOMEARQUIVO,AQ.EXTENCAO,AQ.TAMANHO,AQ.DATA from ARQUIVO AS AQ "
				+ "inner join PESSOA AS PE on (PE.IDPessoa = AQ.COD_PESSOA) " + "where PE.nomePessoa like '%"
				+ nomepessoa + "%' OR AQ.nomeArquivo like '%" + nomearquivo + "%'"
				+ " ORDER BY PE.nomePessoa, AQ.nomeArquivo DESC";

		PreparedStatement stmt = conectionAux.getConnection().prepareStatement(select);

		ResultSet rs = stmt.executeQuery();
		try {
			while (rs.next()) {
				Arquivo arquivo = new Arquivo();

				arquivo.setId(rs.getInt(1));
				arquivo.setId_pessoa(Integer.parseInt(rs.getString(2)));
				arquivo.setNomePessoa(rs.getString(3));
				arquivo.setNomearquivo(rs.getString(4));
				arquivo.setExtencao(rs.getString(5));
				arquivo.setTamanho(rs.getString(6));
				arquivo.setData((rs.getString(7)));
				arquivos.add(arquivo);

			}
		} catch (SQLException eq) {
			JOptionPane.showMessageDialog(null, "Problemas com Banco de Dados Messagem Nativa: !" + eq.getMessage());
		} finally {
			rs.close();
			stmt.close();
		}
		return arquivos;
	}

	public File SalvarArquivoExtr(String nome, String cod, JButton auxButton) throws SQLException, IOException {

		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setSelectedFile(new File(nome));
		File file = null;

		String select = "select dados,nomearquivo from arquivo where id = ?";
		PreparedStatement stmt = null;

		stmt = conectionAux.getConnection().prepareStatement(select);

		ResultSet rs = null;
		stmt.setInt(1, new Integer(cod));
		rs = stmt.executeQuery();
		if (rs.next()) {

			InputStream stream = rs.getBinaryStream("dados");
			String nomearquivo = rs.getString("nomearquivo");

			if (jFileChooser.showSaveDialog(auxButton) == JFileChooser.APPROVE_OPTION) {

				file = new File(jFileChooser.getSelectedFile().toString());
				try {

					int c;
					OutputStream f = new FileOutputStream(file);
				// int tamanho =  stream.available();
					while ((c = stream.read()) > -1) {

						f.write(c);
					}
					rs.close();
					f.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		return file;

	}
}
