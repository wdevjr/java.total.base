/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.combosaninhados.converter;

import br.com.combosaninhados.modelo.Cidade;
import br.com.combosaninhados.util.MeuDao;
import br.com.combosaninhados.util.MeuDaoImpl;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



/**
 *
 * @author altitdb
 */
@FacesConverter(value="converterCidade")
public class ConverterCidade implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            MeuDao dao = new MeuDaoImpl();
            return dao.getByIdCidade(Long.valueOf(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Cidade) {
            Cidade municipio = (Cidade) value;
            return String.valueOf(municipio.getCidade_id());
        }
        return "";
    }

}
