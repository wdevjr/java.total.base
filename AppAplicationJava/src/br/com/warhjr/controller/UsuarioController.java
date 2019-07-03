package br.com.warhjr.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import br.com.warhjr.dao.ArquivoDAO;
import br.com.warhjr.dao.UsuarioDAO;
import br.com.warhjr.model.Usuario;

public class UsuarioController {

	private UsuarioDAO userDaoAux = new UsuarioDAO();

	public void SalvaUsuarios(Usuario usuarios) {
		try {
			userDaoAux.inserir(usuarios);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void UpdateUsuarios(Usuario usuarios) {

		try {
			userDaoAux.updateUsuarios(usuarios);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage().toString(), "Atenção Possivel Erro!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void deleteUser(String cod) {
		try {
			if (cod != null) {
				userDaoAux.remover(cod);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage().toString(), "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}

	public boolean LogarEx(Object object, Object senhaAll) throws Exception {

		UsuarioDAO obj = new UsuarioDAO();

		if ((obj.atuentica(object, senhaAll)) == false) {
			return false;

		} else
			return true;
	}

	public boolean verifCodigoUser(Integer valor) throws Exception {
		UsuarioDAO userBoolean = new UsuarioDAO();

		if (userBoolean.verificaUserCodigo(valor) == true) {
			return true;
		} else

			return false;
	}

	public String[] getloginCombo(String login, int comb, JComboBox combo) throws Exception {
		UsuarioDAO auxComboDAO = new UsuarioDAO();

		if (login == null) {
			throw new Exception("Login Vazio, introduza o Campo do Banco! ");
		}

		return auxComboDAO.getloginCombo(login, comb, combo);
	}

	@SuppressWarnings("rawtypes")
	public ComboBoxModel addComboBox(JComboBox combo) throws Exception {
		UsuarioDAO userDao = new UsuarioDAO();
		return userDao.carregaComboBox(combo);
	}

	public List<Usuario> addCombo() throws Exception {
		UsuarioDAO auxComb = new UsuarioDAO();
		return auxComb.getCombo();
	}

	public List<Usuario> buscaUsuarios(String nome) throws Exception {

		UsuarioDAO usedao = new UsuarioDAO();
		return usedao.findUsuarios(nome);
	}

}