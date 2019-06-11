package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


import fachada.PessoaFacade;
import modelo.Pessoa;

public class PessoaConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		return new PessoaFacade().find(new Integer(value));
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		Pessoa pessoa = (Pessoa) object;
		
		return pessoa.getCidade().getId()+"";
	}

}
