package br.com.warhjr.mb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.warhjr.controller.ControllerArquivo;
import br.com.warhjr.dao.JdbcConnectionFactory;
import br.com.warhjr.model.Arquivo;

@ManagedBean(name = "arquivoManaged")
@ViewScoped
public class ArquivoMB implements Serializable {

	private final long serialVersionUID = 1L;

	private List<File> arquivos;

	private List<Arquivo> listararquivos;

	private UploadedFile uploadedFile;

	private Arquivo arquivo;

	private Arquivo descarquivo;

	private UsuarioMB usuariomb;

	private Part arquiv;

	private HttpServletResponse response;

	private String nomeArquivoSaida;

	private String descricao;

	private boolean fildSetInserir;

	private boolean fildSetEditar;

	private Date dInicial;
	private Date dFinal;
	private String nomeUsuario;

	ControllerArquivo auxControllerArquivos = new ControllerArquivo();

	public Arquivo getArquivo() {

		if (this.arquivo == null) {
			this.arquivo = new Arquivo();

		}
		return arquivo;
	}

	public Arquivo getDescarquivo() {

		if (this.descarquivo == null) {
			this.descarquivo = new Arquivo();

		}

		return descarquivo;
	}

	@SuppressWarnings("static-access")
	public void Upload(FileUploadEvent event) throws Exception {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UploadedFile uploadedFile = event.getFile();
		this.arquivo.setNomearquivos(uploadedFile.getFileName());
		String nomeArquivo = uploadedFile.getFileName();
		long filesize = uploadedFile.getSize();
		this.arquivo.setUsuarionome(usuariomb.nome);
		// this.arquivo.setDatacadastro();
		// this.arquivo.setDescricaoarquivos();

		if ((new File("C:/Dados")).exists() == false) {

			new File("C:/Dados").mkdir();

			nomeArquivoSaida = ("C:/Dados/" + uploadedFile.getFileName());
			InputStream is;

//			try {
			is = uploadedFile.getInputstream();
			OutputStream out = new FileOutputStream(nomeArquivoSaida);
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = is.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			// this.arquivo.setDados(bytes);
			Save();
			this.descarquivo = this.arquivo;

			/*
			 * } catch (IOException e) { FacesMessage facesMessageError = new
			 * FacesMessage(FacesMessage.SEVERITY_ERROR, " Erro ao Enviar Arquivo.",
			 * "Erro ao Enviar Arquivo"); facesContext.addMessage(null, facesMessageError);
			 * }
			 */
			String infoAboutFile = "<br /> Arquivo Recedido: <b> " + nomeArquivo + " </b> Tamanho do  Arquivo: <b>"
					+ filesize + "</b>";
			// FacesContext facescontext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage("Sucesso!", infoAboutFile));

		} else {

			nomeArquivoSaida = ("C:/Dados/" + uploadedFile.getFileName());
			InputStream is;

//			try {
			is = uploadedFile.getInputstream();
			OutputStream out = new FileOutputStream(nomeArquivoSaida);
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = is.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			// this.arquivo.setDados(bytes);
			Save();
			this.descarquivo = this.arquivo;

			/*
			 * } catch (IOException e) { FacesMessage facesMessageError = new
			 * FacesMessage(FacesMessage.SEVERITY_ERROR, " Erro ao Enviar Arquivo.",
			 * "Erro ao Enviar Arquivo"); facesContext.addMessage(null, facesMessageError);
			 * }
			 */
			String infoAboutFile = "<br /> Arquivo Recedido: <b> " + nomeArquivo + " </b> Tamanho do  Arquivo: <b>"
					+ filesize + "</b>";
			// FacesContext facescontext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage("Sucesso!", infoAboutFile));
		}
	}

	// <h:inputFile value="#{arquivoManaged.arquiv}" />
	// @SuppressWarnings("static-access")
	// public void Upload() throws Exception {
	// FacesContext facesContext = FacesContext.getCurrentInstance();
	// this.arquivo.setNomearquivos(arquiv.getSubmittedFileName());
	// this.arquivo.setUsuarionome(usuariomb.nome);
	//
	// //nomeArquivoSaida = ("C:/Dados/" + arquiv.getSubmittedFileName());
	// InputStream is;
	// try {
	// is = arquiv.getInputStream();
	// @SuppressWarnings("resource")
	// OutputStream out = new FileOutputStream(arquiv.getSubmittedFileName());
	// int read = 0;
	// byte[] bytes = new byte[1024];
	// while ((read = is.read(bytes)) != -1) {
	// out.write(bytes, 0, read);
	// }
	// this.arquivo.setDados(bytes);
	// Save();
	// FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
	// " Enviado com sucesso.",
	// "Enviado com sucesso.");
	// facesContext.addMessage(null, facesMessage);
	// } catch (IOException e) {
	// FacesMessage facesMessageError = new
	// FacesMessage(FacesMessage.SEVERITY_ERROR, " Erro ao Enviar Arquivo.",
	// "Erro ao Enviar Arquivo");
	// facesContext.addMessage(null, facesMessageError);
	// }
	//
	// }

