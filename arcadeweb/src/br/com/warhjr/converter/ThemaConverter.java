package br.com.warhjr.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.warhjr.dao.MeuDao;
import br.com.warhjr.dao.MeuDaoImpl;
import br.com.warhjr.dao.ThemaHibernateDAO;
import br.com.warhjr.model.Thema;

@FacesConverter(value = "ThemaConverter")
public class ThemaConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (value != null && !value.equals("")) {
			MeuDao dao = new MeuDaoImpl();
			return dao.getByIdThema(Integer.valueOf(value));
		}

		return null;
	}

	private int Integer(String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value instanceof Thema) {
			Thema thema = (Thema) value;
			return String.valueOf(thema.getId());
		}

		return "";

	}

}
