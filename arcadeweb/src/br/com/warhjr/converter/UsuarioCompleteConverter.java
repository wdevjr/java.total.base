package br.com.warhjr.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.warhjr.dao.UsuarioHibernateDAO;
import br.com.warhjr.model.Usuario;

@FacesConverter(value = "UsuarioConverter")
public class UsuarioCompleteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		UsuarioHibernateDAO usaurioDAO = new UsuarioHibernateDAO();
		Usuario usuario = (Usuario) usaurioDAO.ListaUsuariosPorNome(value);

		return usuario;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Usuario usuario = new Usuario();
		usuario = (Usuario) value;
		return usuario.getNomeusuario();
	}

}
