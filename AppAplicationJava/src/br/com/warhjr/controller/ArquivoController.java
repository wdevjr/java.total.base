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

	public void SalvarArq(Arquivo arquivo, String file) throws Exception {
		ArquivoDAO auxDao = new ArquivoDAO();
		if (arquivo.getNomearquivo() == "") {
			throw new Exception("Nome do Arquivo é importante! ");

		} else
			auxDao.insertArquivo(arquivo, file);
		throw new Exception("Arquivo Inserido com Sucesso! ");
	}

	public void EditaArq(Arquivo arquivo, String file) throws Exception {
		ArquivoDAO auxDao = new ArquivoDAO();

		if (arquivo.getNomearquivo() == "") {
			throw new Exception("Erro nome do arquivo é necessario");

		} else
			auxDao.UpdateArquivo(arquivo, file);
	}

	@SuppressWarnings("unused")
	public void EditaArquivoSemFile(Arquivo arquivo) throws Exception {
		ArquivoDAO auxDao = new ArquivoDAO();

		if (arquivo.getNomearquivo() == "") {
			throw new Exception("O nome do arquivo é necessario, ok?");
		} else {

			auxDao.updateSemFile(arquivo);
		}
	}

	public void ExtrairArquivoEx(String nomeArquivo, String cod, JButton bot) throws Exception {
		ArquivoDAO auxExtr = new ArquivoDAO();
		if (cod != null) {
			auxExtr.SalvarArquivoExtr(nomeArquivo, cod, bot);
			JOptionPane.showMessageDialog(null, "Sucesso!, Salvamento pronto!");
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

//		public String getMostraData(JDateChooser jd)
//		{
//			if (jd.getDate() != null)
//				
//				return ds.format(jd.getDate());
//			
//			else
//			{
//			return null;
//			}
//		}

	public Date StringADate(String dataNum) {
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		Date FechaE = null;

		try {
			FechaE = dt.parse(dataNum);
			return FechaE;
		} catch (ParseException ex) {
			return null;
		}

	}

}
