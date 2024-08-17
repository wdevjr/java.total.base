package br.com.warhjr.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import br.com.warhjr.model.Cidade;

public class CidadeDAO {

	private ConectionDataBase connectionAux = new ConectionDataBase();

	public List<Cidade> findCidades(String nomeCidade) throws SQLException {

		List<Cidade> cidades = new ArrayList<Cidade>();
		// String select = "select * from CIDADE where nomecidade like ? order by
		// nomecidade desc";

//		Statement stmt = getConnection().prepareStatement();
//		ResultSet rs;

		String select = "select * from CIDADE where nomecidade like '%" + nomeCidade + "%' order by nomecidade desc";

		@SuppressWarnings("static-access")
		PreparedStatement stmt = connectionAux.getConnection().prepareStatement(select);

		// stmt.setString(1, "'%"+nomeCidade+"%'");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Cidade cidade = new Cidade();

			cidade.setId_cidade(rs.getInt("idcidade"));
			cidade.setNomecidade(rs.getString("nomecidade"));
			cidade.setUf(rs.getString("uf"));

			cidades.add(cidade);

		}
		rs.close();
		stmt.close();
		return cidades;
	}

}
