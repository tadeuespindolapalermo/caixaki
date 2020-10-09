package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Estado;

/**
 * Responsavel por efetuar a conversão nos combos de campos de estado
 * @author alex
 *
 */
@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.isEmpty()) {
				return (Estado) HibernateUtil.getCurrentSession().get(
						Estado.class, new Long(value));
		}
		return value;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Estado c = (Estado) value;
			return c.getEst_id() != null && c.getEst_id() > 0 ? c.getEst_id().toString() : null;
		} else {
			return null;
		}
	}

}
