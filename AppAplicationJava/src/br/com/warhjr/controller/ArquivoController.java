package br.com.warhjr.controller;

import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import org.codehaus.groovy.control.messages.ExceptionMessage;

//import com.toedter.calendar.JDateChooser;

import br.com.warhjr.dao.ArquivoDAO;
import br.com.warhjr.model.Arquivo;

public class ArquivoController {

	SimpleDateFormat ds = new SimpleDateFormat("dd/MM/YYYY");

	public static String getExtensao(String nomeArquivo) {
		int i = nomeArquivo.lastIndexOf('.');

		if (i > 0 && i < (nomeArquivo.length() - 1))
			return nomeArquivo.substring(i + 1).toLowerCase();
		return null;

	}

	public void SalvarArq(Arquivo arquivo, String file) throws Exception{
		
		ArquivoDAO auxDao = new ArquivoDAO();
		
		if (arquivo.getNomearquivo().trim().length() == 0) {
			throw new Exception("Nome do Arquivo � importante! ");
			// JOptionPane.showMessageDialog(null, "arquivo � importante!");
		} else {

			if (arquivo.getArquivo().pessoa.getIdPessoa() == 0) {
				throw new Exception("Pessoa � importante!");
				// JOptionPane.showMessageDialog(null, "Pessoa � importante!");
			} else {

				if (arquivo.getArquivo().pessoa.getNomePessoa() == "") {
					throw new Exception("Nome Pessoa � importante!");
					// JOptionPane.showMessageDialog(null, "Pessoa � importante!");
				} else {
					if (arquivo != null) {
						auxDao.insertArquivo(arquivo, file);
						throw new Exception("Arquivo Inserido com Sucesso! ");
					}
				}
			}
		}
	}
			//}
		//}

	public void EditaArq(Arquivo arquivo, String file) throws Exception {
		ArquivoDAO auxDao = new ArquivoDAO();

		if (arquivo.getNomearquivo() == "") {
			throw new Exception("Erro nome do arquivo � necessario");

		} else
			auxDao.UpdateArquivo(arquivo, file);
		throw new Exception("Arquivo Editado com Sucesso! ");
	}

	public void EditaArquivoSemFile(Arquivo arquivo) throws Exception {
		ArquivoDAO auxDao = new ArquivoDAO();

		if (arquivo.getNomearquivo() == "") {
			throw new Exception("O nome do arquivo � necessario, ok?");
		} else {

			auxDao.updateSemFile(arquivo);
			throw new Exception("Arquivo Editado com Sucesso! ");
		}
	}

	public void ExtrairArquivoEx(String nomeArquivo, String cod, JButton bot) throws Exception {
		ArquivoDAO auxExtr = new ArquivoDAO();
		if (cod != null) {
			auxExtr.SalvarArquivoExtr(nomeArquivo, cod, bot);
			//JOptionPane.showMessageDialog(null, "Sucesso!, Salvamento pronto!");
		}
	}

	public List<Arquivo> BuscaArquivos(String nomeArquivo, String pessoa) throws Exception {
		ArquivoDAO auxArquivoFind = new ArquivoDAO();
		if ((nomeArquivo != null) || (pessoa != null)) {
			return auxArquivoFind.findArquivos(pessoa, nomeArquivo);
		}

		return null;

	}

	public void deletarArquivo(String cod) {
		try {
			if (cod != null) {
				ArquivoDAO auxArquivoDAO = new ArquivoDAO();
				auxArquivoDAO.excluirArquivo(cod);
			}
		} catch (Exception ep1) {
			ep1.printStackTrace();
		}
	}





}
