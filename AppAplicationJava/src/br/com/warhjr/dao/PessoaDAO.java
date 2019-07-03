package br.com.warhjr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.warhjr.model.Pessoa;

public class PessoaDAO {

	private ConectionDataBase auxConection = new ConectionDataBase();

	public List<Pessoa> findPessoas(String nomePessoa) throws SQLException {

		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		String select = "select UP.idPessoa, UP.COD_ENDERECO, UP.nomepessoa, UP.idade, UP.sexo, ED.endereco, ED.bairro, CI.nomecidade, CI.uf "
				+ "from Pessoa as UP " + " inner join ENDERECO as ED on (ED.IDEndereco = UP.COD_ENDERECO)"
				+ " inner join CIDADE as CI on (CI.IDCidade = ED.COD_CIDADE)" + " where UP.nomePessoa like '%"
				+ nomePessoa + "%'" + " order by UP.nomePessoa desc";

		PreparedStatement stmt = auxConection.getConnection().prepareStatement(select);

		ResultSet rs = stmt.executeQuery();

		try {
			while (rs.next()) {

				Pessoa pessoa = new Pessoa();

				pessoa.setId_pessoa(rs.getInt("idPessoa"));
				pessoa.setCod_endereco(rs.getInt("cod_endereco"));
				pessoa.setNomePessoa(rs.getString("nomePessoa"));
				pessoa.setIdade(rs.getInt("idade"));
				pessoa.setSexo(rs.getString("sexo"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoa.setBairro(rs.getString("bairro"));
				pessoa.setNomecidade(rs.getString("nomecidade"));
				pessoa.setUf(rs.getString("uf"));
				pessoas.add(pessoa);

			}
		} catch (SQLException eq) {
			JOptionPane.showMessageDialog(null,
					"Erro no procedimento do Banco de Dados, Menssagem Nativa: " + eq.getMessage());
		} finally {
			rs.close();
			stmt.close();
		}
		return pessoas;
	}

}
