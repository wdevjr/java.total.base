package beansgerenciados;

import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public abstract class AbstractPessoa {
	
	
	protected void gerarRelatorio(String nomeRelatorio, HashMap paramRel)
			throws JRException, ClassNotFoundException, SQLException, FileNotFoundException {
		
		 FacesContext context = FacesContext.getCurrentInstance();
				 HttpServletResponse response = (HttpServletResponse)
				 context.getExternalContext().getResponse();
				 ServletContext sc = (ServletContext)
				 context.getExternalContext().getContext();
				 String relPath = sc.getRealPath("/");
				 // String imagemLogo = relPath + "resources/imagens/logo_mmo.jpg";
				 // paramRel.put("imagemLogo", imagemLogo);
				 // paramRel.put("nmSistema", Constants.NOME_SISTEMA);
				 paramRel.put("REPORT_LOCALE", new Locale("pt", "BR"));
				 try {
				 JasperPrint print = null;
				 Class.forName("com.mysql.jdbc.Driver");
				 String url = "jdbc:mysql://localhost:3306/crud";
				 String user = "root";
				 String pass = "root";
				 java.sql.Connection connection = DriverManager.getConnection(url, user,pass);
				 print = JasperFillManager.fillReport(relPath +
				 "relatorios/"+nomeRelatorio+".jasper", paramRel,connection);
				 response.setContentType("application/pdf");
				 response.addHeader("Content-disposition", "attachment; filename=\"" +
				 nomeRelatorio + ".pdf\"");
				 JasperExportManager.exportReportToPdfStream(print,response.getOutputStream());
				 ServletOutputStream responseStream = response.getOutputStream();
				 responseStream.flush();
				 responseStream.close();
				 FacesContext.getCurrentInstance().renderResponse();
				 FacesContext.getCurrentInstance().responseComplete();
				 } catch (Exception e) {
				 e.printStackTrace();
				 }
	
	
	}

}
