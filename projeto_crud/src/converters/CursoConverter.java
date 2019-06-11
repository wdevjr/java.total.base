package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import fachada.CursoFacade;
import modelo.Curso;

@FacesConverter("CursoConverter")
public class CursoConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return new CursoFacade().find(new Integer(value));
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Curso curso = (Curso) value;
        return curso.getId()+"";
    }
}

