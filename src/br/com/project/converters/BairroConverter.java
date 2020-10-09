package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Bairro;

@FacesConverter(forClass = Bairro.class)
public class BairroConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.isEmpty()) {
				return (Bairro) HibernateUtil.getCurrentSession().get(
						Bairro.class, new Long(value));
		}
		return value;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Bairro c = (Bairro) value;
			return c.getBai_codigo() != null && c.getBai_codigo() > 0 ? c.getBai_codigo().toString() : null;
		} else {
			return null;
		}
	}
}
