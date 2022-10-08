package br.com.warhjr.controller;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import org.codehaus.groovy.ast.stmt.ThrowStatement;

//import com.toedter.calendar.JDateChooser;

import br.com.warhjr.dao.ArquivoDAO;
import br.com.warhjr.ui.arquivo.*;
import br.com.warhjr.model.Arquivo;
import br.com.warhjr.ui.ProgressBar;

public class ArquivoController 
{



	SimpleDateFormat ds = new SimpleDateFormat("dd/MM/YYYY");
	
	//public static final String msgArquivo="Insira um Arquivo!";
	private String msgPessoa="Informe uma Pessoa-Busque na lupa!";


	public static final String getExtensao(String nomeArquivo) {
		int i = nomeArquivo.lastIndexOf('.');

		if (i > 0 && i < (nomeArquivo.length() - 1))
			return nomeArquivo.substring(i + 1).toLowerCase();
		return null;

	}

	public void SalvarArq(Arquivo arquivo, String file) throws Exception {
		
		ArquivoDAO auxDao = new ArquivoDAO();
		
		if (file.isEmpty()) {
		throw new Exception("Insira um Arquivo!");
		//JOptionPane.showMessageDialog(null, "Arquivo, vazio ! Insira um Arquivo!","Arquivos!",JOptionPane.WARNING_MESSAGE);
		//return;
		} else {
		if (arquivo.getNomearquivo().equals("")) {
		throw new Exception("Insira um Arquivo!");
		//JOptionPane.showMessageDialog(null, "Nome do Arquivo, Não Informado !","Arquivos!",JOptionPane.WARNING_MESSAGE);
		//return;
		} else {
		if ((arquivo.getIdPessoa()  <= 0)) {
	    throw new Exception("Insira uma Pessoa!");
	    //JOptionPane.showMessageDialog(null, msgPessoa,"Arquivos!",JOptionPane.WARNING_MESSAGE);	
	   // return;
		} else {
			try {
				auxDao.insertArquivo(arquivo, file);
				throw new Exception("Dados Inseridos com Sucesso!!");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 }
   }
}
	
			
			

		//JOptionPane.showMessageDialog(null, "Arquivo Inserido com Sucesso!","Arquivos!",JOptionPane.INFORMATION_MESSAGE);
		
	//	}
	

    
     
	
	
	public void EditaArq(Arquivo arquivo, String file) throws Exception {
		ArquivoDAO auxDao = new ArquivoDAO();

//		if (arquivo.getNomearquivo() == "") {
//			throw new Exception("Erro nome do arquivo é necessario");
//
//		} else
			auxDao.UpdateArquivo(arquivo, file);
		throw new Exception("Arquivo Editado com Sucesso! ");
	}

	public void EditaArquivoSemFile(Arquivo arquivo) throws Exception {
		ArquivoDAO auxDao = new ArquivoDAO();

//		if (arquivo.getNomearquivo() == "") {
//			throw new Exception("O nome do arquivo é necessario, ok?");
//		} else {

			auxDao.updateSemFile(arquivo);
			throw new Exception("Arquivo Editado com Sucesso! ");
		//}
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