	public void dec() {
		if (this.descarquivo != null) {
			fildSetInserir = true;
		}

		if ((this.arquivo != null)) {
			fildSetEditar = true;
		} else {
			fildSetEditar = false;
		}

	}

	public void Save() {
		ControllerArquivo auxController = new ControllerArquivo();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {

			auxController.Inserir(this.arquivo);

			// getlistarArquivos();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void EditInserir() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ControllerArquivo auxControllerEdit = new ControllerArquivo();
		try {

			auxControllerEdit.Editar(this.descarquivo);
			this.descarquivo.setDescricaoarquivos(null);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Enviado descrição com sucesso.",
					"");
			facesContext.addMessage(null, facesMessage);

		} catch (Exception e) {

			e.printStackTrace();

			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
					"Erro ao Enviar descrição!");
			facesContext.addMessage(null, facesMessage);
		}

	}

	public void EditEdit() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ControllerArquivo auxControllerEdit = new ControllerArquivo();
		try {

			auxControllerEdit.Editar(this.arquivo);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,null,
					"Editado com Sucesso!");
			facesContext.addMessage(null, facesMessage);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void RemoverArquivo() {
		auxControllerArquivos.Excluir(this.arquivo);
		File file = new File("C:/Dados/"+this.arquivo.getNomearquivos());
		file.delete();
		pesquisaDataouNome();
		this.arquivo = new Arquivo();
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public static java.io.File diretorioRaizParaArquivos() {
		File dir = new File(diretorioRaiz(), "");

		if (!dir.exists()) {
			dir.mkdirs();
		}

		return dir;
	}

	public static File diretorioRaiz() {
		// Estamos utilizando um diretório dentro da pasta temporária.
		// No seu projeto, imagino que queira mudar isso para algo como:
		// File dir = new File(System.getProperty("user.home"), "algaworks");
		System.setProperty("user.dir", "C:");
		File dir = new File("C:/Dados");

		if (!dir.exists()) {
			dir.mkdirs();
		}

		return dir;
	}

	public void downloadFile() throws IOException {

		/// String extensao =
		/// nomedoarquivo.substring(nomedoarquivo.lastIndexOf("."),
		/// nomedoarquivo.length());

		@SuppressWarnings("unused")
		FacesContext facesContext = null;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		// ServletContext sc = (ServletContext)
		// context.getExternalContext().getContext();

		String extensao = this.arquivo.getNomearquivos().substring(this.arquivo.getNomearquivos().lastIndexOf("."),
				this.arquivo.getNomearquivos().length());
		response.setContentType("application/octet-stream");

		String fullFileName = "C:/Dados/" + this.arquivo.getNomearquivos();

		File file = new File(fullFileName);
		response.setContentLength((int) (file.length()));
		response.setHeader("Content-Disposition", "attachment;filename=\"" + this.arquivo.getNomearquivos() + "\"");
		OutputStream output = response.getOutputStream();
		Files.copy(file.toPath(), output);

		response.getOutputStream().flush();
		response.getOutputStream().close();
		/*
		 * try {
		 * 
		 * FileOutputStream fos = null; InputStream in = new FileInputStream(file);
		 * OutputStream out = response.getOutputStream(); // byte[] buffer =
		 * this.arquivo.getDados(); byte[] buf = new byte[(int) file.length()]; int
		 * count;
		 * 
		 * while ((count = in.read(buf)) >= 0) { out.write(buf, 0, count); }
		 * 
		 * in.close(); out.flush(); out.close(); } catch (IOException ex) {
		 * System.out.println("Erro no download do arquivo: " + ex.getMessage());
		 * ex.printStackTrace(); }
		 */
	}

	// String original_filename =
	// file.getName().substring(file.getName().lastIndexOf("/") +
	// 1,file.getName().length());
	/*
	 * BufferedReader bufferedReader = null; try { bufferedReader = new
	 * BufferedReader(new FileReader(fullFileName)); } catch (FileNotFoundException
	 * fnfe) { fnfe.printStackTrace(); }
	 * 
	 * // response.setHeader("Content-Disposition", "attachment; filename=\"" +
	 * original_filename + "\""); try { int anInt = 0; while ((anInt =
	 * bufferedReader.read()) != -1) { out.write(anInt); } } catch (IOException ioe)
	 * { ioe.printStackTrace(); }
	 */

	// }
	/*
	 * try { FileOutputStream fos = null; InputStream in = new
	 * FileInputStream(file); OutputStream out = response.getOutputStream(); //
	 * byte[] buffer = this.arquivo.getDados(); byte[] buf = new byte[(int)
	 * file.length()]; int count; while ((count = in.read(buf)) >= 0) {
	 * out.write(buf, 0, count); }
	 * 
	 * in.close(); out.flush(); out.close(); } catch (IOException ex) {
	 * System.out.println("Erro no download do arquivo: " + ex.getMessage());
	 * ex.printStackTrace(); }
	 * 
	 * }
	 */

	/*
	 * public void download(){ try { File file = new File("C:/Dados/" +
	 * this.arquivo.getNomearquivos()); byte buffer[] = new byte[(int)
	 * file.length()]; BufferedInputStream input = new BufferedInputStream(new
	 * FileInputStream(file.getAbsolutePath())); input.read(buffer, 0,
	 * buffer.length); input.close(); //return (buffer); } catch (Exception e) {
	 * e.printStackTrace(); //return (null); } }
	 */

	public void pesquisaDataouNome() {

		listararquivos = auxControllerArquivos.listaAvancada(dInicial, dFinal, nomeUsuario);
		if (listararquivos.size() > 0) {
			// this.habilitarFormulario = true;
		}

	}

	// public List<Arquivo> getListarArquivos() {
	// return listararquivos;
	// }

	@SuppressWarnings({ "unused", "static-access" })
	public void insertFile() throws Exception {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		this.arquivo.setNomearquivos(arquiv.getName());
		this.arquivo.setUsuarionome(usuariomb.nome);
		InputStream is;
		is = arquiv.getInputStream();

		File f = new File(arquiv.getName());
		Connection c = JdbcConnectionFactory.getConnection();
		try {
			PreparedStatement ps = c.prepareStatement(
					"INSERT INTO publicararquivos(nomearquivos,descricaoarquivos,usuarionome,dados) VALUES(?,?,?,?)");
			// InputStream is = new FileInputStream(f);
			int len = (int) f.length();
			byte[] bytes = new byte[(int) f.length()];
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}

			ps.setString(1, this.arquivo.getNomearquivos());
			ps.setString(2, this.arquivo.getDescricaoarquivos());
			ps.setString(3, this.arquivo.getUsuarionome());
			ps.setBytes(4, bytes);
			ps.execute();
			ps.close();
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, " Enviado com sucesso.",
					"Enviado com sucesso.");
			facesContext.addMessage(null, facesMessage);
		} catch (IOException e) {
			FacesMessage facesMessageError = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Erro ao Enviar Arquivo.",
					"Erro ao Enviar Arquivo");
			facesContext.addMessage(null, facesMessageError);
		}
	}

	public void setListararquivos(List<Arquivo> listararquivos) {
		this.listararquivos = listararquivos;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	public List<File> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<File> arquivos) {
		this.arquivos = arquivos;
	}

	public Part getArquiv() {
		return arquiv;
	}

	public void setArquiv(Part arquiv) {
		this.arquiv = arquiv;
	}

	// public ArquivoMB() {
	// this.arquivo = new Arquivo();
	// this.descarquivo = new Arquivo();
	// this.listararquivos = new ArrayList<Arquivo>();
	// }

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDescarquivo(Arquivo descarquivo) {
		this.descarquivo = descarquivo;
	}

	public boolean isFildSetInserir() {
		return fildSetInserir;
	}

	public void setFildSetInserir(boolean fildSetInserir) {
		this.fildSetInserir = fildSetInserir;
	}

	public boolean isFildSetEditar() {
		return fildSetEditar;
	}

	public void setFildSetEditar(boolean fildSetEditar) {
		this.fildSetEditar = fildSetEditar;
	}

	public Date getdInicial() {
		return dInicial;
	}

	public void setdInicial(Date dInicial) {
		this.dInicial = dInicial;
	}

	public Date getdFinal() {
		return dFinal;
	}

	public void setdFinal(Date dFinal) {
		this.dFinal = dFinal;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public List<Arquivo> getListararquivos() {
		return listararquivos;
	}

}
