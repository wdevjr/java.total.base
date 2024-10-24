package br.com.warhjr.controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import br.com.warhjr.dao.UsuarioDAO;
import br.com.warhjr.model.Usuario;

public class UsuarioController {

	private UsuarioDAO userDaoAux = new UsuarioDAO();

	public void SalvaUsuarios(Usuario usuarios) {
		
			if (usuarios.getNome().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Informe o Nome do Usuario","Nome!",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (usuarios.getLogin().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Informe o Login do Usuario","Login!",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (usuarios.getSenha().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Informe a Senha do Usuario","Senha!",JOptionPane.WARNING_MESSAGE);
				return;
			}
			try {
			userDaoAux.inserir(usuarios);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void UpdateUsuarios(Usuario usuarios) {
		
		if (usuarios.getNome().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Informe o Nome do Usuario","Nome!",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if (usuarios.getLogin().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Informe o Login do Usuario","Login!",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if (usuarios.getSenha().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Informe a Senha do Usuario","Senha!",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		try {
			userDaoAux.updateUsuarios(usuarios);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage().toString(), "Aten��o Possivel Erro!",
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


	@SuppressWarnings("rawtypes")
	public ComboBoxModel addComboBox(JComboBox combo) throws Exception {
		UsuarioDAO userDao = new UsuarioDAO();
		return userDao.carregaComboBox(combo);
	}

	public List<Usuario> ListaddCombo() throws Exception {
			UsuarioDAO auxComb = new UsuarioDAO();
			return auxComb.getCombo();
	}

	public List<Usuario> buscaUsuarios(String nome) throws Exception {

		UsuarioDAO usedao = new UsuarioDAO();
		return usedao.findUsuarios(nome);

    }

	public List<Usuario> buscaUserId(Long idUser) throws Exception {

		UsuarioDAO usedao = new UsuarioDAO();
		return usedao.consultaCostumizadaCodigoUsuario(idUser);
	}

}