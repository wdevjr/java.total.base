package beansgerenciados;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import fachada.CidadeFacade;
import modelo.Cidade;

@ManagedBean(name = "CidadeManagedBean")
@RequestScoped
public class CidadeManagedBean implements Serializable {

	private static final long serialVersionUID = -333995781063775201L;
	private Cidade cidade;
	private Object desc;
	private List<Cidade> lista;
	private CidadeFacade cidadeFacade = new CidadeFacade();
	boolean fildset = false;
	boolean listaFieldSet;
	boolean mostratudo;

	public CidadeManagedBean() {
	}

	public Cidade getCidade() {
		if (this.cidade == null) {
			// listaFieldSet=true;
			return this.cidade = new Cidade();

		} else

			return this.cidade;
	}

	public Object getDesc() {

		return desc;
	}

	public void setDesc(Object desc) {
		this.desc = desc;
	}

	public List<Cidade> findsql(Object desc) {

		List<Cidade> lista = this.cidadeFacade.listCidadesBy(desc);

		return lista;

	}

	public void findCidadeBy(ActionEvent e) {
		this.mostratudo = true;
		Integer id = (Integer) e.getComponent().getAttributes().get("codCidade");
		this.cidade = this.cidadeFacade.CidadeBy(id);

	}

	public String pesquisaCidades() {

		// this.listaFieldSet=true;
		if (desc.equals("")) {
			lista = this.cidadeFacade.findAll();
			// listaFieldSet=true;
			return null;

		} else {

			lista = findsql(desc);
			// listaFieldSet=true;
			return null;
		}

	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getLista() {
		listaFieldSet = true;

		if (this.desc == null) {
			if (FacesContext.getCurrentInstance().isPostback()) {
				// listaFieldSet=true;
				lista = this.cidadeFacade.findAll();

			}
		} else {
			// listaFieldSet=true;
			lista = findsql(desc);

		}

		return lista;

	}

	public boolean isListaFieldSet() {
		return listaFieldSet;
	}

	public void setListaFieldSet(boolean listaFieldSet) {
		this.listaFieldSet = listaFieldSet;
	}

	public void setLista(List<Cidade> lista) {
		this.lista = lista;
	}

	public void findCidade(ActionEvent e) {
		Integer id = (Integer) e.getComponent().getAttributes().get("codCidade");
		this.cidade = this.cidadeFacade.find(id);
		this.mostratudo = true;
	}

	public boolean isMostratudo() {
		return mostratudo;
	}

	public void setMostratudo(boolean mostratudo) {
		this.mostratudo = mostratudo;
	}

}
